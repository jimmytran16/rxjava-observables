package com.example.reactivejavatest.tests;

import com.example.reactivejavatest.models.Books;

import java.util.ArrayList;

public class Generate {
    public static ArrayList<Books> getBookList(){
        ArrayList<Books> bookList = new ArrayList<>();
        Books book1 = new Books("Book1","Introduction to Java Programming",9.44);
        Books book2 = new Books("Book2","Python",10.44);
        Books book3 = new Books("Book3","C++",12.44);
        Books book4 = new Books("Book4","Programming in C",12.44);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        return bookList;
    }
    public static ArrayList<Books> getBookList2(){
        ArrayList<Books> bookList = new ArrayList<>();
        Books book1 = new Books("Book1","Introduction to Java Programming",9.44);
        Books book2 = new Books("Book2","Python",10.44);
        Books book3 = new Books("Book3","C++",12.44);
        Books book4 = new Books("Book4","Programming in C",12.44);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        return bookList;
    }
}
