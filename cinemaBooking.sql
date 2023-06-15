-- --------------------------------------------------------

-- DROP TABLE useraccount;
-- DROP TABLE userprofile;
-- DROP TABLE movie;
-- DROP TABLE cinemaroom;
-- DROP TABLE moviesession;
-- DROP TABLE tickettype;

--
-- Table structure for table `userProfile`
--


CREATE TABLE userprofile (
    profileID INT(11) PRIMARY KEY,
    description VARCHAR(50) NOT NULL
);

ALTER TABLE userprofile
  MODIFY profileID int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
-- COMMIT;

INSERT INTO userprofile (description)
VALUES
    ('User Admin'),
    ('Cinema Owner'),
    ('Cinema Manager'),
	('Customer');

select * from userprofile;

--
-- Table structure for table `userAccount`
--


-- DROP TABLE useraccount; 

CREATE TABLE useraccount (
    `UID` INT(11) PRIMARY KEY,
	`name` VARCHAR(50) NOT NULL,
    `DOB` Date NOT NULL,
	`user` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
	`phoneNo` VARCHAR(10) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
	`address` varchar (50) NOT NULL,
	`suspended` BIT(1) NOT NULL DEFAULT 0,
    `profileID` INT(2) NOT NULL,
	CONSTRAINT FK_profile FOREIGN KEY (profileID) REFERENCES userProfile(profileID)
);

ALTER TABLE useraccount
  MODIFY `UID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
  
 
INSERT INTO useraccount (`name`, `DOB`, `user`, `password`, `phoneNo`, `email`, `address`, `suspended`,`profileID`)
VALUES
	('Jerome', '1990-05-12', 'chin', 'yano', '1234567890', 'chin@example.com', '321 Main St', 0, 1),
    ('John Smith', '1990-05-12', 'useradmin', 'asdf', '1234567890', 'johnsmith@example.com', '123 Main St', 0, 1),
    ('Jane Doe', '2009-09-20', 'cinemaowner', 'asdf', '9876543210', 'janedoe@example.com', '456 Elm St', 0, 2),
    ('Alice Johnson', '1976-03-08', 'cinemamanager', 'asdf', '5554443333', 'alicej@example.com', '789 Oak St', 0, 3),
    ('Bob Williams', '1992-07-18', 'bobw', 'bobpass', '1112223333', 'bobw@example.com', '246 Pine St', 0, 4),
    ('Sarah Davis', '2012-01-25', 'sarahd', 'sarahpass', '9998887777', 'sarahd@example.com', '678 Maple St', 0, 4),
    ('Michael Johnson', '1994-11-05', 'michaelj', 'mikepass', '5556667777', 'michaelj@example.com', '987 Oak St', 0, 4),
    ('Emily Smith', '1960-06-30', 'emilys', 'emilypass', '8887776666', 'emilys@example.com', '543 Elm St', 0, 4),
    ('David Brown', '1992-02-15', 'davidb', 'davidpass', '4445556666', 'davidb@example.com', '321 Pine St', 0, 4),
    ('Jessica Wilson', '2010-09-10', 'jessicaw', 'jessicapass', '2223334444', 'jessicaw@example.com', '876 Maple St', 0, 4),
    ('Andrew Miller', '1959-04-20', 'andrewm', 'andrewpass', '7778889999', 'andrewm@example.com', '543 Oak St', 0, 4),
    ('Olivia Johnson', '1991-08-07', 'oliviaj', 'oliviapass', '3334445555', 'oliviaj@example.com', '234 Elm St', 0, 4),
    ('William Davis', '2011-03-16', 'williamd', 'williampass', '6667778888', 'williamd@example.com', '789 Pine St', 0, 4),
    ('Sophia Anderson', '1959-12-02', 'sophiaa', 'sophiapass', '4445556666', 'sophiaa@example.com', '432 Maple St', 0, 4),
    ('Daniel Johnson', '1994-06-22', 'danielj', 'danielpass', '8889990000', 'danielj@example.com', '345 Oak St', 0, 4),
    ('Mike Smith', '2013-07-18', 'mikes', 'mikepass', '1231231231', 'mikes@example.com', '987 Pine St', 0, 4);





-- --------------------------------------------------------

--
-- Table structure for table `ticketType`
--

CREATE TABLE `tickettype` (
  `ID` int(3) NOT NULL PRIMARY KEY,
  `typeName` varchar(255) NOT NULL,
  `price` double(4,2) NOT NULL
);

ALTER TABLE tickettype
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

INSERT INTO tickettype (typeName, price)
VALUES ("adult", 15);
INSERT INTO tickettype (typeName, price)
VALUES ("senior", 6);
INSERT INTO tickettype (typeName, price)
VALUES ("student", 8);
-- --------------------------------------------------------

--
-- Table structure for table `CinemaRoom`
--

CREATE TABLE `cinemaroom` (
  `ID` int(3) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(25) NOT NULL,
  `Capacity` int(3) NOT NULL,
  `State` VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--

INSERT INTO cinemaroom (Name, Capacity, State)
VALUES ("Snowdrop", 150, "Available");

INSERT INTO cinemaroom (Name, Capacity, State)
VALUES ("Amaryllis", 200, "Not Available (Renovation)");

INSERT INTO cinemaroom (Name, Capacity, State)
VALUES ("Alchemilla", 125, "Available");

INSERT INTO cinemaroom (Name, Capacity, State)
VALUES ("Iris", 175, "Available");

INSERT INTO cinemaroom (Name, Capacity, State)
VALUES ("Daisy", 100, "Available");

-- --------------------------------------------------------

--
-- Table structure for table `Movie`
--

CREATE TABLE movie (
  ID int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  Name VARCHAR(255) NOT NULL,
  Description VARCHAR(255) NOT NULL,
  Genre VARCHAR(50) NOT NULL,
  Duration int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 

INSERT INTO movie (Name, Description, Genre, Duration)
VALUES ("James Bond", "abcde", "Action", 150);

INSERT INTO movie (Name, Description, Genre, Duration)
VALUES ("Mission Impossible", "123", "Action", 150);

INSERT INTO movie (Name, Description, Genre, Duration)
VALUES ("Fight Club", "abc", "Thriller", 120);

INSERT INTO movie (Name, Description, Genre, Duration)
VALUES ("John Wick", "456", "Action", 135);

INSERT INTO movie (Name, Description, Genre, Duration)
VALUES ("Conjuring", "000", "Horror", 100);

-- --------------------------------------------------------

--
-- Table structure for table `MovieSession`
--

CREATE TABLE moviesession (
  RoomID int(3) NOT NULL,
  SessionTiming DATETIME NOT NULL,
  MovieID int(5) NOT NULL,
  PRIMARY KEY(RoomID, SessionTiming),
  CONSTRAINT FK_MOVIE_ID FOREIGN KEY (MovieID) REFERENCES Movie(ID),
  CONSTRAINT FK_ROOM_ID FOREIGN KEY (RoomID) REFERENCES CinemaRoom(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 

INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (1, "2023-03-10 12:00:00", 1);

INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (2, "2023-03-10 15:00:00", 1);
--
INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (5, "2023-03-11 14:30:00", 1);

INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (4, "2023-03-11 14:30:00", 2);

INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (5, "2023-03-12 14:30:00", 3);

INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (4, "2023-03-12 14:30:00", 4);

INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (1, "2023-03-25 14:30:00", 5);
--
INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (1, "2023-03-12 11:30:00", 2);

INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (3, "2023-04-15 16:20:00", 3);

INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (3, "2023-06-15 15:20:00", 4);

INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (1, "2023-06-15 15:30:00", 2);

INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (2, "2023-07-01 18:00:00", 1);

INSERT INTO moviesession (RoomID, SessionTiming, MovieID)
VALUES (4, "2023-07-02 21:30:00", 5);





-- DROP TABLE food_items;
-- --------------------------------------------------------
--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bookingID` int(6) NOT NULL PRIMARY KEY,
  `roomID` int(3) NOT NULL,
  `SessionTiming` DATETIME NOT NULL,
  `UID` int(11) NOT NULL,
  `ticketID` int(3) NOT NULL,
  `quantity` int(3) NOT NULL,
  `price` double(5,2) NOT NULL,
  `seating` varchar(50) NOT NULL,
  `book_date` datetime NOT NULL DEFAULT current_timestamp(),
  CONSTRAINT FK_bookMS FOREIGN KEY (roomID,SessionTiming)
	REFERENCES MovieSession(roomID,SessionTiming),
  CONSTRAINT FK_bookUserAcc FOREIGN KEY (UID)
	REFERENCES userAccount(UID),
  CONSTRAINT FK_bookTicType FOREIGN KEY (ticketID)
	REFERENCES tickettype(ID)
);

ALTER TABLE booking
  MODIFY `bookingID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100000;
  
  
INSERT INTO `booking` (`roomID`, `SessionTiming`, `UID`, `ticketID`, `quantity`, `price`,`seating`, `book_date`)
VALUES
(5, '2023-03-11 14:30:00', 14, 100, 2, 30.00,'1,2', '2023-03-11 09:30:00'),
(5, '2023-03-11 14:30:00', 15, 102, 5, 40.00,'3,4,5,6,7', '2023-03-11 10:00:00'),
(5, '2023-03-11 14:30:00', 16, 100, 1, 15.00,'8', '2023-03-11 10:30:00'),
(5, '2023-03-11 14:30:00', 17, 101, 2, 12.00,'9,10', '2023-03-11 11:00:00'),
(5, '2023-03-11 14:30:00', 18, 100, 4, 60.00,'11,12,13,14', '2023-03-11 11:30:00'),
(5, '2023-03-11 14:30:00', 19, 102, 1, 8.00,'15', '2023-03-11 12:30:00'),
(5, '2023-03-11 14:30:00', 20, 101, 2, 12.00,'16,17', '2023-03-11 13:30:00'),

(4, '2023-03-11 14:30:00', 21, 100, 2, 30.00,'1,2', '2023-03-11 09:30:00'),
(4, '2023-03-11 14:30:00', 22, 102, 1, 8.00,'3', '2023-03-11 09:35:00'),
(4, '2023-03-11 14:30:00', 23, 101, 1, 6.00,'4', '2023-03-11 10:30:00'),
(4, '2023-03-11 14:30:00', 24, 100, 2, 30.00,'5,6', '2023-03-11 11:30:00'),
(4, '2023-03-11 14:30:00', 25, 102, 2, 16.00,'7,8', '2023-03-11 11:40:00'),

(5, '2023-03-12 14:30:00', 14, 100, 2, 30.00,'18,19', '2023-03-12 09:30:00'),
(5, '2023-03-12 14:30:00', 15, 102, 5, 40.00,'20,21,22,23,24', '2023-03-12 10:00:00'),
(5, '2023-03-12 14:30:00', 16, 100, 1, 15.00,'25', '2023-03-12 10:30:00'),
(5, '2023-03-12 14:30:00', 17, 101, 2, 12.00,'26,27', '2023-03-12 11:00:00'),
(5, '2023-03-12 14:30:00', 18, 100, 4, 60.00,'28,29,30,31', '2023-03-12 11:30:00'),
(5, '2023-03-12 14:30:00', 19, 102, 1, 8.00,'32', '2023-03-12 12:30:00'),
(5, '2023-03-12 14:30:00', 20, 101, 2, 12.00,'33,34', '2023-03-12 13:30:00'),

(4, '2023-03-12 14:30:00', 21, 100, 2, 30.00,'9,10', '2023-03-12 09:30:00'),
(4, '2023-03-12 14:30:00', 22, 102, 1, 8.00,'11', '2023-03-12 09:35:00'),
(4, '2023-03-12 14:30:00', 23, 101, 1, 6.00,'12', '2023-03-12 10:30:00'),
(4, '2023-03-12 14:30:00', 24, 100, 2, 30.00,'13,14', '2023-03-12 11:30:00'),
(4, '2023-03-12 14:30:00', 25, 102, 2, 16.00,'15,16', '2023-03-12 11:40:00'),

(1, '2023-03-25 14:30:00', 14, 100, 2, 30.00,'1,2', '2023-03-25 09:30:00'),
(1, '2023-03-25 14:30:00', 15, 102, 5, 40.00,'3,4,5,6,7', '2023-03-25 10:00:00'),
(1, '2023-03-25 14:30:00', 16, 100, 1, 15.00,'8', '2023-03-25 10:30:00'),
(1, '2023-03-25 14:30:00', 17, 101, 2, 12.00,'9,10', '2023-03-25 11:00:00'),
(1, '2023-03-25 14:30:00', 18, 100, 4, 60.00,'11,12,13,14', '2023-03-25 11:30:00'),
(1, '2023-03-25 14:30:00', 19, 102, 1, 8.00,'15', '2023-03-25 12:30:00'),
(1, '2023-03-25 14:30:00', 20, 101, 2, 12.00,'16,17', '2023-03-25 13:30:00');




-- --------------------------------------------------------
--
-- Table structure for table `food_items`
--


CREATE TABLE food_items (
  `id` int(4) PRIMARY KEY NOT NULL,
  `name` varchar(50),
  `description` VARCHAR(100),
  `price` DECIMAL(10, 2) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE food_items
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

INSERT INTO food_items (id,name, description, price)
VALUES (0001,"Pop Corn", "Caramel", "12");

INSERT INTO food_items (id,name, description, price)
VALUES (0002,"Nuggets", "Fried", "9.5");

INSERT INTO food_items (id,name, description, price)
VALUES (0003,"Chips", "BBQ", "5.5");

INSERT INTO food_items (id,name, description, price)
VALUES (0004,"Hot Dog", "Cheese", "7.8");

INSERT INTO food_items (id,name, description, price)
VALUES (0005,"Ice Lemon Tea", "Heaven&Earth", "3.6");

  
  -- --------------------------------------------------------
--
-- Table structure for table `food_sales`
--

CREATE TABLE `food_sales` (
  `fsalesID` int(5) NOT NULL PRIMARY KEY,
  `bookingID` int(6) NOT NULL,
  `foodID` int(3) NOT NULL,
  `quantity` int(3) NOT NULL,
  `price` double(5,2)  NOT NULL,
  CONSTRAINT FK_fsalesBooking FOREIGN KEY (bookingID)
	REFERENCES booking(bookingID),
  CONSTRAINT FK_fsales_fItems FOREIGN KEY (foodID)
	REFERENCES food_items(id)
);

ALTER TABLE food_sales
  MODIFY `fsalesID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
  
  
INSERT INTO food_sales (fsalesID, bookingID, foodID, quantity, price) VALUES
  (10000, 100000, 1, 2, 12.00),
  (10001, 100001, 2, 3, 9.50),
  (10002, 100002, 3, 1, 5.50),
  (10003, 100003, 4, 2, 7.80),
  (10004, 100004, 5, 1, 3.60),
  (10005, 100005, 1, 2, 12.00),
  (10006, 100006, 2, 3, 9.50),
  (10007, 100007, 3, 1, 5.50),
  (10008, 100008, 4, 2, 7.80),
  (10009, 100009, 5, 1, 3.60),
  (10010, 100010, 1, 2, 12.00),
  (10011, 100011, 2, 3, 9.50),
  (10012, 100012, 3, 1, 5.50),
  (10013, 100013, 4, 2, 7.80),
  (10014, 100014, 5, 1, 3.60),
  (10015, 100015, 1, 2, 12.00),
  (10016, 100016, 2, 3, 9.50),
  (10017, 100017, 3, 1, 5.50),
  (10018, 100018, 4, 2, 7.80),
  (10019, 100019, 5, 1, 3.60),
  (10020, 100020, 1, 2, 12.00),
  (10021, 100021, 2, 3, 9.50),
  (10022, 100022, 3, 1, 5.50),
  (10023, 100023, 4, 2, 7.80),
  (10024, 100024, 5, 1, 3.60),
  (10025, 100025, 1, 2, 12.00),
  (10026, 100026, 2, 3, 9.50),
  (10027, 100027, 3, 1, 5.50),
  (10028, 100028, 4, 2, 7.80),
  (10029, 100029, 5, 1, 3.60),
  (10030, 100030, 1, 2, 12.00);
  
   -- --------------------------------------------------------
--
-- Table structure for table `ReviewAndRating`
-- 
  
  CREATE TABLE ReviewAndRating (
	ReviewID int(5) PRIMARY KEY NOT NULL,
	Review VARCHAR(255) NOT NULL,
	Rating int(1) NOT NULL, 
	UID int(11) NOT NULL,
	ReviewDate datetime NOT NULL DEFAULT current_timestamp(),
	CONSTRAINT FK_reviewUserAcc FOREIGN KEY (UID)
		REFERENCES userAccount(UID)
);

ALTER TABLE ReviewAndRating
  MODIFY `ReviewID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;
  
INSERT INTO ReviewAndRating(Review,Rating, UID) 
VALUES ("10/10 Will come 2nd time",5,24);

INSERT INTO ReviewAndRating(Review,Rating, UID) 
VALUES ("Impressive movie but still have alot of spaces to improve",1,14);

INSERT INTO ReviewAndRating(Review,Rating, UID) 
VALUES ("I cannot",1,22);