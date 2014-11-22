package PsychoCompiler;

import jj.MyLangTokenManager;
import jj.Token;
import jj.MyLang;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class CompilerMain {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        FileInputStream ml_file = null;
        String parent_path = "I:\\JetBrains\\PsychoCompiler\\src\\main\\resources\\MyLang_code\\";
        String file_name = "MyLang_simple_1.ml";
        File file = new File(parent_path + file_name);
        if (file.isFile() && file.exists()) {
            System.out.println("find file success");
        } else {
            System.out.println("not find file");
        }
        try {
            ml_file = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {
            System.out.print("src file open failed.");
        }

        MyLang parser = new MyLang(ml_file);


        try {
            // Token test
            String temp = null;
            while(!(temp = parser.getNextToken().toString()).equals("")) {
                System.out.println(temp);
            }

            // Parser test
            file = new File(parent_path + file_name);
            ml_file = new FileInputStream(file);
            parser = new MyLang(ml_file);
            parser.Start();


            System.out.println("Format true!");
        } catch (Exception e){
            System.out.println("Parser Exception:");
            System.out.println(e.getMessage());
        }
        catch (Error e){
            System.out.println("Token Error:");
            System.out.println(e.getMessage());
        }
    }
}
