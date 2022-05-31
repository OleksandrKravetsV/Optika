create sequence hibernate_sequence start 3 increment 1;
create table user_role (
                           user_id int8 not null,
                           roles varchar(255)
);

create table usr (
                     id int8 not null,
                     username varchar(255) not null,
                     password varchar(255) not null,
                     primary key (id)
);

