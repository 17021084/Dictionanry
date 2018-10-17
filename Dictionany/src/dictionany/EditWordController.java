/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionany;

import dictionarymanagement.Prompt;
import java.net.URL;
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
public class EditWordController  extends FXMLController implements Initializable {

   
  
   
    @FXML
    private TextArea explain;
    
    public void edit(ActionEvent k){
    // String target = super.type.getText();
     String exp= explain.getText();
      Prompt p =new Prompt( "1", exp ,"edit.txt");
     
       p.dictionaryExportToFile();    
        super.boolEdit =true;
        voice.say("da sua ");
        
        
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
