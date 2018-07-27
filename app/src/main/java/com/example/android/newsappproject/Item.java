package com.example.android.newsappproject;

public class Item {
    // Article title
    private String title;

    // Author of article
    private String author;

    // Publish date
    private String publishDate;

    // Article content
    private String content;

    // Section name
    private String sectionName;

    public Item(String title, String author, String publishDate, String content, String sectionName) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.content = content;
        this.sectionName = sectionName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getContent() {
        return content;
    }

    public String getSectionName() {
        return sectionName;
    }
}
