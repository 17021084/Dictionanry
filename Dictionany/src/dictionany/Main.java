/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionany;

import dictionarymanagement.DictionaryManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author DoQuangTrung
 */
public class Main  extends Application  {
    
    @Override
    public void start(Stage primaryStage) {
       try{
          // get cai file FXML.fxml
           Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
           Scene scene = new Scene(root);
           primaryStage.setScene(scene);
           primaryStage.show();
           
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   
        launch(args);

    }

   
}
