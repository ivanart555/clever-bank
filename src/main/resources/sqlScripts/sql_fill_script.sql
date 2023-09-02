INSERT INTO cleverbank.banks (id, name)
VALUES (1, 'Clever-Bank'),
       (2, 'Lama'),
       (3, 'Groot-Bank'),
       (4, 'PPT Bank'),
       (5, 'Bober-Bank');

INSERT INTO cleverbank.customers (first_name, last_name)
VALUES ('Alexander', 'Richardson'),
       ('August', 'Morris'),
       ('Demetrius', 'Jarvis'),
       ('Gage', 'Conway'),
       ('Hedley', 'Bennett'),
       ('Vincent', 'Fletcher'),
       ('Scott', 'Atkins'),
       ('Tiger', 'Morton'),
       ('Chester', 'Cunningham'),
       ('Julian', 'Maddox'),
       ('Roth', 'Duffy'),
       ('Hilel', 'Hudson'),
       ('Dolan', 'Boone'),
       ('Peter', 'Rosario'),
       ('Samuel', 'Brady'),
       ('Jackson', 'Thornton'),
       ('Raja', 'Rowland'),
       ('Kasimir', 'Boyle'),
       ('Allistair', 'Thornton'),
       ('Jacob', 'Dejesus');

INSERT INTO cleverbank.accounts ("number", "bankId", "customerId", balance)
VALUES ('BY02ABCD12345678901234', 1, 1, 12345),
       ('BY03EFGH56789012345678', 2, 2, 67890),
       ('BY04IJKL90123456789012', 3, 3, 45678),
       ('BY05MNOP23456789012345', 4, 4, 34567),
       ('BY02QRST56789012345678', 5, 5, 23456),
       ('BY03UVWX90123456789012', 1, 6, 12345),
       ('BY04YZAB23456789012345', 2, 7, 56789),
       ('BY05CDEF56789012345678', 3, 8, 78901),
       ('BY02GHIJ90123456789012', 4, 9, 90123),
       ('BY03KLMN23456789012345', 5, 10, 12345),
       ('BY04OPQR56789012345678', 1, 11, 23456),
       ('BY05STUV90123456789012', 2, 12, 34567),
       ('BY02WXYZ23456789012345', 3, 13, 45678),
       ('BY03ABCD56789012345678', 4, 14, 56789),
       ('BY04EFGH90123456789012', 5, 15, 67890),
       ('BY05IJKL23456789012345', 1, 16, 78901),
       ('BY02MNOP56789012345678', 2, 17, 90123),
       ('BY03QRST90123456789012', 3, 18, 12345),
       ('BY04UVWX23456789012345', 4, 19, 23456),
       ('BY05YZAB56789012345678', 5, 20, 34567),
       ('BY02CDEF90123456789012', 1, 1, 45678),
       ('BY03GHIJ23456789012345', 2, 2, 56789),
       ('BY04KLMN56789012345678', 3, 3, 67890),
       ('BY05OPQR90123456789012', 4, 4, 78901),
       ('BY02STUV23456789012345', 5, 5, 90123),
       ('BY03WXYZ56789012345678', 1, 6, 12345),
       ('BY04ABCD90123456789012', 2, 7, 23456),
       ('BY05EFGH23456789012345', 3, 8, 34567),
       ('BY02IJKL56789012345678', 4, 9, 45678),
       ('BY03MNOP90123456789012', 5, 10, 56789),
       ('BY04QRST23456789012345', 1, 11, 67890),
       ('BY05UVWX56789012345678', 2, 12, 78901),
       ('BY02YZAB90123456789012', 3, 13, 90123),
       ('BY03CDEF23456789012345', 4, 14, 12345),
       ('BY04GHIJ56789012345678', 5, 15, 23456),
       ('BY05KLMN90123456789012', 1, 16, 34567),
       ('BY02OPQR23456789012345', 2, 17, 45678),
       ('BY03STUV56789012345678', 3, 18, 56789),
       ('BY04WXYZ90123456789012', 4, 19, 67890),
       ('BY05ABCD23456789012345', 5, 20, 78901),
       ('BY02EFGH56789012345678', 1, 1, 90123),
       ('BY03IJKL90123456789012', 2, 2, 12345),
       ('BY04MNOP23456789012345', 3, 3, 23456),
       ('BY05QRST56789012345678', 4, 4, 34567),
       ('BY02UVWX90123456789012', 5, 5, 45678),
       ('BY03YZAB23456789012345', 1, 6, 56789),
       ('BY04CDEF56789012345678', 2, 7, 67890),
       ('BY05GHIJ90123456789012', 3, 8, 78901),
       ('BY02KLMN23456789012345', 4, 9, 90123),
       ('BY03OPQR56789012345678', 5, 10, 12345);