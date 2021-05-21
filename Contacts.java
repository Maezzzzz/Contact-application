import java.util.*;
import java.io.*;

public class Contacts {
    static final ArrayList<String> contacts = new ArrayList<>();
    static final Scanner keyboard = new Scanner(System.in);
    static int option = 0;
    public static void main(String[] args) {

        instructions();

        start();
    }

    private static void instructions() {
        System.out.println("Welcome to the contact application!");
        System.out.println("The system allows you to add, store, search and delete contacts.");
    }

    private static void start(){
        while (true) {
            System.out.println("Enter '0' to import contacts");
            System.out.println("Enter '1' to add a contact.");
            System.out.println("Enter '2' to delete a contact.");
            System.out.println("Enter '3' to search for a contact.");
            System.out.println("Enter '4' to view contacts");
            System.out.println("Enter '5' to save and quit.");
            System.out.println("Enter '6' to quit without saving.");
            option = keyboard.nextInt();

            if(option > 6 || option < 0) {
                System.out.println("Incorrect input!");
                start();
            }
            if(option == 0) {
                importContacts();
            }

            if(option == 1) {
                addContact();
            }

            if (option == 2) {
                deleteContact();
            }

            if(option == 3) {
                search();
            }

            if(option == 4) {
                print();
            }
            if(option == 5) {
                save();
            }
            if (option == 6) {
                System.exit(0);
            }
        }
    }

    private static void importContacts() {
        try {
            File myObj = new File("contacts.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                contacts.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Contacts imported successfully!");
        sort();
        return;
    }

    private static void addContact() {
        System.out.print("Enter contacts name: ");
        contacts.add(keyboard.next());
        System.out.println("Contact added.");
        sort();
    }

    private static void sort(){
        Collections.sort(contacts);
    }
    
    private static void deleteContact() {
        System.out.print("Enter Contacts name to be deleted: ");
        String nameToDelete = keyboard.next();
        if(contacts.size() >= 1) {
            int left = 0;
            int right = contacts.size() - 1;

            while(left <= right) {
                int mid = left + (right-left) / 2;

                if(nameToDelete.equalsIgnoreCase(contacts.get(mid))) {
                    contacts.remove(mid);
                    System.out.println(nameToDelete + " was removed from your contacts.");
                    return;
                }
                if(nameToDelete.toLowerCase().compareTo(contacts.get(mid).toLowerCase()) > 0) {
                    left = mid + 1;
                }
                if(nameToDelete.toLowerCase().compareTo(contacts.get(mid).toLowerCase()) < 0) {
                    right = mid - 1;
                }
            }
        }
        System.out.println(nameToDelete + " is not in your contacts.");
    }

    private static void save() {
        try {
            FileWriter myWriter = new FileWriter("Contacts.txt");
            for (String contact : contacts) {
                myWriter.write(contact + "\n");
            }
            myWriter.close();
            System.out.println("Successfully saved contacts.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static void search(){
        System.out.print("Enter the contacts name you would like to search for: ");
        String nameToSearch = keyboard.next();

        if(contacts.size() >= 1) {
            int left = 0;
            int right = contacts.size() - 1;

            while(left <= right) {
                int mid = left + (right-left) / 2;

                if(nameToSearch.equalsIgnoreCase(contacts.get(mid))) {
                    System.out.println(nameToSearch + " was found!");
                    return;
                }
                if(nameToSearch.toLowerCase().compareTo(contacts.get(mid).toLowerCase()) > 0) {
                    left = mid + 1;
                }
                if(nameToSearch.toLowerCase().compareTo(contacts.get(mid).toLowerCase()) < 0) {
                    right = mid - 1;
                }
            }
        }
        System.out.println(nameToSearch + " was not found.");
    }

    private static void print() {
        for (String contact : contacts) {
            System.out.println(contact);
        }
    }
}