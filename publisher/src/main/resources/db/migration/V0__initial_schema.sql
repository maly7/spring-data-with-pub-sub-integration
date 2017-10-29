CREATE TABLE book (
  id    BIGINT(20)   NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY (id)
);

CREATE TABLE writer (
  id   BIGINT(20)  NOT NULL AUTO_INCREMENT,
  name VARCHAR(36) NOT NULL
);

CREATE TABLE book_author (
  author_id BIGINT(20) NOT NULL,
  book_id   BIGINT(20) NOT NULL,
  PRIMARY KEY (author_id),
  CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES writer (id),
  CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE label (
  id   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE book_label (
  book_id  BIGINT(20) NOT NULL,
  label_id BIGINT(20) NOT NULL,
  PRIMARY KEY (book_id, label_id),
  CONSTRAINT fk_book_label_book_id FOREIGN KEY (book_id) REFERENCES book (id),
  CONSTRAINT fk_book_label_label_id FOREIGN KEY (label_id) REFERENCES label (id)
);
