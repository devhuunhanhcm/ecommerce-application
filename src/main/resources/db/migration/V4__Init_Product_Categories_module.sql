CREATE TABLE e_categories(
	id					VARCHAR(36) NOT NULL,
	created_at 			TIMESTAMP,
	created_by 			VARCHAR ( 36 ),
	last_modified_at 	TIMESTAMP,
	last_modified_by 	VARCHAR ( 36 ),
	VERSION 			INT4 NOT NULL,
	name				VARCHAR(255) NOT NULL UNIQUE,
	
	PRIMARY KEY ( id ) 
);
CREATE TABLE e_product(
	id					VARCHAR(36) NOT NULL,
	created_at 			TIMESTAMP,
	created_by 			VARCHAR ( 36 ),
	last_modified_at 	TIMESTAMP,
	last_modified_by 	VARCHAR ( 36 ),
	VERSION 			INT4 NOT NULL,
	title 				VARCHAR(255) NOT NULL,
	name				VARCHAR(255) NOT NULL UNIQUE,
	price				INT NOT NULL,
	sale				FLOAT,
	description			VARCHAR(255),
	image				VARCHAR(255),
	quantity			INT4 NOT NULL,
	views 				INT4,
	likes 				INT4,
	PRIMARY KEY ( id )
);
CREATE TABLE e_categories_product ( 
	categories_id 		VARCHAR ( 36 ) NOT NULL, 
	product_id 			VARCHAR ( 36 ) NOT NULL, 
	PRIMARY KEY ( categories_id, product_id ) 
);

ALTER TABLE IF EXISTS e_product
	ADD CONSTRAINT CK_SALE_PERCENT
	CHECK (sale >= 0 AND sale <= 100);

ALTER TABLE IF EXISTS e_categories_product 
	ADD CONSTRAINT FK_CATE_PRODUCT 
	FOREIGN KEY ( product_id ) REFERENCES e_product(id);
	
ALTER TABLE IF EXISTS e_categories_product 
	ADD CONSTRAINT FK_PRODUCT_CATE
	FOREIGN KEY ( categories_id ) REFERENCES e_categories(id);