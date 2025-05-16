FROM payara/server-web:6.2024.1-jdk17
COPY target/jakartaee-hello-world.war $DEPLOY_DIR
EXPOSE 8080
