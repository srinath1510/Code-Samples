
// --== CS400 File Header Information ==--
// Name: Srinath Srinivasan
// Email: srinivasan32@wisc.edu
// Team: NG
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: My part of the project.


import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/*
 * This class executes the front end interface of the Grocery Store Database.
 */
public class FrontEndDeveloper {
  private final static String WELCOME_MSG =
      "======== WELCOME to the Grocery Store Database ========";
  private final static String GOODBYE_MSG =
      "======== Thank you for using this Application! ========";
  private final static String MENU = "\nCOMMAND MENU:\n"
      + "[A] Add a grocery item profile to the database.\n"
      + "[S] search for a specific grocery item in the database. \n"
      + "[R]  Removes a specified grocery item profile from the hashtable.\n"
      + "[C] Clears the entire grocery store hashtable.\n"
      + "[L] Lists every item with details and the total number of items in the hashtable.\n"
      + "[F] Reads a given file and adds all properly formatted items in that list to the hashtable.\n"
      + "[H] Display this menu again. \n" + "[Q] Quit program.";

  private static BackEndDeveloper backEnd = new BackEndDeveloper();

  /*
   * This method executes the user interface and allows the user to run possible commands that
   * utilize the Grocery Store database.
   * 
   * @param String command
   */
  public static void processUserCommandLine(String command) throws InputMismatchException {
    String[] input = command.trim().split(" ");
    Scanner scnr;
    String serial = "";
    String itemName = "";
    double price = 0;
    int quantity = 0;
    int location = 0;
    switch (input[0].toUpperCase()) {
      case "A":
        while (true) {
          try {
            scnr = new Scanner(System.in);
            System.out.print("Enter 12 Digit Serial Number (12 numerical digits): ");
            serial = scnr.next();
            backEnd.serialValidity(serial);
            backEnd.alreadyExists(serial);
          } catch (Exception e) {
            System.out.println(e.getMessage());
            continue;
          }
          break;
        }
        System.out.print("Enter Item Name: ");
        itemName = scnr.next();
        while (true) {
          try {
            scnr = new Scanner(System.in);
            System.out.print("Enter Item Price (A 2 decimal number such as 3.00): ");
            price = scnr.nextDouble();
          } catch (InputMismatchException e) {
            System.out.println("Not a valid price!");
            continue;
          }
          break;
        }
        while (true) {
          try {
            scnr = new Scanner(System.in);
            System.out.print("Enter the Quantity Currently Available: ");
            quantity = scnr.nextInt();
          } catch (InputMismatchException e) {
            System.out.println("Not a Number!");
            continue;
          }
          break;
        }
        while (true) {
          try {
            scnr = new Scanner(System.in);
            System.out.print("Enter the Location in the Store (0-9): ");
            location = scnr.nextInt();
            if (location > 9 || location < 0) {
              System.out.println("Location is not in range");
              continue;
            }
          } catch (InputMismatchException e) {
            System.out.println("Not a Number!");
            continue;
          }
          break;
        }
        GroceryStoreItem itemToAdd =
            new GroceryStoreItem(serial, itemName, price, quantity, location);
        backEnd.add(itemToAdd);
        break;

      case "S":
        scnr = new Scanner(System.in);
        System.out.print("Enter the Serial Number of the Item You Are Looking for: ");
        serial = scnr.next();
        GroceryStoreItem itemInfo = null;
        try {
          itemInfo = (GroceryStoreItem) backEnd.search(serial);
        } catch (NoSuchElementException e) {
          System.out.println(e.getMessage());
          break;
        }
        System.out.println("Item Serial Number: " + itemInfo.getSerialNumber());
        System.out.println("Item Name: " + itemInfo.getName());
        System.out.println("Item Price: " + itemInfo.getCost());
        System.out.println("Item Quantity in Store: " + itemInfo.getQuantity());
        System.out.println("Item Location: " + itemInfo.getLocation());
        break;

      case "R":
        try {
          scnr = new Scanner(System.in);
          System.out.print("Enter the serial number of the item you would like removed: ");
          serial = scnr.next();
        } catch (NoSuchElementException e) {
          System.out.println("Not a valid Serial Number!");
        }
        GroceryStoreItem removedItem = null;
        removedItem = (GroceryStoreItem) backEnd.remove(serial);
        if (removedItem == null) {
          System.out.println("Serial number either invalid or not found!");
        } else {
          System.out.println("Removed Item Serial Number: " + removedItem.getSerialNumber());
          System.out.println("Removed Item Name: " + removedItem.getName());
          System.out.println("Removed Item Price: " + removedItem.getCost());
          System.out.println("Removed Item Quantity: " + removedItem.getQuantity());
          System.out.println("Removed Item Location: " + removedItem.getLocation());
        }
        break;

      case "C":
        backEnd.clear();
        System.out.println("All items removed from table");
        break;

      case "L":
        String[] serialNumbersInList = backEnd.list();
        GroceryStoreItem currentItem;
        String currentSerialNumber;
        String currentName;
        double currentPrice;
        int currentQuantity;
        int currentLocation;
        for (int i = 0; i < serialNumbersInList.length; i++) {
          currentItem = (GroceryStoreItem) backEnd.search(serialNumbersInList[i]);
          currentSerialNumber = (String) currentItem.getSerialNumber();
          currentName = (String) currentItem.getName();
          currentPrice = (double) currentItem.getCost();
          currentQuantity = (int) currentItem.getQuantity();
          currentLocation = (int) currentItem.getLocation();
          System.out.println("Item " + (i + 1) + ": ");
          System.out.println("Serial Number: " + currentSerialNumber);
          System.out.println("Name: " + currentName);
          System.out.println("Price: $" + currentPrice);
          System.out.println("Quantity: " + currentQuantity);
          System.out.println("Location: " + currentLocation);
          System.out.println();
        }
        System.out.println("==========================================");
        System.out.println("Total net worth of store: $" + backEnd.sumGrocery());
        break;
      case "F":
        DataWrangler dataWrangler = new DataWrangler(backEnd);
        scnr = new Scanner(System.in);
        System.out.println("What is the name of the file that you would like to read from: ");
        String fileToRead = scnr.next();
        dataWrangler.readFile(fileToRead);
        break;


      case "H":
        System.out.println(MENU);
        break;

      default:
        System.out.println("WARNING. Invalid command. Please enter H to refer to the menu.");
    }
  }

  /*
   * This method initializes the scanner that will allow user input and provides an informative user
   * interface.
   */
  private static void driver() {
    Scanner scnr = new Scanner(System.in);
    String promptCommandLine = "\nENTER COMMAND: ";

    System.out.print(MENU);
    System.out.print(promptCommandLine);
    String line = scnr.nextLine().trim();
    char c = line.charAt(0);

    while (Character.toUpperCase(c) != 'Q') {
      processUserCommandLine(line);
      System.out.println(promptCommandLine);
      line = scnr.nextLine().trim();
      c = line.charAt(0);
    }
    scnr.close();
  }

  /*
   * The main method executes the driver() method and provides relevant information.
   */
  public static void main(String[] args) {
    System.out.println(WELCOME_MSG);
    driver();
    System.out.println(GOODBYE_MSG);
  }
}
