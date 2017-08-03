DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, description, datetime, calories) VALUES
  (100000, 'Конфеты', '2017-08-02 07:20', 550),
  (100000, 'Котлеты', '2017-08-02 13:40', 1020),
  (100000, 'Ужин', '2017-08-02 18:30', 510),
  (100000, 'Завтрак', '2017-08-01 06:20', 450),
  (100000, 'Обед', '2017-08-01 14:40', 820),
  (100000, 'Ужин  в ресторане', '2017-08-01 20:30', 530),
  (100001, 'Завтрак админа', '2017-08-01 08:30', 1000),
  (100001, 'Ужин админа', '2017-08-01 20:35', 1001);