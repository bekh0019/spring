INSERT INTO user (email, password_hash, role)
VALUES ('admin@admin.com', '$2a$10$qjyTYpcAcTgfHItKgCof0uGwZ3Tcj/9/JYG48p8OH1O73PCJfRH1G', 'BOOKING_MANAGER');

INSERT INTO PHONE_COMPANY (name) VALUES
('Vodafone'),
('Kyivstar'),
('Life');

INSERT INTO PHONE (number,company_id, user_id) VALUES
('0555', 1,1),
('0666', 2,1),
('0777', 3,1),
('0556', 1,1),
('0557', 1,1);
