resource "aws_iam_instance_profile" "ec2_fmdb_profile" {
  name = "ec2-fmdb-profile"
  role = aws_iam_role.fmdb_ec2_instance.name
}

data "aws_ami" "ec2" {
  most_recent = true

  filter {
    name   = "name"
    values = ["amzn2-ami-kernel-5.10-hvm*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }

  owners = ["137112412989"]
}

resource "aws_instance" "db_access_ec2" {
  ami           = data.aws_ami.ec2.id
  instance_type = "t2.micro"
  iam_instance_profile = aws_iam_instance_profile.ec2_fmdb_profile.name
  #subject to change
  security_groups = [data.aws_security_group.data.id]
  #look into making this dynamic
  subnet_id = data.aws_subnets.data.ids[0]
  user_data = <<EOF
  #!/bin/bash
  yum update
  yum install postgresql
  yum install -y https://s3.amazonaws.com/ec2-downloads-windows/SSMAgent/latest/linux_amd64/amazon-ssm-agent.rpm
  start amazon-ssm-agent
  systemctl enable amazon-ssm-agent
  EOF


}