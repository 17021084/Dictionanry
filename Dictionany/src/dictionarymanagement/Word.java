
package dictionarymanagement;

/**
 *
 * @author DoQuangTrung
 */
public class Word {
    public String word_target;
    public String word_explain;
    
     Word(){
     }
    Word( String target , String explain ){
        this.word_explain = explain;
        this.word_target = target;
    }
}
