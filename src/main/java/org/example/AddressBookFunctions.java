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

    public static void addContact(String bookName){
        System.out.print("First name: ");
        firstName = obj.next();
        AddressBook.setFirstName(firstName);

        System.out.print("Last name: ");
        lastName = obj.next();
        AddressBook.setLastName(lastName);
        if (AddressBookFunctions.checkDuplicate(bookName,firstName,lastName)) {

            System.out.print("City: ");
            city = obj.next();
            AddressBook.setCity(city);

            System.out.print("State: ");
            state = obj.next();
            AddressBook.setState(state);

            System.out.print("Zip: ");
            zip = obj.nextInt();
            AddressBook.setZip(zip);

            System.out.print("E-mail: ");
            mail = obj.next();
            AddressBook.setMail(mail);

            System.out.print("Phone number: ");
            phone = obj.nextLong();
            AddressBook.setPhone(phone);

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

    public static void editContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first name to edit contact details.");
        String name = scanner.next();
        if (name.equals(firstName)){
            System.out.println("New First Name:");
            firstName = scanner.next();
            System.out.println("New Last Name:");
            lastName = scanner.next();
            System.out.println("New City:");
            city = scanner.next();
            System.out.println("New State:");
            state = scanner.next();
            System.out.println("New Zip:");
            zip = scanner.nextInt();
            System.out.println("New Phone:");
            phone = scanner.nextLong();
            System.out.println("New E-mail:");
            mail = scanner.next();
        }
    }

    public static void deleteContact(ArrayList<AddressBook> Contacts){
        System.out.println("Enter the first name to delete contact");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        if (name.equals(firstName)){
            Contacts.clear();
            System.out.println("Deletion completed.");
        } else {
            System.out.println("Wrong name input.");
        }
    }

    public static void addAddressBook(){
        System.out.println("Enter addressbook name:");
        String addressBookName = obj.next();
        if (findAddressBook(addressBookName)!=null){
            System.out.println("Already exists.");
            System.out.println(hashMapOfAddressBook.get(addressBookName));
        }
        System.out.println( addressBookName);
    }

    public static ArrayList<AddressBook> findAddressBook(String name){
        for (Map.Entry<String,ArrayList<AddressBook>> itr: hashMapOfAddressBook.entrySet()){
            if (itr.getKey().equals(name)){
                return itr.getValue();
            }
        }
        return null;
    }

    public static boolean checkDuplicate(String book_name, String first_name, String last_name){
        int f = 0;
        if (hashMapOfAddressBook.get(book_name)==null)
            return true;
        ArrayList<AddressBook> arrayList = hashMapOfAddressBook.get(book_name);
        for (AddressBook data : arrayList) {
            if (data.getFirstName().equals(first_name) && data.getLastName().equals(last_name))
                return false;
        }
        return true;
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