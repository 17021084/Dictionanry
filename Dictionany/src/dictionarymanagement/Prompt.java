/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionarymanagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author DoQuangTrung
 */
public class Prompt {
    public String word_target, word_explain;
    public String pathPrompt;
     
    
    public Prompt( String wt , String we, String path ){
        this.word_explain=we;
        this.word_target=wt;
        this.pathPrompt=path;
    }
    
    
    
   public Prompt(){     
   }
   
    
     public void insertFromFile() {
             
       // make a obj file ,  we read data from this file
        File file =new File (this.pathPrompt);
      //try catch 
        try(  Scanner sc = new Scanner(file)){
        // loop de doc toan bo dl
               while (sc.hasNext())  {
           //docfile ghi ra  word
          String target = sc.next();  // den khoang trang thi dung
          String explain = sc.nextLine();// den dau \n dung
           this.word_explain=explain;
           this.word_target=target;
          // set word vao dic
        
           }
           
       }catch (Exception e){  
            System.out.println(e.getMessage());
       }       
       
      
    }
    
     public void dictionaryExportToFile(){
     
        // make a obj file ,  we read data from this file
        
       File file =new File (this.pathPrompt);
        try ( PrintWriter pw = new PrintWriter(file) ){
         pw.print(this.word_target +"   " + this.word_explain );
         
         
        }
        catch (Exception e){
        }
   }
  
     
}
