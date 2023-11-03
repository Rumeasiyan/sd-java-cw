import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Theatre {
    static ArrayList<Ticket> tickets = new ArrayList<>(); //Array list to store tickets information
    static ArrayList<Ticket> sorted_tickets = new ArrayList<>(); //Array list to store tickets information after it was sorted
    static int[] rowOne = new int[12]; //first row of the theatre, with 12 seats

    static int[] rowTwo = new int[16]; //second row of the theatre with 16 seats

    static int[] rowThree = new int[20]; //third row of the theatre with 20 seats

// main method
    public static void main(String[] args) {
        // 0 in the row arrays represents the available seats in that row
        // in the beginning no seats were booked. so all arrays are filled with 0
        Arrays.fill(rowOne, 0); //adding all elements as 0
        Arrays.fill(rowTwo, 0); //adding all elements as 0
        Arrays.fill(rowThree, 0); //adding all elements as 0

        Scanner input = new Scanner(System.in); //creates input to get user input

        while (true) {
            System.out.println("\nWelcome to the New Theatre"); //displays welcome message


            int menuOption; //creating an integer variable to store and use the user's menu selection

            while (true) {
                //printing the menu screen
                System.out.println("-------------------------------------------------");
                System.out.println("Please select an option: ");
                System.out.println("1) Buy a ticket");
                System.out.println("2) Print seating area");
                System.out.println("3) Cancel ticket");
                System.out.println("4) List available seats");
                System.out.println("5) Save to file");
                System.out.println("6) Load from file");
                System.out.println("7) Print ticket information and total price");
                System.out.println("8) Sort tickets by price");
                System.out.println("\t0) Quit");
                System.out.println("-------------------------------------------------");
                System.out.print("Enter option: ");

                //to validate the user input. we are using try catch.
                //the user is only required to give integers within 0 to 8.
                //otherwise it'll show an error message
                try {
                    menuOption = input.nextInt(); //getting user input for menu selection

                    //checking is the input is within 0 to 8
                    if (menuOption <= 8 && menuOption >= 0) {
                        break; //if the input is within 0 to 8. the loop for user input will break
                    } else {
                        System.out.println("Invalid option. please try again."); //error message if the input is not within the range
                    }
                //catching error
                } catch (Exception e) {
                    System.out.println("Invalid option. please try again."); //error message
                    input.nextLine(); //To avoid the code to use the initial input. otherwise it'll cause a loop
                }
            }

            // selection menu.
            if (menuOption == 1) {
                buy_ticket(input); //buy a ticket
            } else if (menuOption == 2) {
                print_seating_area(); //print seating area
            } else if (menuOption == 3) {
                cancel_ticket(input); //cancel ticket
            } else if (menuOption == 4) {
                show_available(); //list available seats
            } else if (menuOption == 5) {
                save(); //save to file
            } else if (menuOption == 6) {
                load(); //load from file
            } else if (menuOption == 7) {
                show_tickets_info(); //print the ticket information and total price
            } else if (menuOption == 8) {
                sorted_tickets = sort_tickets(0,tickets.size()-1); //sort the tickets by price
                tickets.clear(); //clearing the existing not sorted ticket array list to add sorted tickets
                tickets.addAll(sorted_tickets); //adding all the elements in sorted_tickets to ticket arraylist
                show_tickets_info(); //printing the tickets information
            } else { //here we don't need to use menuOption == 0, since we already validated the input.
                System.out.println("Program is exiting..."); //exit message
                break; //it'll exit the program
            }
        }
    }

//buy a ticket method
    public static void buy_ticket(Scanner input) {

        System.out.print("Enter your name: ");
        String name = input.next(); //getting buyer name
        System.out.print("Enter your surname: ");
        String surname = input.next(); //getting buyer surname
        System.out.print("Enter your email: ");
        String email = input.next(); //getting buyer email

        Person person = new Person(name, surname, email); //create person object with provided values in Person class

        //creating variables to store price, seat limit, row and seat number.
        double price;
        int seatLimit, rowNumber, seatNumber;

        while (true) {
            while (true) {
                System.out.print("\nSelect a row between 1 to 3: ");
                //validating the row number input
                try {
                    rowNumber = input.nextInt(); //getting user input for row number
                } catch (Exception e) {
                    rowNumber = 9; //to avoid this code to break if the input is not in the range
                    input.nextLine(); //to avoid the code from using the initial input
                }

                if (rowNumber == 1) {
                    seatLimit = 12; //there are 12 seats in row one
                    price = 45.0; //ticket price for seats in row one are 45$ each
                    break; //breaks the loop if the row number input is valid
                } else if (rowNumber == 2) {
                    seatLimit = 16; //there are 16 seats in row two
                    price = 30.0; //ticket price for seats in row one are 30$ each
                    break; //breaks the loop if the row number input is valid
                } else if (rowNumber == 3) {
                    seatLimit = 20; //there are 20 seats in row three
                    price = 15.0; //ticket price for seats in row one are 15$ each
                    break; //breaks the loop if the row number input is valid
                } else {
                    System.out.println("Invalid row selection. please try again."); //error message if the input is not in the range
                }
            }
            while (true) {
                System.out.print("\nSelected row number: " + rowNumber + "\nSelect a seat between 1 to " + seatLimit + ": ");
                //validating the user input for seat number
                try {
                    seatNumber = input.nextInt(); //getting user input
                    //checks is the seat number is within the range
                    if (seatNumber >= 1 && seatNumber <= seatLimit) {
                        break; //if the input is within the range the loop will break
                    } else {
                        System.out.println("Invalid seat selection. please try again."); //error message
                    }
                } catch (Exception e) {
                    System.out.println("Invalid seat selection. please try again."); //error message.
                    input.nextLine(); //to avoid the code from using the initial input
                }
            }

            //change the seat in array from 0 to 1. to show the seat is now booked
            if (rowNumber == 1) {
                //checking is the seat is available or not
                if (rowOne[seatNumber - 1] == 0) {
                    rowOne[seatNumber - 1] = 1;
                    System.out.println("Seat is booked successfully\nRow No: " + rowNumber + "\nSeat No: " + seatNumber);
                    break;
                } else {
                    System.out.println("Seat is not available. Already booked. Please select another."); //error message if the seat is already booked.
                }
                //checking is the seat is available or not
            } else if (rowNumber == 2) {
                if (rowTwo[seatNumber - 1] == 0) {
                    rowTwo[seatNumber - 1] = 1;
                    System.out.println("Seat is booked successfully\nRow No: " + rowNumber + "\nSeat No: " + seatNumber);
                    break;
                } else {
                    System.out.println("Seat is not available. Already booked. Please select another."); //error message if the seat is already booked.
                }
                //checking is the seat is available or not
            } else {
                if (rowThree[seatNumber - 1] == 0) {
                    rowThree[seatNumber - 1] = 1;
                    System.out.println("Seat is booked successfully\nRow No: " + rowNumber + "\nSeat No: " + seatNumber);
                    break;
                } else {
                    System.out.println("Seat is not available. Already booked. Please select another."); //error message if the seat is already booked.
                }
            }
        }

        Ticket ticket = new Ticket(rowNumber, seatNumber, price, person); //creating ticket object in Ticket class with values.
        tickets.add(ticket); //add the ticket object to tickets arraylist
    }

// method to print the seating area
    public static void print_seating_area() {
        //printing screen
        System.out.println("\t*************");
        System.out.println("\t*   STAGE   *");
        System.out.println("\t*************");

        // O for available and X for booked
        //printing row one
        System.out.print("\t");
        for(int i = 0; i < rowOne.length; ++i) {
            if( i == 6) {
                System.out.print(" ");
            }
            if(rowOne[i] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
        }
        System.out.println();

        //printing row two
        System.out.print("  ");
        for(int i = 0; i < rowTwo.length; ++i) {
            if( i == 8) {
                System.out.print(" ");
            }
            if(rowTwo[i] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
        }
        System.out.println();

        //printing row three
        for(int i = 0; i < rowThree.length; ++i) {
            if( i == 10) {
                System.out.print(" ");
            }
            if(rowThree[i] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
        }
        System.out.println();

    }

//cancel ticket method
    public static void cancel_ticket(Scanner input) {
        int seatLimit, rowNumber, seatNumber; //creating variables
            while (true) {
                System.out.print("\nSelect a row between 1 to 3: ");
                //validating users input
                try {
                    rowNumber = input.nextInt(); //getting row number as input
                } catch (Exception e) {
                    rowNumber = 9; //to avoid this code to break if the input is not in the range
                    input.nextLine(); //to avoid the code from using the initial input
                }

                if (rowNumber == 1) {
                    seatLimit = 12; //12 seats in row one
                    break;
                } else if (rowNumber == 2) {
                    seatLimit = 16; //16 seats in row two
                    break;
                } else if (rowNumber == 3) {
                    seatLimit = 20; //20 seats in row three
                    break;
                } else {
                    System.out.println("Invalid row selection. please try again."); //error message if the input is outside the range
                }
            }
            while (true) {
                System.out.print("\nSelected row number: " + rowNumber + "\nSelect a seat between 1 to " + seatLimit + ": ");
                //validating the user input for seat number
                try {
                    seatNumber = input.nextInt(); //gets user input for seat number
                    //checks is the input is within the range
                    if (seatNumber >= 1 && seatNumber <= seatLimit) {
                        break; //if the input is in valid range
                    } else {
                        System.out.println("Invalid seat selection. please try again."); //if the input is not in the range
                    }
                } catch (Exception e) {
                    System.out.println("Invalid seat selection. please try again."); //error message
                    input.nextLine(); //to avoid the code from using the initial input
                }
            }

            //change the seat in array from 1 to 0. to show the seat is now available
            if (rowNumber == 1) {
                //checking is the seat is booked or not
                if (rowOne[seatNumber - 1] == 1) {
                    rowOne[seatNumber - 1] = 0;
                    System.out.println("\nRow No: " + rowNumber + "\nSeat No: " + seatNumber + "\nSeat is cancelled successfully");
                } else {
                    System.out.println("Seat is not booked."); //error message if the seat is available.
                }
            } else if (rowNumber == 2) {
                //checking is the seat is booked or not
                if (rowTwo[seatNumber - 1] == 1) {
                    rowTwo[seatNumber - 1] = 0;
                    System.out.println("\nRow No: " + rowNumber + "\nSeat No: " + seatNumber + "\nSeat is cancelled successfully");
                } else {
                    System.out.println("Seat is not booked."); //error message if the seat is available.
                }
            } else {
                //checking is the seat is booked or not
                if (rowThree[seatNumber - 1] == 1) {
                    rowThree[seatNumber - 1] = 0;
                    System.out.println("\nRow No: " + rowNumber + "\nSeat No: " + seatNumber + "\nSeat is cancelled successfully");
                } else {
                    System.out.println("Seat is not booked."); //error message if the seat is available.
                }
        }

        Ticket remove_ticket = null; //creating a variable to store the ticket that needs to be removed from the tickets arraylist
        for (Ticket ticket: tickets) {
            //check for the ticket needed to be removed
            if(ticket.getRow() == rowNumber && ticket.getSeat() == seatNumber) {
                remove_ticket = ticket; //store that ticket in remove_ticket
            }
        }
        // validate the remove_ticket to store any ticket
        if (remove_ticket != null) {
            tickets.remove(remove_ticket); //remove the cancelled ticket from tickets arraylist
        }

    }

//method to print available seat information
    public static void show_available() {
        //printing available seats in row one
        System.out.print("Seats available in row 1: ");
        for(int i = 0; i < rowOne.length; ++i) {
            if (rowOne[i] == 0) {
                System.out.print( (i+1) + ", ");
            }
        }
        System.out.println();

        //printing available seats in row two
        System.out.print("Seats available in row 2: ");
        for(int i = 0; i < rowTwo.length; ++i) {
            if (rowTwo[i] == 0) {
                System.out.print( (i+1) + ", ");
            }
        }
        System.out.println();

        //printing available seats in row three
        System.out.print("Seats available in row 3: ");
        for(int i = 0; i < rowThree.length; ++i) {
            if (rowThree[i] == 0) {
                System.out.print( (i+1) + ", ");
            }
        }
        System.out.println();
    }

// method to save row information to file
    public static void save() {
        try {
            //creates fileWriter object using FileWriter to write in a file
            FileWriter fileWriter = new FileWriter("Seats Information.txt");
            fileWriter.write("Row 01: "); //writes in the file
            //writing the row information in file
            for (int i : rowOne) {
                if (i == 0) {
                    fileWriter.write("O");
                } else {
                    fileWriter.write("1");
                }
            }
            fileWriter.write("\nRow 02: ");

            for (int i : rowTwo) {
                if(i == 0) {
                    fileWriter.write("O");
                } else {
                    fileWriter.write("1");
                }
            }
            fileWriter.write("\nRow 03: ");

            for (int i : rowThree) {
                if(i == 0) {
                    fileWriter.write("O");
                } else {
                    fileWriter.write("1");
                }
            }

            System.out.println("Successfully wrote to the file."); //success message
            fileWriter.close(); //close the fileWriter
        }
        catch (IOException e) {
            System.out.println("An error occurred."); //error message
        }
    }

//method to load information from a file
    public static void load() {
        try {
            //creating file object in File class to read an existing file
            File file = new File("Seats information.txt");
            Scanner file_reader = new Scanner(file); //creating an object in scanner class to get input from the file

            //checks is the file has more lines to read
            while (file_reader.hasNextLine()) {
                String text = file_reader.nextLine(); //read the line from file and store it in text variable
                System.out.println(text); //print the values in the variable
            }
            file_reader.close(); //close the reader
        } catch (IOException e) {
            System.out.println("Error while reading a file"); //error message
        }
    }

//method to print information of tickets
    public static void show_tickets_info() {
        double total = 0; //creates variable to store the total price of all tickets
        int count = 1; //creates a variable to counts the number of tickets
        for (Ticket ticket:tickets ) {
            //printing information of tickets
            System.out.println("Ticket " + count); //ticket number
            count += 1;
            ticket.print();
            System.out.println();
            total += ticket.getPrice(); //calculates the total
        }
        System.out.println("Total price: $" + total); //prints the total
    }

//merge sorting method
    public static ArrayList<Ticket> sort_tickets(int start, int end) {
        //creates an arraylist called sorted to store the sorted tickets
        ArrayList<Ticket> sorted = new ArrayList<>();
        if (tickets.size() > 0) {
            if (start < end) {
                int mid = (start + end) / 2; //to find the middle index of the array

                //separate the array into two parts
                ArrayList<Ticket> arrayLeft = sort_tickets(start, mid);
                ArrayList<Ticket> arrayRight = sort_tickets(mid + 1, end);
                sorted = merge(arrayLeft, arrayRight); //calls the method to sort the arraylist
            } else {
                sorted.add(0, tickets.get(start));
            }
        }

        return sorted; //return the sorted array
    }

    public static ArrayList<Ticket> merge (ArrayList<Ticket> array1, ArrayList<Ticket> array2) {
        ArrayList<Ticket> merged = new ArrayList<>(); //creates a new arraylist
        int idx_1 = 0, idx_2 = 0, idx_merged = 0;
        while (idx_1 < array1.size() && idx_2 < array2.size()) {
            if (array1.get(idx_1).getPrice() <= array2.get(idx_2).getPrice()) {
                merged.add(idx_merged, array1.get(idx_1));
                idx_1++;
            } else {
                merged.add(idx_merged, array2.get(idx_2));
                idx_2++;
            }
            idx_merged++;
        }

        while (idx_1 < array1.size()) {
            merged.add(idx_merged, array1.get(idx_1));
            idx_1++;
            idx_merged++;
        }
        while (idx_2 < array2.size()) {
            merged.add(idx_merged, array2.get(idx_2));
            idx_2++;
            idx_merged++;
        }
        return merged;
    }

}
