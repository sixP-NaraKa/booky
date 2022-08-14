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

    public String getFormattedText() {
        return "Book: " + bookName + " \n" +
                "Series: " + seriesName + "\tEntry: " + seriesEntry + " \n" +
                "Release: " + releaseDate + "\tEdition: " + edition + "\tLanguage: " + language + " \n" +
                "Author: " + author + "\tArtist: " + artist + " \n" +
                "Chapters: " + chapterAmount + "\tPages: " + pageAmount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", seriesName='" + seriesName + '\'' +
                ", releaseDate=" + releaseDate +
                ", edition='" + edition + '\'' +
                ", author='" + author + '\'' +
                ", artist='" + artist + '\'' +
                ", chapterAmount=" + chapterAmount +
                ", pageAmount=" + pageAmount +
                '}';
    }
}
