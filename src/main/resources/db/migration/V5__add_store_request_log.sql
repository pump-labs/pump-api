CREATE TABLE `store_request_log` (
                                      `id` int PRIMARY KEY AUTO_INCREMENT,
                                      `store_id` int not null,
                                      `user_id` int not null,
                                      `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                      `modified_at` TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
                                      `removed_at` TIMESTAMP
);