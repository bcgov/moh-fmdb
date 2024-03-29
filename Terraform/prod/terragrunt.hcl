include {
  path = find_in_parent_folders()
}

locals {
  #project = get_env("LICENSE_PLATE")
#   commontags = [
#     environment = "prod",
#     application = "fmdb"
#   ]
}

generate "prod_tfvars" {
  path              = "prod.auto.tfvars"
  if_exists         = "overwrite"
  disable_signature = true
  contents          = <<-EOF
  fargate_cpu = 512
  fargate_memory = 1024
  app_port = 8181
  fam_console_idp_name = "DEV-IDIR"
  domain = "fmdb.hlth.gov.bc.ca"
  alb_origin_id = "fmdb.ynr9ed-prod.nimbus.cloud.gov.bc.ca"
  EOF
}
