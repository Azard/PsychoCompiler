package PsychoCompiler;

import jjt.MyLangTree;
import jjt.SimpleNode;
import sun.java2d.pipe.SpanShapeRenderer;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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

    // 查找是否有该var
    public static boolean check_variable_name_repeat(Variable_table variable_table, String name) {
        for (int i = 0; i < variable_table.variable_name.size(); i++) {
            if (variable_table.variable_name.get(i).toString().equals(name)) {
                return false;
            }
        }
        return true;
    }

    // 搜索function
    public static int search_function_by_name(Function_table function_table, String name) {
        int num = function_table.function_name.size();
        for (int i = 0; i < num; i++) {
            if (function_table.function_name.get(i).toString().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    // 搜索type
    public static int search_type_by_name(Type_table type_table, String name) {
        int num = type_table.type_name.size();
        for (int i = 0; i < num; i++) {
            if (type_table.type_name.get(i).toString().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    // 搜索variable
    public static int search_var_by_name(Variable_table variable_table, String name) {
        int num = variable_table.variable_name.size();
        for (int i = 0; i < num; i++) {
            if (variable_table.variable_name.get(i).toString().equals(name)) {
                return i;
            }
        }
        return -1;
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
                        function_table.function_parameter_name.add(new ArrayList());
                    }
                    else {
                        // 有参数
                        SimpleNode node_parameters = (SimpleNode)node_parameter_list.jjtGetChild(0);
                        int parameter_num = node_parameters.jjtGetNumChildren();
                        function_table.function_parameter_num.add(parameter_num);
                        ArrayList mul_para_name = new ArrayList();  // 形参列表
                        for (int j = 0; j < parameter_num; j++) {
                            // 参数名加入函数的结构中，判断重复
                            SimpleNode temp_parameter_node = (SimpleNode)node_parameters.jjtGetChild(j);
                            String para_name = temp_parameter_node.jjtGetFirstToken().toString();
                            for (int k = 0; k < mul_para_name.size(); k++) {
                                if (mul_para_name.get(k).toString().equals(para_name)) {
                                    throw new Exception("function " + function_name + " parameter name repeat");
                                }
                            }
                            mul_para_name.add(para_name);
                        }
                        function_table.function_parameter_name.add(mul_para_name);
                    }


                    // 参数类型的保存和check
                    int para_type_num = node_function_parameters_type.jjtGetNumChildren();
                    if (para_type_num != Integer.parseInt(function_table.function_parameter_num.get(function_table.function_parameter_num.size()-1).toString())) {
                        // 类型申明和传参数量没对上
                        throw new Exception("function " + function_name + " parameter type declare number wrong");
                    }
                    ArrayList para_name_list = (ArrayList)function_table.function_parameter_name.get(function_table.function_parameter_name.size()-1);
                    ArrayList para_type_list = new ArrayList();
                    for (int j = 0; j < para_type_num; j++) {
                        SimpleNode node_function_para_type = (SimpleNode)node_function_parameters_type.jjtGetChild(j);
                        // 参数名判断是否和传参一致
                        String temp_name = node_function_para_type.jjtGetFirstToken().next.toString();
                        if (!temp_name.equals(para_name_list.get(j).toString())) {
                            throw new Exception("function " + function_name + " parameter name wrong");
                        }
                        // 参数类型判断是否存在
                        String temp_type = node_function_para_type.jjtGetFirstToken().next.next.next.toString();
                        if (!check_type_exist(type_table, temp_type)) {
                            throw new Exception("function " + function_name + " parameter type not exist");
                        }
                        para_type_list.add(temp_type);
                    }
                    function_table.function_parameter_type.add(para_type_list);


                    // 保存return的类型
                    if (node_function_return_declaration == null) {
                        // 没有返回值的情况
                        function_table.function_return_type.add(null);
                    } else {
                        // 有返回值的情况
                        String temp_return_type = node_function_return_declaration.jjtGetFirstToken().next.toString();
                        if(!check_type_exist(type_table, temp_return_type)) {
                            // 不存在返回的类型
                            throw new Exception("function " + function_name + " return type not exist");
                        }
                        function_table.function_return_type.add(temp_return_type);
                    }


                    // 函数内部变量申明
                    SimpleNode node_function_variable_declarations = (SimpleNode)node_function_body.jjtGetChild(0);
                    Variable_table variable_list = new Variable_table();
                    ArrayList function_para_name_list = (ArrayList)function_table.function_parameter_name.get(function_table.function_parameter_name.size()-1);

                    int function_variable_num = node_function_variable_declarations.jjtGetNumChildren();
                    for (int j = 0; j < function_variable_num; j++) {
                        SimpleNode node_function_variable_declaration = (SimpleNode)node_function_variable_declarations.jjtGetChild(j);
                        SimpleNode node_variable_declaration = (SimpleNode)node_function_variable_declaration.jjtGetChild(0);
                        String var_name = node_variable_declaration.jjtGetFirstToken().next.toString();
                        String var_type = node_variable_declaration.jjtGetFirstToken().next.next.next.toString();
                        if (!check_variable_name_repeat(variable_list, var_name)) {
                            throw new Exception("variable " + var_type + " declaration name repeat");
                        }
                        if (!check_type_exist(type_table, var_type)) {
                            throw new Exception("variable " + var_type + " declaration type not exist");
                        }
                        // 不与parameter名字重复
                        for (int k = 0; k < function_para_name_list.size(); k++ ) {
                            if (function_para_name_list.get(k).toString().equals(var_name)) {
                                throw new Exception("variable " + var_type + " declaration name repeat");
                            }
                        }
                        variable_list.variable_name.add(var_name);
                        variable_list.variable_type.add(var_type);
                        variable_list.variable_value.add(0);
                    }
                    function_table.function_variable_list.add(variable_list);


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


//class Symbol_table {
//    public enum Symbol_type {
//        PROGRAM_NAME, FUNCTION_NAME, TYPE_NAME, VARIABLE_NAME
//    }
//
//    ArrayList symbol_name = new ArrayList();
//    ArrayList symbol_type = new ArrayList();
//}
//
//class Function_table {
//    ArrayList function_name = new ArrayList();              // String
//    ArrayList function_parameter_num = new ArrayList();     // int
//    ArrayList function_parameter_type = new ArrayList();    // ArrayList<String>，二维的List对应参数类型
//    ArrayList function_parameter_name = new ArrayList();    // ArrayList<String>，形参名字
//    ArrayList function_return_type = new ArrayList();       // String, null表示没有返回值
//
//    ArrayList function_variable_list = new ArrayList();     // Variable_table，保存函数声明的变量
//    ArrayList function_run_node = new ArrayList();
//}
//
//class Type_table {
//    public enum Basic_type {
//        INTEGER, BOOLEAN
//    }
//
//    ArrayList type_name = new ArrayList();      // String 类型名
//    ArrayList type_rederence = new ArrayList(); // String 数组类型，例如Integer[], boolean[]
//    ArrayList type_array_num = new ArrayList(); // int 数组元素数量
//}
//
//class Variable_table {
//    ArrayList variable_name = new ArrayList();
//    ArrayList variable_type = new ArrayList();
//    ArrayList variable_value = new ArrayList();
//}
//
//
//class Struct_Program {
//    String program_name;
//}
















