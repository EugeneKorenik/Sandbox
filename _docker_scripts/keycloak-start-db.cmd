docker container run ^
    -e KEYCLOAK_USER=keycloak ^
    -e KEYCLOAK_PASSWORD=keycloak ^
    -p 8089:8080 ^
    --name telegram_keycloak ^
    -d jboss/keycloak