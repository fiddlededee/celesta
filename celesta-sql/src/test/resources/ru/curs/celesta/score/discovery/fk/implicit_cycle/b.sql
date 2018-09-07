SET SCHEMA test;

create table b(
  id int not null primary key,
  c_id int not null,
  constraint fk_b_c foreign key (c_id) references c(id)
);

create table c(
  id int not null primary key,
  a_id int not null,
  constraint fk_c_a foreign key (a_id) references a(id)
);