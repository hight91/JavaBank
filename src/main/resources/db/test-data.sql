DELETE FROM customer;
INSERT INTO customer(ID, CREATIONTIME, UPDATETIME, VERSION, NAME) VALUES
  (1, TIMESTAMP '2017-10-10 08:45:56.468', TIMESTAMP '2017-10-10 08:45:56.468', 0, 'Rui'),
  (2, TIMESTAMP '2017-10-10 08:45:56.481', TIMESTAMP '2017-10-10 08:45:56.481', 0, 'Sergio'),
  (3, TIMESTAMP '2017-10-10 08:45:56.482', TIMESTAMP '2017-10-10 08:45:56.482', 0, 'Bruno'),
  (4, TIMESTAMP '2017-10-10 08:45:56.482', TIMESTAMP '2017-10-10 08:45:56.482', 0, 'No Accounts');

DELETE FROM account;
/*
INSERT INTO account(ACCOUNT_TYPE, ID, CREATIONTIME, UPDATETIME, VERSION, BALANCE, CUSTOMER_ID) VALUES
  ('CheckingAccount', 1, TIMESTAMP '2017-10-10 10:18:53.819', TIMESTAMP '2017-10-10 10:22:58.578', 2, 100.0, 1),
  ('SavingsAccount', 2, TIMESTAMP '2017-10-10 10:23:02.194', TIMESTAMP '2017-10-10 10:23:19.801', 1, 50.5, 1),
  ('CheckingAccount', 3, TIMESTAMP '2017-10-10 14:30:37.769', TIMESTAMP '2017-10-10 14:30:43.042', 1, 10.0, 2),
  ('SavingsAccount', 4, TIMESTAMP '2017-10-10 14:30:38.426', TIMESTAMP '2017-10-10 14:30:46.471', 1, 150.0, 2),
  ('CheckingAccount', 5, TIMESTAMP '2017-10-10 14:30:37.769', TIMESTAMP '2017-10-10 14:30:43.042', 1, 20.5, 3),
  ('SavingsAccount', 6, TIMESTAMP '2017-10-10 14:30:38.426', TIMESTAMP '2017-10-10 14:30:46.471', 1, 30.5, 3),
  ('CheckingAccount', 7, TIMESTAMP '2017-10-10 14:30:37.769', TIMESTAMP '2017-10-10 14:30:43.042', 1, 20.5, NULL );
*/