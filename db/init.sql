-- Create User table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- Create Author table
CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    bio TEXT
);

-- Create Book table
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id INT NOT NULL,
    published_year INT,
    genre VARCHAR(100),
    is_available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);

-- Create Circulation table for tracking borrowed books
CREATE TABLE circulations (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    borrow_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    return_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);


-- Insert sample users
INSERT INTO users (name, email) VALUES
('Alice Johnson', 'alice@example.com'),
('Bob Smith', 'bob@example.com'),
('Carol Davis', 'carol@example.com'),
('David Wilson', 'david@example.com'),
('Emma Brown', 'emma@example.com');

-- Insert sample authors
INSERT INTO authors (name, bio) VALUES
('J.K. Rowling', 'British author, best known for the Harry Potter fantasy series.'),
('George R.R. Martin', 'American novelist and creator of A Song of Ice and Fire series.'),
('Jane Austen', 'English novelist known for her romantic fiction set among the landed gentry.'),
('Stephen King', 'American author of horror, supernatural fiction, suspense, and fantasy novels.'),
('Agatha Christie', 'English writer known for her detective novels featuring Hercule Poirot and Miss Marple.'),
('Isaac Asimov', 'American author and professor of biochemistry, famous for his science fiction works.'),
('Harper Lee', 'American novelist best known for To Kill a Mockingbird.'),
('Gabriel García Márquez', 'Colombian novelist and Nobel Prize winner, known for magical realism.');

-- Insert sample books
INSERT INTO books (title, author_id, published_year, genre, is_available) VALUES
-- J.K. Rowling books
('Harry Potter and the Sorcerers Stone', 1, 1997, 'Fantasy', TRUE),
('Harry Potter and the Chamber of Secrets', 1, 1998, 'Fantasy', TRUE),
('Harry Potter and the Prisoner of Azkaban', 1, 1999, 'Fantasy', FALSE),
('Harry Potter and the Goblet of Fire', 1, 2000, 'Fantasy', TRUE),

-- George R.R. Martin books
('A Game of Thrones', 2, 1996, 'Fantasy', TRUE),
('A Clash of Kings', 2, 1998, 'Fantasy', FALSE),
('A Storm of Swords', 2, 2000, 'Fantasy', TRUE),

-- Jane Austen books
('Pride and Prejudice', 3, 1813, 'Romance', TRUE),
('Sense and Sensibility', 3, 1811, 'Romance', TRUE),
('Emma', 3, 1815, 'Romance', TRUE),

-- Stephen King books
('The Shining', 4, 1977, 'Horror', TRUE),
('It', 4, 1986, 'Horror', FALSE),
('The Stand', 4, 1978, 'Horror', TRUE),
('Carrie', 4, 1974, 'Horror', TRUE),

-- Agatha Christie books
('Murder on the Orient Express', 5, 1934, 'Mystery', TRUE),
('And Then There Were None', 5, 1939, 'Mystery', TRUE),
('The Murder of Roger Ackroyd', 5, 1926, 'Mystery', FALSE),

-- Isaac Asimov books
('Foundation', 6, 1951, 'Science Fiction', TRUE),
('I Robot', 6, 1950, 'Science Fiction', TRUE),
('The Gods Themselves', 6, 1972, 'Science Fiction', TRUE),

-- Harper Lee books
('To Kill a Mockingbird', 7, 1960, 'Fiction', TRUE),

-- Gabriel García Márquez books
('One Hundred Years of Solitude', 8, 1967, 'Magical Realism', TRUE),
('Love in the Time of Cholera', 8, 1985, 'Romance', TRUE);

-- Insert sample circulation records (some books borrowed)
INSERT INTO circulations (user_id, book_id, borrow_date, return_date) VALUES
-- Alice has borrowed and returned a book
(1, 1, CURRENT_TIMESTAMP - INTERVAL '10 days', CURRENT_TIMESTAMP - INTERVAL '3 days'),
-- Bob currently has 2 books borrowed (not returned yet)
(2, 3, CURRENT_TIMESTAMP - INTERVAL '5 days', NULL),
(2, 6, CURRENT_TIMESTAMP - INTERVAL '2 days', NULL),
-- Carol currently has 1 book borrowed
(3, 12, CURRENT_TIMESTAMP - INTERVAL '1 day', NULL),
-- David has borrowed and returned books in the past
(4, 1, CURRENT_TIMESTAMP - INTERVAL '20 days', CURRENT_TIMESTAMP - INTERVAL '15 days'),
(4, 8, CURRENT_TIMESTAMP - INTERVAL '15 days', CURRENT_TIMESTAMP - INTERVAL '8 days'),
-- Emma currently has 1 book borrowed
(5, 17, CURRENT_TIMESTAMP - INTERVAL '4 days', NULL);

