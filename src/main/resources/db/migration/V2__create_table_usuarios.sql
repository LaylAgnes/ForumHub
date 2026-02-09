CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

INSERT INTO usuarios (login, senha, role)
VALUES (
    'admin',
    '$2a$10$7QJ8rYx7m0Yj5u5Pzv1D6O9t2EJ1E0cZxQe4YxJH0y5z5C1n3ZC9a',
    'ROLE_ADMIN'
);
