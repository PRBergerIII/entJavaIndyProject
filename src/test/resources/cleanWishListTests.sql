delete from user;
delete from wish_list;
delete from wish_list_item;
ALTER TABLE user AUTO_INCREMENT = 1;
ALTER TABLE wish_list AUTO_INCREMENT = 1;
ALTER TABLE wish_list_item AUTO_INCREMENT = 1;
insert into user (username, first_name, last_name, email, street, city, state, zip, address_visibility, about, admin)
values  ('pberger', 'Paul', 'Berger', 'pberger@madisoncollege.edu', '123 main st', 'place', 'WI', '12324', 'private', 'I am a size 6', true);
insert into wish_list (owner_id, title, visibility, purchased_items_visibility, list_type, event_date)
values  (1, 'My Birthday List', 'public', true, 'Birthday', '2022-05-31'),
        (1, 'Christmas', 'public', false, 'Holiday', '2022-12-25'),
        (1, 'New Baby Boyyyeeeeee', 'followers', false, 'Shower', '2023-03-12'),
        (1, 'Treat Yo'' Self', 'private', true, null, null);
insert into flexregistry_test.wish_list_item (wish_list_id, name, specific_item, details, priority, price_range, purchased, purchased_message)
values  (1, 'Whatsit', true, 'you know the one', 5, '10-20', true, 'Here you go!');