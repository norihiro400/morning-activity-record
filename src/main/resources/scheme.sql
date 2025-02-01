作成したテーブル情報をメモしておく
create table task(
    id int auto_increment primary key,
    task varchar(255) not null,
    isDone boolean
);

create table user(
    id int auto_increment primary key,
    user_name varchar(255),
    password varchar(255),
    email varchar(255)

);


