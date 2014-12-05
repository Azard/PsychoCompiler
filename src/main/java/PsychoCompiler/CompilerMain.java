package PsychoCompiler;

import jjt.MyLangTree;
import jjt.SimpleNode;


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

        MyLangTree parser = new MyLangTree(ml_file);


        try {

            // Token test
            String temp = null;
            while(!(temp = parser.getNextToken().toString()).equals("")) {
                System.out.print(temp + " ");
            }
            System.out.println(" ");

            // Parser test
            file = new File(parent_path + file_name);
            ml_file = new FileInputStream(file);
            parser = new MyLangTree(ml_file);
            SimpleNode root = parser.Start();

            //root.dump("");
            analyse(root);





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

    public static void analyse(SimpleNode root) {
        System.out.println("Fuck: " + root.toString());
        System.out.println("Fuck: " + root.jjtGetChild(0).toString());
        System.out.println("Fuck: " + root.jjtGetChild(0).jjtGetChild(0).toString());
        SimpleNode ttt = (SimpleNode)root.jjtGetChild(0).jjtGetChild(0);
        System.out.println("Fuck: " + ttt.toString());
        System.out.println("Fuck: " + ttt.jjtGetFirstToken().toString());
        System.out.println("Fuck: " + ttt.jjtGetFirstToken().next.toString());
        System.out.println("Fuck: " + ttt.jjtGetFirstToken().next.next.toString());
        System.out.println("Fuck: " + ttt.jjtGetFirstToken().next.next.next.toString());
        System.out.println("Fuck: " + ttt.jjtGetFirstToken().next.next.next.next.toString());
        System.out.println("Fuck: " + ttt.jjtGetFirstToken().next.next.next.next.next.toString());
    }
}
