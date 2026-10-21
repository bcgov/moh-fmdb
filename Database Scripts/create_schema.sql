CREATE SCHEMA IF NOT EXISTS :db_name AUTHORIZATION "fmdb_owner";

GRANT ALL ON SCHEMA :db_name TO "fmdb_owner";

GRANT USAGE ON SCHEMA :db_name TO fmdb_owner;
GRANT USAGE ON SCHEMA :db_name TO fmdb_proxy_user ;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA :db_name TO fmdb_proxy_user;
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA :db_name TO fmdb_proxy_user;
GRANT CONNECT ON DATABASE :db_name TO fmdb_proxy_user;
