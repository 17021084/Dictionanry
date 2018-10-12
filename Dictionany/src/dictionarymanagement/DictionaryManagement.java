
package dictionarymanagement;
import java.io.*;
import java.util.*;

/**
 *
 * @author DoQuangTrung
 */
public final class DictionaryManagement   {
     
    // file text dang doc
   // public String pathImport = "Vocabulary3.txt";
   // public String pathImport = "fileout.txt";
   public String pathImport = "dictionaries.txt"; 
        public String pathExport = "VocabularyExport.txt";
    public Dictionary dictionary; 
    // adv method search : recommend Word 
    public double CORRECT_CONSTANT;
    
    public DictionaryManagement(){
        this.insertFromFile(); 
        this.CORRECT_CONSTANT =0.5;// default
    }

    public void setCORRECT_CONSTANT(double CORRECT_CONSTANT) {
        this.CORRECT_CONSTANT = CORRECT_CONSTANT;
    }
    
    
// doc tu file 
    public void insertFromFile() {
         // make a obj dic (Dictionary) we will return dic
        Dictionary dic = new Dictionary();
       // make a obj file ,  we read data from this file
        File file =new File (this.pathImport);
      //try catch 
        try(  Scanner sc = new Scanner(file)){
        // loop de doc toan bo dl
               while (sc.hasNext())  {
           //docfile ghi ra  word
          String target = sc.next();  // den khoang trang thi dung
          String explain = sc.nextLine();// den dau \n dung
          Word word = new Word(target.toLowerCase(),explain.toLowerCase());  
          // set word vao dic
          dic.setWord(word);
           }
           
       }catch (Exception e){       
       }       
        this.dictionary=dic;
      
    }
    
   public void dictionaryExportToFile(){
     
        // make a obj file ,  we read data from this file
        
       File file =new File (this.pathExport);
        try (FileWriter fw = new FileWriter (file,true); BufferedWriter bf = new BufferedWriter(fw) ;PrintWriter pw = new PrintWriter(bf) ){
         for ( int i =0 ; i <  this.dictionary.getList().size(); i++)   {
             pw.println( this.dictionary.getList().get(i).word_target + "  " +  this.dictionary.getList().get(i).word_explain +"\n");
         }
        }
        catch (Exception e){
        }
   }
    
    public void additionWord(String word_target,  String word_explain  ){
       // method input word      
        Word w = new Word(word_target.toLowerCase(),word_explain.toLowerCase());
        this.dictionary.setWord(w);   
    }
   
    public void deleteWord(String delete_word ){
        
       String deleteWord = delete_word.toLowerCase();
         for ( int  i =0 ; i < this.dictionary.getList().size();i++ ){
            if ( deleteWord.equals(this.dictionary.getList().get(i).word_target)){
               this.dictionary.getList().remove(i);
                System.out.println("Delete complete!!");
                return;
            }   
        }      
        System.out.println(" Sorry , maybe you typed wrong word or the word not in this list  ");
        
       
        
    }
    
    public void editWord(  String editWord ,String editMean){
       editWord = editWord.toLowerCase();
        editMean = editMean.toLowerCase();
          for ( int  i =0 ; i < this.dictionary.getList().size();i++ ){
            if ( editWord.equals(this.dictionary.getList().get(i).word_target)){
               this.dictionary.getList().get(i).word_explain=editMean;
                System.out.println("Edit complete!!");
                return;
            }   
        }      
        System.out.println(" Sorry , maybe you typed wrong word or the word not in this list  ");
    
    }
    
    
     public ArrayList<Word> dictionarySearcher( String wordLookUp ){
        ArrayList <Word> listLookFor = new ArrayList<>();     
        wordLookUp =  wordLookUp.toLowerCase();
  
       // duyet toan bo tu dien     
    for ( int i=0 ; i < this.dictionary.getList().size() ; i++){
        // cat chuoi tu cai tu trong tu dien, neu no bang tu nhap vao thi in ra
        // notice word  bi  cat ra phai lon hon tu tim vao
        
        if ( wordLookUp.equals(this.dictionary.getList().get(i).word_target) ){
             
            listLookFor.add(this.dictionary.getList().get(i));
            
            return listLookFor;
            
        }
         if ( (this.dictionary.getList().get(i).word_target.length() >=  wordLookUp.length()) && wordLookUp.equals(this.dictionary.getList().get(i).word_target.substring(0, wordLookUp.length()))){                  
             
            listLookFor.add(this.dictionary.getList().get(i));     
            
         }
         
       }
    // neu ma day  ko rong  word nao thi tra ve
        if (!listLookFor.isEmpty()){
         
            return listLookFor;
           
        }
        
        // duyet toan tu dien 
       for ( int i=0 ; i < this.dictionary.getList().size() ; i++){
          
            if ( this.dictionary.getList().get(i).word_target.length() ==  wordLookUp.length() ){                  
               double sameChar=0;
                for ( int j =0 ; j < wordLookUp.length() -1  ; j++){
                  if ( wordLookUp.substring(j, j+1).equals(this.dictionary.getList().get(i).word_target.substring(j, j+1))){
                      sameChar++;
                  }                         
               }
               // System.out.println((sameChar)/wordLookUp.length());
                
               if ( (double) sameChar/wordLookUp.length() >= CORRECT_CONSTANT ){
                       
                         listLookFor.add(this.dictionary.getList().get(i)); 
                         
                   }                 
            }
          }    
    
     return listLookFor;
    
    }
    
    
}