# Java 23 のベースイメージ（Adoptium系）
FROM eclipse-temurin:23-jdk

# 作業ディレクトリ
WORKDIR /app

# JARファイルをコンテナにコピー（名前に注意！）
COPY build/libs/todo-0.0.1-SNAPSHOT.jar app.jar

# アプリケーションを実行
ENTRYPOINT ["java", "-jar", "app.jar"]

