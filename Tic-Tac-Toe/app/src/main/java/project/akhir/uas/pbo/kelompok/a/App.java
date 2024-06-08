package project.akhir.uas.pbo.kelompok.a;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml")); // Untuk load file fxml / UI
        primaryStage.setTitle("Tic Tac Toe");                                       // Untuk set judul Aplikasi
        primaryStage.setScene(new Scene(root));                                     // Untuk set root ke dalam scene, lalu set scene ke dalam Stage primarystage
        primaryStage.show();                                                        // Untuk menampilkan stage
    }


    public static void main(String[] args) {
        launch(args);                                                               //Untuk menjalankan program
    }
}
