-- usersテーブル
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255)
);

-- taskテーブル
CREATE TABLE IF NOT EXISTS task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task VARCHAR(255) NOT NULL,
    label VARCHAR(20) NOT NULL,
    is_done BOOLEAN NOT NULL DEFAULT FALSE,
    scheduled_date DATE NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- task_detailテーブル
CREATE TABLE IF NOT EXISTS task_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task_id BIGINT NOT NULL,
    image_path VARCHAR(255),
    detail TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES task(id)
);


