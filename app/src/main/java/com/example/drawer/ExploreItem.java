package com.example.drawer;

public class ExploreItem {
    private int image;
    private String title;
    private String content;
    private String author;


    public ExploreItem(int image, String title, String content, String author) {
        this.image = image;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
