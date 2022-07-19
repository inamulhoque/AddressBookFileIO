package org.example;

import java.util.ArrayList;
import java.util.Scanner;

import static org.example.AddressBookFunctions.Contacts;

public class AddressBookMain{

    public static void main(String[] args) {
        int loop = 1;
        do {
            System.out.println("Enter your choice from below:");
            System.out.println("1.Add AddressBook" +
                    "2.Add contact\t" +
                    "3.Edit contacts\t" +
                    "4.Delete contacts\t" +
                    "5.View contact with city name\t" +
                    "6.View contact with state name\t" +
                    "7.View the contacts in sorted order\t" +
                    "8.Display the contacts sorted by City,State or zip");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    AddressBookFunctions.addAddressBook();
                case 2:
                    int i = 1;
                    do {
                        System.out.println("Enter addressbook name:");
                        String book = scanner.next();
                        AddressBookFunctions.addContact(book);
                        System.out.println("Do you want to add more contact? press 1 or press any key to exit.");
                        i = scanner.nextInt();
                    } while (i == 1);
                    break;
                case 3:
                    AddressBookFunctions.editContact();
                    break;
                case 4:
                    AddressBookFunctions.deleteContact();
                    break;
                case 5:
                    System.out.println("Enter city:");
                    String city = scanner.next();
                    AddressBookFunctions.sameCity(city);
                    break;
                case 6:
                    System.out.println("Enter state:");
                    String state = scanner.next();
                    AddressBookFunctions.sameState(state);
                    break;
                case 7:
                    System.out.println("Display in sorted");
                    AddressBookFunctions.sortByName();
                case 8:
                    System.out.println("1 -> Sort by city" +
                            "2 -> Sort by State" +
                            "3 -> Sort by Zip");
                    int a = scanner.nextInt();
                    switch (a){
                        case 1:
                            AddressBookFunctions.sortByCity();
                            break;
                        case 2:
                            AddressBookFunctions.sortByState();
                            break;
                        case 3:
                            AddressBookFunctions.sortByZip();
                            break;
                    }
                default:
                    System.out.println("Enter a valid choice.");
                    break;
            }
            System.out.println("Press 1 to repeat the menu or quit.");
            loop = scanner.nextInt();
        }while (loop==1);
    }
}
