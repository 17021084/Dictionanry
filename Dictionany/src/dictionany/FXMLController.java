/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionany;
import dictionarymanagement.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DoQuangTrung
 */
public class FXMLController  implements Initializable {
    
    public boolean boolAdd = false, boolEdit=false;
    public DictionaryManagement dic =new DictionaryManagement();
    public  Voice voice= new Voice("kevin16");
    public ArrayList<String> recent = new ArrayList<>();  
    public ArrayList<Word> listWordTaget = new ArrayList<>();
    
    @FXML
     TextField type ,rate;
    @FXML 
     WebView descrip;
    @FXML
     ListView recently = new ListView<>();
    @FXML
     ListView listSearch = new ListView<>();
    
//    @FXML 
//    private ListView listWord;
//     @FXML
//     private Button search , add , delete , speech ,fix;
    
    public void recentlyUptoType(){
      
       recently.setOnMouseClicked(t->{
          String uptoType="";
           ObservableList <String> click;
       click= recently.getSelectionModel().getSelectedItems();
       for( String m : click){
           uptoType+=m;
       }       
       type.setText(uptoType);  
       
       });  
    }
    
      public void ListSearchUptoType(){
      
       listSearch.setOnMouseClicked(t->{
          String uptoType="";
           ObservableList <String> click;
       click= listSearch.getSelectionModel().getSelectedItems();
       for( String m : click){
           uptoType+=m;
       }       
       type.setText(uptoType);                 
       });  
    }
    
    
    public void setConstant(ActionEvent e){
        String num = rate.getText();
       
        if( num.equals("")){
            voice.say("ja ji mac din la 50 phan cham ");
            
            return;
        }else{
            double value = Double.parseDouble(num);
           if( value <0 || value>100){
               voice.say("ban , nhap ja  ji sai roi ");
               return;
           }
           
           voice.say("ban , nhap ja  ji  "+value +" percent");
           
           value/=100;
            dic.setCORRECT_CONSTANT(value);
            System.out.println("constant is " + value);
        }
        
    }
    
    
  
    // xong
     public void search(ActionEvent e ){      
          
//         prompt.insertFromFile();
//          dic.additionWord(prompt.word_target, prompt.word_explain);
//         System.out.println(prompt.word_target +"       " + prompt.word_explain);
        
//         dic.insertFromFile();


         String word = type.getText();// lay text tu cai textfield
         // hàm search sẽ trả về  một array list <word>        
         // duyet toan bo nhung tu tim duoc
         //ArrayList<Word> listWordTaget = new ArrayList<>();
         listWordTaget = dic.dictionarySearcher(word);
         if (listWordTaget.size()==1){
             //voice.say("Foud" );
          //   voice.say("aa,aa  tim thay,roi , toi thong minh phai khong cac ban ");
          //   descrip.setText( listWordTaget.get(0).word_explain );
            recent.add(listWordTaget.get(0).word_target);
            recently.getItems().clear();
            for ( int i =0 ; i < recent.size(); i++ ){
                  recently.getItems().add(recent.get(i));
             }
           
            
         
            WebEngine engin =new WebEngine();
             engin = descrip.getEngine();
             engin.loadContent(listWordTaget.get(0).word_explain);
         }else {
           //  voice.say("Cant find , ,i, recommend, some word ,below");
            // voice.say("hait,  khong tim thay roi ,chack sai chin  ja roi ");
               //   voice.say(    "  toi set  gus ee , cho ban, ");
             
             
         }
         listSearch.getItems().clear();
         for ( int i = 0 ; i < listWordTaget.size() ; i++){
             listSearch.getItems().addAll(listWordTaget.get(i).word_target );
             
             
         }

         
     }

     
     public void refresh(ActionEvent e){
         if ( boolAdd ){
           Prompt prompt = new Prompt(); 
         prompt.pathPrompt="prompt.txt";
         prompt.insertFromFile();
          dic.additionWord(prompt.word_target, prompt.word_explain);
          prompt.word_explain=prompt.word_target="";
          boolAdd=false;
         }
         if( boolEdit){
            Prompt p = new Prompt();
        p.pathPrompt="edit.txt";
        p.insertFromFile();
         dic.editWord(type.getText(), p.word_explain);
         p.word_explain=p.word_target="";
             boolEdit=false;
             
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
        // voice.say("Do you want to delete  " + word + "  do  not regret ");
         voice.say(" ban chak  mon shao  khong");
        
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
           //  voice.say("Delete ." + word + ". complete ");
            voice.say(" da shoa thanh cong ");
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
       // nó không ghi đè
      //dic.dictionaryExportToFile();
         
        
        
     }
     
     
     public void add(ActionEvent e) throws IOException {
      //   dic.additionWord("konichiwa", "xin chào");
         
         
        
         try {
          Pane pane = FXMLLoader.load(getClass().getResource("AddWord.fxml"));                   
        Scene sceneAdd = new Scene(pane);           
        Stage next = new Stage();   
        next.setTitle("Add Word");
        next.setScene(sceneAdd);
         next.show();
        
             this.boolAdd=true;
         }
         catch(IOException t ){
              System.out.println(t.getMessage());
       
         }
            
     }
     
     
     
     
     
     
     
     
     //
     
     public void fix(ActionEvent e ){
         
         try {
          Pane pane = FXMLLoader.load(getClass().getResource("EditWord.fxml"));                   
        Scene sceneAdd = new Scene(pane);           
        Stage next1 = new Stage();   
        next1.setTitle("Fix Word");
        next1.setScene(sceneAdd);
         next1.show();
         this.boolEdit=true;
       
         }
         catch(IOException t ){
              System.out.println(t.getMessage());
       
         }
         
         
        
         
     }
     
          // xong
      public void speech(ActionEvent e){
         
          String word = type.getText();// lay text tu cai textfield        
          if (word.equals("") ){
           voice.say("ban phai nhap tu ,thi toi,, moit, doc  chu ");
          }else {
          voice.say(word);           
          }
     }
     
     
     
     
      
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        
}
