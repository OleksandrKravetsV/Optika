insert into usr (id, username, password)
values (1, 'admin', 'password');

insert into usr (id, username, password)
values (2, 'user', 'password');


insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');


insert into user_role (user_id, roles)
values (2, 'USER');
