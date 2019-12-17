create table owner (id bigint not null auto_increment, first_name varchar(25), last_name varchar(25), address varchar(255), city varchar(255), telephone varchar(20), primary key (id)) engine=MyISAM
create table pet (id bigint not null auto_increment, birth_date date, name varchar(20), owner_id bigint not null, type_id bigint not null, primary key (id)) engine=MyISAM
create table speciality (id bigint not null auto_increment, description varchar(25), primary key (id)) engine=MyISAM
create table type (id bigint not null auto_increment, name varchar(20), primary key (id)) engine=MyISAM
create table vet (id bigint not null auto_increment, first_name varchar(25), last_name varchar(25), primary key (id)) engine=MyISAM
create table vet_speciality (vet_id bigint not null, speciality_id bigint not null, primary key (vet_id, speciality_id)) engine=MyISAM
create table visit (id bigint not null auto_increment, date date, desciption varchar(255), pet_id bigint not null, primary key (id)) engine=MyISAM
alter table pet add constraint FK7qfti9yba86tgfe9oobeqxfxg foreign key (owner_id) references owner (id)
alter table pet add constraint FKe575pwyhy4o7k8wci2hmoj1a2 foreign key (type_id) references type (id)
alter table vet_speciality add constraint FKns25xuxcyf46sbycxgsblcvja foreign key (speciality_id) references speciality (id)
alter table vet_speciality add constraint FKoh5v7f4x7bl2m2lkaw69h4lr7 foreign key (vet_id) references vet (id)
alter table visit add constraint FKpr2103nfb1ueid78p80lvv1ed foreign key (pet_id) references pet (id)
