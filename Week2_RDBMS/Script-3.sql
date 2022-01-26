-- DDL language subset to create data structure
--create table GREETING (
--	id serial primary key,
--	greeting_text varchar(40) not null unique
--);
--
--create table GREETING_USER (
--	id serial primary key,
--	username varchar(20),
--	password varchar(40)
--);
--
--
--alter table greeting 
--	add column user_id int4;
--
--alter table greeting
--	add constraint fk_greeting_user
--	foreign key(user_id)
--	references greeting_user(id);

drop table greeting_user;
drop table greeting;

create table if not exists GREETING_ROLE (
	id serial primary key,
	role varchar(10) not null unique
);

create table if not exists GREETING_USER (
	id serial primary key,
	username varchar(20),
	password varchar(40)
);

create table if not exists GREETING_USER_ROLE (
	user_id int4 references GREETING_USER(id),
	role_id int4 references GREETING_ROLE(id),
	primary key(user_id, role_id)
);

create table if not exists GREETING (
	id serial primary key,
	greeting_text varchar(40) not null unique,
	user_id int4 references GREETING_USER(id)
);

truncate table greeting;

-- DML language subset used to manipulate data (Insert, Update, Delete)

-- write to the database and returns the number of rows written
insert into greeting_role (role) values ('ROLE_ADMIN'), ('ROLE_USER');
insert into greeting_user (username, password) values ('august.duet', 'p@$$w0rd123'), ('john.doe', 'password');
insert into greeting_user_role (user_id, role_id) values (1, 1), (1, 2), (2, 2);
insert into greeting (greeting_text, user_id) values ('Hullo, Thar!', 1), ('Hello, World!', 1);

insert into greeting_user (username, password) values ('a.b', 'p@$$w0rd123'), ('c_f', 'password'), ('a_r', 'password'), ('a_e', 'password'),('z.y', 'password');

-- update data that already exists in the db
-- update <table-name> set col1 = val1, col2 = val2,... where colx = valx AND/OR coly = valy;
update greeting set greeting_text = 'Good morning' where greeting_text = 'Hello, World!';


-- delete data that already exists in the db
delete from greeting where id = 3;


-- DQL language subset for retrieving data from the database
-- select col1, col2, col3, ..... from <table-name> where col1=val1;
select username, password from greeting_user;

select username, password from greeting_user where username like '%' order by username limit 10;

-- Inner Queries are used to utilize the results of query within another query
-- 1. Nested Subquery - Use of an inner query within a WHERE or HAVING clause to provide a value or resultset 
--			For filtering
-- 2. Select Inner Query - Allows you to use an inner query in the select portion of a select statement.
-- 3. Inline Views - Allow an inner query to be utilized as the dataset that a select statement operates on.

-- Prefer to use a join

-- Nested Subquery
select username from greeting_user where id in (select user_id from greeting group by user_id having count(*) >= 1);

-- Select Inner Query
select id, username, (select count(*) from greeting where user_id = id) as greetings from greeting_user where id in (select user_id from greeting group by user_id having count(*) >= 1);

-- Inline views
select id, username from 
	(select count(*) from greeting where user_id = id) as greetings from greeting_user where id in (select user_id from greeting group by user_id having count(*) >= 1) as inlineview 
	where username like '%august';

-- Kinds of Joins
-- * LEFT (OUTER) JOIN -- All data from left side, left side replicated to accompany any matching data
							-- right side may be null if there are no matching records
-- * RIGHT (OUTER) JOIN -- Same, but the opposite directionality
-- * FULL OUTER JOIN -- Select all data from both sides replicating as necessary, allowing null values 
-- 							on either side of no matching records are found for a given row
-- * INNER JOIN -- Selects only matching rows, replicated as necessary to show all relationships
-- * CROSS JOIN - Joining all rows to all other rows - Their is no matching clause, everything is joined.
-- * NATURAL JOIN - Joining two tables using equality of like column names as the implicit condition
-- * SELF JOIN - Joining a table to itself
-- * UNEQUAL JOIN - Any join in which the applied condition for joining is not equality

-- Note: A cross join can also be accomplished with the following syntax:
-- SELECT * FROM table_a, table_b;

select g.greeting_text, username from greeting g
	join greeting_user gu on g.user_id = gu.id;

select username, role_id from greeting_user gu
	left join greeting_user_role gur on gu.id = gur.user_id;

select username, role_id from greeting_user gu
	right join greeting_user_role gur on gu.id = gur.user_id;

select username, role from greeting_user gu
	left join greeting_user_role gur on gu.id = gur.user_id
	left join greeting_role gr on gur.role_id = gr.id;

select * from greeting;


-- Other ops for select statements
-- grouping -> Group By
-- Aggregation -> Having
-- Limiting -> Limit
-- Skipping -> Offset
-- Order -> Order By

-- Multiplicity -- defines the number of objects in the relationship
-- One-to-One
-- One-to-Many
-- Many-to-Many

































