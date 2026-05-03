import java.io.*;
import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isBooked;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }
}

class Booking {
    String name;
    int roomNumber;
    String category;

    Booking(String name, int roomNumber, String category) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.category = category;
    }
}

public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "bookings.txt";

    public static void main(String[] args) {

        // Initialize rooms
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));

        int choice;

        do {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelBooking();
                    break;
            }

        } while (choice != 4);
    }

    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            if (!r.isBooked) {
                System.out.println("Room " + r.roomNumber + " (" + r.category + ")");
            }
        }
    }

    static void bookRoom() {
        sc.nextLine();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        viewRooms();
        System.out.print("Enter room number to book: ");
        int roomNo = sc.nextInt();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo && !r.isBooked) {
                r.isBooked = true;

                // Simulate payment
                System.out.println("Processing payment...");
                System.out.println("Payment successful!");

                saveBooking(new Booking(name, roomNo, r.category));

                System.out.println("Room booked successfully!");
                return;
            }
        }

        System.out.println("Room not available!");
    }

    static void cancelBooking() {
        System.out.print("Enter room number to cancel: ");
        int roomNo = sc.nextInt();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo && r.isBooked) {
                r.isBooked = false;
                removeBooking(roomNo);
                System.out.println("Booking cancelled.");
                return;
            }
        }

        System.out.println("No booking found!");
    }

    static void saveBooking(Booking b) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(b.name + "," + b.roomNumber + "," + b.category + "\n");
        } catch (IOException e) {
            System.out.println("Error saving booking.");
        }
    }

    static void removeBooking(int roomNo) {
        try {
            File file = new File(FILE_NAME);
            File temp = new File("temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("," + roomNo + ",")) {
                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            file.delete();
            temp.renameTo(file);

        } catch (IOException e) {
            System.out.println("Error cancelling booking.");
        }
    }
}