package gui.model.book;

import java.time.LocalDate;
import java.util.Date;

public class Book {

    private String bookName;

    private String seriesName;

    private LocalDate releaseDate;

    private String edition;

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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
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