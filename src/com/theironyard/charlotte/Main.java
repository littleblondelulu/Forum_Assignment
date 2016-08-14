package com.theironyard.charlotte;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        Book b = Book.createItem();

        File f = new File("Book.json");

        // write json
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(b);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();

        // read json
        Scanner s = new Scanner(f);
        s.useDelimiter("\\Z");
        String contents = s.next();
        JsonParser parser = new JsonParser();
        Book b2 = parser.parse(contents, Book.class);

        System.out.println(b2);

    }

}
