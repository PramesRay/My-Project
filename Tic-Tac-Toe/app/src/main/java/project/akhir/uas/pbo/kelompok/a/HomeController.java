package project.akhir.uas.pbo.kelompok.a;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;                 //
import javafx.scene.Scene;                  //
import javafx.stage.Stage;                  //
import javafx.scene.Node;                   
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private Stage stage;
    private Parent root;   

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    
    public void openGame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/fxml/Game.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));                                  
        stage.show(); 
    }

    public void openHowToPlay(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/fxml/HowToPlay.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));                                  
        stage.show(); 
    }
}
