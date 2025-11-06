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
                    System.out.println("Locker Location Management\n");
                    break;
                case 5:
                    System.out.println("Booking/Reservation Management\n");
                    break;
                case 6:
                    System.out.println("Cancellation Management\n");
                    break;
                case 7:
                    System.out.println("Payment Management\n");
                    break;
                case 8:
                    System.out.println("Locker Transfer Management\n");
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


