package service.openlibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Book data model which is used to map the openlibrary.org API response to an object via Jackson.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibraryBookData implements Serializable {

    @JsonIgnore
    private Long isbn;

    /* openlibrary API response parameters */

    private String title;

    private List<Author> authors;

    @JsonProperty(value = "number_of_pages")
    private int numberOfPages;

    private List<Publisher> publishers;

    @JsonProperty(value = "publish_date")
    private int publishDate;

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public int getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(int publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "OpenLibraryBookData{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", numberOfPages=" + numberOfPages +
                ", publishers=" + publishers +
                ", publishDate=" + publishDate +
                '}';
    }
}
