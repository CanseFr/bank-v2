# Étape 1 : Construction de l'application
FROM eclipse-temurin:17-jdk-focal as build

WORKDIR /build

# Copie des fichiers de configuration Maven et téléchargement des dépendances
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Copie du code source de l'application
COPY src ./src

# Construction de l'application et création du fichier JAR
RUN ./mvnw package -DskipTests

# Étape 2 : Création de l'image de production
FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Copie du fichier JAR depuis l'étape de construction
COPY --from=build /build/target/*.jar app.jar

# Commande pour exécuter l'application
CMD ["java", "-jar", "app.jar"]
