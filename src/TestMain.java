

import javafx.application.Application;
import javafx.stage.Stage;
import model.*;

import java.util.*;

import static javafx.application.Application.launch;

public class TestMain extends Application {
    public static void main(String[] args)
    {
        try
        {
            launch(args);
            Teller testers = new Teller();
            Librarian tester = new Librarian();

            int y = 1;
            Scanner input = new Scanner(System.in);
            while (y == 1) {
                System.out.println("Enter the letter " + ConsoleColors.GREEN + "a" + ConsoleColors.RESET
                        + " if you want to enter a book: \nEnter the letter " + ConsoleColors.GREEN + "s" + ConsoleColors.RESET + " if you want to enter a new patron: \n" +
                        "If you want to get Books with a specific title enter " + ConsoleColors.GREEN + "d" + ConsoleColors.RESET + ": \n" +
                        "If you want to get Books with a year before enter " + ConsoleColors.GREEN + "f" + ConsoleColors.RESET + ": \n" +
                        "If you want to get Patrons with a date before enter " + ConsoleColors.GREEN + "g" + ConsoleColors.RESET + ": \n" +
                        "If you want to get Patrons with a specific zip enter " + ConsoleColors.GREEN + "h" + ConsoleColors.RESET + ": \n" +
                        "If you want to exit the Program, enter " + ConsoleColors.GREEN + "q: " + ConsoleColors.RESET);
                String x = input.nextLine();

            /* A loop that allows a user to enter new books into the database
               It will add as many as desired...
             */
                if (x.compareToIgnoreCase("a") == 0) {
                    while (x.compareToIgnoreCase("a") == 0) {
                        Properties p = new Properties();

                        System.out.println("Enter bookTitle: ");
                        p.setProperty("bookTitle", input.nextLine());
                        System.out.println("Enter the author: ");
                        p.setProperty("author", input.nextLine());
                        System.out.println("Enter the pubYear: ");
                        p.setProperty("pubYear", input.nextLine());
                        int check = 1;
                        while (check == 1) {
                            System.out.println("Enter status(" + ConsoleColors.GREEN_BOLD + "Active" + ConsoleColors.RESET
                                    + " or " + ConsoleColors.GREEN_BOLD + "Inactive" + ConsoleColors.RESET + "): ");
                            String status = input.nextLine();
                            if (status.compareTo("Active") != 0 && status.compareTo("Inactive") != 0)
                                System.out.println(ConsoleColors.RED_UNDERLINED + "Not a valid input:" + ConsoleColors.RESET + " " + status);
                            else {
                                p.setProperty("status", status);
                                check = 0;
                            }
                        }
                        Book bk2 = new Book(p);

                        bk2.update();
                        System.out.println("If you want to enter another book, enter " + ConsoleColors.GREEN + "a" + ConsoleColors.RESET + ": \nEnter an " + ConsoleColors.RED + "n " + ConsoleColors.RESET + "if you do not wish to enter another book: ");
                        x = input.nextLine();
                    }
                }




            /* A loop that allows a user to enter new patrons into the database
               It will add as many as desired...
            */
                if (x.compareToIgnoreCase("s") == 0) {
                    while (x.compareToIgnoreCase("s") == 0) {
                        Properties p = new Properties();

                        System.out.println("Enter name: ");
                        p.setProperty("name", input.nextLine());
                        System.out.println("Enter address: ");
                        p.setProperty("address", input.nextLine());
                        System.out.println("Enter city: ");
                        p.setProperty("city", input.nextLine());
                        System.out.println("Enter stateCode: ");
                        p.setProperty("stateCode", input.nextLine());
                        System.out.println("Enter zip: ");
                        p.setProperty("zip", input.nextLine());
                        System.out.println("Enter email: ");
                        p.setProperty("email", input.nextLine());
                        System.out.println("Enter date of birth " + ConsoleColors.GREEN + "(yyyy-mm-dd): " + ConsoleColors.RESET);
                        p.setProperty("dateOfBirth", input.nextLine());
                        int check = 1;
                        while (check == 1) {
                            System.out.println("Enter status(" + ConsoleColors.GREEN_BOLD + "Active" + ConsoleColors.RESET
                                    + " or " + ConsoleColors.GREEN_BOLD + "Inactive" + ConsoleColors.RESET + "): ");
                            String status = input.nextLine();
                            if (status.compareTo("Active") != 0 && status.compareTo("Inactive") != 0)
                                System.out.println(ConsoleColors.RED_UNDERLINED + "Not a valid input:" + ConsoleColors.RESET + " " + status);
                            else {
                                p.setProperty("status", status);
                                check = 0;
                            }
                        }
                        Patron pt1 = new Patron(p);

                        pt1.update();
                        System.out.println("If you want to enter another patron, enter " + ConsoleColors.GREEN + "s" + ConsoleColors.RESET + ": \nEnter an " + ConsoleColors.RED + "n " + ConsoleColors.RESET + "if you do not wish to enter another patron: ");
                        x = input.nextLine();
                    }
                }

                if (x.compareToIgnoreCase("d") == 0){
                    while (x.compareToIgnoreCase("d") == 0) {
                        BookCollection bkc = new BookCollection();
                        System.out.println("Enter a book title: ");
                        String in = input.nextLine();
                        bkc.getTitle(in);
                        System.out.println("Book Collection with title: " + in);
                        System.out.println(bkc.toString());
                        System.out.println("If you want to search for more books by title, enter "  + ConsoleColors.GREEN + "d" + ConsoleColors.RESET + ": \nEnter an " + ConsoleColors.RED + "n " + ConsoleColors.RESET + "if you do not wish to get more books: ");
                        x = input.nextLine();
                    }
                }

                if (x.compareToIgnoreCase("f") == 0){
                    while (x.compareToIgnoreCase("f") == 0) {
                        BookCollection bkc = new BookCollection();
                        System.out.println("Enter a book year (yyyy): ");
                        String in = input.nextLine();
                        bkc.getDateBefore(in);
                        System.out.println("Book Collection with year before: " + in);
                        System.out.println(bkc.toString());
                        System.out.println("If you want to search for more books by year, enter " + ConsoleColors.GREEN + "f" + ConsoleColors.RESET + ": \nEnter an " + ConsoleColors.RED + "n " + ConsoleColors.RESET + "if you do not wish to search more books: ");
                        x = input.nextLine();
                    }
                }


                if (x.compareToIgnoreCase("g") == 0){
                    while (x.compareToIgnoreCase("g") == 0) {
                        PatronCollection bkc = new PatronCollection();
                        System.out.println("Enter a patron's DOB: ");
                        String in = input.nextLine();
                        bkc.getDOBBefore(in);
                        System.out.println("Patron Collection with DOB before (yyyy-mm-dd): " + in);
                        System.out.println(bkc.toString());
                        System.out.println("If you want to search for more patrons by DOB, enter " + ConsoleColors.GREEN + "g" + ConsoleColors.RESET + ": \nEnter an " + ConsoleColors.RED + "n " + ConsoleColors.RESET + "if you do not wish to get more patrons: ");
                        x = input.nextLine();
                    }
                }

                if (x.compareToIgnoreCase("h") == 0){
                    while (x.compareToIgnoreCase("h") == 0) {
                        PatronCollection bkc = new PatronCollection();
                        System.out.println("Enter a patron's zip: ");
                        String in = input.nextLine();
                        bkc.getZip(in);
                        System.out.println("Patron Collection with zip: " + in);
                        System.out.println(bkc.toString());
                        System.out.println("If you want to search for more patrons by zip, enter "  + ConsoleColors.GREEN + "h" + ConsoleColors.RESET + ": \nEnter an " + ConsoleColors.RED + "n " + ConsoleColors.RESET + "if you do not wish to get more patrons: " );
                        x = input.nextLine();
                    }
                }

                if (x.compareToIgnoreCase("q") == 0)
                    y++;

            }


        }
        catch (Exception ex)
        {
            System.out.println("Error in accessing database: " + ex.toString());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
