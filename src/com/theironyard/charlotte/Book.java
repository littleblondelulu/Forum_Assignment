package com.theironyard.charlotte;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.theironyard.charlotte.Main.books;
import static java.lang.System.out;


public class Book {
    private String title;
    private String author;
    private String genre;
    private String numOfPages;
    private String publisher;

    Book(){}

    public Book(String title, String author, String genre, String numOfPages, String publisher) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.numOfPages = numOfPages;
        this.publisher = publisher;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNumOfPages() {
        return numOfPages;
    }

    void setNumOfPages(String numOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public static void saveBook() throws IOException {
        Book book = new Book();
        JsonSerializer s = new JsonSerializer();
        String json = s.include("*").serialize(book);
        File f = new File("book.json");

        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    //If the JSON parsing fails, give a friendly error rather than crashing the program.
    public static String nextLine() throws IOException{
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (line.startsWith("/")) {
            switch (line) {
                case "/save":
                    try {
                        Book.saveBook();
                        out.println("Saved book.");
                        for(int i = 0; i < books.size(); i++){
                            System.out.println(books.get(i));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        out.println("The server has thrown an exception.");
                    }
                    break;
            }
            line = scanner.nextLine();
        }
        return line;
    }

    public static void loadBook() throws FileNotFoundException {
        File f = new File("book.json");
        Scanner fileScanner = new Scanner(f);

        //Scanner will continue to parse and store data from json file until there's a line with nothing to scan (the end of the file)
        while (fileScanner.hasNext()) {
            fileScanner.useDelimiter("\\Z");
            String contents = fileScanner.nextLine();

            //parse json file and save each line to books arrayList and read into memory
            JsonParser parser = new JsonParser();
            Book b2 = parser.parse(contents, Book.class);
            books.add(b2);
        }
    }

    public static void updateBook() throws Exception {
        Book book = new Book();
        Scanner consoleScanner = new Scanner(System.in);

        //1: Read in json file and save each line to memory and add them to books arrayList
        loadBook();

        //2: Get the book object to update from our user and iterate over books to check input against each book
        System.out.println("Enter the title of the book you would like to update.");
        String titleForUpdate = consoleScanner.nextLine();
        for (int i = 0; i < books.size(); i++) {
            if (book.equals(i) && book.getTitle().equalsIgnoreCase(titleForUpdate)) {
                int idx = books.indexOf(book);
                book = books.get(idx);

                System.out.println("What would you like to update? [title, author, genre, numOfPages, publisher]");
                String fieldToUpdate = consoleScanner.nextLine();

                System.out.println("What would you like to change it to?");
                String valueToSet = consoleScanner.nextLine();

                switch (fieldToUpdate) {
                    case "title":
                        book.setTitle(valueToSet);
                        books.set(idx, book);
                        System.out.println("Book has been updated");
                        break;

                    case "author":
                        book.setAuthor(valueToSet);
                        books.set(idx, book);
                        System.out.println("Book has been updated");
                        break;

                    case "genre":
                        book.setGenre(valueToSet);
                        books.set(idx, book);
                        System.out.println("Book has been updated");
                        break;

                    case "numOfPages":
                        book.setNumOfPages(valueToSet);
                        books.set(idx, book);
                        System.out.println("Book has been updated");
                        break;

                    case "publisher":
                        book.setPublisher(valueToSet);
                        books.set(idx, book);
                        System.out.println("Book has been updated");
                        break;

                    default:
                        System.out.println("Sorry, that is not a valid option");
                        break;
                }
            }

            File f = new File("book.json");
            JsonSerializer serializer = new JsonSerializer();
            String json = serializer.serialize(books);

            FileWriter fw = new FileWriter(f);
            fw.write(json);
            fw.close();
        }
    }

}
