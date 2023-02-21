resource "aws_secretsmanager_secret" "jdbc_connection" {
  name = "jdbc_connection"
}

resource "aws_secretsmanager_secret" "pg_user" {
  name = "pg_user"
}

resource "aws_secretsmanager_secret" "pg_password" {
  name = "pg_password"
}

resource "aws_secretsmanager_secret_version" "jdbc_connection" {
  secret_id     = aws_secretsmanager_secret.jdbc_connection.id
  secret_string = "changeme"
}
resource "aws_secretsmanager_secret_version" "pg_user" {
  secret_id     = aws_secretsmanager_secret.pg_user.id
  secret_string = "changeme"
}
resource "aws_secretsmanager_secret_version" "pg_password" {
  secret_id     = aws_secretsmanager_secret.pg_password.id
  secret_string = "changeme"
}