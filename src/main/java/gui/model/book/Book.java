package gui.model.book;

import java.time.LocalDate;

public final class Book {

    private String bookName;

    private String seriesName;

    private int seriesEntry;

    private LocalDate releaseDate;

    private int edition;

    private String language;

    private String author;

    private String artist;

    private int chapterAmount;

    private int pageAmount;

    private Long isbn;

    private String publisher;

    private String publishPlace;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public int getSeriesEntry() {
        return this.seriesEntry;
    }

    public void setSeriesEntry(int seriesEntry) {
        this.seriesEntry = seriesEntry;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getChapterAmount() {
        return chapterAmount;
    }

    public void setChapterAmount(int chapterAmount) {
        this.chapterAmount = chapterAmount;
    }

    public int getPageAmount() {
        return pageAmount;
    }

    public void setPageAmount(int pageAmount) {
        this.pageAmount = pageAmount;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishPlace() {
        return publishPlace;
    }

    public void setPublishPlace(String publishPlace) {
        this.publishPlace = publishPlace;
    }

    public String getFormattedText() {
        return "Book: " + bookName + " \n" +
                "Series: " + seriesName + " \nEntry: " + seriesEntry + " \n" +
                "Release: " + releaseDate + " \nEdition: " + edition + " \nLanguage: " + language + " \n" +
                "Author: " + author + " \nArtist: " + artist + " \n" +
                "Chapters: " + chapterAmount + " \nPages: " + pageAmount + "\nISBN: " + isbn + "\n" +
                "Publisher: " + publisher + "\n" + "Publish Place: " + publishPlace;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", seriesName='" + seriesName + '\'' +
                ", seriesEntry=" + seriesEntry +
                ", releaseDate=" + releaseDate +
                ", edition=" + edition +
                ", language='" + language + '\'' +
                ", author='" + author + '\'' +
                ", artist='" + artist + '\'' +
                ", chapterAmount=" + chapterAmount +
                ", pageAmount=" + pageAmount +
                ", isbn=" + isbn +
                ", publisher='" + publisher + '\'' +
                ", publishPlace='" + publishPlace + '\'' +
                '}';
    }
}
