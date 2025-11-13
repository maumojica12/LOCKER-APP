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
		locationID INT AUTO_INCREMENT PRIMARY KEY,
		locationName VARCHAR(100) NOT NULL,
		locationCity VARCHAR(100),
		locationPostalCode VARCHAR(10),
		contact VARCHAR(50)
);

-- 4. LOCKER TABLE
CREATE TABLE Locker(
		lockerID INT AUTO_INCREMENT PRIMARY KEY,
		lockerTypeID INT NOT NULL,
		locationID INT NOT NULL,
		locationPostalCode VARCHAR(10),
		lockerStatus ENUM('Available','Occupied') DEFAULT 'Available',
		FOREIGN KEY (lockerTypeID) REFERENCES LockerType(lockerTypeID),
		FOREIGN KEY (locationID) REFERENCES Location(locationID)
);



-- TRANSACTION RECORD
-- 1. BOOKING TABLE
CREATE TABLE Booking(
		bookingReference VARCHAR(20) PRIMARY KEY,
		userID INT NOT NULL,
		lockerID INT NOT NULL,
		reservationFee DECIMAL(10,2),
		reservationDate DATETIME DEFAULT NOW(),
		bookingStatus ENUM('Pending Check-in','Checked-In','Checked-Out','Cancelled') DEFAULT 'Pending Check-in',
		checkInTime DATETIME,
		checkOutTime DATETIME,
		FOREIGN KEY (userID) REFERENCES User(userID),
		FOREIGN KEY (lockerID) REFERENCES Locker(lockerID)
);

-- 2. CANCELLATION TABLE
CREATE TABLE Cancellation(
		cancellationID INT AUTO_INCREMENT PRIMARY KEY,
		bookingReference VARCHAR(20),
		cancelDate DATETIME DEFAULT NOW(),
		reason VARCHAR(255),
		refundFee DECIMAL(10,2),
		FOREIGN KEY (bookingReference) REFERENCES Booking(bookingReference)
);

-- 3. PAYMENT TABLE
CREATE TABLE Payment(
		paymentID INT AUTO_INCREMENT PRIMARY KEY,
		bookingReference VARCHAR(20),
		userID INT NOT NULL,
		paymentAmount DECIMAL(10,2),
		paymentMethod ENUM('Credit Card', 'E-wallet'),
		paymentStatus ENUM('Paid', 'Not Paid') DEFAULT 'Not Paid',
		paymentDate DATETIME DEFAULT NOW(),
		FOREIGN KEY (bookingReference) REFERENCES Booking(bookingReference),
		FOREIGN KEY (userID) REFERENCES User(userID)
);

-- 4. LOCKER TRANSFER TABLE
CREATE TABLE LockerTransfer(
		transferID INT AUTO_INCREMENT PRIMARY KEY,
		bookingReference VARCHAR(20),
		transferDate DATETIME DEFAULT NOW(),
		adjustmentAmount DECIMAL(10,2),
		oldLockerID INT NOT NULL,
		newLockerID INT,
		FOREIGN KEY (bookingReference) REFERENCES Booking(bookingReference),
		FOREIGN KEY (oldLockerID) REFERENCES Locker(lockerID)
);



-- REPORTS
-- 1. LOCKER OCCUPANCY REPORT
SELECT L.lockerID AS 'Locker ID', LT.lockerTypeSize AS 'Locker Size', LOC.locationName AS 'Locker Location',
		COUNT(B.bookingReference) AS 'Total Bookings'
FROM Locker L JOIN LockerType LT ON L.lockerTypeID = LT.lockerTypeID
				JOIN Location LOC ON L.locationID = LOC.locationID
				JOIN Booking B ON L.lockerID = B.lockerID
WHERE MONTH(B.reservationDate) = MONTH(NOW())
AND YEAR(B.reservationDate) = YEAR(NOW())
GROUP BY L.lockerID, LT.lockerTypeSize, LOC.locationName
ORDER BY LOC.locationName, L.lockerID;

-- 2. CANCELED BOOKINGS REPORT
SELECT C.cancellationID AS 'Cancellation ID', B.bookingReference AS 'Booking Reference', CONCAT(U.firstName, ' ', U.lastName) AS 'User',
		LT.lockerTypeSize AS 'Locker Size', LOC.locationName AS 'Locker Location', C.cancelDate AS 'Cancelled Date', C.reason AS 'Reason', C.refundFee AS 'Refund Amount'
FROM Cancellation C JOIN Booking B ON C.bookingReference = B.bookingReference
					JOIN User U ON B.userID = U.userID
					JOIN Locker L ON B.lockerID = L.lockerID
					JOIN LockerType LT ON L.lockerTypeID = LT.lockerTypeID
					JOIN Location LOC ON L.locationID = LOC.locationID
WHERE MONTH(C.cancelDate) = MONTH(NOW())
AND YEAR(C.cancelDate) = YEAR(NOW())
ORDER BY C.cancelDate DESC;

-- 3. PAYMENT TRANSACTION REPORT
SELECT P.paymentID AS 'Payment ID', B.bookingReference AS 'Booking Reference', CONCAT(U.firstName, ' ', U.lastName) AS 'User',
		P.paymentAmount AS 'Amount Paid', P.paymentMethod AS 'Method', P.paymentStatus AS 'Status', P.paymentDate AS 'Payment Date'
FROM Payment P JOIN Booking B ON P.bookingReference = B.bookingReference
				JOIN User U ON P.userID = U.userID
WHERE MONTH(P.paymentDate) = MONTH(NOW())
AND YEAR(P.paymentDate) = YEAR(NOW())
ORDER BY P.paymentDate DESC;

-- 4. REVENUE REPORT
-- 4.1 LOCKERTYPE REPORT
SELECT LT.lockerTypeSize AS 'Locker Size', SUM(P.paymentAmount) AS 'Total Revenue'
FROM Payment P JOIN Booking B ON P.bookingReference = B.bookingReference
				JOIN Locker L ON B.lockerID = L.lockerID
				JOIN LockerType LT ON L.lockerTypeID = LT.lockerTypeID
WHERE P.paymentStatus = 'Paid'
AND MONTH(P.paymentDate) = MONTH(NOW())
AND YEAR(P.paymentDate) = YEAR(NOW())
GROUP BY LT.lockerTypeSize
ORDER BY LT.lockerTypeSize;

-- 4.2 LOCATION REPORT
SELECT LOC.locationName AS 'Locker Location', SUM(P.paymentAmount) AS 'Total Revenue'
FROM Payment P JOIN Booking B ON P.bookingReference = B.bookingReference
				JOIN Locker L ON B.lockerID = L.lockerID
				JOIN Location LOC ON L.locationID = LOC.locationID
WHERE P.paymentStatus = 'Paid'
AND MONTH(P.paymentDate) = MONTH(NOW())
AND YEAR(P.paymentDate) = YEAR(NOW())
GROUP BY LOC.locationName
ORDER BY LOC.locationName;


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
INSERT INTO Location (locationName, locationCity, locationPostalCode, contact) VALUES
('DLSU Manila', 'Manila', '1004', '09170001111'),
('DLSU Laguna', 'Biñan', '4024', '09170002222');

-- LOCKER
INSERT INTO Locker (lockerTypeID, locationID, locationPostalCode, lockerStatus) VALUES
(1, 1, '1004', 'Available'),  
(2, 1, '1004', 'Available'),  
(3, 2, '4024', 'Available');

-- BOOKING
INSERT INTO Booking (bookingReference, userID, lockerID, reservationFee, reservationDate, bookingStatus, checkInTime, checkOutTime) VALUES
('BKG-0001', 1, 1, 80.00, NOW(), 'Checked-In', NOW(), DATE_ADD(NOW(), INTERVAL 4 HOUR)),
('BKG-0002', 2, 2, 120.00, NOW(), 'Pending Check-in', NULL, NULL),
('BKG-0003', 3, 3, 180.00, NOW(), 'Cancelled', NULL, NULL);

-- PAYMENT
INSERT INTO Payment (bookingReference, userID, paymentAmount, paymentMethod, paymentStatus, paymentDate) VALUES
('BKG-0001', 1, 80.00, 'E-wallet', 'Paid', NOW()),
('BKG-0002', 2, 120.00, 'Credit Card', 'Not Paid', NOW());

-- CANCELLATION
INSERT INTO Cancellation (bookingReference, cancelDate, reason, refundFee) VALUES
('BKG-0003', NOW(), 'User cancelled before check-in', 100.00);

-- LOCKER TRANSFER
INSERT INTO LockerTransfer (bookingReference, transferDate, adjustmentAmount, oldLockerID, newLockerID) VALUES
('BKG-0001', NOW(), 40.00, 1, 2);

SELECT * FROM User;
SELECT * FROM LockerType;
SELECT * FROM Location;
SELECT * FROM Locker;
SELECT * FROM Reservation;
SELECT * FROM Booking;
SELECT * FROM Payment;
SELECT * FROM Cancellation;
SELECT * FROM LockerTransfer;

