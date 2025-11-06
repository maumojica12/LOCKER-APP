CREATE DATABASE IF NOT EXISTS luggage_locker_db;

USE luggage_locker_db;

-- CORE RECORDS
-- 1. USER TABLE
CREATE TABLE User(
		userID INT AUTO_INCREMENT PRIMARY KEY,
		firstName VARCHAR(50) NOT NULL,
		lastName VARCHAR(50) NOT NULL,
		userContact VARCHAR(20),
		userEmail VARCHAR(100)	
);

-- 2. LOCKERTYPE TABLE
CREATE TABLE LockerType(
		lockerTypeID INT AUTO_INCREMENT PRIMARY KEY,
		lockerTypeSize ENUM('Small','Medium','Large'),
		lockerMaxWeight DECIMAL(6,2),
		lockerRate DECIMAL(10,2)
);

-- 3. LOCATION TABLE
CREATE TABLE Location(
		locationID INT, 
		locationName VARCHAR(100),
		locationCity VARCHAR(100),
		locationProvince VARCHAR(100),
		locationPostalCode VARCHAR(10) PRIMARY KEY, 
		contact VARCHAR(50)
);

-- 4. LOCKER TABLE
CREATE TABLE Locker(
		lockerID INT AUTO_INCREMENT PRIMARY KEY,
		lockerLocation VARCHAR(100),
		lockerTypeID INT NOT NULL,
		lockerStatus ENUM('Available', 'Occupied') DEFAULT 'Available',
		FOREIGN KEY (lockerTypeID) REFERENCES LockerType(lockerTypeID)
);

-- TRANSACTION RECORD
-- 1 RESERVATION & CHECK-IN TABLE
CREATE TABLE Reservation_CheckIn(
		bookingReference INT AUTO_INCREMENT PRIMARY KEY,
		userID INT NOT NULL,
		lockerID INT NOT NULL,
		reservationFee DECIMAL(10,2),
		reservationDate DATETIME DEFAULT NOW(),
		bookingStatus ENUM('Pending','Active','Completed', 'Cancelled') DEFAULT 'Pending',
        checkInTime DATETIME,
        checkOutTime DATETIME,
		FOREIGN KEY (userID) REFERENCES User(userID),
		FOREIGN KEY (lockerID) REFERENCES Locker(lockerID)
);

-- 2. CANCELLATION TABLE
CREATE TABLE Cancellation(
		cancellationID INT AUTO_INCREMENT PRIMARY KEY,
		bookingID INT NOT NULL,
		cancelDate DATETIME DEFAULT NOW(),
		reason VARCHAR(255),
		refundFee DECIMAL(10,2),
		FOREIGN KEY (bookingID) REFERENCES Booking(bookingID)
);

-- 3. PAYMENT TABLE
CREATE TABLE Payment(
		paymentID INT AUTO_INCREMENT PRIMARY KEY,
		bookingID INT NOT NULL,
		userID INT NOT NULL,
		paymentAmount DECIMAL(10,2),
		paymentMethod ENUM('Credit Card', 'E-wallet') DEFAULT 'E-wallet',
		paymentStatus ENUM('Paid', 'Not Paid') DEFAULT 'Not Paid',
		paymentDate DATETIME DEFAULT NOW(),
		FOREIGN KEY (bookingID) REFERENCES Booking(bookingID),
		FOREIGN KEY (userID) REFERENCES User(userID)
);

-- 4. LOCKER TRANSFER TABLE
CREATE TABLE LockerTransfer(
		transferID INT AUTO_INCREMENT PRIMARY KEY,
		bookingID INT NOT NULL,
		oldLockerID INT NOT NULL,
		newLockerID INT NOT NULL,
		transferDate DATETIME DEFAULT NOW(),
		adjustmentAmount DECIMAL(10,2),
		FOREIGN KEY (bookingID) REFERENCES Booking(bookingID),
		FOREIGN KEY (oldLockerID) REFERENCES Locker(lockerID),
		FOREIGN KEY (newLockerID) REFERENCES Locker(lockerID)
);



-- TEST INPUTS
-- USER
INSERT INTO User (firstName, lastName, userContact, userEmail) VALUES
('Carl', 'Crespo', '09171234567', 'carl_crespo@gmail.com'),
('Maurienne', 'Mojica', '09173456789', 'maurienne_mojica@gmail.com'),
('Clarisse', 'Nazario', '09175551234', 'clarisse_nazario@gmail.com'),
('Venice', 'Plurad', '09176667890', 'venice_plurad@gmail.com');

-- LOCKERTYPE
INSERT INTO LockerType (lockerTypeSize, lockerMaxWeight, lockerRate) VALUES
('Small', 5.00, 80.00),
('Medium', 15.00, 120.00),
('Large', 25.00, 180.00);

-- LOCATION
INSERT INTO Location (locationName, locationCity, locationProvince, locationPostalCode, contact) VALUES
('DLSU Manila', 'Manila', 'Metro Manila', '1004', '09170001111'),
('DLSU Laguna', 'Biñan', 'Laguna', '4024', '09170002222');

-- LOCKER
INSERT INTO Locker (lockerLocation, lockerTypeID, lockerStatus) VALUES
('DLSU Manila', 1, 'Available'),
('DLSU Manila', 2, 'Available'),
('DLSU Laguna', 3, 'Available');

-- RESERVATION
INSERT INTO Reservation (userID, lockerID, reservationFee, reservationDate, bookingStatus) VALUES
(1, 1, 80.00, NOW(), 'Pending Check-in'),
(2, 2, 120.00, NOW(), 'Pending Check-in');

-- BOOKING
INSERT INTO Booking (userID, lockerID, bookingRef, startTime, endTime, bookingStatus) VALUES
(1, 1, 'BKG-0001', NOW(), DATE_ADD(NOW(), INTERVAL 4 HOUR), 'Active'),
(2, 2, 'BKG-0002', NOW(), DATE_ADD(NOW(), INTERVAL 6 HOUR), 'Pending');

-- CANCELLATION
INSERT INTO Cancellation (bookingID, cancelDate, reason, refundFee) VALUES
(2, NOW(), 'User cancelled before check-in', 50.00);

-- PAYMENT
INSERT INTO Payment (bookingID, userID, paymentAmount, paymentMethod, paymentStatus, paymentDate) VALUES
(1, 1, 80.00, 'E-wallet', 'Paid', NOW()),
(2, 2, 120.00, 'Credit Card', 'Not Paid', NOW());

-- LOCKER TRANSFER
INSERT INTO LockerTransfer (bookingID, oldLockerID, newLockerID, transferDate, adjustmentAmount) VALUES
(1, 1, 2, NOW(), 40.00);

SELECT * FROM User;
SELECT * FROM LockerType;
SELECT * FROM Location;
SELECT * FROM Locker;
SELECT * FROM Reservation;
SELECT * FROM Booking;
SELECT * FROM Payment;
SELECT * FROM Cancellation;
SELECT * FROM LockerTransfer;