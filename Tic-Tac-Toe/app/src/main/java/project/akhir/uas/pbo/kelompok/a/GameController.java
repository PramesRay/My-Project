package project.akhir.uas.pbo.kelompok.a;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;  
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.Parent;                 
import javafx.scene.Scene;                  
import javafx.scene.shape.Line;             
import javafx.scene.shape.Rectangle;        
import javafx.scene.control.TextField;      
import javafx.scene.text.Font;              
import javafx.scene.text.FontWeight;        
import javafx.stage.Stage;                  

public class GameController implements Initializable {
    private Stage stage;
    private Parent root;

    @FXML
    private Button button1;             

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Label roundText;

    @FXML
    private TextField skorOField;
    @FXML
    private TextField skorXField;

    @FXML
    private Label playerX;
    @FXML
    private Label playerO;

    @FXML
    private Line lineX1;
    @FXML
    private Line lineX2;
    @FXML
    private Line lineX3;
    @FXML
    private Line lineX4;

    @FXML
    private Line lineO1;
    @FXML
    private Line lineO2;
    @FXML
    private Line lineO3;
    @FXML
    private Line lineO4;

    @FXML
    private Rectangle kotakX;
    @FXML
    private Rectangle kotakO;

    @FXML
    private Label winnerX;
    @FXML
    private Label winnerO;
    
    private int playerTurn = 0;
    private int roundCounter = 1;
    private int skorX = 0;
    private int skorO = 0;
    private Boolean muncul = true;
    String temp = "";

    ArrayList<Button> buttons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));  

        roundText.setText("ROUND 1");
        fontBold(playerX);
        fontRegular(playerO);
        
        buttons.forEach(button ->{                                                                                          
            setupButton(button);                                                                                            
            button.setFocusTraversable(false);                                                                              
        });
    }
    
    @FXML
    void restartGame(ActionEvent event) {                                                                                   
        buttons.forEach(this::resetButton);                                                                                 
    }
    
    public void resetButton(Button button){                                                                                       
        button.setDisable(false);                                                                                           
        button.setText("");  
        muncul = false;
        winnerXFrame(muncul);
        winnerOFrame(muncul);                                                                                            
        roundText.setText("ROUND " + String.valueOf(this.roundCounter));
    }
    
    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {                                                                            
            setPlayerSymbol(button);                                                                                        
            button.setDisable(true);                                                                                        
            checkIfGameIsOver();                                                                                            
        });
    }
    
    public void setPlayerSymbol(Button button){
        if(playerTurn % 2 == 0){                                                                                            
            button.setText("X");
            playerTurn = 1;
            fontBold(playerO);
            fontRegular(playerX);
        } else{                                                                                                             
            button.setText("O");
            playerTurn = 0;
            fontBold(playerX);
            fontRegular(playerO);
        }
    }

    public void fontBold(Label player){
        player.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
    }

    public void fontRegular(Label player){
        player.setFont(Font.font("SansSerif", 25));
        playerO.setStyle("-fx-font-weight: regular;");
    }

    public void winnerXFrame(Boolean muncul){
        if(muncul == true){
            lineX1.setVisible(true);
            lineX2.setVisible(true);
            lineX3.setVisible(true);
            lineX4.setVisible(true);
            kotakX.setVisible(true);
            winnerX.setVisible(true);
        }
        else {
            lineX1.setVisible(false);
            lineX2.setVisible(false);
            lineX3.setVisible(false);
            lineX4.setVisible(false);
            kotakX.setVisible(false);
            winnerX.setVisible(false);
        }
    }

    public void winnerOFrame(Boolean muncul){
        if(muncul == true){
            lineO1.setVisible(true);
            lineO2.setVisible(true);
            lineO3.setVisible(true);
            lineO4.setVisible(true);
            kotakO.setVisible(true);
            winnerO.setVisible(true);
        }
        else {
            lineO1.setVisible(false);
            lineO2.setVisible(false);
            lineO3.setVisible(false);
            lineO4.setVisible(false);
            kotakO.setVisible(false);
            winnerO.setVisible(false);
        }
    }
    
    public void gotWinnner() {
        if (muncul) {
            roundCounter++;
        }
    }

    private void setDisabledAll(){
        buttons.forEach(button ->{                                                                                          // Untuk menerapkan apa yang ada didalam scope terhadap seluruh button dengan looping
            button.setDisable(true);                                                                              // Untuk set Button agar tidak terseleksi sehingga menghasilkan border biru
        });
    }

    public void checkIfGameIsOver(){
        String line =  "";
        for (int a = 0; a < 8; a++) {
            switch (a) {                                                                                      
                case 0: 
                    line =  button1.getText() + button2.getText() + button3.getText();                                        
                    break;
                case 1: 
                    line =  button4.getText() + button5.getText() + button6.getText();                                        
                    break;
                case 2: 
                    line =  button7.getText() + button8.getText() + button9.getText();                                        
                    break;

                case 3: 
                    line =  button1.getText() + button5.getText() + button9.getText();                                        
                    break;
                case 4: 
                    line =  button3.getText() + button5.getText() + button7.getText();                                        
                    break;

                case 5: 
                    line =  button1.getText() + button4.getText() + button7.getText();                                        
                    break;
                case 6: 
                    line =  button2.getText() + button5.getText() + button8.getText();                                        
                    break;
                case 7: 
                    line =  button3.getText() + button6.getText() + button9.getText();                                        
                    break;
                default: 
                    line =  null;
            }; 
            if (line.equals("XXX")) {                                                                                       
                muncul = true;
                winnerXFrame(muncul);   
                roundCounter++;  
                skorX++;
                skorXField.setText(String.valueOf(skorX));  
                setDisabledAll(); 
            }
            
            
            else if (line.equals("OOO")){                                                                                   
                muncul = true;
                winnerOFrame(muncul);  
                roundCounter++;                                                                             
                skorO++;
                skorOField.setText(String.valueOf(skorO));
                setDisabledAll();
            }
        }
        
        temp = "";
        for (Button button : buttons) {
            temp = temp + button.getText();                                                                         
        }
        
        if (temp.length() == 9 && muncul == false) {   
            roundText.setText("DRAW!");   
            this.roundCounter++;                                                                                                                                                      
        }
    }

    public void openHome(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));                                  
        stage.show(); 
    }
}
