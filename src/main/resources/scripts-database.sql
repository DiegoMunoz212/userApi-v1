CREATE SCHEMA `users-api` ;

-- Table Users
CREATE TABLE `users-api`.`users` (
                                     `id` INT NOT NULL AUTO_INCREMENT,
                                     `username` VARCHAR(25) NOT NULL,
                                     `email` VARCHAR(250) NOT NULL,
                                     `password` VARCHAR(250) NOT NULL,
                                     `created_at` DATE NULL,
                                     `updated_at` DATE NULL,
                                     PRIMARY KEY (`id`),
                                     UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
                                     UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
