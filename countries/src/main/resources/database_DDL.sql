CREATE TABLE country(
id BIGINT(20) AUTO_INCREMENT,
description VARCHAR(255) NOT NULL,
codeISOA2 VARCHAR(50),
codeISOA3 VARCHAR(50),
codeISON3 INT(11),
start_Date  DATETIME(6),
end_Date  DATETIME(6),
created_On  DATETIME(6),
updated_On  DATETIME(6),

PRIMARY KEY (id)
);


CREATE TABLE historic(
id bigint(20) AUTO_INCREMENT NOT NULL,
created_On DATETIME(6)  DEFAULT NULL,
updated_On DATETIME(6)  DEFAULT NULL,
type_action INT(11)  DEFAULT NULL,
country_id bigint(20) DEFAULT NULL,

KEY fk_country_id(country_id),
PRIMARY KEY (id),

CONSTRAINT fk_country_id FOREIGN KEY (country_id) REFERENCES country(id)
);