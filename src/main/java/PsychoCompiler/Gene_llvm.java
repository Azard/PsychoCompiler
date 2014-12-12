package PsychoCompiler;

import jjt.SimpleNode;

import java.awt.*;
import java.net.SocketImpl;

/**
 * Created by NinetuOne on 2014/12/10.
 */
public class Gene_llvm {
    GetTable gt;


    private static int var_num=0;
    private static int if_num=0;
    private static int while_num=0;
    private static int call_num=0;
    private static int add_num=0;
    private static int sum_num=0;
    private static int mul_num=0;
    private static int div_num=0;
    private static int mod_num=0;
    private static int last_ope = 0;
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
        add_num=0;
        sum_num=0;
        mul_num=0;
        div_num=0;
        mod_num=0;
        last_ope=0;
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
        var_num=var_num+1;
    }


    public void ge_store(TextArea area,String var,String value,String prefix){
        String instr = prefix+"store i32 "+ value+", i32* %"+var+", align 4\n";
        area.append(instr);
    }


    public void ge_call(TextArea area){

    }


    public void ge_operation(TextArea area,String ope,String left_var,String right_var,String prefix){

        if(ope.equals("+"))
        {
            String instr = prefix + "%add"+String.valueOf(add_num)+" = add nsw i32 "+left_var+", "+right_var+"\n";
            area.append(instr);
            add_num = add_num+1;
        }
        else if(ope.equals("-"))
        {
            String instr = prefix + "%sub"+String.valueOf(sum_num)+" = sub nsw i32 "+left_var+", "+right_var+"\n";
            area.append(instr);
            sum_num = sum_num+1;
        }
        else if(ope.equals("*"))
        {
            String instr = prefix + "%mul"+String.valueOf(mul_num)+" = mul nsw i32 "+left_var+", "+right_var+"\n";
            area.append(instr);
            mul_num = mul_num+1;
        }
        else if(ope.equals("/"))
        {
            String instr = prefix + "%div"+String.valueOf(div_num)+" = sdiv i32 "+left_var+", "+right_var+"\n";
            area.append(instr);
            div_num = div_num+1;
        }
        else if(ope.equals("%"))
        {
            String instr = prefix + "%rem"+String.valueOf(mod_num)+" = srem i32 "+left_var+", "+right_var+"\n";
            area.append(instr);
            mod_num = mod_num+1;
        }




    }

    public pair ge_mul_expression(TextArea area,SimpleNode node,String prefix){
        int ex_num = node.jjtGetNumChildren();
        if(ex_num==1){
            SimpleNode last = (SimpleNode)node.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
            if(last.toString().equals("Digits")){

                return new pair(0,last.jjtGetFirstToken().toString());
            }
            else if(last.toString().equals("Identifier")){
                ge_load(area,last.jjtGetFirstToken().toString(),prefix);
                return new pair(-1,"%"+String.valueOf(var_num-1));
            }

        }
        else{
            SimpleNode left = (SimpleNode)node.jjtGetChild(0);
            pair left_pair =null;


            if(left.jjtGetChild(0).toString().equals("Arithmetic_expression"))
            {
                left_pair = ge_add_expression(area,(SimpleNode)left.jjtGetChild(0).jjtGetChild(0),prefix);
            }
            else if(left.jjtGetChild(0).toString().equals("Left_value")) {

                SimpleNode temp=(SimpleNode)left.jjtGetChild(0).jjtGetChild(0);
                ge_load(area,temp.jjtGetFirstToken().toString(),prefix);
                left_pair = new pair(-1,"%"+String.valueOf(var_num-1));
            }
            else if(left.jjtGetChild(0).toString().equals("Constant_expression")){

                SimpleNode temp = (SimpleNode)left.jjtGetChild(0).jjtGetChild(0);
                left_pair = new pair(0,temp.jjtGetFirstToken().toString());
            }
            for (int i = 0; i < ex_num - 2; ) {

                SimpleNode right = (SimpleNode) node.jjtGetChild(i + 2);
                pair right_pair =null;


                if(right.jjtGetChild(0).toString().equals("Arithmetic_expression"))
                {
                    right_pair = ge_add_expression(area,(SimpleNode)right.jjtGetChild(0).jjtGetChild(0),prefix);
                }
                else if(right.jjtGetChild(0).toString().equals("Left_value")) {

                    SimpleNode temp=(SimpleNode)right.jjtGetChild(0).jjtGetChild(0);
                    ge_load(area,temp.jjtGetFirstToken().toString(),prefix);
                    right_pair = new pair(-1,"%"+String.valueOf(var_num-1));
                }
                else if(right.jjtGetChild(0).toString().equals("Constant_expression")){

                    SimpleNode temp = (SimpleNode)right.jjtGetChild(0).jjtGetChild(0);
                    right_pair = new pair(0,temp.jjtGetFirstToken().toString());
                }

                SimpleNode ope = (SimpleNode) node.jjtGetChild(i + 1);
                if(ope.jjtGetFirstToken().toString().equals("*"))
                {
                    ge_operation(area,"*",left_pair.var,right_pair.var,prefix);
                    left_pair.ret=3;
                    left_pair.var="%mul"+String.valueOf(mul_num-1);
                }
                else if(ope.jjtGetFirstToken().toString().equals("/")){
                    ge_operation(area,"/",left_pair.var,right_pair.var,prefix);
                    left_pair.ret=4;
                    left_pair.var="%div"+String.valueOf(div_num-1);
                }
                else if(ope.jjtGetFirstToken().toString().equals("%")){
                    ge_operation(area,"%",left_pair.var,right_pair.var,prefix);
                    left_pair.ret=5;
                    left_pair.var="%rem"+String.valueOf(mod_num-1);
                }

                i = i + 2;

            }
            return left_pair;
        }
        return null;
    }

    public pair ge_add_expression(TextArea area,SimpleNode node,String prefix){
        SimpleNode left = (SimpleNode) node.jjtGetChild(0);

        int ex_num = node.jjtGetNumChildren();
        if(ex_num==1)
            return ge_mul_expression(area, left, prefix);
        else {
            pair left_pair =ge_mul_expression(area, left, prefix);

            for (int i = 0; i < ex_num - 2; ) {

                SimpleNode right = (SimpleNode) node.jjtGetChild(i + 2);
                pair right_pair =ge_mul_expression(area,right,prefix);

                SimpleNode ope = (SimpleNode) node.jjtGetChild(i + 1);
                if(ope.jjtGetFirstToken().toString().equals("+"))
                {
                    ge_operation(area,"+",left_pair.var,right_pair.var,prefix);
                    left_pair.ret=1;
                    left_pair.var="%add"+String.valueOf(add_num-1);
                }
                else if(ope.jjtGetFirstToken().toString().equals("-")){
                    ge_operation(area,"-",left_pair.var,right_pair.var,prefix);
                    left_pair.ret=2;
                    left_pair.var="%sub"+String.valueOf(sum_num-1);
                }
                i = i + 2;

            }
            return left_pair;
        }
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
                if(!cur_code.jjtGetFirstToken().next.next.next.toString().equals(";")){//expression
                    //SimpleNode expression = (SimpleNode)additive_expression.jjtGetChild(0);
                    pair result=ge_add_expression(area,additive_expression,prefix);
                    if(result == null)
                    {
                        System.out.println("result is null\n");
                    }
                    else
                    {
                        String var = cur_code.jjtGetFirstToken().toString();
                        ge_store(area,var,result.var,prefix);
                    }
                }
                else{//single digit
                    if(additive_expression.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).toString().equals("Left_value"))//单个变量
                    {
                        String var = cur_code.jjtGetFirstToken().toString();
                        String right_var = cur_code.jjtGetFirstToken().next.next.toString();
                        ge_load(area,right_var,prefix);
                        ge_store(area, var,"%"+String.valueOf(var_num-1), prefix);
                    }
                    else {//单个数字
                        String var = cur_code.jjtGetFirstToken().toString();
                        String value = cur_code.jjtGetFirstToken().next.next.toString();
                        ge_store(area, var, value, prefix);
                    }
                }
            }
            else if(instr_name.equals("Print_statement")){
                SimpleNode additive_expression = (SimpleNode)statement.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
                if(additive_expression.jjtGetNumChildren()>1){//expression

                }
                else{//single digit
                    //String var = cur_code.jjtGetFirstToken().toString();

                    String var = cur_code.jjtGetFirstToken().next.toString();//获得打印的变量名
                    ge_print(area, var, prefix);
                }
            }
        }

    }

    public void ge_if(TextArea area,SimpleNode node){

    }

    public void ge_for(TextArea area,SimpleNode node){

    }

    public void ge_while(TextArea area,SimpleNode node){

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

class pair{
    int ret;
    String var;
    pair(int r,String v){
        ret =r;
        var = v;
    }

}
