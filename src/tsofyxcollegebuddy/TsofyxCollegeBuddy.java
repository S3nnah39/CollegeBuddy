/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsofyxcollegebuddy;

import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *Image Source: https://icons8.com/illustrations/illustration/marginalia-girl-came-to-visit-her-friend
 * 
 * 
 * @author TashaOgoti
 */
public class TsofyxCollegeBuddy extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("TsofyxSignInFXML.fxml"));
        HBox root = new HBox();
        root.setPrefSize(710, 450);
        root.setAlignment(Pos.CENTER);
        Text message = new Text("This is a failure!");
        message.setFont(Font.font(STYLESHEET_MODENA, 32));
        root.getChildren().add(message);
        
        Scene scene = new Scene(root);
        
        Switchable.scene = scene;
        Switchable.switchTo("TsofyxSignInFXML");
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
