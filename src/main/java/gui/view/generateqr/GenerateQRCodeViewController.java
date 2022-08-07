package gui.view.generateqr;

import gui.model.book.Book;
import gui.model.book.BookCustomMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class GenerateQRCodeViewController {

    @FXML
    private Button qrOutDirButton;

    @FXML
    private TextField qrOutDir;

    @FXML
    private Button generateQrCodeButton;

    /* TextFields which hold the book information */

    @FXML
    private TextField bookName;

    @FXML
    private TextField seriesName;

    @FXML
    private TextField releaseDate;

    @FXML
    private TextField edition;

    @FXML
    private TextField author;

    @FXML
    private TextField artist;

    @FXML
    private TextField chapterAmount;

    @FXML
    private TextField pageAmount;

    @FXML
    public void initialize() {
        System.out.println("Initializing " + getClass().getName());
        if (qrOutDir.getText() == null || qrOutDir.getText().isBlank()) generateQrCodeButton.setDisable(true);
    }

    @FXML
    private void onQrOutDir() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if (selectedDirectory == null) {
            generateQrCodeButton.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Select a directory!").showAndWait();
            return;
        }
        qrOutDir.setText(selectedDirectory.getAbsolutePath());
        generateQrCodeButton.setDisable(false);
    }

    @FXML
    private void onGenerateQrCode() {
        System.out.println("Generate QR code...");
        Book book = new BookCustomMapper().builder()
                .setBookName(bookName)
                .setSeriesName(seriesName)
                .setReleaseDate(releaseDate)
                .setEdition(edition)
                .setAuthor(author)
                .setArtist(artist)
                .setChapterAmount(chapterAmount)
                .setPageAmount(pageAmount)
                .build();

        System.out.println("New Book: " + book);
    }

}
