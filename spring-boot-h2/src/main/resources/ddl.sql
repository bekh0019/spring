INSERT INTO USER (first_name, last_name) VALUES
('Aliko', 'Dangote'),
('Bill', 'Gates'),
('Folrunsho', 'Alakija');

INSERT INTO PHONE_COMPANY (name) VALUES
('Vodafone'),
('Kyivstar'),
('Life');

INSERT INTO PHONE (number,company_id, user_id) VALUES
('0555', 1,2),
('0666', 2,2),
('0777', 3,1),
('0556', 1,1),
('0557', 1,3);



SELECT first_name, last_name, number
FROM user
INNER JOIN phone ON user.id=phone.user_id;