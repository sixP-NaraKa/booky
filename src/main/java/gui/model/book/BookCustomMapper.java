package gui.model.book;

import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BookCustomMapper {

    private final Book book;

    private BookCustomMapper() {
        book = new Book();
    }

    public static BookCustomMapper builder() {
        return new BookCustomMapper();
    }

    public Book build() {
        return book;
    }

    public BookCustomMapper setBookName(TextField node) {
        book.setBookName(node.getText());
        return this;
    }

    public BookCustomMapper setSeriesName(TextField node) {
        book.setSeriesName(node.getText());
        return this;
    }

    public BookCustomMapper setReleaseDate(TextField node) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.GERMAN);
        book.setReleaseDate(LocalDate.parse(node.getText(), formatter));
        return this;
    }

    public BookCustomMapper setEdition(TextField node) {
        book.setEdition(node.getText());
        return this;
    }

    public BookCustomMapper setAuthor(TextField node) {
        book.setAuthor(node.getText());
        return this;
    }

    public BookCustomMapper setArtist(TextField node) {
        book.setArtist(node.getText());
        return this;
    }

    public BookCustomMapper setChapterAmount(TextField node) {
        book.setChapterAmount(Integer.parseInt(node.getText().isBlank() ? "0" : node.getText()));
        return this;
    }

    public BookCustomMapper setPageAmount(TextField node) {
        book.setPageAmount(Integer.parseInt(node.getText().isBlank() ? "0" : node.getText()));
        return this;
    }
}
