DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM users;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password, role)
VALUES ('Admin0', 'admin0@yandex.ru', '{noop}password0', 'ADMIN'),
       ('Admin1', 'admin1@yandex.ru', '{noop}password1', 'ADMIN'),
       ('User0', 'user0@yandex.ru', '{noop}password0', 'USER'),
       ('User1', 'user1@gmail.com', '{noop}password1', 'USER'),
       ('User2', 'user2@gmail.com', '{noop}password2', 'USER'),
       ('User3', 'user3@gmail.com', '{noop}password3', 'USER'),
       ('User4', 'user4@gmail.com', '{noop}password4', 'USER');

INSERT INTO user_roles (role, user_id)
VALUES ('ADMIN', 100000),
       ('ADMIN', 100001),
       ('USER', 100002),
       ('USER', 100003),
       ('USER', 100004),
       ('USER', 100005),
       ('USER', 100006);

INSERT INTO restaurants (name)
VALUES ('Груша'),
       ('Кампания');

INSERT INTO dishes (restaurant_id, user_id, name, price)
VALUES (100007, 100000, 'пирог', 150.56),
       (100007, 100000, 'десерт', 200.13),
       (100007, 100000, 'чай', 100.05),
       (100008, 100001, 'суп', 150.34),
       (100008, 100001, 'пельмени', 200.54),
       (100008, 100001, 'сок', 100.57);


INSERT INTO votes (restaurant_id, user_id, date, time)
VALUES (100007, 100002, '2020-08-25', '10:00:00'),
       (100007, 100003, '2020-08-25', '13:00:00'),
       (100007, 100004, '2020-08-25', '10:00:00'),
       (100008, 100005, '2020-08-25', '10:00:00'),
       (100008, 100006, '2020-08-25', '10:00:00');
