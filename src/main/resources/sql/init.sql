drop table if exists users;

create table todo.users(
                      id int primary key auto_increment,
                      username varchar(32) unique ,
                      email varchar(32) unique ,
                      birth_date date
);


DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `description` varchar(100) NOT NULL,
                        `status` int(11) NOT NULL,
                        user_id int,
                        PRIMARY KEY (`id`),
                        foreign key (user_id) references users(id)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
SET character_set_client = @saved_cs_client ;

LOCK TABLES `task` WRITE;
ALTER TABLE `task` DISABLE KEYS ;