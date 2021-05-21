import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Contacts {
    static final ArrayList<String> contacts = new ArrayList<>();
    static final Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {

        JFrame frame = new JFrame("Contacts Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        JPanel panel = new JPanel(new GridLayout(8,1));

        JLabel instructions = new JLabel("<html> Welcome to the contact application!<br/>" +
                " The system allows you to add, store, search and delete contacts.</html>" );
        instructions.setHorizontalTextPosition(SwingConstants.CENTER);

        JButton importContacts = new JButton("Import contacts");
        importContacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JText Files", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(frame);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    if(importContacts(chooser.getSelectedFile().getName())) {
                        JOptionPane.showMessageDialog(frame, "Contacts imported succesfully!");
                    }else {
                        JOptionPane.showMessageDialog(frame, "Contacts could not be imported.");
                    }
                }
            }
        });

        JButton addContact = new JButton("Add contact");
        addContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton deleteContact = new JButton("Delete contact");

        JButton searchContacts = new JButton("Search contacts");

        JButton viewContacts = new JButton("View contacts");

        JButton saveAndQuit = new JButton("Save and quit");

        JButton quit = new JButton("Quit without saving");

        panel.add(instructions);
        panel.add(importContacts);
        panel.add(addContact);
        panel.add(deleteContact);
        panel.add(searchContacts);
        panel.add(viewContacts);
        panel.add(saveAndQuit);
        panel.add(quit);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private static Boolean importContacts(String filePath) {
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                contacts.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        sort();
        return true;
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