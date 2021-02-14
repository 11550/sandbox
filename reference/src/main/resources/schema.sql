drop table if exists reference;

create table reference
(
    id int primary key auto_increment,
    name varchar not null,
    abbreviation varchar not null
);

