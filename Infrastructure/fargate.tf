# resource "aws_ecs_cluster_capacity_providers" "fmdb_cluster" {
#   cluster_name               = "FMDB_Cluster"
#   capacity_providers = ["FARGATE"]

#   default_capacity_provider_strategy {
#     capacity_provider = "FARGATE"
#     weight            = 100

#   }


# }

# resource "aws_ecs_task_definition" "fmdb_td" {
#   #Change to local
#   count                    = 1
#   family                   = "fmdb-${var.target_env}-task"
#   execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
#   task_role_arn            = aws_iam_role.fmdb_container_role.arn
#   network_mode             = "awsvpc"
#   requires_compatibilities = ["FARGATE"]
#   cpu                      = var.fargate_cpu
#   memory                   = var.fargate_memory
#   # tags                     = local.common_tags
#   container_definitions = jsonencode([
#     {
#       essential   = true
#       name        = "fmdb-${var.target_env}-definition"
#       #change to variable to env. for GH Actions
#       image       = "public.ecr.aws/lts/apache2:2.4-20.04_beta"
#       cpu         = var.fargate_cpu
#       memory      = var.fargate_memory
#       networkMode = "awsvpc"
#       portMappings = [
#         {
#           protocol      = "tcp"
#           containerPort = var.app_port
#           hostPort      = var.app_port
#         }
#       ]
#       environment = [
#         #place holder for any env. variables
#       ]
#     }
#   ])
# }

# resource "aws_ecs_service" "main" {
#   count                             = local.create_ecs_service
#   name                              = "fmdb-${var.target_env}-service"
#   cluster                           = aws_ecs_cluster_capacity_providers.fmdb_cluster.id
#   # Cant do count.index (research)
#   task_definition                   = aws_ecs_task_definition.fmdb_td[0].arn
#   desired_count                     = var.app_count
#   enable_ecs_managed_tags           = true
#   propagate_tags                    = "TASK_DEFINITION"
#   #Health Check need to go up?
#   health_check_grace_period_seconds = 60
#   wait_for_steady_state             = false

#   capacity_provider_strategy {
#     capacity_provider = "FARGATE"
#     weight            = 100
#   }

#   network_configuration {
#     security_groups  = [aws_security_group.ecs_tasks.id]
# #NEED TO FIGURE OUT aws_subnets
#     subnets          = data.aws_subnets.app.ids
#     assign_public_ip = false
#   }

# #   load_balancer {
# #     target_group_arn = aws_alb_target_group.app.id
# #     container_name   = var.container_name
# #     container_port   = var.app_port
# #   }

# #   #depends_on = [data.aws_alb_listener.front_end, aws_iam_role_policy_attachment.ecs_task_execution_role]

# #   tags = local.common_tags
# }