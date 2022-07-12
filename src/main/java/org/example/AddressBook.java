package org.example;

public class AddressBook {
    static String firstName;
    static String lastName;
    static String city;
    static String state;
    static int zip;
    static long phone;
    static String mail;

    /*static List<AddressBook> contacts = new ArrayList<AddressBook>();*/
    public AddressBook(String firstName,String lastName,String city, String state,int zip,long phone,String mail) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.mail = mail;
    }
    public AddressBook(){}

    public String toString(){
        return "Contact details are:\n"+"First Name is: "+firstName+"\nLast Name is: "+lastName+"\nCity is: "+city
                +"\nState is: "+state+"\nZip code is: "+zip+"\nPhone number  is: "+phone+"\nEmail id is: "+mail;
    }

    public static String getFirstName() {
        return firstName;
    }
    public static void setFirstName(String firstName) {
        AddressBook.firstName = firstName;
    }
    public static String getLastName() {
        return lastName;
    }
    public static void setLastName(String lastName) {
        AddressBook.lastName = lastName;
    }
    public static String getCity() {
        return city;
    }
    public static void setCity(String city) {
        AddressBook.city = city;
    }
    public static String getState() {
        return state;
    }
    public static void setState(String state) {
        AddressBook.state = state;
    }
    public static int getZip() {
        return zip;
    }
    public static void setZip(int zip) {
        AddressBook.zip = zip;
    }
    public static long getPhone() {
        return phone;
    }
    public static void setPhone(long phone) {
        AddressBook.phone = phone;
    }
    public static String getMail() {
        return mail;
    }
    public static void setMail(String mail) {
        AddressBook.mail = mail;
    }

}
