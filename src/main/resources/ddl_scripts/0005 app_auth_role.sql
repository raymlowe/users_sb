CREATE TABLE app_auth_role (
	id varchar(32) NOT NULL unique PRIMARY KEY,
	role_name varchar(32) NOT NULL UNIQUE,
	role_description varchar(32)
);