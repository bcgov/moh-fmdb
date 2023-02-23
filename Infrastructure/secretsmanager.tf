resource "aws_secretsmanager_secret" "jdbc_setting" {
  name = "jdbc_setting"
}

resource "aws_secretsmanager_secret" "pg_user" {
  name = "pg_user"
}

resource "aws_secretsmanager_secret" "pg_password" {
  name = "pg_password"
}

resource "aws_secretsmanager_secret" "fmdb_keycloak-client-secret" {
  name = "fmdb_keycloak-client-secret"
}

resource "aws_secretsmanager_secret" "redirect_uri"{ 
  name = "redirect_uri"
}

resource "aws_secretsmanager_secret_version" "jdbc_setting" {
  secret_id     = aws_secretsmanager_secret.jdbc_setting.id
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

resource "aws_secretsmanager_secret_version" "fmdb_keycloak-client-secret" {
  secret_id     = aws_secretsmanager_secret.fmdb_keycloak-client-secret.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "redirect_uri" {
  secret_id     = aws_secretsmanager_secret.redirect_uri.id
  secret_string = "changeme"
}