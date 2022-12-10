CREATE TABLE e_refresh_token(
	ID 					VARCHAR ( 36 ) NOT NULL,
	token 				VARCHAR ( 255 ),
	expiry_date 		TIMESTAMP,
	user_id				VARCHAR (36),
	PRIMARY KEY ( ID ) 
);
ALTER TABLE IF EXISTS e_refresh_token
	ADD CONSTRAINT FK_RF_TOKEN_USER
	FOREIGN KEY (user_id) REFERENCES e_user(id);