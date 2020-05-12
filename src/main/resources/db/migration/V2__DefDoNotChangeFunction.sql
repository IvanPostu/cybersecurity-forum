
CREATE FUNCTION do_not_change()
  RETURNS TRIGGER
AS
$$
BEGIN
  RAISE EXCEPTION 'Cannot modify table procedure.';
END;
$$
LANGUAGE plpgsql;
