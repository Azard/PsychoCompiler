package PsychoCompiler;

import jjt.SimpleNode;

import java.awt.*;

/**
 * Created by NinetuOne on 2014/12/10.
 */
public class Gene_llvm {
    GetTable gt;
    private static int var_num=0;
    private static int if_num=0;
    private static int while_num=0;
    private static int call_num=0;
    Gene_llvm(SimpleNode s){
        gt = new GetTable(s);
    }

    public void generate(TextArea area){

        gt.analyse();
        ge_code(area,gt.root);

    }

    public void init(){
        var_num = 0;
        if_num = 0;
        while_num = 0;
        call_num = 0;
    }
    public void ge_code(TextArea area,SimpleNode node){
        area.append("@.str = private unnamed_addr constant [5 x i8] c\"%d  \\00\", align 1\n");

        SimpleNode node_program = (SimpleNode)node.jjtGetChild(0);
        SimpleNode node_program_body = (SimpleNode)node_program.jjtGetChild(1);

        SimpleNode node_program_func = (SimpleNode)node_program_body.jjtGetChild(0);//函数声明
        ge_all_function(area,node_program_func);

        SimpleNode node_program_process = (SimpleNode)node_program_body.jjtGetChild(2);//主函数过程
        ge_main_process(area,node_program_process);

        area.append("declare i32 @printf(i8*, ...)");
        init();
    }


    public void ge_alloc(TextArea area,String var,String prefix){

        String instr=prefix+"%"+var+" = "+"alloca i32, align 4\n";
        area.append(instr);

    }


    public void ge_load(TextArea area,String var,String prefix){
        String instr=prefix+"%"+String.valueOf(var_num)+" = load i32* %"+var+", align 4\n";
        area.append(instr);
    }


    public void ge_store(TextArea area,String var,String value,String prefix){
        String instr = prefix+"store i32 "+ value+", i32* %"+var+", align 4\n";
        area.append(instr);
    }


    public void ge_call(TextArea area){

    }


    public void ge_operation(TextArea area){

    }

    public void ge_print(TextArea area,String var,String prefix){

        String instr1 =prefix+ "%"+String.valueOf(var_num)+" = load i32* %"+var+", align 4\n";
        area.append(instr1);
        String instr2 = prefix+ "%call"+String.valueOf(call_num)+" = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([5 x i8]* @.str,     i32 0, i32 0), i32 %"+String.valueOf(var_num)+")\n";
        area.append(instr2);
        call_num = call_num+1;
        var_num = var_num+1;
    }

    public void ge_block(TextArea area,SimpleNode node,String prefix){

        SimpleNode block = (SimpleNode)node.jjtGetChild(0);
        int instr_num = block.jjtGetNumChildren();
        for(int i=0;i<instr_num;i++)
        {
            SimpleNode statement = (SimpleNode)block.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
            SimpleNode cur_code = (SimpleNode)block.jjtGetChild(i);
            String instr_name = statement.toString();
            if(instr_name.equals("Assignment_expression")){
                SimpleNode additive_expression = (SimpleNode)statement.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0);
                if(additive_expression.jjtGetNumChildren()>1){//expression

                }
                else{//single digit

                    String var = cur_code.jjtGetFirstToken().toString();
                    String value = cur_code.jjtGetFirstToken().next.next.toString();
                    ge_store(area, var, value,prefix);
                }
            }
            else if(instr_name.equals("Print_statement")){
                SimpleNode additive_expression = (SimpleNode)statement.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
                if(additive_expression.jjtGetNumChildren()>1){//expression

                }
                else{//single digit

                    //String var = cur_code.jjtGetFirstToken().toString();
                    String var = cur_code.jjtGetFirstToken().next.toString();
                    ge_print(area, var, prefix);
                }
            }
        }

    }

    public void ge_if(TextArea area,SimpleNode node){

    }

    public void ge_for(TextArea area,SimpleNode node){

    }
    public void ge_function(TextArea area,String func_name,String type,SimpleNode node){
        if(type.equals("void"))
        {
            String instr="define void @"+func_name+"() nounwind {\n";
            area.append(instr);
            area.append("entry:\n");

        }
        else
        {
            String instr="define i32 @"+func_name+"() nounwind {\n";
            area.append(instr);
            area.append("entry:\n");
        }

        area.append("}\n");

    }


    public void ge_all_function(TextArea area,SimpleNode node){

    }


    public void ge_main_process(TextArea area,SimpleNode node){
        int i=0;
        for (i = 0; i < gt.symbol_table.symbol_name.size(); i++) {
            String symbol = gt.symbol_table.symbol_type.get(i).toString();
            if(symbol.equals("VARIABLE_NAME")){
                break;
            }

        }
        area.append("define i32 @main() nounwind {\n");
        area.append("entry:\n");
        for(;i < gt.symbol_table.symbol_name.size(); i++) {
            String symbol = gt.symbol_table.symbol_type.get(i).toString();
            if(symbol.equals("VARIABLE_NAME")){
                ge_alloc(area,gt.symbol_table.symbol_name.get(i).toString(),"  ");
            }
        }

        ge_block(area,node,"  ");
        area.append("  ret i32 0\n");
        area.append("}\n");
    }


}

