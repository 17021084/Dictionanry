
package dictionarymanagement;
import java.util.ArrayList;

/**
 *
 * @author DoQuangTrung
 */
public class Dictionary {
    public   ArrayList <Word> list = new ArrayList<>();

    public void setWord( Word w){
       this.list.add(w);
    }   
    public ArrayList<Word>  getList(){
        return this.list;
    }

}
