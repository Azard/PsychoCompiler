package PsychoCompiler;

import jj.ParseException;
import jj.MyLang;


public class CompilerMain {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        MyLang parser = new MyLang(System.in);
        try {
            parser.Start();
        } catch(ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
