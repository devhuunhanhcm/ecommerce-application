CREATE TABLE e_group (
	ID 					VARCHAR ( 36 ) NOT NULL,
	created_at 			TIMESTAMP,
	created_by 			VARCHAR ( 36 ),
	last_modified_at 	TIMESTAMP,
	last_modified_by 	VARCHAR ( 36 ),
	VERSION 			int4 NOT NULL,
	name 				VARCHAR ( 100 ),
	description 		VARCHAR ( 255 ),
	PRIMARY KEY ( ID ) 
);

CREATE TABLE e_role (
	ID 					VARCHAR ( 36 ) NOT NULL,
	created_at 			TIMESTAMP,
	created_by 			VARCHAR ( 36 ),
	last_modified_at 	TIMESTAMP,
	last_modified_by 	VARCHAR ( 36 ),
	VERSION 			int4 NOT NULL,
	name 				VARCHAR ( 100 ),
	description 		VARCHAR ( 255 ),
	PRIMARY KEY ( ID ) 
);
CREATE TABLE e_group_role ( 
	group_id 			VARCHAR ( 36 ) NOT NULL,
	role_id 			VARCHAR ( 36 ) NOT NULL, 
	PRIMARY KEY ( group_id, role_id )
);
ALTER TABLE
IF
	EXISTS e_group_role 
	ADD CONSTRAINT FK_GROUP_ROLE_ROLE 
	FOREIGN KEY ( role_id ) REFERENCES e_role;
ALTER TABLE
IF
	EXISTS e_group_role 
	ADD CONSTRAINT FK_GROUP_ROLE_GROUP 
	FOREIGN KEY ( group_id ) REFERENCES e_group;