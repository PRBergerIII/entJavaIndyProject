DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(25) NOT NULL,
                        `first_name` varchar(20) NOT NULL,
                        `last_name` varchar(30) NOT NULL,
                        `email` varchar(50) NOT NULL,
                        `street` varchar(50) DEFAULT NULL,
                        `city` varchar(25) DEFAULT NULL,
                        `state` char(2) DEFAULT NULL,
                        `zip` varchar(5) DEFAULT NULL,
                        `address_visibility` varchar(9) NOT NULL DEFAULT 'private',
                        `about` varchar(255) DEFAULT NULL,
                        `admin` bit(1) NOT NULL DEFAULT b'0',
                        PRIMARY KEY (`id`)
);

INSERT INTO `user` (`username`, `first_name`, `last_name`, `email`, `street`, `city`, `state`, `zip`, `address_visibility`, `about`, `admin`) VALUES ('pberger','Paul','Berger','pberger@madisoncollege.edu','123 main st','place','WI','12324','private','I am a size 6',_binary ''),('user1234','A','Dude','notthatdude@aol.com','444 all over st','Los Angeles','CA','91342','public',NULL,_binary '\0'),('user123','some','user','user123@email.com',NULL,NULL,NULL,NULL,'followers','I have an address but it\'s not on here',_binary '\0');
