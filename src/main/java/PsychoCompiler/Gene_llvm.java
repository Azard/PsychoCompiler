package PsychoCompiler;

import jjt.SimpleNode;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NinetuOne on 2014/12/10.
 */
public class Gene_llvm {
    GetTable gt;

    Map<String,array_info> array_var = new HashMap<String, array_info>();
    Map<String,array_info> func_array_var = new HashMap<String, array_info>();
    Map<String,String> func_var = new HashMap<String, String>();

    private static int var_num=0;
    //if语句会用到的变量，记录if的状态
    private static int then_num=0;
    private static int else_num=0;
    private static int end_num=0;
    private static int cmp_num=0;
    private static int true_num=0;
    private static int false_num=0;
    private static int while_num=0;

    private static int and_last=0;
    private static int or_last=0;
    private static int if_last=0;
    private static int call_num=0;
    //表达式会用到的变量
    private static int add_num=0;
    private static int sum_num=0;
    private static int mul_num=0;
    private static int div_num=0;
    private static int mod_num=0;

    private static int arrayidx=0;

    private static int arraydecay =0;

    private static int in_function=0;
   // private static int last_ope = 0;

    Gene_llvm(SimpleNode s){
        gt = new GetTable(s);
    }

    public void generate(TextArea area){

        gt.analyse();
        ge_code(area,gt.root);

    }

    public void init(){
        var_num = 0;

        then_num=0;
        else_num=0;
        end_num=0;
        cmp_num=0;
        true_num=0;
        false_num=0;
        while_num = 0;
        and_last=0;
        or_last=0;
        if_last=0;
        call_num = 0;

        add_num=0;
        sum_num=0;
        mul_num=0;
        div_num=0;
        mod_num=0;
        arrayidx=0;
        arraydecay=0;

        in_function = 0;
        array_var.clear();
        func_array_var.clear();
        func_var.clear();
     //   last_ope=0;
    }

    public void ge_code(TextArea area,SimpleNode node){
        area.append("@.str = private unnamed_addr constant [5 x i8] c\"%d  \\00\", align 1\n");
        area.append("@.str1 = private unnamed_addr constant [2 x i8] c\"\\0A\\00\", align 1\n");

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

    public void ge_alloc_array(TextArea area,String var,String type,String prefix){

        for(int i=0;i<gt.type_table.type_name.size();i++)
        {
            if(gt.type_table.type_name.get(i).toString().equals(type))
            {
                if(gt.type_table.type_rederence.get(i).toString().equals("integer") || gt.type_table.type_rederence.get(i).toString().equals("boolean")){
                    int cnt = (Integer)gt.type_table.type_array_num.get(i);
                    String instr=prefix+"%"+var+" = "+"alloca ["+String.valueOf(cnt)+" x i32], align 4\n";
                    area.append(instr);
                    array_var.put(var,new array_info(1,cnt,0));
                    break;
                }
                else{
                    int cnt1 = (Integer)gt.type_table.type_array_num.get(i);
                    String type2 = gt.type_table.type_rederence.get(i).toString();
                    for(int j=0;j<gt.type_table.type_name.size();j++)
                    {
                        if(gt.type_table.type_name.get(j).toString().equals(type2))
                        {

                                int cnt2 = (Integer)gt.type_table.type_array_num.get(j);
                                String instr=prefix+"%"+var+" = "+"alloca ["+String.valueOf(cnt1)+" x ["+String.valueOf(cnt2)+" x i32]], align 4\n";
                                area.append(instr);
                                array_var.put(var,new array_info(2,cnt1,cnt2));
                                break;
                        }
                    }
                    break;
                }
            }
        }

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
            SimpleNode left_value =(SimpleNode)node.jjtGetChild(0).jjtGetChild(0);
            if(last.toString().equals("Digits")||last.toString().equals("Sign")){
                if(last.jjtGetFirstToken().toString().equals("-"))
                    return new pair(0,"-"+last.jjtGetFirstToken().next.toString());
                else
                    return new pair(0,last.jjtGetFirstToken().toString());
            }
            else if(last.toString().equals("Identifier")){
                if(left_value.jjtGetNumChildren()==1) {
                    String temp_name = last.jjtGetFirstToken().toString();
                    if(in_function == 1 && func_var.containsKey(temp_name)){
                        temp_name = func_var.get(temp_name);
                    }
                    ge_load(area, temp_name, prefix);
                    return new pair(-1, "%" + String.valueOf(var_num - 1));
                }
                else if(left_value.jjtGetNumChildren()==2){

                    SimpleNode array_index = (SimpleNode)left_value.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0);
                    pair index = ge_add_expression(area,array_index,prefix);

                    String var = left_value.jjtGetFirstToken().toString();
                    int length=0;
                    if(in_function ==1&& func_var.containsKey(var)){
                        length = func_array_var.get(var).cnt1;
                        String instr11 = prefix+"%"+String.valueOf(var_num)+" = load i32** %"+var+".addr, align 4\n";
                       // var = String.valueOf(var_num);
                        var_num++;
                        area.append(instr11);
                    }
                    else {
                        length = array_var.get(var).cnt1;
                    }

                    String instr="";
                    if(in_function ==1 && func_var.containsKey(var)) {
                        var = String.valueOf(var_num-1);
                        instr = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds i32* %" + var + ", i32 " + index.var + "\n";
                    }
                    else
                        instr = prefix+"%arrayidx"+String.valueOf(arrayidx)+" = getelementptr inbounds ["+String.valueOf(length)+" x i32]* %"+var+", i32 0, i32 "+index.var+"\n";
                    area.append(instr);
                    ge_load(area, "arrayidx" + String.valueOf(arrayidx), prefix);
                    arrayidx = arrayidx+1;
                    return new pair(-1, "%" + String.valueOf(var_num - 1));
                }
                else if(left_value.jjtGetNumChildren()==3){
                    //SimpleNode left_value =(SimpleNode)node.jjtGetChild(0).jjtGetChild(0);
                    SimpleNode array_index = (SimpleNode)left_value.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0);
                    pair index1 = ge_add_expression(area,array_index,prefix);
                    SimpleNode array_index2 = (SimpleNode)left_value.jjtGetChild(2).jjtGetChild(0).jjtGetChild(0);
                    pair index2 = ge_add_expression(area,array_index2,prefix);

                    String var = left_value.jjtGetFirstToken().toString();
                    int length1=0;
                    int length2=0;
                    if(in_function==1&& func_var.containsKey(var)){
                        length1 = func_array_var.get(var).cnt1;
                        length2 = func_array_var.get(var).cnt2;
                        String instr11 = prefix+"%"+String.valueOf(var_num)+" = load ["+String.valueOf(length2)+" x i32]** %"+var+".addr, align 4\n";
                        //var = String.valueOf(var_num);
                        var_num++;
                        area.append(instr11);
                    }
                    else {
                        length1 = array_var.get(var).cnt1;
                        length2 = array_var.get(var).cnt2;
                    }

                    String instr1="";
                    String instr2="";
                    if(in_function==1&& func_var.containsKey(var)){
                        var = String.valueOf(var_num-1);
                        instr1 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %" + var + ", i32 " + index1.var + "\n";
                        arrayidx = arrayidx + 1;
                        area.append(instr1);

                        instr2 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %arrayidx" + String.valueOf(arrayidx - 1) + ", i32 0, i32 " + index2.var + "\n";

                        area.append(instr2);
                    }
                    else {
                        instr1 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length1) + " x [" + String.valueOf(length2) + " x i32]]* %" + var + ", i32 0, i32 " + index1.var + "\n";
                        arrayidx = arrayidx + 1;
                        area.append(instr1);

                        instr2 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %arrayidx" + String.valueOf(arrayidx - 1) + ", i32 0, i32 " + index2.var + "\n";

                        area.append(instr2);
                    }


                    ge_load(area, "arrayidx" + String.valueOf(arrayidx), prefix);
                    arrayidx = arrayidx+1;
                    return new pair(-1, "%" + String.valueOf(var_num - 1));
                }
            }
            else if(last.toString().equals("Function_name")){
                SimpleNode func = (SimpleNode)node.jjtGetChild(0).jjtGetChild(0);
                return ge_call(area,func,prefix);
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
                SimpleNode left_value = (SimpleNode)left.jjtGetChild(0);

                if(left_value.jjtGetNumChildren()==1) {
                    SimpleNode temp=(SimpleNode)left.jjtGetChild(0).jjtGetChild(0);

                    String temp_name = temp.jjtGetFirstToken().toString();
                    if(in_function == 1&& func_var.containsKey(temp_name)){
                        temp_name = func_var.get(temp_name);
                    }
                    ge_load(area,temp_name,prefix);
                    //return new pair(-1, "%" + String.valueOf(var_num - 1));
                }
                else if(left_value.jjtGetNumChildren()==2){

                    SimpleNode array_index = (SimpleNode)left_value.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0);
                    pair index = ge_add_expression(area,array_index,prefix);
                    String var = left_value.jjtGetFirstToken().toString();

                    int length=0;
                    if(in_function ==1&& func_var.containsKey(var)){
                        length = func_array_var.get(var).cnt1;
                        String instr11 = prefix+"%"+String.valueOf(var_num)+" = load i32** %"+var+".addr, align 4\n";
                        //var = ""+var_num;
                        var_num++;
                        area.append(instr11);
                    }
                    else {
                        length = array_var.get(var).cnt1;
                    }

                    String instr="";
                    if(in_function ==1&& func_var.containsKey(var)) {
                        var = String.valueOf(var_num-1);
                        instr = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds i32* %" + var + ", i32 " + index.var + "\n";
                    }
                    else
                        instr = prefix+"%arrayidx"+String.valueOf(arrayidx)+" = getelementptr inbounds ["+String.valueOf(length)+" x i32]* %"+var+", i32 0, i32 "+index.var+"\n";
                    area.append(instr);

                    ge_load(area, "arrayidx" + String.valueOf(arrayidx), prefix);
                    arrayidx = arrayidx+1;
                    //return new pair(-1, "%" + String.valueOf(var_num - 1));
                }
                else if(left_value.jjtGetNumChildren()==3){
                    //SimpleNode left_value =(SimpleNode)node.jjtGetChild(0).jjtGetChild(0);
                    SimpleNode array_index = (SimpleNode)left_value.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0);
                    pair index1 = ge_add_expression(area,array_index,prefix);
                    SimpleNode array_index2 = (SimpleNode)left_value.jjtGetChild(2).jjtGetChild(0).jjtGetChild(0);
                    pair index2 = ge_add_expression(area,array_index2,prefix);

                    String var = left_value.jjtGetFirstToken().toString();

                    int length1=0;
                    int length2=0;
                    if(in_function==1&& func_var.containsKey(var)){
                        length1 = func_array_var.get(var).cnt1;
                        length2 = func_array_var.get(var).cnt2;
                        String instr11 = prefix+"%"+String.valueOf(var_num)+" = load ["+String.valueOf(length2)+" x i32]** %"+var+".addr, align 4\n";
                        //var = ""+var_num;
                        var_num++;
                        area.append(instr11);
                    }
                    else {
                        length1 = array_var.get(var).cnt1;
                        length2 = array_var.get(var).cnt2;
                    }


                    String instr1="";
                    String instr2="";
                    if(in_function==1&& func_var.containsKey(var)){
                        var = String.valueOf(var_num-1);
                        instr1 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %" + var + ", i32 " + index1.var + "\n";
                        arrayidx = arrayidx + 1;
                        area.append(instr1);

                        instr2 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %arrayidx" + String.valueOf(arrayidx - 1) + ", i32 0, i32 " + index2.var + "\n";

                        area.append(instr2);
                    }
                    else {
                        instr1 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length1) + " x [" + String.valueOf(length2) + " x i32]]* %" + var + ", i32 0, i32 " + index1.var + "\n";
                        arrayidx = arrayidx + 1;
                        area.append(instr1);

                        instr2 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %arrayidx" + String.valueOf(arrayidx - 1) + ", i32 0, i32 " + index2.var + "\n";

                        area.append(instr2);
                    }
                    ge_load(area, "arrayidx" + String.valueOf(arrayidx), prefix);
                    arrayidx = arrayidx+1;
                   // return new pair(-1, "%" + String.valueOf(var_num - 1));
                }

                left_pair = new pair(-1,"%"+String.valueOf(var_num-1));
            }
            else if(left.jjtGetChild(0).toString().equals("Constant_expression")){

                SimpleNode temp = (SimpleNode)left.jjtGetChild(0).jjtGetChild(0);
                left_pair = new pair(0,temp.jjtGetFirstToken().toString());
            }
            else if(left.jjtGetChild(0).toString().equals("Function_call_expression")){
                SimpleNode func = (SimpleNode)left.jjtGetChild(0).jjtGetChild(0);
                left_pair = ge_call(area,func,prefix);
            }
            for (int i = 0; i < ex_num - 2; ) {

                SimpleNode right = (SimpleNode) node.jjtGetChild(i + 2);
                pair right_pair =null;


                if(right.jjtGetChild(0).toString().equals("Arithmetic_expression"))
                {
                    right_pair = ge_add_expression(area,(SimpleNode)right.jjtGetChild(0).jjtGetChild(0),prefix);
                }
                else if(right.jjtGetChild(0).toString().equals("Left_value")) {

                    SimpleNode left_value = (SimpleNode)right.jjtGetChild(0);

                    if(left_value.jjtGetNumChildren()==1) {
                        SimpleNode temp=(SimpleNode)right.jjtGetChild(0).jjtGetChild(0);

                        String temp_name = temp.jjtGetFirstToken().toString();
                        if(in_function == 1&& func_var.containsKey(temp_name)){
                            temp_name = func_var.get(temp_name);
                        }
                        ge_load(area,temp_name,prefix);

                        //return new pair(-1, "%" + String.valueOf(var_num - 1));
                    }
                    else if(left_value.jjtGetNumChildren()==2){

                        SimpleNode array_index = (SimpleNode)left_value.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0);
                        pair index = ge_add_expression(area,array_index,prefix);
                        String var = left_value.jjtGetFirstToken().toString();

                        int length=0;
                        if(in_function ==1&& func_var.containsKey(var)){
                            length = func_array_var.get(var).cnt1;
                            String instr11 = prefix+"%"+String.valueOf(var_num)+" = load i32** %"+var+".addr, align 4\n";
                            //var = ""+var_num;
                            var_num++;
                            area.append(instr11);
                        }
                        else {
                            length = array_var.get(var).cnt1;
                        }

                        String instr="";
                        if(in_function ==1&& func_var.containsKey(var)) {
                            var = String.valueOf(var_num-1);
                            instr = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds i32* %" + var + ", i32 " + index.var + "\n";
                        }
                        else
                            instr = prefix+"%arrayidx"+String.valueOf(arrayidx)+" = getelementptr inbounds ["+String.valueOf(length)+" x i32]* %"+var+", i32 0, i32 "+index.var+"\n";
                        area.append(instr);
                        ge_load(area, "arrayidx" + String.valueOf(arrayidx), prefix);
                        arrayidx = arrayidx+1;
                        //return new pair(-1, "%" + String.valueOf(var_num - 1));
                    }
                    else if(left_value.jjtGetNumChildren()==3){
                        //SimpleNode left_value =(SimpleNode)node.jjtGetChild(0).jjtGetChild(0);
                        SimpleNode array_index = (SimpleNode)left_value.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0);
                        pair index1 = ge_add_expression(area,array_index,prefix);
                        SimpleNode array_index2 = (SimpleNode)left_value.jjtGetChild(2).jjtGetChild(0).jjtGetChild(0);
                        pair index2 = ge_add_expression(area,array_index2,prefix);

                        String var = left_value.jjtGetFirstToken().toString();


                        int length1=0;
                        int length2=0;
                        if(in_function==1&& func_var.containsKey(var)){
                            length1 = func_array_var.get(var).cnt1;
                            length2 = func_array_var.get(var).cnt2;
                            String instr11 = prefix+"%"+String.valueOf(var_num)+" = load ["+String.valueOf(length2)+" x i32]** %"+var+".addr, align 4\n";
                            //var = ""+var_num;
                            var_num++;
                            area.append(instr11);
                        }
                        else {
                            length1 = array_var.get(var).cnt1;
                            length2 = array_var.get(var).cnt2;
                        }


                        String instr1="";
                        String instr2="";
                        if(in_function==1&& func_var.containsKey(var)){
                            var = String.valueOf(var_num-1);
                            instr1 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %" + var + ", i32 " + index1.var + "\n";
                            arrayidx = arrayidx + 1;
                            area.append(instr1);

                            instr2 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %arrayidx" + String.valueOf(arrayidx - 1) + ", i32 0, i32 " + index2.var + "\n";

                            area.append(instr2);
                        }
                        else {
                            instr1 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length1) + " x [" + String.valueOf(length2) + " x i32]]* %" + var + ", i32 0, i32 " + index1.var + "\n";
                            arrayidx = arrayidx + 1;
                            area.append(instr1);

                            instr2 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %arrayidx" + String.valueOf(arrayidx - 1) + ", i32 0, i32 " + index2.var + "\n";

                            area.append(instr2);
                        }
                        ge_load(area, "arrayidx" + String.valueOf(arrayidx), prefix);
                        arrayidx = arrayidx+1;
                        // return new pair(-1, "%" + String.valueOf(var_num - 1));
                    }

                    right_pair = new pair(-1,"%"+String.valueOf(var_num-1));
                }
                else if(right.jjtGetChild(0).toString().equals("Constant_expression")){

                    SimpleNode temp = (SimpleNode)right.jjtGetChild(0).jjtGetChild(0);
                    right_pair = new pair(0,temp.jjtGetFirstToken().toString());
                }
                else if(right.jjtGetChild(0).toString().equals("Function_call_expression")){
                    SimpleNode func = (SimpleNode)right.jjtGetChild(0).jjtGetChild(0);
                    left_pair = ge_call(area,func,prefix);
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
        String instr="";
        if(!var.equals("-145876239")){
            instr = prefix + "%call" + String.valueOf(call_num) + " = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([5 x i8]* @.str,     i32 0, i32 0), i32 " + var + ")\n";
        }
        else {
            instr = prefix + "%call" + String.valueOf(call_num) + " = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([2 x i8]* @.str1, i32 0, i32 0))\n";
        }
        area.append(instr);
        call_num = call_num+1;
    }
    public void ge_return(TextArea area,String ret,String prefix){

        String instr =prefix+ "ret i32 "+ret+"\n";
        area.append(instr);

    }

    public pair ge_call(TextArea area,SimpleNode node,String prefix){

        SimpleNode name = (SimpleNode)node.jjtGetChild(0);
        String func_name = name.jjtGetFirstToken().toString();
        SimpleNode parameter = (SimpleNode)node.jjtGetChild(1);
        String instr = prefix;
        int para_num = parameter.jjtGetNumChildren();

        int num = gt.function_table.function_return_type.size();
        for (int i = 0; i < num; i++) {
            if (gt.function_table.function_name.get(i).toString().equals(func_name)) {
                if(!(gt.function_table.function_return_type.get(i)==null)){
                    instr = instr+"%call"+String.valueOf(call_num)+" = "+"call i32 @"+func_name+"(";
                    call_num++;
                }
                else{
                    instr = instr +"call void @"+func_name+"(";
                }
                break;
            }
        }


        for(int i=0;i<para_num;i++) {
            SimpleNode para = (SimpleNode)parameter.jjtGetChild(i);
            String para_name = para.jjtGetFirstToken().toString();
            if(in_function ==1){
                if (func_array_var.containsKey(para_name) && para.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren() == 1) {

                    array_info cur_para = func_array_var.get(para_name);
                    if (cur_para.dim == 1) {
                        String instr1 = prefix + "%"+String.valueOf(var_num)+" = load i32** %"+para_name+".addr, align 4\n";
                        area.append(instr1);
                        instr = instr + "i32* %" +String.valueOf(var_num);
                        var_num++;
                    } else {
                        String instr1 = prefix + "%"+String.valueOf(var_num)+" = load ["+String.valueOf(cur_para.cnt2)+" x i32]** %"+para_name+".addr, align 4\n";
                        area.append(instr1);
                        instr = instr + "[" + String.valueOf(cur_para.cnt2) + " x i32]* %" + String.valueOf(var_num);
                        var_num++;
                    }
                } else {
                    SimpleNode expression = (SimpleNode) para.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
                    pair p = ge_add_expression(area, expression, prefix);
                    instr = instr + "i32 " + p.var;
                }
                if (i == para_num - 1) {
                    instr = instr + ")\n";
                } else {
                    instr = instr + ", ";
                }
            }
            else {
                if (array_var.containsKey(para_name) && para.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren() == 1) {

                    array_info cur_para = array_var.get(para_name);
                    if (cur_para.dim == 1) {
                        String instr1 = prefix + "%arraydecay" + String.valueOf(arraydecay) + " = getelementptr inbounds [" + String.valueOf(cur_para.cnt1) + " x i32]* %" + para_name + ", i32 0, i32 0" + "\n";
                        area.append(instr1);
                        instr = instr + "i32* %arraydecay" + String.valueOf(arraydecay);
                        arraydecay = arraydecay + 1;
                    } else {
                        String instr1 = prefix + "%arraydecay" + String.valueOf(arraydecay) + " = getelementptr inbounds [" + String.valueOf(cur_para.cnt1) + " x [" + String.valueOf(cur_para.cnt2) + " x i32]]* %" + para_name + ", i32 0, i32 0" + "\n";
                        area.append(instr1);
                        instr = instr + "[" + String.valueOf(cur_para.cnt2) + " x i32]* %arraydecay" + String.valueOf(arraydecay);
                        arraydecay = arraydecay + 1;
                    }
                } else {
                    SimpleNode expression = (SimpleNode) para.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
                    pair p = ge_add_expression(area, expression, prefix);
                    instr = instr + "i32 " + p.var;
                }
                if (i == para_num - 1) {
                    instr = instr + ")\n";
                } else {
                    instr = instr + ", ";
                }
            }
        }
        area.append(instr);
        return new pair(-2,"%call"+String.valueOf(call_num-1));
    }
    public void ge_block(TextArea area,SimpleNode node,String prefix){

       // SimpleNode block = (SimpleNode)node.jjtGetChild(0);
        SimpleNode block = node;
        int instr_num = block.jjtGetNumChildren();
        for(int i=0;i<instr_num;i++)
        {
            System.out.println("block\n");
            SimpleNode statement = (SimpleNode)block.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
            SimpleNode statement2=(SimpleNode)block.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0);

            SimpleNode cur_code = (SimpleNode)block.jjtGetChild(i);
            String instr_name = statement.toString();

            if(instr_name.equals("Assignment_expression")){
                SimpleNode left_value=(SimpleNode)block.jjtGetChild(i).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
                SimpleNode additive_expression = (SimpleNode)statement.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0);
                    pair result=ge_add_expression(area,additive_expression,prefix);
                    if(result == null)
                    {
                        System.out.println("result is null\n");
                    }
                    else
                    {
                        if(left_value.jjtGetNumChildren()==1) {
                            String var = cur_code.jjtGetFirstToken().toString();
                            if(in_function == 1&& func_var.containsKey(var)){
                                var = func_var.get(var);
                            }
                            ge_store(area, var, result.var, prefix);
                        }
                        else if(left_value.jjtGetNumChildren()==2){
                            SimpleNode array_index = (SimpleNode)left_value.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0);
                            pair index = ge_add_expression(area,array_index,prefix);
                            String var = cur_code.jjtGetFirstToken().toString();

                            int length=0;
                            if(in_function ==1&& func_var.containsKey(var)){
                                length = func_array_var.get(var).cnt1;
                                String instr11 = prefix+"%"+String.valueOf(var_num)+" = load i32** %"+var+".addr, align 4\n";
                                //var = ""+var_num;
                                var_num++;
                                area.append(instr11);
                            }
                            else {
                                length = array_var.get(var).cnt1;
                            }

                            String instr="";
                            if(in_function ==1 && func_var.containsKey(var)) {
                                var = String.valueOf(var_num-1);
                                instr = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds i32* %" + var + ", i32 " + index.var + "\n";
                            }
                            else
                                instr = prefix+"%arrayidx"+String.valueOf(arrayidx)+" = getelementptr inbounds ["+String.valueOf(length)+" x i32]* %"+var+", i32 0, i32 "+index.var+"\n";
                            area.append(instr);
                            ge_store(area,"arrayidx"+String.valueOf(arrayidx),result.var,prefix);
                            arrayidx = arrayidx+1;
                        }
                        else if(left_value.jjtGetNumChildren()==3){
                            SimpleNode array_index = (SimpleNode)left_value.jjtGetChild(1).jjtGetChild(0).jjtGetChild(0);
                            pair index1 = ge_add_expression(area,array_index,prefix);
                            SimpleNode array_index2 = (SimpleNode)left_value.jjtGetChild(2).jjtGetChild(0).jjtGetChild(0);
                            pair index2 = ge_add_expression(area,array_index2,prefix);

                            String var = cur_code.jjtGetFirstToken().toString();

                            int length1=0;
                            int length2=0;
                            if(in_function==1&& func_var.containsKey(var)){
                                length1 = func_array_var.get(var).cnt1;
                                length2 = func_array_var.get(var).cnt2;
                                String instr11 = prefix+"%"+String.valueOf(var_num)+" = load ["+String.valueOf(length2)+" x i32]** %"+var+".addr, align 4\n";
                                //var = ""+var_num;
                                var_num++;
                                area.append(instr11);
                            }
                            else {
                                length1 = array_var.get(var).cnt1;
                                length2 = array_var.get(var).cnt2;
                            }
                            String instr1="";
                            String instr2="";
                            if(in_function==1&& func_var.containsKey(var)){
                                var = String.valueOf(var_num-1);
                                instr1 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %" + var + ", i32 " + index1.var + "\n";
                                arrayidx = arrayidx + 1;
                                area.append(instr1);

                                instr2 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %arrayidx" + String.valueOf(arrayidx - 1) + ", i32 0, i32 " + index2.var + "\n";

                                area.append(instr2);
                            }
                            else {
                                instr1 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length1) + " x [" + String.valueOf(length2) + " x i32]]* %" + var + ", i32 0, i32 " + index1.var + "\n";
                                arrayidx = arrayidx + 1;
                                area.append(instr1);

                                instr2 = prefix + "%arrayidx" + String.valueOf(arrayidx) + " = getelementptr inbounds [" + String.valueOf(length2) + " x i32]* %arrayidx" + String.valueOf(arrayidx - 1) + ", i32 0, i32 " + index2.var + "\n";

                                area.append(instr2);
                            }
                            ge_store(area,"arrayidx"+String.valueOf(arrayidx),result.var,prefix);
                            arrayidx = arrayidx+1;
                        }
                    }
            }
            else if(instr_name.equals("Print_statement")){
                SimpleNode additive_expression = (SimpleNode)statement.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
                pair result=ge_add_expression(area,additive_expression,prefix);
                ge_print(area, result.var, prefix);
            }
            else if(instr_name.equals("Return_statement")){
                if(statement.jjtGetChild(0).toString().equals("Logical_expression")){
                    SimpleNode logical =  (SimpleNode)statement.jjtGetChild(0).jjtGetChild(0);
                    if(logical.jjtGetFirstToken().toString().equals("yes"))
                        ge_return(area, "1", prefix);
                    else if(logical.jjtGetFirstToken().toString().equals("no"))
                        ge_return(area, "0", prefix);
                }
                else {
                    SimpleNode additive_expression = (SimpleNode) statement.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
                    pair result = ge_add_expression(area, additive_expression, prefix);
                    ge_return(area, result.var, prefix);
                }

            }
            else if(instr_name.equals("Function_statement")){
                System.out.println("func");
                SimpleNode call_expression = (SimpleNode)statement.jjtGetChild(0);
                ge_call(area, call_expression, prefix);
            }
            else if(statement2.toString().equals("If_statement")&&instr_name.equals("Logical_expression")){
                SimpleNode if_statement = (SimpleNode)cur_code.jjtGetChild(0).jjtGetChild(0);
                ge_if(area, if_statement, prefix);

            }
            else if(statement2.toString().equals("While_statement")&&instr_name.equals("Logical_expression")){
                SimpleNode while_statement = (SimpleNode)cur_code.jjtGetChild(0).jjtGetChild(0);
                ge_while(area, while_statement, prefix);
            }
        }
    }

    public void ge_cmp(TextArea area,String cmp,String left_var,String right_var,String prefix,int con){

        if((cmp.equals("==")&&con!=1) || (cmp.equals("!=")&&con==1)){
            String instr =prefix+ "%cmp"+String.valueOf(cmp_num)+" = icmp eq i32 "+left_var+", "+right_var+"\n";
            cmp_num = cmp_num+1;
            area.append(instr);
        }
        else if((cmp.equals("!=")&&con!=1)|| (cmp.equals("==")&&con==1)){
            String instr =prefix+ "%cmp"+String.valueOf(cmp_num)+" = icmp ne i32 "+left_var+", "+right_var+"\n";
            cmp_num = cmp_num+1;
            area.append(instr);
        }
        else if((cmp.equals(">")&&con!=1)|| (cmp.equals("<=")&&con==1)){
            String instr =prefix+ "%cmp"+String.valueOf(cmp_num)+" = icmp sgt i32 "+left_var+", "+right_var+"\n";
            cmp_num = cmp_num+1;
            area.append(instr);
        }
        else if((cmp.equals(">=")&&con!=1)|| (cmp.equals("<")&&con==1)){
            String instr =prefix+ "%cmp"+String.valueOf(cmp_num)+" = icmp sge i32 "+left_var+", "+right_var+"\n";
            cmp_num = cmp_num+1;
            area.append(instr);
        }
        else if((cmp.equals("<")&&con!=1)|| (cmp.equals(">=")&&con==1)){
            String instr =prefix+"%cmp"+String.valueOf(cmp_num)+" = icmp slt i32 "+left_var+", "+right_var+"\n";
            cmp_num = cmp_num+1;
            area.append(instr);
        }
        else if((cmp.equals("<=")&&con!=1)|| (cmp.equals(">")&&con==1)){
            String instr = prefix+"%cmp"+String.valueOf(cmp_num)+" = icmp sle i32 "+left_var+", "+right_var+"\n";
            cmp_num = cmp_num+1;
            area.append(instr);
        }
    }
    public void ge_if_jump(TextArea area,String prefix,int cur_end_num){
        if(or_last<=0 && and_last<=0){
            String instr =prefix+ "br i1 %cmp"+String.valueOf(cmp_num-1)+", label %land.lhs.true"+String.valueOf(true_num)+", label %lor.lhs.false"+String.valueOf(false_num)+"\n\n";
            area.append(instr);
        }
        else if(or_last>=1 && and_last<=0){
            if(if_last==0) {
                String instr = prefix + "br i1 %cmp" + String.valueOf(cmp_num - 1) + ", label %land.lhs.true" + String.valueOf(true_num) + ", label %if.else" + String.valueOf(else_num) + "\n\n";
                area.append(instr);
            }
            else{
                String instr = prefix + "br i1 %cmp" + String.valueOf(cmp_num - 1) + ", label %land.lhs.true" + String.valueOf(true_num) + ", label %if.end" + String.valueOf(cur_end_num) + "\n\n";
                area.append(instr);
            }
        }
        else if(or_last<=0 && and_last>=1){
            String instr = prefix +"br i1 %cmp"+String.valueOf(cmp_num-1)+", label %if.then"+String.valueOf(then_num)+", label %lor.lhs.false"+String.valueOf(false_num)+"\n\n";
            area.append(instr);
        }
        else if(or_last>=1 && and_last>=1){
            if(if_last==0) {
                String instr = prefix + "br i1 %cmp" + String.valueOf(cmp_num-1) + ", label %if.then" + String.valueOf(then_num) + ", label %if.else" + String.valueOf(else_num) + "\n\n";
                area.append(instr);
            }
            else if(if_last==1){
                String instr = prefix + "br i1 %cmp" + String.valueOf(cmp_num-1) + ", label %if.then" + String.valueOf(then_num) + ", label %if.end" + String.valueOf(cur_end_num) + "\n\n";
                area.append(instr);
            }
        }

    }
    public void ge_while_jump(TextArea area,String prefix,int cur_while_num){
        if(or_last<=0 && and_last<=0){
            String instr =prefix+ "br i1 %cmp"+String.valueOf(cmp_num-1)+", label %land.lhs.true"+String.valueOf(true_num)+", label %lor.lhs.false"+String.valueOf(false_num)+"\n\n";
            area.append(instr);
        }
        else if(or_last>=1 && and_last<=0){
                String instr = prefix + "br i1 %cmp" + String.valueOf(cmp_num - 1) + ", label %land.lhs.true" + String.valueOf(true_num) + ", label %while.end" + String.valueOf(cur_while_num) + "\n\n";
                area.append(instr);
        }
        else if(or_last<=0 && and_last>=1){
            String instr = prefix +"br i1 %cmp"+String.valueOf(cmp_num-1)+", label %while.body"+String.valueOf(cur_while_num)+", label %lor.lhs.false"+String.valueOf(false_num)+"\n\n";
            area.append(instr);
        }
        else if(or_last>=1 && and_last>=1){
                String instr = prefix + "br i1 %cmp" + String.valueOf(cmp_num-1) + ", label %while.body" + String.valueOf(cur_while_num) + ", label %while.end" + String.valueOf(cur_while_num) + "\n\n";
                area.append(instr);
        }
    }
    public void ge_relation(TextArea area,SimpleNode node,String prefix,int cur_end_num,int if_or_while,int con){
        if(node.toString().equals("Relation_expression")) {
            SimpleNode left = (SimpleNode) node.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
            SimpleNode rel = (SimpleNode) node.jjtGetChild(1);
            SimpleNode right = (SimpleNode) node.jjtGetChild(2).jjtGetChild(0).jjtGetChild(0);

            //if()


            if (and_last == 0 || and_last == 1) {
                String sub_prefix = prefix.substring(0, prefix.length() - 2);
                String instr = sub_prefix + "land.lhs.true" + String.valueOf(true_num) + ":\n";
                true_num = true_num + 1;
                area.append(instr);
            }
            pair left_pair = ge_add_expression(area, left, prefix);
            pair right_pair = ge_add_expression(area, right, prefix);

            ge_cmp(area, rel.jjtGetFirstToken().toString(), left_pair.var, right_pair.var, prefix,con);
        }
        else if(node.toString().equals("Function_call_expression"))
        {
            pair func = ge_call(area,node,prefix);
            ge_cmp(area, "!=", func.var, "0", prefix,con);
        }
        if (if_or_while == 0) {
            ge_if_jump(area, prefix, cur_end_num);
        } else {
            ge_while_jump(area, prefix, cur_end_num);
        }


    }
    public void ge_and_logical(TextArea area,SimpleNode node,String prefix,int cur_end_num,int if_or_while){
        int and_num = node.jjtGetNumChildren();
        int con=0;
        for(int i=0;i<and_num;){
            and_last=0;
            if(i==0)and_last=-1;
            if(i==and_num-1) and_last=1;
            if(and_num-1==0)and_last=2;

            SimpleNode and_node = (SimpleNode)node.jjtGetChild(i).jjtGetChild(0);
            if(and_node.toString().equals("Unary_logical_operator")) {
                con=1;
                and_node = (SimpleNode) node.jjtGetChild(i).jjtGetChild(1).jjtGetChild(0);
            }
            ge_relation(area,and_node,prefix,cur_end_num, if_or_while,con);
            i=i+2;
        }
        and_last=0;
    }
    public void ge_or_logical(TextArea area,SimpleNode node,String prefix,int cur_end_num,int if_or_while){
        int or_num = node.jjtGetNumChildren();
        SimpleNode left = (SimpleNode)node.jjtGetChild(0);
        if(or_num==1)or_last=2;
        else or_last=-1;
        ge_and_logical(area,left,prefix, cur_end_num,if_or_while);
        if(or_num>1) {

            for (int i = 2; i < or_num; ) {
                or_last=0;
                if(i==or_num-1)or_last=1;
                String sub_prefix = prefix.substring(0,prefix.length()-2);
                String instr =sub_prefix+ "lor.lhs.false"+String.valueOf(false_num)+":\n";
                area.append(instr);
                false_num =false_num+1;
                SimpleNode right = (SimpleNode)node.jjtGetChild(i);
                ge_and_logical(area,right,prefix,cur_end_num, if_or_while);
                i=i+2;
            }
        }
        //记录逻辑表达式走到什么位置，是否已经走到最后
        or_last=0;
        and_last=0;
    }
    public void ge_if(TextArea area,SimpleNode node,String prefix){
        int child_num = node.jjtGetNumChildren();
        int cur_end_num = end_num;
        end_num = end_num+1;
        if(child_num==2)if_last=1;
        for(int i=0;i<child_num;i++){
            SimpleNode child = (SimpleNode)node.jjtGetChild(i);
            if(child.toString().equals("Logical_expression")){
                SimpleNode logical_node =(SimpleNode)child.jjtGetChild(0);
                ge_or_logical(area, logical_node, prefix,cur_end_num,0);
            }
            else if(child.toString().equals("Block")){
                String sub_prefix = prefix.substring(0,prefix.length()-2);
                String instr = sub_prefix+"if.then"+String.valueOf(then_num)+":\n";
                area.append(instr);
                then_num = then_num+1;
               // SimpleNode block_node = (SimpleNode)
                ge_block(area,child,prefix);
                String instr2 = prefix+"br label %if.end"+String.valueOf(cur_end_num)+"\n";
                area.append(instr2);
            }
            else if(child.toString().equals("Elif_statement")){
                if(i==child_num-1)if_last=1;
                String sub_prefix = prefix.substring(0,prefix.length()-2);
                String instr = sub_prefix+"if.else"+String.valueOf(else_num)+":\n";
                else_num = else_num+1;
                area.append(instr);
                SimpleNode elif_node= (SimpleNode)child.jjtGetChild(0).jjtGetChild(0);
                ge_or_logical(area,elif_node,prefix,cur_end_num,0);

                String sub_prefix2 = prefix.substring(0,prefix.length()-2);
                String instr2 = sub_prefix2+"if.then"+String.valueOf(then_num)+":\n";
                area.append(instr2);
                then_num = then_num+1;
                SimpleNode block_node = (SimpleNode)child.jjtGetChild(1);
                ge_block(area,block_node,prefix+"  ");

                String instr3 = prefix+"br label %if.end"+String.valueOf(cur_end_num)+"\n";
                area.append(instr3);

            }
            else if(child.toString().equals("Else_statement")){
                if_last=1;
                String sub_prefix = prefix.substring(0,prefix.length()-2);
                String instr = sub_prefix+"if.else"+String.valueOf(else_num)+":\n";
                else_num = else_num+1;
                area.append(instr);

                SimpleNode block_node = (SimpleNode)child.jjtGetChild(0);
                ge_block(area,block_node,prefix);
                String instr3 = prefix+"br label %if.end"+String.valueOf(cur_end_num)+"\n";
                area.append(instr3);
            }

        }
        String sub_prefix = prefix.substring(0,prefix.length()-2);
        String instr =sub_prefix+ "if.end"+String.valueOf(cur_end_num)+":\n";
        area.append(instr);
        if_last=0;
    }

    public void ge_for(TextArea area,SimpleNode node,String prefix){

    }

    public void ge_while(TextArea area,SimpleNode node,String prefix){
        int cur_while_num=while_num;
        while_num=while_num+1;
        String sub_prefix = prefix.substring(0,prefix.length()-2);
        String instr =prefix+ "br label %while.cond"+String.valueOf(cur_while_num)+"\n\n";
        area.append(instr);

        String instr1 = sub_prefix+ "while.cond"+String.valueOf(cur_while_num)+":\n";
        area.append(instr1);

        SimpleNode logical = (SimpleNode)node.jjtGetChild(0).jjtGetChild(0);
        ge_or_logical(area,logical,prefix,cur_while_num,1);

        SimpleNode block = (SimpleNode)node.jjtGetChild(1);
        String instr2 = sub_prefix+ "while.body"+String.valueOf(cur_while_num)+":\n";
        area.append(instr2);
        ge_block(area,block,prefix+"  ");
        String instr3 = prefix+ "br label %while.cond"+String.valueOf(cur_while_num)+"\n\n";
        area.append(instr3);
        String instr4 = sub_prefix+ "while.end"+String.valueOf(cur_while_num)+":\n";
        area.append(instr4);
    }
    public void ge_function(TextArea area,SimpleNode node,String prefix){
        SimpleNode name =  (SimpleNode)node.jjtGetChild(0).jjtGetChild(0);
        String func_name = name.jjtGetFirstToken().toString();
        String type="";
        int num = gt.function_table.function_return_type.size();
        int i=0;
        for ( i = 0; i < num; i++) {
            if (gt.function_table.function_name.get(i).toString().equals(func_name)) {
                if(!(gt.function_table.function_return_type.get(i)==null)){
                    type = "i32";
                }
                else{
                    type = "void";
                }
                break;
            }
        }
        String instr= "";
        if(type.equals("void"))
        {
            instr="define void @"+func_name+"(";
        }
        else
        {
            instr="define i32 @"+func_name+"(";

        }
        String instr1 = "";
        int para_num = (Integer)gt.function_table.function_parameter_num.get(i);
        ArrayList para_name =(ArrayList) gt.function_table.function_parameter_name.get(i);
        ArrayList para_type =(ArrayList) gt.function_table.function_parameter_type.get(i);
        for(int j= 0;j<para_num;j++){

            String p_name = para_name.get(j).toString();
            String p_type = para_type.get(j).toString();
            if(p_type.equals("integer") || p_type.equals("boolean")){
                func_var.put(p_name,p_name+".addr");
                instr = instr+"i32 %"+p_name;
                if(j==para_num-1){
                    instr = instr+") nounwind {\n"+"entry:\n";
                }
                else{
                    instr = instr +", ";
                }
                instr1 = instr1 +prefix+"%"+p_name+".addr = alloca i32, align 4\n";
                instr1 = instr1 +prefix+"store i32 %"+p_name+", i32* %"+p_name+".addr, align 4\n";
            }
            else{

                for(int k=0;k<gt.type_table.type_name.size();k++)
                {
                    if(gt.type_table.type_name.get(k).toString().equals(p_type))
                    {
                        if(gt.type_table.type_rederence.get(k).toString().equals("integer") || gt.type_table.type_rederence.get(k).toString().equals("boolean")){
                            int cnt = (Integer)gt.type_table.type_array_num.get(k);
                            instr=instr+"i32* %"+p_name;
                            func_var.put(p_name,p_name+".addr");
                            func_array_var.put(p_name,new array_info(1,cnt,0));

                            instr1 = instr1 +prefix+"%"+p_name+".addr = alloca i32*, align 4\n";
                            instr1 = instr1 +prefix+"store i32* %"+p_name+", i32** %"+p_name+".addr, align 4\n";
                            break;
                        }
                        else{
                            int cnt1 = (Integer)gt.type_table.type_array_num.get(k);
                            String type2 = gt.type_table.type_rederence.get(k).toString();
                            for(int kk=0;kk<gt.type_table.type_name.size();kk++)
                            {
                                if(gt.type_table.type_name.get(kk).toString().equals(type2))
                                {

                                    int cnt2 = (Integer)gt.type_table.type_array_num.get(kk);
                                    instr=instr+"["+String.valueOf(cnt2)+" x i32]* %"+p_name;
                                    func_var.put(p_name,p_name+".addr");
                                    func_array_var.put(p_name,new array_info(2,cnt1,cnt2));
                                    instr1 = instr1 +prefix+"%"+p_name+".addr = alloca ["+String.valueOf(cnt2)+" x i32]*, align 4\n";
                                    instr1 = instr1 +prefix+"store ["+String.valueOf(cnt2)+" x i32]* %"+p_name+", ["+String.valueOf(cnt2)+" x i32]** %"+p_name+".addr, align 4\n";
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }

                if(j==para_num-1){
                    instr = instr+") nounwind {\n"+"entry:\n";
                }
                else{
                    instr = instr +", ";
                }
            }
        }
        area.append(instr);
        area.append(instr1);
        Variable_table var_t = (Variable_table)gt.function_table.function_variable_list.get(i);
        ArrayList var_name = var_t.variable_name;
        ArrayList var_type = var_t.variable_type;
        int var_num  = var_name.size();
        for(int j=0;j<var_num;j++){
            String v_name = var_name.get(j).toString();
            String v_type = var_type.get(j).toString();
            if (v_type.equals("integer") || v_type.equals("boolean"))
                ge_alloc(area, v_name,"  ");
            else
                ge_alloc_array(area, v_name,v_type,"  ");

        }
        SimpleNode func_block = (SimpleNode)node.jjtGetChild(1).jjtGetChild(1).jjtGetChild(0);
        ge_block(area,func_block,prefix);
        if(type.equals("i32")){
            area.append("  ret i32 0\n");
        }
        else{
            area.append("  ret void\n");
        }
        area.append("}\n");

    }


    public void ge_all_function(TextArea area,SimpleNode node){
        int func_num  = node.jjtGetNumChildren();
        for(int i = 0;i<func_num;i++)
        {
            init();
            SimpleNode func_node = (SimpleNode)node.jjtGetChild(i).jjtGetChild(0);
            if(func_node.toString().equals("Function_declaration")){

                in_function =1;
                ge_function(area,func_node,"  ");

            }
            init();
        }
        in_function=0;
        init();
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
        for(i=0;i < gt.variable_table.variable_name.size(); i++) {
            if(gt.variable_table.variable_type.get(i).toString().equals("integer") || gt.variable_table.variable_type.get(i).toString().equals("boolean"))
                ge_alloc(area, gt.variable_table.variable_name.get(i).toString(),"  ");
            else
                ge_alloc_array(area, gt.variable_table.variable_name.get(i).toString(),gt.variable_table.variable_type.get(i).toString(),"  ");

        }
        SimpleNode block = (SimpleNode)node.jjtGetChild(0);
        ge_block(area,block,"  ");
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
class array_info{
    int dim;
    int cnt1;
    int cnt2;

    array_info(int d,int c1,int c2){
        dim=d;
        cnt1=c1;
        cnt2=c2;
    }
}


