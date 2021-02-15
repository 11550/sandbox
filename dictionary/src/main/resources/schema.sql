drop table if exists dictionary;

create table dictionary
(
    id int primary key auto_increment,
    name varchar not null,
    abbreviation varchar not null
);

