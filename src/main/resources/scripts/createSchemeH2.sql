-- -----------------------------------------------------
-- Table group
-- -----------------------------------------------------
CREATE TABLE if not exists circle (
                                                    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                    name VARCHAR(50) NOT NULL
                                               );
                                               



-- -----------------------------------------------------
-- Table contact
-- -----------------------------------------------------
CREATE TABLE if not exists contact (
                                                   id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                   first_name VARCHAR(50) NULL,
                                                   last_name VARCHAR(50) NOT NULL,
                                                   email VARCHAR(50) NULL,
                                                   phones VARCHAR(200) NOT NULL
                                          );



-- -----------------------------------------------------
-- Table contact_has_group
-- -----------------------------------------------------
CREATE TABLE if not exists contact_has_group (
                                                        contact_id BIGINT NOT NULL,
                                                        group_id BIGINT NOT NULL,
                                                        PRIMARY KEY (contact_id, group_id),
                                                        CONSTRAINT fk_contact_id
                                                            FOREIGN KEY (contact_id)
                                                                REFERENCES contact (id)
                                                                ON DELETE NO ACTION
                                                                ON UPDATE NO ACTION,
                                                        CONSTRAINT fk_group_id
                                                            FOREIGN KEY (group_id)
                                                                REFERENCES circle (id)
                                                                ON DELETE NO ACTION
                                                                ON UPDATE NO ACTION);
