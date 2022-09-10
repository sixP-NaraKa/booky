package gui.model.book;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public final class BookCustomMapper {

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

    public BookCustomMapper setSeriesEntry(TextFormatter<Integer> formatter) {
        book.setSeriesEntry(formatter.getValue());
        return this;
    }

    public BookCustomMapper setReleaseDate(DatePicker node) {
        book.setReleaseDate(node.getValue());
        return this;
    }

    public BookCustomMapper setEdition(TextFormatter<Integer> formatter) {
        book.setEdition(formatter.getValue());
        return this;
    }

    public BookCustomMapper setLanguage(TextField node) {
        book.setLanguage(node.getText());
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

    public BookCustomMapper setChapterAmount(TextFormatter<Integer> formatter) {
        book.setChapterAmount(formatter.getValue());
        return this;
    }

    public BookCustomMapper setPageAmount(TextFormatter<Integer> formatter) {
        book.setPageAmount(formatter.getValue());
        return this;
    }

    public BookCustomMapper setPublisher(TextField node) {
        book.setPublisher(node.getText());
        return this;
    }

    public BookCustomMapper setPublishPlace(TextField node) {
        book.setPublishPlace(node.getText());
        return this;
    }
}
