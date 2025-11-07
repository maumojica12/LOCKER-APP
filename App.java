import java.util.Scanner;

public class App {

    public static void UserManagementMenu(){
        int choice;

        do{
            System.out.println("\nUser Management");
            System.out.println("[1] Add User");
            System.out.println("[2] View All Users");
            System.out.println("[3] Search User by ID or Name");
            System.out.println("[0] Return to Main Menu");
            System.out.print("Select an option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Add User Selected\n");
                    break;
                case 2:
                    System.out.println("View All Users Selected\n");
                    break;
                case 3:
                    System.out.println("Search User Selected\n");
                    break;
                case 0:
                    System.out.println("Returning to Main Menu\n");
                    break;
                default:
                    System.out.println("Invalid Input. Try again.");
            }
        }while(choice != 0);
    }

    public static void LockerTypeManagementMenu(){
        int choice;

        do{
            System.out.println("\nLocker Type Management");
            System.out.println("[1] View All Locker Types");
            System.out.println("[2] Search Locker Size or Weight");
            System.out.println("[0] Return to Main Menu");
            System.out.print("Select an option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();


            switch(choice){
                case 1:
                    System.out.println("View All Locker Types Selected\n");
                    break;
                case 2:
                    System.out.println("Search Locker Type Selected\n");
                    break;
                case 0:
                    System.out.println("Returning to Main Menu\n");
                    break;
                default:
                    System.out.println("Invalid Input. Try again.");
            }
        }while(choice != 0);
    }

    public static void LockerManagementMenu(){
        int choice;

        /* (lockerID, lockerLocation, lockerTypeID, lockerStatus) */
        do{
            System.out.println("\nLocker Management");
            System.out.println("[1] View All Lockers");
            System.out.println("[2] View All Available Lockers");
            System.out.println("[3] View all Occupied Lockers");
            System.out.println("[0] Return to Main Menu");
            System.out.print("Select an option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("View All Lockers\n");
                    break;
                case 2:
                    System.out.println("View All Available Lockers\n");
                    break;
                case 3:
                    System.out.println("View all Occupied Lockers\n");
                    break;
                case 0:
                    System.out.println("Returning to Main Menu\n");
                    break;
                default:
                    System.out.println("Invalid Input. Try again.");
            }
        }while(choice != 0);
    }  


    
    public static void LocationManagementMenu(){
        int choice;

        /* (lockerID, lockerLocation, lockerTypeID, lockerStatus) */
        do{
            System.out.println("\nLocker Location Management");
            System.out.println("[1] View All Locker Locations");
            System.out.println("[2] View All Available Lockers in Location");
            System.out.println("[0] Return to Main Menu");
            System.out.print("Select an option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("View All Lockers\n");
                    break;
                case 2:
                    System.out.println("View All Available Lockers in Location\n");
                    break;
                case 0:
                    System.out.println("Returning to Main Menu\n");
                    break;
                default:
                    System.out.println("Invalid Input. Try again.");
            }
        }while(choice != 0);
    }

    public static void BookingManagementMenu(){
        int choice;

        do{
            System.out.println("\nBooking Management");
            System.out.println("[1] Make a Reservation");
            System.out.println("[2] Book and Confirm Reservation");
            System.out.println("[0] Return to Main Menu");
            System.out.print("Select an option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Make a Reservation\n");
                    break;
                case 2:
                    System.out.println("Book and Confirm Reservation\n");
                    break;
                case 0:
                    System.out.println("Returning to Main Menu\n");
                    break;
                default:
                    System.out.println("Invalid Input. Try again.");
            }
        }while(choice != 0);
    }

    public static void CancellationManagementMenu(){
        int choice;

        do{
            System.out.println("\nCancellation Management");
            System.out.println("[1] Cancel a Reservation");
            System.out.println("[2] View All Cancellations");
            System.out.println("[0] Return to Main Menu");
            System.out.print("Select an option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Cancel a Reservation\n");
                    break;
                case 2:
                    System.out.println("View All Cancellations\n");
                    break;
                case 0:
                    System.out.println("Returning to Main Menu\n");
                    break;
                default:
                    System.out.println("Invalid Input. Try again.");
            }
        }while(choice != 0);
    }

    public static void ManagePayments(){
        int choice;

        do{
            System.out.println("\nPayment Management");
            System.out.println("[1] View All Payments");
            System.out.println("[2] Search Payment by ID");
            System.out.println("[0] Return to Main Menu");
            System.out.print("Select an option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("View All Payments\n");
                    break;
                case 2:
                    System.out.println("Search Payment by ID\n");
                    break;
                case 0:
                    System.out.println("Returning to Main Menu\n");
                    break;
                default:
                    System.out.println("Invalid Input. Try again.");
            }
        }while(choice != 0);
    }

    public static void LockerTransferManagementMenu(){
        int choice;

        do{
            System.out.println("\nLocker Transfer Management");
            System.out.println("[1] View All Transfers");
            System.out.println("[2] Search Transfer by ID");
            System.out.println("[0] Return to Main Menu");
            System.out.print("Select an option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("View All Transfers\n");
                    break;
                case 2:
                    System.out.println("Search Transfer by ID\n");
                    break;
                case 0:
                    System.out.println("Returning to Main Menu\n");
                    break;
                default:
                    System.out.println("Invalid Input. Try again.");
            }
        }while(choice != 0);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
     
            System.out.println("LUGGAGE LOCKERS BOOKING SYSTEM");
            System.out.println("[1] Manage Users");
            System.out.println("[2] Manage Locker Type");
            System.out.println("[3] Manage Locker");
            System.out.println("[4] Manage Locker Locations");
            System.out.println("[5] Book/Manage Reservations");
            System.out.println("[6] Manage Cancellations");
            System.out.println("[7] Manage Payments");
            System.out.println("[8] Manage Locker Transfers");
            System.out.println("[0] Exit");
            System.out.print("Select an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // user management menu
                    UserManagementMenu();
                    break;

                case 2:
                    LockerTypeManagementMenu();
                    break;

                case 3:
                    LockerManagementMenu();
                    break;

                case 4:
                    LocationManagementMenu();
                    break;
                case 5:
                    BookingManagementMenu();
                    break;
                case 6:
                    CancellationManagementMenu();
                    break;
                case 7:
                    ManagePayments();
                    break;
                case 8:
                    LockerTransferManagementMenu();
                    break;
          
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid Input. Try again.");
            }
        } while (choice != 0);
        sc.close();
    }
}


