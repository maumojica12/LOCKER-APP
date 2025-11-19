import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

public class BookingService {

    private final UserDAO userDAO;
    private final BookingDAO bookingDAO;
    private final LockerDAO lockerDAO;
    private final CancellationDAO cancellationDAO;

    public BookingService(UserDAO userDAO, BookingDAO bookingDAO, LockerDAO lockerDAO, CancellationDAO cancellationDAO) {
        this.userDAO = userDAO;
        this.bookingDAO = bookingDAO;
        this.lockerDAO = lockerDAO;
        this.cancellationDAO = cancellationDAO;
    }

    public Booking makeReservation(int userID, int lockerID, double reservationFee, LocalDateTime selectedDate) {
        // Verify user exists
        if (userDAO.getUserById(userID) == null) {
            System.out.println("User not found. Cannot make reservation.");
            return null;
        }

        // Verify locker availability
        List<Locker> availableLockers = lockerDAO.getAvailableLocker();
        boolean lockerAvailable = availableLockers.stream()
                                                .anyMatch(l -> l.getLockerID() == lockerID);

        if (!lockerAvailable) {
            System.out.println("Locker not available.");
            return null;
        }

        // Format the selected date into DB string
        String formattedSelectedDate = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Generate booking reference
        String bookingRef = bookingDAO.generateBookingReference();

        // Create booking object
        Booking booking = new Booking(
            bookingRef,                              
            userID,                                    
            lockerID,                                   
            reservationFee,                             
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 
            formattedSelectedDate,                      
            "Pending Check-in",                         
            null,                                   
            null                                      
        );

        //Add booking to DB
        boolean success = bookingDAO.addBooking(booking);
        if (!success) {
            System.out.println("Failed to create reservation.");
            return null;
        }

        // Update locker status to "Reserved"
        boolean lockerUpdated = lockerDAO.updateLockerStatus(lockerID, "Reserved");
        if (!lockerUpdated) {
            System.out.println("Warning: Failed to update locker status to Reserved.");
        }
        System.out.println("Reservation Confirmed!");
        System.out.println("Booking Reference: " + booking.getBookingReference());
        return booking;
}

    // Check-In
    public boolean checkIn(String bookingReference) {  
        Booking booking = bookingDAO.getBookingByReference(bookingReference); 
        if (booking == null) {
            System.out.println("Booking not found.");
            return false;
        }

        // Update locker status
        lockerDAO.updateLockerStatus(booking.getLockerID(), "Occupied");

        // Update booking status
        booking.setBookingStatus("Checked-in");
        booking.setCheckInTime(LocalDateTime.now().toString());

        boolean updated = bookingDAO.updateBooking(booking);
        if (updated) {
            System.out.println("Check-in successful for booking reference: " + bookingReference);
            return true;
        } else {
            System.out.println("Failed to check-in.");
            return false;
        }
    }

    // VIEW AVAILABLE LOCKERS 
    public void viewAvailableLockers() {
        List<Locker> availableLockers = lockerDAO.getAvailableLocker();
        System.out.println("Available Lockers:");
        for (Locker locker : availableLockers) {
            System.out.println(locker);
        }
    }

    // Automatic handling of no-shows on or before selected reservation
    public void processNoShowBookings() {
        // Formatter for your DB date format
        DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Booking> pendingBookings = bookingDAO.getPendingCheckInBookings();
        LocalDateTime now = LocalDateTime.now();

        for (Booking booking : pendingBookings) {
            LocalDateTime selectedTime = LocalDateTime.parse(booking.getSelectedReservationDate(), dbFormatter);

            if (selectedTime.isBefore(now)) { 
                lockerDAO.updateLockerStatus(booking.getLockerID(), "Available");
                booking.setBookingStatus("Cancelled");
                bookingDAO.updateBookingStatus(booking.getBookingReference(), "Cancelled");

                // Cancellation time (1 second after reservation)
                LocalDateTime cancelDate = selectedTime.plusSeconds(1);

                // Add cancellation record
                cancellationDAO.addCancellation(
                    booking.getBookingReference(),
                    cancelDate.format(dbFormatter),
                    "Customer failed to check-in on Reserved date and time",
                    0
                );
                System.out.println("No-show processed for booking: " + booking.getBookingReference());
            }
        }
    }
}
