import java.util.*;
import java.io.*;

public class Contacts {
    static ArrayList<String> contacts = new ArrayList<String>();
    static Scanner keyboard = new Scanner(System.in);
    int option = 0;
    public static void main(String[] args) {

        instructions();

        start();

        save();

        //System.out.println(contacts);
    }

    private static void instructions() {
        System.out.println("Welcome to the contact application!");
        System.out.println("The system allows you to add, store, search and delete contacts.");

    }

    private static void start(int option){
        while (option != 4) {
            System.out.println("Enter '0' to import contacts");
            System.out.println("Enter '1' to add a contact.");
            System.out.println("Enter '2' to delete a contact.");
            System.out.println("Enter '3' to search for a contact.");
            System.out.println("Enter '4' to save and quit.");

            if(keyboard.nextInt() == 0) {
                importContacts();
            }
            if(keyboard.nextInt() == 1) {
                //TODO
            }
            if(keyboard.nextInt() == 2) {
                //TODO
            }
            if(keyboard.nextInt() == 3) {
                //TODO
            }
            if(keyboard.nextInt() == 4) {
                //TODO
            }
        }
        save();
    }

    private static void importContacts() {
        //TODO
    }

    private static void addContact() {
        System.out.print("Enter Contacts name: ");
        contacts.add(keyboard.next());

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

    private static void sort(){
        Collections.sort(contacts);
    }

    private static void searchBinary(String nameToSearch) {
        if(contacts.size() >= 1) {
            int left = 0;
            int right = contacts.size() - 1;

            while(left <= right) {
                int mid = left + (right-left) / 2;

                if(nameToSearch.toLowerCase().equals(contacts.get(mid).toLowerCase())) {
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

            System.out.println(nameToSearch + " was not found.");
        }
    }

}
