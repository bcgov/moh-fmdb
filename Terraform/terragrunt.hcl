terraform {
  source = "../..//infrastructure"
}
 locals {
#   tfc_hostname        = "app.terraform.io"
    #project             = get_env("LICENSE_PLATE")
    project             = "ynr9ed"
    environment         = reverse(split("/", get_terragrunt_dir()))[0]
#   app_image           = get_env("app_image", "")
 }

#Will need to update below once we have license_plate information
generate "remote_state" {
  path      = "backend.tf"
  if_exists = "overwrite"
  contents  = <<EOF
terraform {
  backend "s3" {
    bucket         = "terraform-remote-state-${ local.project }-${ local.environment }"
    key            = "${ local.project }/${ local.environment }/fmdb-app.tfstate"
    region         = "ca-central-1"
    encrypt        = true
    
  }
}
EOF
}
#dynamodb_table = "terraform-remote-state-lock-${ local.project }"

generate "tfvars" {
  path              = "terragrunt.auto.tfvars"
  if_exists         = "overwrite"
  disable_signature = true
  contents          = <<-EOF
    target_env = "${local.environment}"
    app_count = 2
EOF
}

generate "provider" {
  path      = "provider.tf"
  if_exists = "overwrite"
  contents  = <<EOF
provider "aws" {
  region  = "ca-central-1"
#   assume_role {
#     role_arn = "arn:aws:iam::$${var.target_aws_account_id}:role/BCGOV_$${var.target_env}_Automation_Admin_Role"
#   }
}
EOF
}