delete from user;
delete from user_connection;
ALTER TABLE user AUTO_INCREMENT = 1;
INSERT INTO `user` (`username`, `first_name`, `last_name`, `email`, `street`, `city`, `state`, `zip`, `address_visibility`, `about`, `admin`) VALUES ('pberger','Paul','Berger','pberger@madisoncollege.edu','123 main st','place','WI','12324','private','I am a size 6',_binary ''),('user1234','A','Dude','notthatdude@aol.com','444 all over st','Los Angeles','CA','91342','public',NULL,_binary '\0'),('user123','some','user','user123@email.com',NULL,NULL,NULL,NULL,'followers','I have an address but it\'s not on here',_binary '\0');
insert into flexregistry_test.user_connection (follower_id, user_followed_id, accepted) values (1, 3, false), (2, 1, false), (2, 3, true), (3, 1, true), (3, 2, false);
# TODO: consider combining all table wipes in case of fk issues