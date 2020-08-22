package org.testTask.DTO;

import java.time.Year;
import java.sql.Date;

/**
 * The type Book.
 */
public class Book extends AbstractDTO {
    /**
     * The title
     */
    private String title;

    /**
     * The author
     */
    private Author author;

    /**
     * The genre
     */
    private Genre genre;

    /**
     * The publisher
     */
    private Publisher publisher;

    /**
     * The year
     */
    private int year;

    /**
     * The city
     */
    private String city;

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Gets publisher.
     *
     * @return the publisher
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * Sets publisher.
     *
     * @param publisher the publisher
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Instantiates a new Book.
     *
     * @param id        the id
     * @param title     the title
     * @param author    the author
     * @param genre     the genre
     * @param publisher the publisher
     * @param year      the year
     * @param city      the city
     */
    public Book(
            long id, String title, Author author, Genre genre, Publisher publisher, int year, String city) {
        super(id);
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.year = year;
        this.city = city;
    }
}
