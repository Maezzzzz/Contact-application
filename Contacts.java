import java.util.*;
import java.io.*;

public class Contacts {
    static ArrayList<String> contacts = new ArrayList<String>();
    static Scanner keyboard = new Scanner(System.in);
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
        while (option != 5) {
            System.out.println("Enter '0' to import contacts");
            System.out.println("Enter '1' to add a contact.");
            System.out.println("Enter '2' to delete a contact.");
            System.out.println("Enter '3' to search for a contact.");
            System.out.println("Enter '4' to view contacts");
            System.out.println("Enter '5' to save and quit.");

            option = keyboard.nextInt();
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
                //TODO
            }

            if(option == 4) {
                //TODO
            }

            if(option == 5) {
                //TODO
            }
        }
        save();
    }

    private static void importContacts() {
        contacts.clear();
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
    }

    private static void addContact() {
        System.out.print("Enter Contacts name: ");
        contacts.add(keyboard.next());
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
                
                if(nameToDelete.toLowerCase().equals(contacts.get(mid).toLowerCase())) {
                    contacts.remove(mid);
                    System.out.println(nameToDelete + " was removed from your contacts.");
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
            for(int i = 0; i < contacts.size(); i++) {
                myWriter.write(contacts.get(i) + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static boolean searchBinary(String nameToSearch) {
        if(contacts.size() >= 1) {
            int left = 0;
            int right = contacts.size() - 1;

            while(left <= right) {
                int mid = left + (right-left) / 2;

                if(nameToSearch.toLowerCase().equals(contacts.get(mid).toLowerCase())) {
                    return true;
                }
                if(nameToSearch.toLowerCase().compareTo(contacts.get(mid).toLowerCase()) > 0) {
                    left = mid + 1;
                }
                if(nameToSearch.toLowerCase().compareTo(contacts.get(mid).toLowerCase()) < 0) {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}