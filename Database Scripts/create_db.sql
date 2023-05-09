-- Database: FMDB_DB

CREATE ROLE fmdb_owner LOGIN PASSWORD 'admin';
CREATE ROLE fmdb_proxy_user LOGIN PASSWORD 'admin';
GRANT fmdb_owner TO postgres;


-- DROP DATABASE IF EXISTS "FMDB_DB";

CREATE DATABASE fmdb_db
    WITH 
    OWNER = fmdb_owner
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    CONNECTION LIMIT = -1;
	


--GRANT TEMPORARY, CONNECT ON DATABASE fmdb_db TO PUBLIC;

GRANT ALL ON DATABASE fmdb_db TO fmdb_owner;
ALTER ROLE fmdb_proxy_user SET search_path TO fmdb;




