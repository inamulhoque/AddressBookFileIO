package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.example.AddressBook.*;

public class AddressBookFunctions {
    public static ArrayList<AddressBook> Contacts = new ArrayList<>();
    public static Scanner obj = new Scanner(System.in);
    public static HashMap<String,ArrayList<AddressBook>> hashMapOfAddressBook = new HashMap<>();
    public static HashMap<String,String> cityDictionary = new HashMap<>();
    public static HashMap<String,String> stateDictionary = new HashMap<>();

    public static ArrayList<AddressBook> findAddressBook(String name){
        for (Map.Entry<String,ArrayList<AddressBook>> itr: hashMapOfAddressBook.entrySet()){
            if (itr.getKey().equals(name)){
                return itr.getValue();
            }
        }
        return null;
    }

    public static void addContact(String bookName){
        System.out.print("First name: ");
        firstName = obj.next();

        System.out.print("Last name: ");
        lastName = obj.next();

        if (AddressBookFunctions.checkDuplicate(bookName,firstName,lastName)) {

            System.out.print("City: ");
            city = obj.next();
            System.out.print("State: ");
            state = obj.next();
            System.out.print("Zip: ");
            zip = obj.nextInt();
            System.out.print("E-mail: ");
            mail = obj.next();
            System.out.print("Phone number: ");
            phone = obj.nextLong();

            AddressBook addressBook = new AddressBook(firstName, lastName, city, state, zip, phone, mail);
            cityDictionary.put(firstName+" "+lastName,city);
            stateDictionary.put(firstName+" "+lastName,state);
            if (findAddressBook(bookName) != null) {
                hashMapOfAddressBook.get(bookName).add(addressBook);
                try {
                    AddressBookIO.writeIntoFile();
                    System.out.println("Data inserted");
                    AddressBookIO.readFromFile();
                } catch (IOException e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }

            Contacts = new ArrayList<AddressBook>();
            Contacts.add(addressBook);
            hashMapOfAddressBook.put(bookName, Contacts);
        }
        else {
            System.out.println("The contact name with "+firstName+" already exist in "+hashMapOfAddressBook.get(bookName));
        }
        try{
            AddressBookIO.writeIntoFile();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void displayByOrder() {
        System.out.println(" Please enter the name of the address book: ");
        String name = obj.next();
        if(hashMapOfAddressBook.get(name).isEmpty())
        {
            System.out.println("The Address Book is empty.");
            return;
        }
        hashMapOfAddressBook.get(name).stream().sorted((contact1, contact2) -> contact1.getFirstName().compareToIgnoreCase(contact2.getFirstName()))
                .forEach(contact -> System.out.println(contact));
        try {
            System.out.println("Data inserted in file is:");
            AddressBookIO.readFromFile();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void editContact() {
        Scanner scanner = new Scanner(System.in);
        byte i = 1;
        do {
            System.out.println("Enter Addressbook name: ");
            String editAddressbookName = scanner.next();
            ArrayList<AddressBook> arrayList = findAddressBook(editAddressbookName);
            if (arrayList == null)
                System.out.println("Address book not found.");
            else if (arrayList.isEmpty())
                System.out.println("The book with " + editAddressbookName + " is empty.");
            int index =  findContact(arrayList);
            if(index == -1)
            {
                System.out.println("Details with this name is not found");
                return;
            }
            else {
                System.out.println("Found addressbook.");
                System.out.println("Enter the first name to edit contact details.");
                String name = scanner.next();
                if (name.equals(firstName)) {
                    System.out.println("Which field you want to edit? Choose from below-");
                    System.out.println("1 -> First name\t" + "2 -> Last name\t" + "3 -> City\t" + "4 -> State\t" + "5 -> Zip\t" +
                            "6 -> Phone\t" + "7 -> E-mail");
                    byte a = scanner.nextByte();
                    switch (a) {
                        case 1:
                            System.out.println("New First Name:");
                            firstName = scanner.next();
                            break;
                        case 2:
                            System.out.println("New Last Name:");
                            lastName = scanner.next();
                            break;
                        case 3:
                            System.out.println("New City:");
                            city = scanner.next();
                            break;
                        case 4:
                            System.out.println("New State:");
                            state = scanner.next();
                            break;
                        case 5:
                            System.out.println("New Zip:");
                            zip = scanner.nextInt();
                            break;
                        case 6:
                            System.out.println("New Phone:");
                            phone = scanner.nextLong();
                            break;
                        case 7:
                            System.out.println("New E-mail:");
                            mail = scanner.next();
                            break;
                    }
                    System.out.println("Do you want to edit more? then press 1 or exit with any key.");
                    i = scanner.nextByte();
                }
            }
        }
        while (i == 1) ;
    }

    public static void deleteContact(){
        System.out.println("Enter the address book name:");
        String AddressBookName = obj.next();
        ArrayList<AddressBook> arrayList = findAddressBook(AddressBookName);
        if (arrayList == null){
            System.out.println("AddressBook not found.");
        }
        if (arrayList.isEmpty()) {
            System.out.println("Address book with name " + AddressBookName + " is empty");
        }
        int index =  findContact(arrayList);
        if(index == -1)
        {
            System.out.println("Details with this name is not found");
        }
        System.out.println("AddressBook deleted.");
        arrayList.remove(index);
    }

    public static void addAddressBook(){
        System.out.println("Enter Address book name:");
        String addressBookName = obj.next();
        if (findAddressBook(addressBookName)!=null){
            System.out.println("Already exists.");
            System.out.println(hashMapOfAddressBook.get(addressBookName));
        }
        System.out.println( addressBookName);
    }
    public static int findContact(ArrayList<AddressBook> arrayList){
        System.out.println("Enter the name: ");
        String findName = obj.next();
        for (AddressBook data: arrayList) {
            if (findName.compareToIgnoreCase(data.getFirstName())==0){
                return arrayList.indexOf(data);
            }
        }
        return -1;
    }


    public static boolean checkDuplicate(String book_name, String first_name, String last_name){
        int f = 0;
        if (hashMapOfAddressBook.get(book_name)==null)
            return true;
        ArrayList<AddressBook> arrayList = hashMapOfAddressBook.get(book_name);
        HashMap<String,String> names = new HashMap<>();
        for (AddressBook data : arrayList) {
            names.put(data.getFirstName(),data.getLastName());
        }
        ICheckDuplicate checkDuplicate = ((f_name, l_name) -> {
            if(f_name.equals(first_name) && l_name.equals(last_name)){
                return false;
            }
            return true;
        });
        Boolean ansToDuplicates = names.entrySet().stream()
                .anyMatch(n->checkDuplicate.checkDuplicate(n.getKey(),n.getValue()));
        return ansToDuplicates;
    }
    public static void sameCity(String city){
        byte count = 0;
        for (String data: cityDictionary.keySet()){
            if (cityDictionary.get(data).equals(city)){
                count ++;
                System.out.println("City found "+data);
            }
        }
        System.out.println("Total number of city with name "+city+" found "+count+" times.");
    }
    public static void sameState(String state){
        byte count =0;
        for (String data : stateDictionary.keySet()){
            if (stateDictionary.get(data).equals(state)){
                count++;
                System.out.println("State found "+data);
            }
        }
        System.out.println("Total number of state with name "+state+" found "+count+" times.");
    }
    public static void sortByName(){
        System.out.println("Enter addressbook name:");
        String name = obj.next();
        hashMapOfAddressBook.get(name).stream().map(x->x.getFirstName()).sorted().forEach(x-> System.out.println(x));
    }
    public static void sortByCity(){
        System.out.println("Enter addressbook name:");
        String name = obj.next();
        hashMapOfAddressBook.get(name).stream().map(x->x.getCity()).sorted().forEach(x-> System.out.println(x));
    }
    public static void sortByState(){
        System.out.println("Enter addressbook name:");
        String name = obj.next();
        hashMapOfAddressBook.get(name).stream().map(x->x.getState()).sorted().forEach(x-> System.out.println(x));
    }
    public static void sortByZip(){
        System.out.println("Enter addressbook name:");
        String name = obj.next();
        hashMapOfAddressBook.get(name).stream().map(x->x.getZip()).sorted().forEach(x-> System.out.println(x));
    }
}