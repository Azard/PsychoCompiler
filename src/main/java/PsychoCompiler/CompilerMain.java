package PsychoCompiler;

import jjt.MyLangTree;
import jjt.SimpleNode;
import sun.java2d.pipe.SpanShapeRenderer;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


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

    // 打印符号表
    public static void show_symbol_table(Symbol_table symbol_table) {
        System.out.println("=============Symbol Table=============");
        System.out.println("total number: " + symbol_table.symbol_name.size());
        for (int i = 0; i < symbol_table.symbol_name.size(); i++) {
            System.out.println(symbol_table.symbol_type.get(i).toString() + ": " +
                    symbol_table.symbol_name.get(i).toString());
        }
        System.out.println("=================End==================");
    }

    // 查找是否有该symbol name
    public static boolean check_repeat_symbol(Symbol_table symbol_table, String name) {
        for (int i = 0; i < symbol_table.symbol_name.size(); i++) {
            if (symbol_table.symbol_name.get(i).toString().equals(name)) {
                return false;
            }
        }
        return true;
    }

    // 查找是否有该type
    public static boolean check_type_exist(Type_table type_table, String name) {

        if (name.equals("integer") || name.equals("boolean"))
            return true;
        for (int i = 0; i < type_table.type_name.size(); i++) {
            if (type_table.type_name.get(i).toString().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // 读取main的变量申明
    public static void read_main_variable(SimpleNode declarations, Symbol_table symbol_table, Variable_table variable_table, Type_table type_table) {
        try {
            int declaration_num = declarations.jjtGetNumChildren();
            for (int i = 0; i < declaration_num; i++) {
                SimpleNode var_declaration = (SimpleNode) declarations.jjtGetChild(i).jjtGetChild(0);
                String var_name = var_declaration.jjtGetFirstToken().next.toString();
                String var_type = var_declaration.jjtGetFirstToken().next.next.next.toString();
                if (!check_repeat_symbol(symbol_table, var_name)) {
                    throw new Exception("variable " + var_name + " declaration name repeat");
                }
                if (!check_type_exist(type_table, var_type)) {
                    throw new Exception("variable " + var_type + " declaration type not exist");
                }
                symbol_table.symbol_name.add(var_name);
                symbol_table.symbol_type.add(Symbol_table.Symbol_type.VARIABLE_NAME);
                variable_table.variable_name.add(var_name);
                variable_table.variable_type.add(var_type);
                variable_table.variable_value.add(0);

            }
        } catch (Exception e){
            System.out.println("Analyse Exception:");
            System.out.println(e.getMessage());
        }
    }

    // 读取type def
    public static void read_type_def(SimpleNode declarations, Symbol_table symbol_table, Type_table type_table) {
        try {
            int component_num = declarations.jjtGetNumChildren();
            for (int i = 0; i < component_num; i++) {
                SimpleNode type_declaration = (SimpleNode) declarations.jjtGetChild(i).jjtGetChild(0);
                if (type_declaration.toString().equals("Type_and_class_declaration")) {
                    String type_name = type_declaration.jjtGetFirstToken().next.toString();
                    int type_array_num = Integer.parseInt(type_declaration.jjtGetFirstToken().next.next.next.next.next.toString());
                    String type_reference = type_declaration.jjtGetFirstToken().next.next.next.next.next.next.toString();

                    if (!check_repeat_symbol(symbol_table, type_name)) {
                        throw new Exception("type " + type_name + " declaration name repeat");
                    }
                    if (!check_type_exist(type_table, type_reference)) {
                        throw new Exception("type " + type_reference + " reference not exist");
                    }

                    symbol_table.symbol_name.add(type_name);
                    symbol_table.symbol_type.add(Symbol_table.Symbol_type.TYPE_NAME);
                    type_table.type_name.add(type_name);
                    type_table.type_rederence.add(type_reference);
                    type_table.type_array_num.add(type_array_num);
                }
            }
        } catch (Exception e){
            System.out.println("Analyse Exception:");
            System.out.println(e.getMessage());
        }
    }

    public static void read_function_def(SimpleNode declarations, Symbol_table symbol_table, Type_table type_table, Function_table function_table) {
        // TODO
        try {
            int component_num = declarations.jjtGetNumChildren();
            for (int i = 0; i < component_num; i++) {
                SimpleNode function_declaration = (SimpleNode) declarations.jjtGetChild(i).jjtGetChild(0);
                if (function_declaration.toString().equals("Function_declaration")) {
                    SimpleNode node_function_head = (SimpleNode)function_declaration.jjtGetChild(0);
                    SimpleNode node_function_body = (SimpleNode)function_declaration.jjtGetChild(1);
                    SimpleNode node_function_parameter_list = (SimpleNode)node_function_head.jjtGetChild(1);
                    SimpleNode node_function_parameters_type = (SimpleNode)node_function_head.jjtGetChild(2);
                    SimpleNode node_function_return_declaration = null;
                    if (node_function_head.jjtGetNumChildren() == 4) {
                        node_function_return_declaration = (SimpleNode)node_function_head.jjtGetChild(3);
                    }

                    // 函数名
                    String function_name = node_function_head.jjtGetFirstToken().next.toString();
                    if (!check_repeat_symbol(symbol_table, function_name)) {
                        throw new Exception("function " + function_name + " declaration name repeat");
                    }
                    symbol_table.symbol_name.add(function_name);
                    symbol_table.symbol_type.add(Symbol_table.Symbol_type.FUNCTION_NAME);
                    function_table.function_name.add(function_name);


                    // 传入参数数量和形参名
                    SimpleNode node_parameter_list = (SimpleNode)node_function_parameter_list.jjtGetChild(0);
                    if (node_parameter_list.jjtGetNumChildren() == 0) {
                        // 没有参数
                        function_table.function_parameter_num.add(0);
                        function_table.function_parameter_type.add(new ArrayList());
                    }
                    else {
                        // 有参数
                        SimpleNode node_parameters = (SimpleNode)node_parameter_list.jjtGetChild(0);
                        int parameter_num = node_parameters.jjtGetNumChildren();
                        for (int j = 0; j < parameter_num; j++) {
                            // TODO 参数加入函数的结构中，判断重复
                            SimpleNode temp_parameter_node = (SimpleNode)node_parameters.jjtGetChild(j);
                            System.out.println(temp_parameter_node.jjtGetFirstToken().toString());
                        }

                    }

                    // TODO 参数的类型申明保存
                    // TODO return类型保存，先判断有无return















                }
            }
        } catch (Exception e){
            System.out.println("Analyse Exception:");
            System.out.println(e.getMessage());
        }
    }

    public static void analyse(SimpleNode root) {

        Symbol_table symbol_table = new Symbol_table();
        Struct_Program struct_program = new Struct_Program();
        Variable_table variable_table = new Variable_table();
        Type_table type_table = new Type_table();
        Function_table function_table = new Function_table();

        // 读取parse tree的program部分
        SimpleNode node_program_declaration = (SimpleNode)root.jjtGetChild(0);
        SimpleNode node_program_head = (SimpleNode)node_program_declaration.jjtGetChild(0);
        SimpleNode node_program_body = (SimpleNode)node_program_declaration.jjtGetChild(1);

        // 添加program name到符号表
        struct_program.program_name = node_program_head.jjtGetFirstToken().next.toString();
        symbol_table.symbol_name.add(struct_program.program_name);
        symbol_table.symbol_type.add(Symbol_table.Symbol_type.PROGRAM_NAME);

        // 添加type def到符号表
        SimpleNode node_component_declarations = (SimpleNode)node_program_body.jjtGetChild(0);
        read_type_def(node_component_declarations, symbol_table, type_table);

        // 添加function到符号表
        read_function_def(node_component_declarations, symbol_table, type_table, function_table);

        // 添加主程序变量到符号表
        SimpleNode node_program_variable_declarations = (SimpleNode)node_program_body.jjtGetChild(1);
        read_main_variable(node_program_variable_declarations, symbol_table, variable_table, type_table);

        show_symbol_table(symbol_table);

    }
}


class Symbol_table {
    public enum Symbol_type {
        PROGRAM_NAME, FUNCTION_NAME, TYPE_NAME, VARIABLE_NAME
    }

    ArrayList symbol_name = new ArrayList();
    ArrayList symbol_type = new ArrayList();
}

class Function_table {
    ArrayList function_name = new ArrayList();              // String
    ArrayList function_parameter_num = new ArrayList();     // int
    ArrayList function_parameter_type = new ArrayList();    // ArrayList(String)，二维的List对应参数类型
    ArrayList function_return_type = new ArrayList();       // String
    ArrayList function_run_node = new ArrayList();
}

class Type_table {
    public enum Basic_type {
        INTEGER, BOOLEAN
    }

    ArrayList type_name = new ArrayList();      // String 类型名
    ArrayList type_rederence = new ArrayList(); // String 数组类型，例如Integer[], boolean[]
    ArrayList type_array_num = new ArrayList(); // int 数组元素数量
}

class Variable_table {
    ArrayList variable_name = new ArrayList();
    ArrayList variable_type = new ArrayList();
    ArrayList variable_value = new ArrayList();
}


class Struct_Program {
    String program_name;
}
















