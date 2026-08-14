#/usr/bin/env -S ${SHELL}

if [ ! -f ../.env ]; then
  echo "Error: .env file not found in the parent directory."
  exit 1
fi

export $(grep -v '^#' ../.env | xargs)

load_sql_script() {
    local script_path="$1"
    if [ ! -f "$script_path" ]; then
        echo "Error: SQL script '$script_path' not found."
        exit 1
    fi
    
    PGPASSWORD="$PG_PASSWORD" psql -v password="$PG_PASSWORD" -v db_name="$DB_NAME" -h localhost -U postgres -d "$PG_DB" -f "$script_path" >&2
}

files=("create_db.sql" "create_schema.sql" "fmdb_postgres.sql" "fmdb_constraints.sql")
for file in "${files[@]}"; do
    load_sql_script "$file"
done
