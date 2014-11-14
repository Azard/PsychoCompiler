package PsychoCompiler;

import jj.ParseException;
import jj.Simple1;


public class CompilerMain {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        Simple1 tt = new Simple1(System.in);
        try {
            tt.Input();
        } catch(ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
