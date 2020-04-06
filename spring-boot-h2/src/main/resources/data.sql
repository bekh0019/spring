INSERT INTO USER (email, user_Cash, password_hash, role) VALUES ('admin@admin.com', 1000000, '$2a$10$qjyTYpcAcTgfHItKgCof0uGwZ3Tcj/9/JYG48p8OH1O73PCJfRH1G', 'BOOKING_MANAGER');
INSERT INTO USER (email, user_Cash, password_hash, role) VALUES ('user1@user.com', 100, '$2a$10$qjyTYpcAcTgfHItKgCof0uGwZ3Tcj/9/JYG48p8OH1O73PCJfRH1G', 'REGISTERED_USER');
INSERT INTO USER (email, user_Cash, password_hash, role) VALUES ('user2@user.com', 100, '$2a$10$qjyTYpcAcTgfHItKgCof0uGwZ3Tcj/9/JYG48p8OH1O73PCJfRH1G', 'REGISTERED_USER');
INSERT INTO USER (email, user_Cash, password_hash, role) VALUES ('user3@user.com', 100, '$2a$10$qjyTYpcAcTgfHItKgCof0uGwZ3Tcj/9/JYG48p8OH1O73PCJfRH1G', 'REGISTERED_USER');
INSERT INTO USER (email, user_Cash, password_hash, role) VALUES ('user4@user.com', 100, '$2a$10$qjyTYpcAcTgfHItKgCof0uGwZ3Tcj/9/JYG48p8OH1O73PCJfRH1G', 'REGISTERED_USER');

INSERT INTO PHONE_COMPANY (name) VALUES
('Vodafone'),
('Kyivstar'),
('Life');

INSERT INTO PHONE (number,company_id) VALUES
('0555', 1),
('0666', 2),
('0777', 3),
('0556', 1),
('0557', 1);

INSERT INTO USER_ACCOUNT (company_id,user_id,phone_id) values
(1,1,2);