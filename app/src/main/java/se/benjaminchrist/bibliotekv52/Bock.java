package se.benjaminchrist.bibliotekv52;

/**
 * Created by Benjamin on 2017-02-14.
 */

public class Bock {

    private String title;
    private String author;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bock(String title) {
        this.title = title;
    }

    public Bock(String title, String author, long id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }

    public Bock(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return title;
    }
}
