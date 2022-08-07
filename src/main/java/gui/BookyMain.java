package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class BookyMain extends Application {

    private Stage primaryStage;

    private BorderPane mainLayout;
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Booky");
        prepareViews();
        scene = new Scene(mainLayout);
        showMainView();
    }

    private void prepareViews() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BookyMain.class.getClassLoader().getResource("GenerateQRCodeView.fxml"));
        mainLayout = loader.load();
    }

    private void showMainView() {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static File showDirectoryChooser() {
        return new DirectoryChooser().showDialog(new Stage());
    }
}
