package org.example;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AddressBookIO {
    static Gson gson = new Gson();
    static File file = new File("addressbook.json");
    public static void writeIntoFile() throws IOException{
        String str = gson.toJson(AddressBookFunctions.hashMapOfAddressBook);
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(str);
        fileWriter.close();
    }
    public static void readFromFile() throws IOException{
        FileReader fileReader = new FileReader(file);
        Object object = gson.toJson(fileReader,Object.class);
        System.out.println(object);
    }
}
