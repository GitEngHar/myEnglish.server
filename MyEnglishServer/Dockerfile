# Java build Base Image
# FROM eclipse-temurin ## amd image
FROM amazoncorretto:21

# 作業ディレクトリを設定
WORKDIR /app

# java application dir をコピー

# jar build

# Buildするjarをコピー
COPY ./build/libs/*.jar app.jar

# portを設定
EXPOSE 8080

# jarの起動
ENTRYPOINT ["java", "-jar", "app.jar"]
