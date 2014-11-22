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
    FileInputStream ml_file = null;
    FileInputStream ml_file2 = null;
    MyLangTree parser;
    CompilerGUI() {
        super("Compiler");//标题
        setLayout(null);//布局
        setBackground(Color.gray);//背景色
        setSize(800, 700);//设置大学
        setVisible(true);//设置可见
        load = new Button("加载文件");//创建按钮
        ge_token = new Button("生成Token");
        ge_tree = new Button("生成语法树");
        quit = new Button("退出");
        tarea = new TextArea("");//创建文本框
        tarea.setFont(new Font("SansSerif",Font.BOLD,15));
        add(load);//添加按钮
        add(ge_token);
        add(ge_tree);
        add(quit);
        add(tarea);
        tarea.setBounds(30, 50, 640, 600);//设置文本框位置
        load.setBounds(700, 60, 70, 30);
        ge_token.setBounds(700, 120, 70, 30);
        ge_tree.setBounds(700, 180, 70, 30);
        quit.setBounds(700,240,70,30);
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
                try {
                    ml_file = new FileInputStream(prog);
                    ml_file2=new FileInputStream(prog);
                }
                catch (FileNotFoundException e3) {
                    System.out.print("src file open failed.");
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
                if(ml_file !=null){
                    parser = new MyLangTree(ml_file);
                    // Token test
                    String temp = null;
                    tarea.setText(" ");
                   //String str;
                    int i=0;
                    while(!(temp = parser.getNextToken().toString()).equals("")) {
                        //System.out.print(temp + " ");
                        tarea.append(temp + ' ');
                        i++;
                        if(i%10 == 0)tarea.append("\n");
                    }
                    //System.out.println(" ");
                }
                else{
                    tarea.setText(" ");
                    tarea.append("file has not been load");
                }
            }catch (Exception e4){
                    System.out.println("Token Error::");
                    System.out.println(e4.getMessage());
                    tarea.setText(" ");
                    tarea.append("\n\n");
                    tarea.append("Token Error::" + e4.getMessage());
            }

        }
        if(e.getSource() == ge_tree)
            try{
                //file = new File(parent_path + file_name);

                //ml_file = new FileInputStream(file);
                if(ml_file2 != null) {
                    parser = new MyLangTree(ml_file2);
                    SimpleNode root = parser.Start();
                    tarea.setText(" ");
                    root.dumptoarea("",tarea);
                   // root.dump("");
                 //   System.out.println("Format true!");
                }
                else{
                    tarea.setText(" ");
                    tarea.append("file has not been load");
                }
            } catch (Exception e5){
                //System.out.println("Parser Exception:");
                //System.out.println(e5.getMessage());
                tarea.setText(" ");
                tarea.append("\n\n");
                tarea.append("Parser Exception:" + e5.getMessage());
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