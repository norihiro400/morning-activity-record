# Java 23 のベースイメージ（Adoptium系）
FROM eclipse-temurin:23-jdk

# 作業ディレクトリ
WORKDIR /app

# ビルド関連ファイルを先にコピーして依存取得
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# 依存だけ先にダウンロードしてキャッシュを効かせる
RUN ./gradlew build --no-daemon || return 0

# アプリケーションのソースコードを追加
COPY . /app

# DevTools を有効にして Spring Boot 起動
CMD ["./gradlew", "bootRun", "--no-daemon"]

