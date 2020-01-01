CREATE TABLE users (
  id int PRIMARY KEY AUTO_INCREMENT,
  email varchar(255),
  user_name varchar(255),
  password varchar(255),
  role int
);

CREATE TABLE profile (
  id int PRIMARY KEY AUTO_INCREMENT,
  user_id int,
  user_name nvarchar,
  address nvarchar,
  numberphone varchar(255),
  cash_balance decimal
);

CREATE TABLE news (
  id int PRIMARY KEY AUTO_INCREMENT,
  title varchar(255),
  content nvarchar,
  img varchar(255),
  date_create_at timestamp,
  user_id int
);

CREATE TABLE products (
  id int PRIMARY KEY AUTO_INCREMENT,
  name nvarchar,
  description nvarchar,
  images varchar(255),
  create_at timestamp,
  update_at timestamp,
  category_id int,
  price decimal
);

CREATE TABLE category (
  id int PRIMARY KEY AUTO_INCREMENT,
  name nvarchar,
  description nvarchar,
  create_at timestamp,
  update_at timestamp
);

CREATE TABLE cart_item (
  id int PRIMARY KEY AUTO_INCREMENT,
  user_id int,
  product_id int,
  status int,
  quantity int
);

CREATE TABLE transaction (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  user_id int,
  create_at timestamp,
  update_at timestamp,
  total_price decimal,
  address varchar(255)
);

CREATE TABLE transaction_detail (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  transaction_id int,
  product_id int,
  quantity int,
  created_at timestamp,
  update_at timestamp
);

CREATE TABLE voucher (
  id int PRIMARY KEY AUTO_INCREMENT,
  discount float,
  created_at timestamp,
  end_at timestamp
);

CREATE TABLE voucher_user (
  voucher_id int,
  user_id int,
  quantity int,
  PRIMARY KEY (voucher_id, user_id)
);

CREATE TABLE store (
  id int PRIMARY KEY AUTO_INCREMENT,
  name nvarchar,
  address varchar(255)
);

CREATE TABLE store_have_product (
  store_id int,
  product_id int,
  quantity int,
  PRIMARY KEY (store_id, product_id)
);

ALTER TABLE profile ADD FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE news ADD FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE products ADD FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE cart_item ADD FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE cart_item ADD FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE transaction ADD FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE transaction_detail ADD FOREIGN KEY (transaction_id) REFERENCES transaction (id);

ALTER TABLE transaction_detail ADD FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE voucher_user ADD FOREIGN KEY (voucher_id) REFERENCES voucher (id);

ALTER TABLE voucher_user ADD FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE store_have_product ADD FOREIGN KEY (store_id) REFERENCES store (id);

ALTER TABLE store_have_product ADD FOREIGN KEY (product_id) REFERENCES products (id);
