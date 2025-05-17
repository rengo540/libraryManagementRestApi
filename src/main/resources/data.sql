-- Authors
insert into author (id, name) values
(1, 'J.K. Rowling'),
(2, 'George R.R. Martin');

-- Publishers
insert into publisher (id, name) values
(1, 'Bloomsbury'),
(2, 'Bantam Books');

-- Categories
insert into category (id, name, parent_id) values
(1, 'Fiction', null),
(2, 'Fantasy', 1);

--   ROLE_ADMIN 0, ROLE_LIBRARIAN 1, ROLE_STAFF 2, ROLE_MEMBER 3
insert into role (id, name) values (1, 0) , (2, 1), (3, 2), (4, 3);
    

-- Users
INSERT INTO users (id, email, password) VALUES
(1, 'admin', '$2a$10$JU/BZQPBosLpV9Hdt81WHezrApuU.F47CfY.4AvosCpRjSsmOgeNy'),
(4, 'ahmed', '$2a$10$FnER6/vdAMIPL4QTMw75muFTZ2BE0vzZSt40qbniIJDed.Q4SeB5C');


-- Books
INSERT INTO book (
    id, name, lang, publication_year, ISBN, edition, summary, availability, publisher_id,category_id
) VALUES
(1, 'Harry Potter and the Philosopher', 'English', '1997-06-26', '9780747532699', '1st', 'A wizarding classic.', 5, 1,1);
