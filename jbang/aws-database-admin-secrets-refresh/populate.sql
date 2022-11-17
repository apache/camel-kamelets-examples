do $$
BEGIN
for r in 1..10 loop
INSERT into accounts (username,city) VALUES ('John', 'New York');
END loop;
END;
$$;
