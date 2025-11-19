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
		lockerStatus ENUM('Available','Reserved','Occupied') DEFAULT 'Available',
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
        selectedReservationDate DATETIME NOT NULL,
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
-- 1. OCCUPANCY REPORTS
-- 1.1 LOCKER SIZE REPORT
SELECT LT.lockerTypeSize AS 'Locker Size',
       COUNT(B.bookingReference) AS 'Total Bookings'
FROM Locker L
JOIN LockerType LT ON L.lockerTypeID = LT.lockerTypeID
LEFT JOIN Booking B ON L.lockerID = B.lockerID AND YEAR(B.reservationDate) = 2025
GROUP BY LT.lockerTypeSize
ORDER BY LT.lockerTypeSize;

-- 1.2 LOCATION REPORT
SELECT LOC.locationName AS 'Locker Location',
       COUNT(B.bookingReference) AS 'Total Bookings'
FROM Locker L
JOIN Location LOC ON L.locationID = LOC.locationID
LEFT JOIN Booking B ON L.lockerID = B.lockerID AND YEAR(B.reservationDate) = 2025
GROUP BY LOC.locationName
ORDER BY LOC.locationName;

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
AND YEAR(P.paymentDate) = YEAR(NOW())
GROUP BY LT.lockerTypeSize
ORDER BY LT.lockerTypeSize;

-- 4.2 LOCATION REPORT
SELECT LOC.locationName AS 'Locker Location', SUM(P.paymentAmount) AS 'Total Revenue'
FROM Payment P JOIN Booking B ON P.bookingReference = B.bookingReference
				JOIN Locker L ON B.lockerID = L.lockerID
				JOIN Location LOC ON L.locationID = LOC.locationID
WHERE P.paymentStatus = 'Paid'
AND YEAR(P.paymentDate) = YEAR(NOW())
GROUP BY LOC.locationName
ORDER BY LOC.locationName;

-- Users
INSERT INTO User (firstName, lastName, userContact, userEmail) VALUES
('Carl', 'Crespo', '09171234567', 'carl.crespo@gmail.com'),
('Maurienne', 'Mojica', '09173456789', 'maurienne.mojica@gmail.com'),
('Clarisse', 'Nazario', '09175551234', 'clarisse.nazario@gmail.com'),
('Venice', 'Plurad', '09176667890', 'venice.plurad@gmail.com'),
('Robin', 'Dela Cruz', '09178889900', 'robin.delacruz@gmail.com'),
('Francheska', 'De Guzman', '09170011223', 'francheska.deguzman@gmail.com'),
('Daniel', 'Pamintuan', '09172233445', 'daniel.pamintuan@gmail.com'),
('Jerone', 'Juarez', '09173344556', 'jerone.juarez@gmail.com'),
('John', 'Doe', '09174455667', 'john.doe@gmail.com'),
('Jane', 'Doe', '09175566778', 'jane.doe@gmail.com'),
('Frank', 'Gallagher', '09453224096', 'frank.gal@gmail.com');

-- LockerType
INSERT INTO LockerType (lockerTypeSize, lockerMaxWeight, lockerRate) VALUES
('Small', 5.00, 80.00),
('Medium', 15.00, 120.00),
('Large', 25.00, 180.00);

-- Location
INSERT INTO Location (locationName, locationCity, locationPostalCode, contact) VALUES
('DLSU Manila', 'Manila', '1004', '09170001111'),
('DLSU Laguna', 'Biñan', '4024', '09170002222');

-- Locker DONE
INSERT INTO Locker (lockerTypeID, locationID, lockerStatus) VALUES
(1, 1,'Reserved'),
(1, 1, 'Reserved'),
(1, 1, 'Occupied'),
(2, 1, 'Occupied'),
(2, 1, 'Occupied'),
(2, 2, 'Available'),
(3, 2, 'Available'),
(3, 2, 'Available'),
(3, 2, 'Available'),
(3, 2, 'Available'),
(1, 1, 'Available');

#DONE
INSERT INTO Booking
(bookingReference, userID, lockerID, reservationFee, reservationDate, selectedReservationDate, bookingStatus, checkInTime, checkOutTime)
VALUES
('BKG-0001', 1, 1, 80.00,  '2025-11-25 11:10:26', '2025-11-27 16:15:26', 'Pending Check-in', NULL, NULL),
('BKG-0002', 2, 2, 80.00, '2025-11-25 12:05:30', '2025-11-27 14:30:00', 'Pending Check-in', NULL, NULL),
('BKG-0003', 3, 3, 80.00, '2025-11-25 12:10:00', '2025-11-25 15:00:00', 'Checked-In', '2025-11-25 14:10:00', NULL),
('BKG-0004', 4, 4, 120.00,  '2025-11-25 12:15:16', '2025-11-25 15:10:16', 'Checked-In', '2025-11-25 14:15:16', NULL),
('BKG-0005', 5, 5, 120.00, '2025-11-25 12:20:00', '2025-11-25 15:15:00', 'Checked-In', '2025-11-25 14:25:00', NULL),
('BKG-0006', 6, 6, 120.00, '2025-11-25 12:23:00', '2025-11-25 14:15:00', 'Checked-Out', '2025-11-25 13:30:00', '2025-11-25 20:30:00'),
('BKG-0007', 7, 7, 180.00, '2025-11-25 12:25:00', '2025-11-25 14:35:00', 'Checked-Out', '2025-11-25 13:40:00', '2025-11-25 19:30:00'),
('BKG-0008', 8, 8, 180.00, '2025-11-25 12:30:49', '2025-11-26 10:00:49', 'Cancelled', NULL, NULL),
('BKG-0009', 9, 9, 180.00,  '2025-11-25 12:35:26', '2025-11-25 15:15:26', 'Cancelled', NULL, NULL),
('BKG-0010', 10, 10, 180.00, '2025-11-25 12:40:00', '2025-11-26 19:30:00', 'Cancelled', NULL, NULL),
('BKG-0011', 11, 11, 80.00, '2025-11-25 12:45:00', '2025-11-25 14:30:00', 'Checked-Out', '2025-11-25 13:40:00', '2025-11-25 21:40:00');

-- Payment
INSERT INTO Payment (paymentID, bookingReference, userID, paymentAmount, paymentMethod, paymentStatus, paymentDate) VALUES
(1, 'BKG-0006',6, 120.0,  'E-wallet', 'Paid', '2025-11-25 20:30:00'),
(2, 'BKG-0007',7, 180.0, 'Credit Card', 'Paid', '2025-11-25 19:30:00'),
(3, 'BKG-0011',11, 80.0, 'Credit Card', 'Paid', '2025-11-25 21:40:00');

-- Cancellation DONE
INSERT INTO Cancellation (bookingReference, cancelDate, reason, refundFee) VALUES
('BKG-0008', '2025-11-25 15:30:49', 'User cancelled before check-in', 100.00),
('BKG-0009', NOW(), 'Customer failed to check-in on Reserved date and time', 0.00),
('BKG-0010', '2025-11-25 18:30:49', 'User cancelled before check-in', 100.00);

SELECT * FROM User;
SELECT * FROM LockerType;
SELECT * FROM Location;
SELECT * FROM Locker;
SELECT * FROM Booking;
SELECT * FROM Payment;
SELECT * FROM Cancellation;
SELECT * FROM LockerTransfer;