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
                JFrame frame = new JFrame();
                String add = JOptionPane.showInputDialog(frame, "Enter contact:");
                addContact(add);
            }
        });

        JButton deleteContact = new JButton("Delete contact");
        deleteContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                String delete = JOptionPane.showInputDialog(frame, "Enter contact:");
                deleteContact(delete);
                if(deleteContact(delete)) {
                    JOptionPane.showMessageDialog(frame,"Contact deleted.");
                }else {
                    JOptionPane.showMessageDialog(frame, "Contact does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton searchContacts = new JButton("Search contacts");
        searchContacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                String search = JOptionPane.showInputDialog(frame, "Enter contact to search for:");
                if(search(search)){
                    JOptionPane.showMessageDialog(frame, "Contact Found!");
                }else {
                    JOptionPane.showMessageDialog(frame, "Contact not found.", "Search error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton viewContacts = new JButton("View contacts");
        viewContacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder builder = new StringBuilder();
                for (int a = 0; a < contacts.size(); a++) {
                    builder.append(contacts.get(a)).append(" \n");
                }
                JTextArea textArea = new JTextArea(String.valueOf(builder));
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize( new Dimension( 300, 300 ) );
                JOptionPane.showMessageDialog(null, scrollPane, "Contacts",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });

        JButton saveAndQuit = new JButton("Save and quit");
        saveAndQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = new JFrame();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");

                int userSelection = fileChooser.showSaveDialog(parentFrame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    if(save(fileToSave.getAbsolutePath())) {
                        //custom title, no icon
                        JOptionPane.showMessageDialog(frame,
                                "Contacts have been saved successfully!",
                                "Contacts saved!",
                                JOptionPane.PLAIN_MESSAGE);
                                System.exit(0);
                    }else {
                        JOptionPane.showMessageDialog(frame, "File could not be saved.", "Error file saving",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton quit = new JButton("Quit without saving");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

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

    private static boolean importContacts(String filePath) {
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

    private static void addContact(String contact) {
        contacts.add(contact);
        sort();
    }

    private static void sort(){
        Collections.sort(contacts);
    }

    private static boolean deleteContact(String name) {
        if(contacts.size() >= 1) {
            int left = 0;
            int right = contacts.size() - 1;

            while(left <= right) {
                int mid = left + (right-left) / 2;

                if(name.equalsIgnoreCase(contacts.get(mid))) {
                    contacts.remove(mid);
                    return true;
                }
                if(name.compareToIgnoreCase(contacts.get(mid)) > 0) {
                    left = mid + 1;
                }
                if(name.compareToIgnoreCase(contacts.get(mid)) < 0) {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    private static boolean save(String filepath) {
        try {
            FileWriter myWriter = new FileWriter(filepath);
            for (String contact : contacts) {
                myWriter.write(contact + "\n");
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    private static boolean search(String name){

        if(contacts.size() >= 1) {
            int left = 0;
            int right = contacts.size() - 1;

            while(left <= right) {
                int mid = left + (right-left) / 2;

                if(name.equalsIgnoreCase(contacts.get(mid))) {
                    return true;
                }
                if(name.compareToIgnoreCase(contacts.get(mid)) > 0) {
                    left = mid + 1;
                }
                if(name.compareToIgnoreCase(contacts.get(mid)) < 0) {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}