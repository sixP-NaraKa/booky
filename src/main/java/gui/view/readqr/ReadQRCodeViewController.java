package gui.view.readqr;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import gui.util.Chooser;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class ReadQRCodeViewController {

    @FXML
    private Button qrCodeReadButton;

    @FXML
    private TextField qrCodeReadField;

    @FXML
    private TextArea qrCodeDataOutputField;


    @FXML
    private void onQrCodeRead() {
        final File selectedQrCode = Chooser.ofFileOpen(null);
        if (selectedQrCode == null) {
            new Alert(Alert.AlertType.ERROR, "Select a QR Code!").showAndWait();
        } else {
            qrCodeReadField.setText(selectedQrCode.getAbsolutePath());
            try {
                String result = readQrCode(selectedQrCode);
                qrCodeDataOutputField.setText(result);
            } catch (IOException | NotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Could not read QR Code!").showAndWait();
            }
        }
    }

    private String readQrCode(File file) throws IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(
                        new BufferedImageLuminanceSource(
                                ImageIO.read(new FileInputStream(file))
                        )));

        Result result = new MultiFormatReader().decode(binaryBitmap);

        return result.getText();
    }
}
