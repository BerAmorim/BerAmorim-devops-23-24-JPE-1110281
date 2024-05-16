# First stage: Build the application
FROM gradle:jdk17 as builder
LABEL author="Bernardo Amorim"

WORKDIR /ca4-part1

# Copie apenas o necessário para o build
COPY ./gradle_basic_demo /ca4-part1

# Execute o build da aplicação
RUN ./gradlew clean build

# Second stage: Create the final image
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho no contêiner final

# Copie o arquivo JAR gerado na primeira etapa
COPY --from=builder /ca4-part1/build/libs/*.jar ca4-part1.jar

# Exponha a porta usada pelo servidor de chat
EXPOSE 59001

# Especifica o comando para rodar a aplicação
ENTRYPOINT ["java", "-cp", "ca4-part1.jar", "basic_demo.ChatServerApp", "59001"]

