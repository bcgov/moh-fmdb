# alb.tf

provider "aws" {
  alias  = "ca-central-1"
  region = "ca-central-1"
}
data "aws_acm_certificate" "certificate" {
  provider    = aws.ca-central-1
  domain      = var.domain
  statuses    = ["ISSUED"]
  most_recent = true
}

resource "aws_lb" "front_end" {
  name               = "default"
  internal           = true
  load_balancer_type = "application"
  security_groups    = [data.aws_security_group.web.id]
  subnets            = [for subnet in data.aws_subnet.web : subnet.id]

  enable_deletion_protection = true
  tags = merge(local.common_tags, { public = "true" })

}

resource "aws_alb_listener" "front_end" {
  load_balancer_arn = aws_lb.front_end.arn
  port              = "443"
  protocol          = "HTTPS"
  ssl_policy        = "ELBSecurityPolicy-TLS13-1-2-Res-PQ-2025-09"
  certificate_arn   = data.aws_acm_certificate.certificate.arn

  default_action {
    type = "fixed-response"

    fixed_response {
      content_type = "text/plain"
      message_body = "Fixed response content"
      status_code  = "200"
    }
  }
}

resource "aws_alb_listener" "http" {
  load_balancer_arn = aws_lb.front_end.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_alb_target_group.app.arn
  }
}

resource "aws_alb_target_group" "app" {
  name                 = "fmdb-test-target-group"
  port                 = var.app_port
  protocol             = "HTTPS"
  vpc_id               = data.aws_vpc.main.id
  target_type          = "ip"
  deregistration_delay = 30
  lifecycle {
    create_before_destroy = true
    ignore_changes = [name]
  }
  stickiness {
    type = "lb_cookie"
    
  }

  health_check {
    healthy_threshold   = "2"
    interval            = "150"
    protocol            = "HTTPS"
    matcher             = "200"
    timeout             = "120"
    path                = var.health_check_path
    unhealthy_threshold = "10"
  }

    tags = local.common_tags
}

resource "aws_lb_listener_rule" "host_based_weighted_routing" {
  listener_arn = aws_alb_listener.front_end.arn
  lifecycle {
    create_before_destroy = true
  }
  action {
    type             = "forward"
    target_group_arn = aws_alb_target_group.app.arn
  }
  #figure out what to place here to replace the environment
  condition {
    host_header {
      values = ["${var.application}.${var.license_plate}-${var.target_env}.stratus.cloud.gov.bc.ca"]
    }
  }
}
