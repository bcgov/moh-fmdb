resource "random_pet" "fmdb_subnet_group_name" {
  prefix = "fmdb-subnet-group"
  length = 2
}


resource "random_password" "fmdb_master_password" {
  length           = 16
  special          = true
  override_special = "!#$%&*()-_=+[]{}<>:?"
}

variable "fmdb_master_username" {
  description = "The username for the DB master user"
  type        = string
  default     = "postgres"
  # sensitive   = true
}

variable "fmdb_database_name" {
  description = "The name of the database"
  type        = string
  default     = "fmdb"
}

resource "aws_db_subnet_group" "fmdb_subnet_group" {
  description = "For Aurora cluster ${var.fmdb_cluster_name}"
  name        = "${var.fmdb_cluster_name}-subnet-group"
  subnet_ids  = data.aws_subnets.app.ids
  tags = {
    managed-by = "terraform"
  }

  tags_all = {
    managed-by = "terraform"
  }
}

data "aws_rds_engine_version" "postgresql" {
  engine  = "aurora-postgresql"
  version = "13.9"
    filter {
    name = "engine-mode"
    values = [ "serverless" ]
  }
}

module "aurora_postgresql_v2" {
  source = "terraform-aws-modules/rds-aurora/aws"
  version = "7.7.1"


  name              = "${var.fmdb_cluster_name}-${var.target_env}"
  engine            = data.aws_rds_engine_version.postgresql.engine
  engine_mode       = "provisioned"
  engine_version    = data.aws_rds_engine_version.postgresql.version
  storage_encrypted = true
  database_name     = var.fmdb_database_name

  vpc_id                 = data.aws_vpc.main.id
  vpc_security_group_ids = [data.aws_security_group.data.id]
  db_subnet_group_name   = aws_db_subnet_group.fmdb_subnet_group.name

  master_username = var.fmdb_master_username
  master_password = random_password.fmdb_master_password.result

  # create_cluster         = true
  create_security_group  = false
  create_db_subnet_group = false
  create_monitoring_role = false
  # create_random_password = false

  apply_immediately   = true
  skip_final_snapshot = true

  db_parameter_group_name         = aws_db_parameter_group.fmdb_postgresql13.id
  db_cluster_parameter_group_name = aws_rds_cluster_parameter_group.fmdb_postgresql13.id

  serverlessv2_scaling_configuration = {
    min_capacity = 0.5
    max_capacity = 1
  }

  instance_class = "db.serverless"
  instances = {
    one = {}
    two = {}
  }

  tags = {
    managed-by = "terraform"
  }

  enabled_cloudwatch_logs_exports = ["postgresql"]
}

resource "aws_db_parameter_group" "fmdb_postgresql13" {
  name        = "${var.fmdb_cluster_name}-parameter-group"
  family      = "aurora-postgresql13"
  description = "${var.fmdb_cluster_name}-parameter-group"
  tags = {
    managed-by = "terraform"
  }
}

resource "aws_rds_cluster_parameter_group" "fmdb_postgresql13" {
  name        = "${var.fmdb_cluster_name}-cluster-parameter-group"
  family      = "aurora-postgresql13"
  description = "${var.fmdb_cluster_name}-cluster-parameter-group"
  tags = {
    managed-by = "terraform"
  }
}

resource "random_pet" "master_creds_secret_name" {
  prefix = "fmdb-master-creds"
  length = 2
}

resource "aws_secretsmanager_secret" "fmdb_mastercreds_secret" {
  name = random_pet.master_creds_secret_name.id

  tags = {
    managed-by = "terraform"
  }
}

resource "aws_secretsmanager_secret_version" "fmdb_mastercreds_secret_version" {
  secret_id     = aws_secretsmanager_secret.fmdb_mastercreds_secret.id
  secret_string = <<EOF
   {
    "username": "${var.fmdb_master_username}",
    "password": "${random_password.fmdb_master_password.result}"
   }
  EOF
  lifecycle {
  ignore_changes = [ secret_string  ]
  }
}

resource "random_password" "fmdb_api_password" {
  length           = 16
  special          = true
  override_special = "!#$%&*()-_=+[]{}<>:?"
}

variable "fmdb_api_username" {
  description = "The username for the DB api user"
  type        = string
  default     = "fam_proxy_api"
  sensitive   = true
}


resource "random_pet" "api_creds_secret_name" {
  prefix = "fmdb-api-creds"
  length = 2
}

resource "aws_secretsmanager_secret" "fmdb_apicreds_secret" {
  name = random_pet.api_creds_secret_name.id

  tags = {
    managed-by = "terraform"
  }
}

resource "aws_secretsmanager_secret_version" "fmdb_apicreds_secret_version" {
  secret_id     = aws_secretsmanager_secret.fmdb_apicreds_secret.id
  secret_string = <<EOF
   {
    "username": "${var.fmdb_api_username}",
    "password": "${random_password.fmdb_api_password.result}"
   }
  EOF
  lifecycle {
  ignore_changes = [ secret_string  ]
  }
}

output "module_master_username" {
  value = var.fmdb_master_username
}