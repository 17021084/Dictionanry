/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionany;

import dictionarymanagement.DictionaryManagement;
import dictionarymanagement.Voice;
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
        public  Voice voice= new Voice("kevin16");

    @Override
    public void start(Stage primaryStage) {
       try{
          // get cai file FXML.fxml
           Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
           Scene scene = new Scene(root);
           primaryStage.setTitle("Dictionary");
           primaryStage.setScene(scene);
           primaryStage.show();
          /*
             voice.say("Mi na ,san, ko,ni,shi,wa");
             voice.say("wa ta she wow   z  so  dets ");
             voice.say("rolo she ku");
             voice.say("oh,ne,gai,she,ma,su");
             voice.say("xin chao toi la tu di ence" );
             voice.say(" toi share noi tieng  viet nam ,");
             voice.say(" nen ,  share , khong hay phai khong cac ban  ");
             voice.say("thong cam ne ma ");
           */
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
