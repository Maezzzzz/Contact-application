import java.util.*;

public class Contacts {
    static ArrayList<String> contacts = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        populateContacts();

        System.out.print("Enter the contacts name to see if they are saved: ");
        String name = keyboard.next().toLowerCase();

        searchSequential(contacts, name);
        searchBinary(contacts, name);

        //System.out.println(contacts);
    }

    private static void populateContacts(){
        contacts.add("Jane");
        contacts.add("Joe");
        contacts.add("Jack");
        contacts.add("Jose");
        contacts.add("Fred");
        contacts.add("Albert");
        contacts.add("Haily");
        contacts.add("Amanda");
        contacts.add("Thomas");
        contacts.add("Kevin");
        contacts.add("Timmy");
        contacts.add("Charlie");
        contacts.add("Devin");
        contacts.add("Eugene");
        contacts.add("Sam");
        Collections.sort(contacts);
    }

    private static void searchSequential(ArrayList<String> list, String nameToSearch) {
        for(int i = 0; i < list.size();i++) {
            if(nameToSearch.equals(list.get(i).toLowerCase())) {
                System.out.println(nameToSearch + " was found!");
                return;
            }
        }
        System.out.println(nameToSearch + " was not found.");
        return;
    }

    private static void searchBinary(ArrayList<String> list, String nameToSearch) {
        if(list.size() >= 1) {
            int left = 0;
            int right = list.size() - 1;

            while(left <= right) {
                int mid = left + (right-left) / 2;

                if(nameToSearch.toLowerCase().equals(list.get(mid).toLowerCase())) {
                    System.out.println(nameToSearch + " was found!");
                    return;
                }

                if(nameToSearch.toLowerCase().compareTo(list.get(mid).toLowerCase()) > 0) {
                    left = mid + 1;
                }

                if(nameToSearch.toLowerCase().compareTo(list.get(mid).toLowerCase()) < 0) {
                    right = mid - 1;
                }
            }

            System.out.println(nameToSearch + " was not found.");
        }
    }

    private static void searchJump(ArrayList<String> list, String nameToSearch) {

    }

    private static void searchInterpolation(ArrayList<String> list, String nameToSearch) {

    }

    private static void searchExponential(ArrayList<String> list, String nameToSearch) {

    }

    private static void searchSublist(ArrayList<String> list, String nameToSearch) {

    }

    private static void searchFibonacci(ArrayList<String> list, String nameToSearch) {

    }

    private static void searchUbiquitousBinary(ArrayList<String> list, String nameToSearch) {

    }

    private static void searchURecursion(ArrayList<String> list, String nameToSearch) {

    }

    private static void searchUnboundBinary(ArrayList<String> list, String nameToSearch) {

    }
}
