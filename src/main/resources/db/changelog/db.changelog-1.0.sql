--liquibase formatted sql

--changeset Volodymyr Malyniak:1
create table IF NOT EXISTS `users` (
    `id` int primary key auto_increment,
    `username` varchar(32) unique ,
    `email` varchar(32) unique ,
    `birth_date` date
    );

--changeset Volodymyr Malyniak:2
CREATE TABLE IF NOT EXISTS `task` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `description` varchar(100) NOT NULL,
    `status` int(11) NOT NULL,
    user_id int,
    PRIMARY KEY (`id`),
    foreign key (user_id) references users(id)
)