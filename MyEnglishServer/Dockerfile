# Java build Base Image
FROM eclipse-temurin

# 作業ディレクトリを設定
WORKDIR /app

# Buildするjarをコピー
COPY ./build/libs/MyEnglishServer-0.0.1-SNAPSHOT.jar app.jar

# portを設定
EXPOSE 8080

# jarの起動
ENTRYPOINT ["java", "-jar", "app.jar"]
