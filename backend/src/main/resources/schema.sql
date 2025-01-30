CREATE TABLE IF NOT EXISTS `users` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS `books` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `isbn` varchar(14) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS `borrowed_books` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `user_id` INT REFERENCES users(id) ON DELETE CASCADE,
  `book_id` INT REFERENCES books(id) ON DELETE CASCADE,
  `isbn` varchar(14) NOT NULL,
  `borrow_date` date NOT NULL,
  `return_date` date
);
