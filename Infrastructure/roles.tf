data "aws_caller_identity" "current" {}
# ECS task execution role data
data "aws_iam_policy_document" "ecs_task_execution_role" {
  version = "2012-10-17"
  statement {
    sid     = ""
    effect  = "Allow"
    actions = ["sts:AssumeRole"]

    principals {
      type        = "Service"
      identifiers = ["ecs-tasks.amazonaws.com"]
    }
  }
}
# ECS task execution role
resource "aws_iam_role" "ecs_task_execution_role" {
  name               = var.ecs_task_execution_role_name
  assume_role_policy = data.aws_iam_policy_document.ecs_task_execution_role.json

  #tags = local.common_tags
}

# ECS task execution role policy attachment
resource "aws_iam_role_policy_attachment" "ecs_task_execution_role" {
  role       = aws_iam_role.ecs_task_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}

resource "aws_iam_role" "fmdb_container_role" {
  name = "fmdb_app_container_role"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "ecs-tasks.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF

  #tags = local.common_tags
}
data "aws_iam_policy_document" "instance_assume_role_policy" {
  statement {
    actions = ["sts:AssumeRole"]

    principals {
      type        = "Service"
      identifiers = ["ec2.amazonaws.com"]
    }
  }
}

resource "aws_iam_role" "fmdb_ec2_instance" {
  name = "ec2instance"
  managed_policy_arns = [aws_iam_policy.ssmaccess.arn, aws_iam_policy.s3access.arn]
  assume_role_policy = data.aws_iam_policy_document.instance_assume_role_policy.json
}

resource "aws_iam_policy" "ssmaccess" {
  name = "ssmaccess-ec2"
  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Sid    = ""
        Principal = {
          Service = "ec2.amazonaws.com"
        }
        managed_policy_arns = "arn:aws:iam::aws:policy/AmazonSSMManagedInstanceCore"
    },]
  })
}
# look into taking out Resource wildcard
resource "aws_iam_policy" "s3access" {
  name = "s3access-ec2"
  policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
    {
      Effect = "Allow",
      Action = ["s3:ListBucket"],
      Resource = ["*"]
    },
    {
      Effect: "Allow",
      Action = [
        "s3:PutObject",
        "s3:GetObject",
        "s3:DeleteObject"
      ],
      "Resource": ["*"]
    }
  ]
  })
}
