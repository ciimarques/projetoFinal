#1º Estágio

 ## Chamando `build-image` do Maven para compilar o projeto
 FROM maven:3-openjdk-17 as build-image
 ## define o diretório de trabalho
 WORKDIR /to-build-app
 ## copia o arquivo pom.xml
 COPY . .
 ## executa o comando `mvn clean package`
 RUN mvnw dependency:go-offline
 RUN mvnw package -DskipTests
 #
 ## 2º Estágio
 ## Chamando a imagem do OpenJDK 17
 FROM eclipse-temurin:17-jre-alpine
 ## define o diretório de trabalho
 WORKDIR /app
 ## copia o arquivo .jar do 1º estágio
 COPY --from=build-image /to-build-app/target/*.jar /app/app.jar
 ## expõe a porta 8080
 EXPOSE 8080
 ## executa o comando `java -jar app.jar`
 ENTRYPOINT ["java", "-jar", "/app/app.jar"]