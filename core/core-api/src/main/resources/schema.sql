create table book
(
    id     bigint generated by default as identity,
    author varchar(255) not null,
    status varchar(255) not null,
    title  varchar(255) not null,
    created_at timestamp(6),
    updated_at timestamp(6),
    primary key (id)
);

create table book_category
(
    id                 bigint generated by default as identity,
    book_category_type varchar(255) not null,
    book_id            bigint not null,
    created_at timestamp(6),
    updated_at timestamp(6),
    primary key (id)
);
create table category
(
    id   bigint generated by default as identity,
    type varchar(255) not null,
    created_at timestamp(6),
    updated_at timestamp(6),
    primary key (id)
);
alter table if exists book_category
    add constraint FKnyegcbpvce2mnmg26h0i856fd
    foreign key (book_id)
    references book;

-- --
-- create table book
-- (
--     `id`     BIGINT GENERATED BY DEFAULT AS IDENTITY ,
--     `author` varchar(255) not null,
--     `status` varchar(255) not null,
--     `title`  varchar(255) not null,
--     primary key (id)
-- );
--
-- create table book_category
-- (
--     id          BIGINT GENERATED BY DEFAULT AS IDENTITY ,
--     book_id     bigint not null,
--     category_id bigint not null,
--     primary key (id)
-- );
--
-- CREATE TABLE category
-- (
--     id     BIGINT GENERATED BY DEFAULT AS IDENTITY,
--     `type` VARCHAR(255) NOT NULL,
--     PRIMARY KEY (id)
-- );


-- alter table if exists book_category
--     add constraint FKnyegcbpvce2mnmg26h0i856fd
--     foreign key (book_id)
--     references book
--
-- alter table if exists book_category
--     add constraint FKam8llderp40mvbbwceqpu6l2s
--     foreign key (category_id)
--     references category