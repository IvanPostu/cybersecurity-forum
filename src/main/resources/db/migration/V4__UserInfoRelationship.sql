CREATE TABLE user_info (
  "id" BIGSERIAL NOT NULL,
  "first_name" VARCHAR(100),
  "last_name" VARCHAR(100),
  PRIMARY KEY(id) 
);

ALTER TABLE app_user 
ADD COLUMN IF NOT EXISTS 
user_info_id BIGINT NOT NULL;

ALTER TABLE IF EXISTS app_user 
ADD CONSTRAINT user_info_fk
FOREIGN KEY (user_info_id) REFERENCES user_info;
