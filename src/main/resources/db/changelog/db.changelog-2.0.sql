
--liquibase formatted sql

--changeset Volodymyr Malyniak:1
insert ignore into users
values (1,'malyniak', 'malyniak@gmail.com', '1996-05-15'),
       (2, 'hbz', 'hbz90@ukr.net', '1993-07-27'),
       (3, 'shalimova', 'shalimove@ukr.net', '1998-05-18'),
       (4, 'marbar', 'marbar@gmail.com', '2006-07-16'),
       (5, 'vasul', 'vasul@gmail.com', '1996-04-13');

--changeset Volodymyr Malyniak:2
INSERT IGNORE INTO `task`
VALUES (1,'aaa',1, 2),(2,'bbb',2, 4),(3,'ccc',0, 1),(4,'ddd',1, 4),(5,'eee',2, 5),(6,'fff',0, 1),(7,'ggg',1, 3),
       (8,'hhh',2, 2),(9,'jjj',0, 4),(10,'kkk',1, 5),(11,'lll',2, 3),(12,'mmm',0, 4),(13,'nnn',1, 4),
       (14,'ooo',2, 2),(15,'ppp',0, 3);

