package com.second.librateproto;
public class Profile {
    String Book_Id,Fine,Name;

    public Profile() {
    }

    public String getBook_Id() {
        return Book_Id;
    }

    public void setBook_Id(String book_Id) {
        Book_Id = book_Id;
    }

    public String getFine() {
        return Fine;
    }

    public void setFine(String fine) {
        Fine = fine;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Profile(String book_Id, String fine, String name) {
        Book_Id = book_Id;
        Fine = fine;
        Name = name;
    }
}

