CREATE TABLE app_auth_user (
	id varchar(32) NOT NULL UNIQUE PRIMARY KEY,
	user_id varchar(32) NOT NULL UNIQUE,
	first_name varchar(32) NOT NULL,
	last_name varchar(32) NOT NULL,
	email varchar(32) NOT NULL,
	phone varchar(32) NOT NULL,
	user_type varchar(8) NOT NULL,
	is_active varchar (1)
);