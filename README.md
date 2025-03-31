朝活の記録を残しておけるwebアプリ
開発途中
みんなの朝活を見れたり悩みなどの相談ができるような機能も追加していきたい

# 朝活記録アプリ

自身の朝活を予定、記録することのできるサービス

## 動作確認
以下のコマンドを実行

`docker compose build && docker compose up `



## 技術スタック
* フロントエンド：HTML,CSS,Thymeleaf
* バックエンド：Java,SpringBoot
* データベース：Mysql


## 動作
* ログイン画面
![ログイン画面](photo/スクリーンショット%202025-03-08%20143211.png)
### メイン画面
* 朝活予定の入力
![メイン画面](photo/スクリーンショット%202025-03-08%20143312.png)
朝活のジャンル(勉強系、運動系、日常系)を選択
* 予定を決定する
![入力](photo/スクリーンショット%202025-03-08%20143400.png)
### 朝活記録の入力
* 予定された朝活がない場合
![予定された朝活がない場合](photo/スクリーンショット%202025-03-08%20144805.png)
* 予定された朝活がある場合
![予定された朝活がある場合](photo/スクリーンショット%202025-03-08%20144039.png)
* 記録の入力
![予定された朝活がある場合](photo/スクリーンショット%202025-03-08%20144239.png)

### 朝活記録の表示
* 今までの朝活記録を見れます
![記録](photo/スクリーンショット%202025-03-08%20144304.png)
* 詳細の表示
![詳細](photo/スクリーンショット%202025-03-08%20144317.png)
## 今後の課題
* 他のユーザの朝活記録の閲覧やいいね機能の追加
* 自身の朝活のリマインド機能追加　など