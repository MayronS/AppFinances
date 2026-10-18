# ==================================================================
# App Finanças - Backend (Docker)
# ==================================================================

# Etapa de build
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /build

# Copia apenas o pom primeiro para cachear dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B -q

# Copia o restante e compila
COPY src ./src
RUN mvn package -DskipTests -B -q

# Etapa de runtime (imagem leve)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia o jar gerado
COPY --from=builder /build/target/*.jar app.jar

# Porta exposta
EXPOSE 8080

# Variáveis com valores defaults (podem ser sobrescritas no docker-compose)
ENV DB_URL=jdbc:postgresql://localhost:5432/appfinances
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgres

ENTRYPOINT ["java", "-jar", "app.jar"]
