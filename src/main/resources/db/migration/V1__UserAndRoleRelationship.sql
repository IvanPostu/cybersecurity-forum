CREATE SEQUENCE hibernate_sequence start 1 increment 1;

CREATE TABLE app_user (
  "id" BIGSERIAL NOT NULL, 
  "activation_code" VARCHAR(32), 
  "active" BOOLEAN, 
  "email" VARCHAR(64) UNIQUE NOT NULL, 
  "password" VARCHAR(64), 
  PRIMARY KEY (id)
);

CREATE TABLE user_role (
  "id" INT2 NOT NULL, 
  "name" VARCHAR(32), 
  PRIMARY KEY (id)
);

CREATE TABLE app_user_user_role (
  user_id INT8 NOT NULL, 
  role_id INT2 NOT NULL, 
  PRIMARY KEY (user_id, role_id)
);

ALTER TABLE IF EXISTS app_user_user_role 
ADD CONSTRAINT user_role_fk
FOREIGN KEY (user_id) REFERENCES app_user;

ALTER TABLE IF EXISTS app_user_user_role
ADD CONSTRAINT role_user_fk
FOREIGN KEY (role_id) REFERENCES user_role;
