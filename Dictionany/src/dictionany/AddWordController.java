/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionany;

import dictionarymanagement.DictionaryManagement;
import dictionarymanagement.Prompt;
import dictionarymanagement.Voice;
import dictionarymanagement.Word;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DoQuangTrung
 */
public class AddWordController extends FXMLController  implements Initializable    {

   // private Voice voice = new Voice("kevin16");
   @FXML 
   private TextField  Word;
   @FXML
   private TextArea textarea;
   
   public void addWord (ActionEvent t){
       String wordExplain,word;
     
       word=Word.getText();
       wordExplain = textarea.getText();

       Prompt prompt =new Prompt( word, wordExplain,"prompt.txt" );
       prompt.dictionaryExportToFile();     
      

//        ArrayList<Word> l = new ArrayList<>();
//         l = dic.dictionarySearcher(word);
//               System.out.println(l.get(0).word_target+l.get(0).word_explain);
//               
//         super.dic.additionWord(word, wordExplain);
//         super.dic.dictionaryExportToFile();
       
        
       voice.say("da thaim");
       
   }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
