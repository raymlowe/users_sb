CREATE TABLE app_auth_user_group (
	user_id varchar(32) NOT NULL,
	group_id varchar(32) NOT NULL,
	CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES app_auth_user (id),
	CONSTRAINT FK_GROUP_ID FOREIGN KEY (group_id) REFERENCES app_auth_group (id)
);