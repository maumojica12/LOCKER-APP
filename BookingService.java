import java.time.LocalDateTime;
import java.util.List;

public class BookingService {

    private final UserDAO userDAO;
    private final BookingDAO bookingDAO;
    private final LockerDAO lockerDAO;

    public BookingService(UserDAO userDAO, BookingDAO bookingDAO, LockerDAO lockerDAO) {
        this.userDAO = userDAO;
        this.bookingDAO = bookingDAO;
        this.lockerDAO = lockerDAO;
    }

    public Booking makeReservation(int userID, int lockerID, double reservationFee) {
    // 1. Verify user exists
    if (userDAO.getUserById(userID) == null) {
        System.out.println("User not found. Cannot make reservation.");
        return null;
    }

    // 2. Verify locker availability
    List<Locker> availableLockers = lockerDAO.getAvailableLocker();
    boolean lockerAvailable = availableLockers.stream()
                                               .anyMatch(l -> l.getLockerID() == lockerID);

    if (!lockerAvailable) {
        System.out.println("Locker not available.");
        return null;
    }

    // 3. Create booking object
    Booking booking = new Booking(userID, lockerID, reservationFee, "Pending Check-in");

    // 4. Add booking to DB (DAO generates bookingReference)
    boolean success = bookingDAO.addBooking(booking);
    if (!success) {
        System.out.println("Failed to create reservation.");
        return null;
    }

    // 5. Update locker status to "Reserved"
    boolean lockerUpdated = lockerDAO.updateLockerStatus(lockerID, "Reserved");
    if (!lockerUpdated) {
        System.out.println("Warning: Failed to update locker status to Reserved.");
    }

    System.out.println("Reservation Confirmed!");
    System.out.println("Booking Reference: " + booking.getBookingReference());
    return booking;
}

    // --- CHECK-IN ---
    public boolean checkIn(String bookingReference) {  // changed to String
        Booking booking = bookingDAO.getBookingByReference(bookingReference); // now correct
        if (booking == null) {
            System.out.println("Booking not found.");
            return false;
        }

        // Update locker status
        lockerDAO.updateLockerStatus(booking.getLockerID(), "Occupied");

        // Update booking
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

    // --- VIEW AVAILABLE LOCKERS ---
    public void viewAvailableLockers() {
        List<Locker> availableLockers = lockerDAO.getAvailableLocker();
        System.out.println("Available Lockers:");
        for (Locker locker : availableLockers) {
            System.out.println(locker);
        }
    }
}