作成したテーブル情報をメモしておく
create table task(
    id int auto_increment primary key,
    task varchar(255) not null,
    year int not null,
    month int not null,
    date int not null
);


