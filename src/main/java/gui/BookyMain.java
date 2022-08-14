package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BookyMain extends Application {

    private Stage primaryStage;

    private TabPane mainTabPane;

    private Scene scene;

    /* Different custom views */

    private BorderPane generateQrCodeView;
    private BorderPane readQrCodeView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Booky");
        primaryStage.setResizable(false);
        prepareViews();
        scene = new Scene(mainTabPane);
        showMainView();
    }

    private void prepareViews() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BookyMain.class.getClassLoader().getResource("MainView.fxml"));
        mainTabPane = loader.load();

        FXMLLoader generateQrloader = new FXMLLoader();
        generateQrloader.setLocation(BookyMain.class.getClassLoader().getResource("generateqr/GenerateQRCodeView.fxml"));
        generateQrCodeView = generateQrloader.load();

        FXMLLoader readQrLoader = new FXMLLoader();
        readQrLoader.setLocation(BookyMain.class.getClassLoader().getResource("readqr/ReadQRCodeView.fxml"));
        readQrCodeView = readQrLoader.load();
    }

    private void showMainView() {
        primaryStage.setScene(scene);
        primaryStage.show();

        mainTabPane.getTabs().get(0).setContent(generateQrCodeView);
        mainTabPane.getTabs().get(1).setContent(readQrCodeView);
    }

}
