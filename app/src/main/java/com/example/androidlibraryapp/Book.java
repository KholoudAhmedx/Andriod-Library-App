package com.example.androidlibraryapp;

public class Book {
    // this is called POJO
    private int id, pages;
    private String name, ImageUrl, author, shortDesc, LongDesc;

    public Book(int id, int pages, String name, String imageUrl, String author, String shortDesc, String longDesc) {
        this.id = id;
        this.pages = pages;
        this.name = name;
        ImageUrl = imageUrl;
        this.author = author;
        this.shortDesc = shortDesc;
        LongDesc = longDesc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return LongDesc;
    }

    public void setLongDesc(String longDesc) {
        LongDesc = longDesc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", pages=" + pages +
                ", name='" + name + '\'' +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", author='" + author + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", LongDesc='" + LongDesc + '\'' +
                '}';
    }
}
