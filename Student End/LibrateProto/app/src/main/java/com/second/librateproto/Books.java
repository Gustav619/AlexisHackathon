package com.second.librateproto;

public class Books {
    private String author1, author2, author3, author4, image_link, bookid, name, quantity;

    public Books(String author1, String author2, String author3, String author4, String image_link, String bookid, String name, String quantity) {
        this.author1 = author1;
        this.author2 = author2;
        this.author3 = author3;
        this.author4 = author4;
        this.image_link = image_link;
        this.bookid = bookid;
        this.name = name;
        this.quantity = quantity;
    }

    public Books() {
    }

    public String getAuthor1() {
        return author1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    public String getAuthor2() {
        return author2;
    }

    public void setAuthor2(String author2) {
        this.author2 = author2;
    }

    public String getAuthor3() {
        return author3;
    }

    public void setAuthor3(String author3) {
        this.author3 = author3;
    }

    public String getAuthor4() {
        return author4;
    }

    public void setAuthor4(String author4) {
        this.author4 = author4;
    }

    public String getimage_link() {
        return image_link;
    }

    public void setimage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
