package gui.view.generateqr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import gui.model.book.Book;
import gui.model.book.BookCustomMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.function.UnaryOperator;

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
    private TextField seriesEntry;

    private TextFormatter<Integer> seriesEntryFormatter;

    @FXML
    private DatePicker releaseDate;

    @FXML
    private TextField edition;

    private TextFormatter<Integer> editionFormatter;

    @FXML
    private TextField language;

    @FXML
    private TextField author;

    @FXML
    private TextField artist;

    @FXML
    private TextField chapterAmount;

    private TextFormatter<Integer> chapterAmountFormatter;

    @FXML
    private TextField pageAmount;

    private TextFormatter<Integer> pageAmountFormatter;

    @FXML
    private ImageView qrCodeImage;

    @FXML
    private Text qrCodeText;

    @FXML
    public void initialize() {
        System.out.println("Initializing " + getClass().getName());

        if (qrOutDir.getText() == null || qrOutDir.getText().isBlank()) generateQrCodeButton.setDisable(true);

        UnaryOperator<TextFormatter.Change> numberValidation = change -> {
            String newText = change.getText();
            if (!newText.matches("([1-9]+)?")) { // allow empty string, e.g. to delete input
                // check if only a 0 has been inputted
                if (newText.matches("0") && change.getControlNewText().length() > 1) return change;
                return null;
            }
            return change;
        };
        seriesEntryFormatter = new TextFormatter<>(new IntegerStringConverter(), 1, numberValidation);
        seriesEntry.setTextFormatter(seriesEntryFormatter);

        editionFormatter = new TextFormatter<>(new IntegerStringConverter(), 1, numberValidation);
        edition.setTextFormatter(editionFormatter);

        chapterAmountFormatter = new TextFormatter<>(new IntegerStringConverter(), 1, numberValidation);
        chapterAmount.setTextFormatter(chapterAmountFormatter);

        pageAmountFormatter = new TextFormatter<>(new IntegerStringConverter(), 1, numberValidation);
        pageAmount.setTextFormatter(pageAmountFormatter);

//        seriesEntryFormatter.valueProperty().addListener(((observableValue, integer, t1) -> System.out.printf("%s - %s - %s -- %s\n", observableValue, integer, t1, t1.getClass())));
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
        qrCodeImage.setImage(null);

        System.out.println("Generating QR code...");
        Book book = BookCustomMapper.builder()
                .setBookName(bookName)
                .setSeriesName(seriesName)
                .setSeriesEntry(seriesEntryFormatter)
                .setReleaseDate(releaseDate)
                .setEdition(editionFormatter)
                .setLanguage(language)
                .setAuthor(author)
                .setArtist(artist)
                .setChapterAmount(chapterAmountFormatter)
                .setPageAmount(pageAmountFormatter)
                .build();

        System.out.println("New Book: " + book);

        // generating QR code
        final String qrCodeFilePath = generateQrCode(book);

        qrCodeImage.setImage(new Image(qrCodeFilePath));
    }

    private String generateQrCode(Book book) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(book.toString(), BarcodeFormat.QR_CODE, 200, 200);
        String filePath = "%s/%s-Vol.%s_%s.png".formatted(
                qrOutDirAsFile.getAbsolutePath(),
                book.getBookName(),
                book.getSeriesEntry(),
                LocalDate.now());
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(filePath).toPath());

        System.out.println("QR Code generated: " + filePath);
        return filePath;
    }

}
