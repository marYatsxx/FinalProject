USE pharmacy;
#roles
INSERT INTO user_role VALUES(1, "administrator");
INSERT INTO user_role values(2, "doctor");
INSERT INTO user_role VALUES(3, "pharmacist");
INSERT INTO user_role values(4, "client");

#users
INSERT INTO user VALUES(default, "admin", "1532178", "Marina", "Yatsushkevich", 1);
INSERT INTO user VALUES(default, "doc1", "1234", "Mark", "Lee", 2);
INSERT INTO user VALUES(default, "doc2", "1234", "Jack", "Peterson", 2);
INSERT INTO user VALUES(default, "pharm1", "qwerty", "Lisa", "Parker", 3);
INSERT INTO user VALUES(default, "pharm2", "zxcv", "Sam", "Willson", 3);
INSERT INTO user VALUES(default, "client1", "pass123", "Ekaterina", "Kutsko", 4);
INSERT INTO user VALUES(default, "client2", "client2", "Fred", "Snake", 4);
INSERT INTO user values(default, "client3", "10003", "Natalya", "Samuylova", 4);
INSERT INTO user values(default, "client4", "10004", "Ivan", "Ivanovich", 4);

#medicine
INSERT INTO medicine VALUES(default, "Amoxicillin", "500mg", 6.00, true);
INSERT INTO medicine VALUES(default, "Calcium", "500mg", 3.20, false);
INSERT INTO medicine VALUES(default, "Flukonazol", "150mg", 4.50, false);
INSERT INTO medicine VALUES(default, "Flukonazol", "50mg", 7.25, false);
INSERT INTO medicine VALUES(default, "Amoxiclav", "250mg", 10.00, false);
INSERT INTO medicine VALUES(default, "Amoxiclav", "875mg", 15.30, false);
INSERT INTO medicine VALUES(default, "Mildronat", "250mg", 11.35, false);
INSERT INTO medicine VALUES(default, "Mildronat", "500mg", 11.35, false);
INSERT INTO medicine VALUES(default, "L-Thyroxinum", "50mg", 5.55, true);
INSERT INTO medicine VALUES(default, "L-Thyroxinum", "100mg", 7.10, 1);
INSERT INTO medicine VALUES(default, "L-Thyroxinum", "150mg", 7.76, 1);

#prescriptions
INSERT INTO prescription VALUES(default, 7, "2019-05-30", 10, 3);
INSERT INTO prescription VALUES(default, 6, "2019-06-15", 3, 2);
INSERT INTO prescription VALUES(default, 8, "2019-07-01", 5, 3);
INSERT INTO prescription VALUES(default, 15, "2019-06-29", 8, 2);
INSERT INTO prescription VALUES(default, 15, "2019-05-3", 3, 3);

#clients
INSERT INTO client VALUES(6, 1000);
INSERT INTO client VALUES(7, 500);
INSERT INTO client VALUES(8, 250);
INSERT INTO client VALUES(9, 1500);






