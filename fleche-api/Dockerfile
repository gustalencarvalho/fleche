# Imagem base (com OpenJDK)
FROM openjdk:17-jdk-slim

# Diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar para dentro do container
COPY target/fleche-api.war /app/fleche-api.war

# Expõe a porta do aplicativo
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "/app/fleche-api.war"]
