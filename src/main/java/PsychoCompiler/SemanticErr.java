package PsychoCompiler;

/**
 * Created by NinetuOne on 2014/12/19.
 */
public class SemanticErr extends Exception {
    String message;
    public SemanticErr(String error){
        message = error;
    }
    public String getMessage(){
        return message;
    }

}
