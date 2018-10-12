
package dictionarymanagement;

/**
 *
 * @author DoQuangTrung
 */


import com.sun.speech.freetts.VoiceManager;


public class Voice {
    private String name; // cai ten ma minh muon goi
    
    private com.sun.speech.freetts.Voice voice;   // tao 1 cai empty instance of  voice class
    
    public  Voice(String name){
        this.name=name;
        this.voice = VoiceManager.getInstance().getVoice(this.name);//
        this.voice.allocate();
        
    }
   public void say( String st){
       this.voice.speak(st);
   }
    
    
}