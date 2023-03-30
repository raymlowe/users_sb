CREATE TABLE app_auth_group_role (
	group_id varchar(32) NOT NULL,
	role_id varchar(32) NOT NULL,
	CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id) REFERENCES app_auth_role (id),
	CONSTRAINT FK_GROUP_ID FOREIGN KEY (group_id) REFERENCES app_auth_group (id)
);