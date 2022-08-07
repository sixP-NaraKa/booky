package gui.view.generateqr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import gui.model.book.Book;
import gui.model.book.BookCustomMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class GenerateQRCodeViewController {

    @FXML
    private Button qrOutDirButton;

    @FXML
    private TextField qrOutDir;

    private File qrOutDirAsFile;

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
        qrOutDirAsFile = directoryChooser.showDialog(new Stage());
        if (qrOutDirAsFile == null) {
            generateQrCodeButton.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Select a directory!").showAndWait();
            return;
        }
        qrOutDir.setText(qrOutDirAsFile.getAbsolutePath());
        generateQrCodeButton.setDisable(false);
    }

    @FXML
    private void onGenerateQrCode() throws WriterException, IOException {
        System.out.println("Generate QR code...");
        Book book = BookCustomMapper.builder()
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

        // generating QR code
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(book.toString(), BarcodeFormat.QR_CODE, 200, 200);
        String filePath = "%s/%s_%s.png".formatted(qrOutDirAsFile.getAbsolutePath(), book.getBookName(), LocalDate.now());
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(filePath).toPath());

        System.out.println("QR Code generated: " + filePath);
    }

}
