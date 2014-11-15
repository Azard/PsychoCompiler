package PsychoCompiler;

import jj.ParseException;
import jj.MyLang;


public class CompilerMain {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        MyLang tt = new MyLang(System.in);
        try {
            tt.Input();
        } catch(ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
