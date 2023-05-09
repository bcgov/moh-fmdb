CREATE SCHEMA IF NOT EXISTS fmdb AUTHORIZATION "fmdb_owner";

GRANT ALL ON SCHEMA fmdb TO "fmdb_owner";

GRANT USAGE ON SCHEMA fmdb TO fmdb_owner;
GRANT USAGE ON SCHEMA fmdb TO fmdb_proxy_user ;
GRANT SELECT ON ALL TABLES IN SCHEMA fmdb TO fmdb_proxy_user;
GRANT CONNECT ON DATABASE fmdb_db TO fmdb_proxy_user;