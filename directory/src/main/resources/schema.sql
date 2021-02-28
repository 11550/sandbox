drop table if exists directory;

create table directory
(
    id int primary key auto_increment,
    name varchar not null,
    abbreviation varchar not null
);

