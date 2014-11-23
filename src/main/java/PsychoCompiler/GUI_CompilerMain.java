package PsychoCompiler;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import jjt.MyLangTree;
import jjt.SimpleNode;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class GUI_CompilerMain {
    public static void main(String args[]) {
        CompilerGUI comgui = new CompilerGUI(); //这里是程序开始执行的地方，新建一个窗口
    }
}


class CompilerGUI extends Frame implements ActionListener {
    FileDialog  open;
    Button      load, ge_token, ge_tree,quit;//按钮
    TextArea    tarea;//文本框
    TextArea    tareaout;
    FileInputStream ml_file = null;
    FileInputStream ml_file2 = null;
    MyLangTree parser;
    CompilerGUI() {
        super("Compiler");//标题
        setLayout(null);//布局
        setBackground(Color.gray);//背景色
        setSize(1140, 700);//设置大学
        setVisible(true);//设置可见
        load = new Button("Load File");//创建按钮
        ge_token = new Button("GE Token");
        ge_tree = new Button("GE Tree");
        quit = new Button("Exit");
        tarea = new TextArea("");//创建文本框
        tareaout = new TextArea("");
        tarea.setFont(new Font("SansSerif",Font.BOLD,15));
        tareaout.setFont(new Font("SansSerif",Font.BOLD,15));
        add(load);//添加按钮
        add(ge_token);
        add(ge_tree);
        add(quit);
        add(tarea);
        add(tareaout);
        tarea.setBounds(30, 50, 440, 600);//设置文本框位置
        tareaout.setBounds(600,50,500,600);
        load.setBounds(500, 60, 70, 30);
        ge_token.setBounds(500, 120, 70, 30);
        ge_tree.setBounds(500, 180, 70, 30);
        quit.setBounds(500,240,70,30);
        open = new FileDialog(this, "打开", FileDialog.LOAD);//新建对话框
        //sv = new FileDialog(this, "保存", FileDialog.SAVE);

        open.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(".ml")) return true;
                return false;
            }
        });
        load.addActionListener(this);//设置按钮点击监听事件
        ge_token.addActionListener(this);
        ge_tree.addActionListener(this);
        quit.addActionListener(this);
        addWindowListener(new WindowAdapter() {//关闭事件处理
            public void windowClosing(WindowEvent e) {
                setVisible(false);//设置不可见
                System.exit(0);//程序关闭
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {//按钮1事件处理
            String str;
            open.setVisible(true);//设置可见
            try {
                File prog = new File(open.getDirectory(), open.getFile());//打开
                if (prog.isFile() && prog.exists()) {
                    System.out.println("find file success");
                } else {
                    System.out.println("not find file");
                }

                FileReader fr = new FileReader(prog);
                BufferedReader br = new BufferedReader(fr);//读取文件
                tarea.setText("");//设置文本框内容为空
                while ((str = br.readLine()) != null)
                    tarea.append(str + '\n');//文本框添加读取的文件内容
                fr.close();//关闭读取
            } catch (Exception e1) {//如果有错误，这里进行处理
                e1.printStackTrace();//打印错误信息
            }
        }

        if (e.getSource() == ge_token) {//按钮1事件处理
            try {

                //File f1 = new File(sv.getDirectory(), sv.getFile());
               // FileWriter fw = new FileWriter(f1);
               // BufferedWriter bw = new BufferedWriter(fw);
               // String gt = tarea.getText();//获取文本框内容
               // bw.write(gt, 0, gt.length());//将文本框内容写入文件
                //bw.flush();//真正的写入文件
                //fw.close();//关闭写入
                if(!tarea.getText().equals("")){
                    File temfile = new File("tempfile");
                    try {
                        FileWriter fw = new FileWriter(temfile);
                        BufferedWriter bw = new BufferedWriter(fw);
                        String gt = tarea.getText();//获取文本框内容
                        bw.write(gt, 0, gt.length());//将文本框内容写入文件
                        bw.flush();//真正的写入文件
                        fw.close();//关闭写入
                    }catch(Exception e6){
                        e6.printStackTrace();
                    }
                    try {
                        ml_file = new FileInputStream(temfile);
                        //ml_file2=new FileInputStream();
                    }
                    catch (FileNotFoundException e3) {
                        System.out.print("src file open failed.");
                    }
                    parser = new MyLangTree(ml_file);
                    // Token test
                    String temp = null;
                    tareaout.setText(" ");
                   //String str;
                    int i=0;
                    while(!(temp = parser.getNextToken().toString()).equals("")) {
                        //System.out.print(temp + " ");
                        tareaout.append(temp + ' ');
                        i++;
                        if(i%10 == 0)tareaout.append("\n");
                    }
                    //System.out.println(" ");
                }
                else{
                    tareaout.setText(" ");
                    tareaout.append("file has not been load");
                }
            }catch (Error e4){
                    System.out.println("Token Error::");
                    System.out.println(e4.getMessage());
                    tareaout.setText(" ");
                    tareaout.append("\n\n");
                    tareaout.append("Token Error::" + e4.getMessage());
            }

        }
        if(e.getSource() == ge_tree)
            try{
                //file = new File(parent_path + file_name);

                //ml_file = new FileInputStream(file);
                if(!tarea.getInputContext().equals("")) {
                    File temfile = new File("tempfile");
                    try {
                        FileWriter fw = new FileWriter(temfile);
                        BufferedWriter bw = new BufferedWriter(fw);
                        String gt = tarea.getText();//获取文本框内容
                        bw.write(gt, 0, gt.length());//将文本框内容写入文件
                        bw.flush();//真正的写入文件
                        fw.close();//关闭写入
                    }catch(Exception e6){
                        e6.printStackTrace();
                    }
                    try {
                        ml_file2 = new FileInputStream(temfile);
                        //ml_file2=new FileInputStream();
                    }
                    catch (FileNotFoundException e3) {
                        System.out.print("src file open failed.");
                    }
                    parser = new MyLangTree(ml_file2);
                    SimpleNode root = parser.Start();
                    tareaout.setText(" ");
                    root.dumptoarea("",tareaout);
                   // root.dump("");
                 //   System.out.println("Format true!");
                }
                else{
                    tareaout.setText(" ");
                    tareaout.append("file has not been load");
                }
            } catch (Exception e5){
                //System.out.println("Parser Exception:");
                //System.out.println(e5.getMessage());
                tareaout.setText(" ");
                tareaout.append("\n\n");
                tareaout.append("Parser Exception:" + e5.getMessage());
            }
        if(e.getSource() == quit)
            try{
                setVisible(false);
                System.exit(0);
            }catch (Exception e3) {
                e3.printStackTrace();
            }
    }
}