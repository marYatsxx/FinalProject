USE pharmacy;
INSERT INTO user_role VALUES(1, "administrator");
INSERT INTO user_role values(2, "doctor");
INSERT INTO user_role VALUES(3, "pharmacist");
INSERT INTO user_role values(4, "clientAccount");

INSERT INTO user VALUES("admin", "1532178", "Marina", "Yatsushkevich", 1);
INSERT INTO user VALUES("doc1", "1234", "Mark", "Lee", 2);
INSERT INTO user VALUES("doc2", "1234", "Jack", "Peterson", 2);
INSERT INTO user VALUES("pharm1", "qwerty", "Lisa", "Parker", 3);
INSERT INTO user VALUES("pharma2", "zxcv", "Sam", "Willson", 3);
INSERT INTO user VALUES("client1", "pass123", "Ekaterina", "Kutsko", 4);
INSERT INTO user VALUES("client2", "client2", "Fred", "Snake", 4);
SELECT * FROM user;