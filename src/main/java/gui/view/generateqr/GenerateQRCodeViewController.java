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
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.function.UnaryOperator;

public final class GenerateQRCodeViewController {

    private Properties applicationProperties;

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
    public void initialize() throws IOException {
        System.out.println("Initializing " + getClass().getName());

        // load application.properties file and set initial directory if present
        applicationProperties = new Properties();
        applicationProperties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        qrOutDirAsFile = new File(applicationProperties.getProperty("booky.qrcode.out"));
        qrOutDir.setText(qrOutDirAsFile.exists() && qrOutDirAsFile.isDirectory() ? qrOutDirAsFile.getAbsolutePath() : "");

        if (qrOutDir.getText() == null || qrOutDir.getText().isBlank()) generateQrCodeButton.setDisable(true);

        UnaryOperator<TextFormatter.Change> numberValidation = change -> {
            if (!change.getText().matches("([0-9]+)?")) { // allow empty string, e.g. to delete input
                return null;
            }

            // if: add "1" to the start immediately if 0
            // else: add "1", no empty input allowed after the next round of change detection (see ".getControlText()")
            //       this is useful since the user can then delete all text without change detection firing instantly
            int newTextLength = change.getControlNewText().length();
            if (change.getControlNewText().startsWith("0")) {
                int addedPos = newTextLength > 1 ? 1 : 0; // prevent IndexOutOfBoundsException during replacing of all input
                getChange(change, "1", addedPos + newTextLength,
                        addedPos + newTextLength);
            } else if (change.getControlText().isBlank() && newTextLength == 0) {
                getChange(change, "1", 1, 1);
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
    }

    private void getChange(TextFormatter.Change change, String text, int caretPosition, int anchorPosition) {
        change.setText(text);
        change.setCaretPosition(caretPosition);
        change.setAnchor(anchorPosition);
    }

    @FXML
    private void onQrOutDir() throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(qrOutDirAsFile);
        final File selectedOutDir = directoryChooser.showDialog(new Stage());
        if (selectedOutDir == null && qrOutDirAsFile == null) {
            generateQrCodeButton.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Select a directory!").showAndWait();
            return;
        }
        qrOutDirAsFile = selectedOutDir != null ? selectedOutDir : qrOutDirAsFile;
        applicationProperties.setProperty("booky.qrcode.out", qrOutDirAsFile.getAbsolutePath());
        applicationProperties.store(new FileWriter("src/main/resources/application.properties"), null);
        qrOutDir.setText(qrOutDirAsFile.getAbsolutePath());
        generateQrCodeButton.setDisable(false);
    }

    @FXML
    private void onGenerateQrCode() throws WriterException, IOException {
        qrCodeImage.setImage(null);
        qrCodeText.setText("");

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
        qrCodeText.setText(book.getBookName());
    }

    private String generateQrCode(Book book) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(book.getFormattedText(), BarcodeFormat.QR_CODE, 200, 200);
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
