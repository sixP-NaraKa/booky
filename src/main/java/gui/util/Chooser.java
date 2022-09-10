package gui.util;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public final class Chooser {

    public static File ofDirectory(File initialDirectory) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        if (initialDirectory != null && initialDirectory.exists() && initialDirectory.isDirectory()) {
            directoryChooser.setInitialDirectory(initialDirectory);
        }
        return directoryChooser.showDialog(new Stage());
    }

    public static File ofFileOpen(File initialDirectory) {
        final FileChooser fileChooser = new FileChooser();
        if (initialDirectory != null && initialDirectory.exists() && initialDirectory.isDirectory()) {
            fileChooser.setInitialDirectory(initialDirectory);
        }
        final FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("QR Codes",
                "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser.showOpenDialog(new Stage());
    }
}
