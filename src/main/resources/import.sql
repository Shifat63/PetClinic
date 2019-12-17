-- This data will only be loaded when spring.jpa.hibernate.ddl-auto in application.properties is create/create-drop

insert into type (name) values ('Dog');
insert into type (name) values ('Cat');

insert into speciality (description) values ('Radiology Specialist');
insert into speciality (description) values ('Surgon');
insert into speciality (description) values ('Dentist');

insert into owner (first_name, last_name, address, city, telephone) values ('Md Emtazul', 'Haque', 'Germany', 'Bonn', '123456')
insert into owner (first_name, last_name, address, city, telephone) values ('Torikul', 'Islam', 'Bangladesh', 'Dhaka', '34567')
insert into owner (first_name, last_name, address, city, telephone) values ('Ibrahim', 'Khan', 'Bangladesh', 'Madaripur', '98765')

insert into pet (birth_date, name, owner_id, type_id) values (current_date,	'Vulo', 1, 2)
insert into pet (birth_date, name, owner_id, type_id) values (current_date,	'Tommy', 1, 1)
insert into pet (birth_date, name, owner_id, type_id) values (current_date,	'Hosko', 2, 1)
insert into pet (birth_date, name, owner_id, type_id) values (current_date,	'Fire', 3, 2)

insert into visit (date, desciption, pet_id) values (current_date, 'Vulo visit 1', 1)
insert into visit (date, desciption, pet_id) values (current_date, 'Tommy visit 1', 2)
insert into visit (date, desciption, pet_id) values (current_date, 'Tommy visit 2', 2)
insert into visit (date, desciption, pet_id) values (current_date, 'Hosko Visit 1', 3)
insert into visit (date, desciption, pet_id) values (current_date, 'Hosko Visit 2', 3)
insert into visit (date, desciption, pet_id) values (current_date, 'Fire Visit 1', 4)

insert into vet (first_name, last_name) values ('Tamim', 'Hawlader')
insert into vet (first_name, last_name) values ('Saiful', 'Islam')

insert into vet_speciality (vet_id, speciality_id) values (1, 2)
insert into vet_speciality (vet_id, speciality_id) values (1, 3)
insert into vet_speciality (vet_id, speciality_id) values (2, 1)
insert into vet_speciality (vet_id, speciality_id) values (2, 2)