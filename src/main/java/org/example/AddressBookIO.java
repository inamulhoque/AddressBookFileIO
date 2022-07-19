package org.example;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.sun.tools.jdeprscan.CSV;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookIO {
    static CSV csv = new CSV();
    static File file = new File("AddressBook.csv");
    public static void writeIntoFile() throws IOException{
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        String[] strContacts = new String[40];
        List<String[]> contacts = new ArrayList<>();
        AddressBookFunctions.hashMapOfAddressBook.entrySet().stream().forEach(n->{
                    strContacts[0] = n.getKey();
                    strContacts[1] = n.getValue().toString();
                    contacts.add(strContacts);
                    csvWriter.writeAll(contacts);
                }
        );
        fileWriter.close();
    }
    public static void readFromFile() throws IOException{
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }
        scanner.close();
    }
}
