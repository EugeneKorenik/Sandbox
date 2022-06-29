docker container run ^
    -e POSTGRES_USER=postgres ^
    -e POSTGRES_PASSWORD=postgres ^
    -e POSTGRES_DB=messaging-service ^
    -p 5432:5432 ^
    --name telegram_db ^
    -d postgres