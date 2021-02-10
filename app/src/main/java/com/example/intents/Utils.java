package com.example.intents;

import java.util.ArrayList;

public class Utils {
    private static Utils instance;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> favoriteBooks;

    private Utils() {
        if(null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }

        if(null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }

        if(null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }

        if(null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }

        if(null == favoriteBooks){
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initData() {

        allBooks.add(new Book(1,
                "The Alchemist",
                "Paulo Coelho",
                200,
                "https://images-na.ssl-images-amazon.com/images/I/410llGwMZGL._SX328_BO1,204,203,200_.jpg",
                "Masterpiece!! is what you can say if you want to describe in one word...",
                "new books in the market"));

        allBooks.add(new Book(2,
                "Zero to One",
                "Peter Thiel",
                180,
                "https://images-na.ssl-images-amazon.com/images/I/71uAI28kJuL.jpg",
                "Masterpiece!! is what you can say if you want to describe in one word...",
                "just bought this one"));

        allBooks.add(new Book(3,
                "diary of a young girl",
                "Anne Frank",
                250,
                "https://m.media-amazon.com/images/I/51gPCT7Hm9L.jpg",
                "Masterpiece!! is what you can say if you want to describe in one word...",
                "good book"));

        allBooks.add(new Book(4,
                "12 rules for life",
                "Jordan Peterson",
                220,
                "https://images-na.ssl-images-amazon.com/images/I/41kspFBwVxL._SX331_BO1,204,203,200_.jpg",
                "Masterpiece!! is what you can say if you want to describe in one word...",
                "want to read this one"));

    }

    public static synchronized Utils getInstance() {
        if(null != instance){
            return instance;
        }
        else{
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Book getBook(int id){
        for(Book b: allBooks){
            if(b.getId() == id){
                return b;
            }
        }

        return null;
    }

    public boolean addToAlreadyRead(Book book){
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book){
        return wantToReadBooks.add(book);
    }

    public boolean addToCurrentlyReading(Book book){
        return currentlyReadingBooks.add(book);
    }

    public boolean addToFavoriteBook(Book book){
        return favoriteBooks.add(book);
    }

    public boolean removeAlreadyRead(Book book){
        return alreadyReadBooks.remove(book);
    }

    public boolean removeCurrentlyReading(Book book){
        return currentlyReadingBooks.remove(book);
    }

    public boolean removeWantToRead(Book book){
        return wantToReadBooks.remove(book);
    }

    public boolean removeFavorite(Book book){
        return favoriteBooks.remove(book);
    }
}
