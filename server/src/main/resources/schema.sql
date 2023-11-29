drop table if exists COMMENT;
drop table if exists TO_DO;
drop table if exists POST;

create table POST
(
    user_id number,
    id      number primary key not null,
    title   varchar2,
    body    varchar2
);

create table COMMENT
(
    post_id number,
    id      number primary key not null,
    name    varchar2,
    email   varchar2,
    body    varchar2( MAX)
);

create table TO_DO
(
    user_id   number,
    id        number primary key not null,
    title     varchar2,
    completed boolean
);