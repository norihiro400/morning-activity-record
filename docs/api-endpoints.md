## 朝活支援サービス　エンドポイント一覧

# タスク(朝活)関連

---

## メイン画面  
- **URL**: `/tasks`  
- **Method**: `GET`  
- **View**: `tasks/tasks.html`  
- **説明**: 朝活の予定や予定された朝活の表示画面  

---

## 朝活一覧表示  
- **URL**: `/tasks/record`  
- **Method**: `GET`  
- **View**: `tasks/record.html`  
- **説明**: 完了した朝活をリストにして一覧表示  

---

## 朝活登録（翌日の予定入力）  
- **URL**: `/tasks/input`  
- **Method**: `POST`  
- **処理**: 翌日の朝活を登録してメイン画面にリダイレクト  

---

## 朝活入力画面（当日分の入力）  
- **URL**: `/tasks/input`  
- **Method**: `GET`  
- **View**: `tasks/input.html`  
- **説明**: 本日の朝活があれば表示し、フォームを表示する画面  
- **備考**: 当日の朝活がなければ「進行中の朝活はありません」と表示  

---

## 朝活詳細入力  
- **URL**: `/tasks/detail/{taskId}`  
- **Method**: `POST`  
- **処理**: 特定の朝活タスクに対して詳細情報（感想・画像）を登録し、完了状態に更新  
- **リダイレクト**: `/tasks/input`  
- **備考**: 画像未指定時はデフォルト画像 `/images/default.png` を使用  

---

## タスク削除  
- **URL**: `/tasks/delete/{id}`  
- **Method**: `POST`  
- **処理**: 指定されたIDのタスクを削除し、`/tasks` にリダイレクト  

---

## タスク詳細表示  
- **URL**: `/tasks/{id}`  
- **Method**: `GET`  
- **View**: `tasks/detail.html`  
- **説明**: タスクとその詳細（Task + TaskDetail）を表示  

---

## 公開済み朝活一覧（みんなの朝活）  
- **URL**: `/tasks/public`  
- **Method**: `GET`  
- **View**: `tasks/public-tasks.html`  
- **説明**: 他ユーザーが公開設定にした朝活の一覧を表示  

---
---

# プロフィール関連


## プロフィール画面表示  
- **URL**: `/profile`  
- **Method**: `GET`  
- **View**: `profile/profile.html`  
- **説明**: ログインユーザーのプロフィール画面を表示  
- **処理内容**:  
  - ログイン中のユーザー名を取得  
  - 該当ユーザーの朝活タスクをすべて取得し、その件数（taskCount）を表示  
  - `username` と `taskCount` をテンプレートに渡す  
