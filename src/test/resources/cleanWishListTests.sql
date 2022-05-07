delete from user;
delete from wish_list;
delete from wish_list_item;
ALTER TABLE user AUTO_INCREMENT = 1;
ALTER TABLE wish_list AUTO_INCREMENT = 1;
ALTER TABLE wish_list_item AUTO_INCREMENT = 1;
insert into user (username, first_name, last_name, email, street, city, state, zip, address_visibility, about, admin)
values  ('pberger', 'Paul', 'Berger', 'pberger@madisoncollege.edu', '123 main st', 'place', 'WI', '12324', 'private', 'I am a size 6', true),
        ('user1234', 'A', 'Dude', 'notthatdude@aol.com', '444 all over st', 'Los Angeles', 'CA', '91342', 'public', null, false),
        ('user123', 'some', 'user', 'user123@email.com', null, null, null, null, 'followers', 'I have an address but it''s not on here', false);
insert into wish_list (owner_id, title, visibility, purchased_items_visibility, list_type, event_date)
values  (1, 'My Birthday List', 'public', true, 'Birthday', '2022-05-31'),
        (1, 'Christmas', 'public', false, 'Holiday', '2022-12-25'),
        (2, 'New Baby Boyyyeeeeee', 'followers', false, 'Shower', '2023-03-12'),
        (3, 'Treat Yo'' Self', 'private', true, null, null);
insert into flexregistry_test.wish_list_item (wish_list_id, name, specific_item, details, priority, price_range, purchased, purchased_message)
values  (1, 'Hoosit', true, 'go to this link: (pretend this is a link)', 1, '50-100', false, null),
        (1, 'Whatsit', true, 'you know the one', 5, '10-20', true, 'Here you go!'),
        (2, 'Thing', false, 'the thing', 3, null, false, null),
        (2, 'Shirt', false, 'I like blue, so any blue', 4, null, false, null),
        (3, 'diapers', true, 'Only want huggies, but any amount will do', 2, null, false, null),
        (3, 'car seat', true, 'Only the best for my little champ', 5, '100000', false, null),
        (3, 'teething rings', false, 'Any will do', 2, '5-10', true, 'chomp chomp!'),
        (4, 'PS5', true, 'Link to stock monitoring site', 5, '499', false, null);