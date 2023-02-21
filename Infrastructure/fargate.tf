resource "aws_ecs_cluster" "fmdb_cluster" {
  name = "FMDB_Cluster"
}

resource "aws_ecs_cluster_capacity_providers" "fmdb_cluster" {
  cluster_name               = aws_ecs_cluster.fmdb_cluster.name
  capacity_providers = ["FARGATE"]

  default_capacity_provider_strategy {
    capacity_provider = "FARGATE"
    weight            = 100

  }
}
#difference between task and execution roles
resource "aws_ecs_task_definition" "fmdb_td" {
  family                   = "fmdb-${var.target_env}-task"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_execution_role.arn
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = var.fargate_cpu
  memory                   = var.fargate_memory
  # tags                     = local.common_tags
  container_definitions = jsonencode([
    {
      essential   = true
      name        = "fmdb-${var.target_env}-definition"
      #change to variable to env. for GH Actions
      image       = "public.ecr.aws/lts/apache2:2.4-20.04_beta"
      cpu         = var.fargate_cpu
      memory      = var.fargate_memory
      networkMode = "awsvpc"
      portMappings = [
        {
          protocol      = "tcp"
          containerPort = var.app_port
          hostPort      = var.app_port
        }
      ]
      secrets = [
        {"name": "PG_USER", 
         "valueFrom": "${aws_secretsmanager_secret_version.pg_user.arn}"},
        {"name": "PG_PASSWORD", 
         "valueFrom": "${aws_secretsmanager_secret_version.pg_password.arn}"},
        {"name": "JDBC_URL", 
         "valueFrom": "${aws_secretsmanager_secret_version.jdbc_url.arn}"}
      ]
    }
  ])
}

resource "aws_ecs_service" "main" {
  name                              = "fmdb-${var.target_env}-service"
  cluster                           = aws_ecs_cluster.fmdb_cluster.arn
  # Cant do count.index (research)
  task_definition                   = aws_ecs_task_definition.fmdb_td.arn
  desired_count                     = 2
  #Health Check need to go up?
  health_check_grace_period_seconds = 60
  wait_for_steady_state             = false

  network_configuration {
    security_groups  = [data.aws_security_group.app.id]
#NEED TO FIGURE OUT aws_subnets
    subnets          = data.aws_subnets.app.ids
    assign_public_ip = false
  }

  load_balancer {
    target_group_arn = aws_alb_target_group.app.id
    container_name   = "fmdb-${var.target_env}-definition"
    container_port   = var.app_port
  }

  depends_on = [data.aws_alb_listener.front_end, aws_iam_role_policy_attachment.ecs_task_execution_role]

#   tags = local.common_tags
}