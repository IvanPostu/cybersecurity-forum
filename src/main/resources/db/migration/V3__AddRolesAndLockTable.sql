INSERT INTO user_role (id, "name") VALUES
(0, 'USER'),
(1, 'ADMIN');

CREATE TRIGGER no_change_trigger
  BEFORE INSERT OR UPDATE OR DELETE ON "user_role"
  EXECUTE PROCEDURE do_not_change();