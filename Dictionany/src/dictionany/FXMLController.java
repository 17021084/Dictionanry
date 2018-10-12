/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionany;
import dictionarymanagement.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author DoQuangTrung
 */
public class FXMLController  implements Initializable {
    
    public DictionaryManagement dic =new DictionaryManagement();
    public  Voice voice= new Voice("kevin16");
    
    @FXML
     private TextField type ,rate;
    @FXML 
    private WebView descrip;
    @FXML
    private ListView recently = new ListView<>();
    @FXML
    private ListView listSearch = new ListView<>();
    
//    @FXML 
//    private ListView listWord;
//     @FXML
//     private Button search , add , delete , speech ,fix;
    
    public void setConstant(ActionEvent e){
        String num = rate.getText();
       
        if( num.equals("")){
            voice.say("default constant corrects ,is 50 percent ");
            
            return;
        }else{
            double value = Double.parseDouble(num);
           if( value <0 || value>100){
               voice.say("constant corrects ,is out of scope ");
               return;
           }
           
           voice.say("constant corrects is "+value +" percent");
           value/=100;
            dic.setCORRECT_CONSTANT(value);
            System.out.println("constant is " + value);
        }
        
    }
    //xong
    public void display(ActionEvent e){
        type.setText(listSearch.getTypeSelector());
                     
    }
  
    // xong
     public void search(ActionEvent e ){      
          
         String word = type.getText();// lay text tu cai textfield
         // hàm search sẽ trả về  một array list <word>        
         // duyet toan bo nhung tu tim duoc
         ArrayList<Word> listWordTaget = new ArrayList<>();
         listWordTaget = dic.dictionarySearcher(word);
         if (listWordTaget.size()==1){
             voice.say("Foud" );
          //   descrip.setText( listWordTaget.get(0).word_explain );
 
            WebEngine engin =new WebEngine();
             engin = descrip.getEngine();
             engin.loadContent(listWordTaget.get(0).word_explain);
         }else {
             voice.say("Cant find , ,i, recommend, some word ,below");
             
         }
         listSearch.getItems().clear();
         for ( int i = 0 ; i < listWordTaget.size() ; i++){
             listSearch.getItems().addAll(listWordTaget.get(i).word_target );
             
             
         }

         
     }

     
     // xong
     public void delete(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         String word = type.getText();// lay text tu cai textfield
  
        // set tile cho alert
        alert.setTitle("Delete Word");
        alert.setHeaderText( " Do you want to delete  " + word );
        alert.setContentText("Choose your information");
         voice.say("Do you want to delete  " + word + "  do  not regret ");
        
        // tao button
        ButtonType btYes = new ButtonType("Yes", ButtonBar.ButtonData.YES );
        ButtonType btNo = new ButtonType("No", ButtonBar.ButtonData.NO );
        ButtonType btCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
         
         // dua ca button vao alert
        
        alert.getButtonTypes().setAll(btYes, btNo ,btCancel);
        // cái result  mà client choose
        Optional<ButtonType>  result = alert.showAndWait();
        String message;
        if ( result.get()==btYes ){
            System.out.println("Da an yes");
            message=" Well done ";
             voice.say("Delete ." + word + ". complete ");
             dic.deleteWord(word);
            
        }else if ( result.get().getButtonData() == ButtonBar.ButtonData.NO ){
            System.out.println("da an no");
              message="No";
                voice.say("cancel");
        }
        else{
            System.out.println("cancel");
            message="Cancel";
          
        }
        
        Alert alert1 =new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Delete");
        alert1.setHeaderText("Notification");
        alert1.setContentText(message);
        alert1.show();
        
     }
     
     
     public void add(ActionEvent e){
         
         
     }
     
     
     //
     public void fix(ActionEvent e ){
         
         
     }
     
     // xong
      public void speech(ActionEvent e){
         
          String word = type.getText();// lay text tu cai textfield        
          if (word.equals("") ){
           voice.say("you must type, to listen");
          }else {
          voice.say(word);           
          }
     }
     
     
     
     
      
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        
}
