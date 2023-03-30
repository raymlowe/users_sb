CREATE TABLE app_auth_group (
	id varchar(32) NOT NULL UNIQUE PRIMARY KEY,
	group_name varchar(32) NOT NULL UNIQUE,
	group_description varchar(32)
);