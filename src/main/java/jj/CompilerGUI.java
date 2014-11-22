package jj;

/**
 * Created by NinetuOne on 2014/11/22.
 */
import java.awt.*;
public class CompilerGUI extends Frame implements CompilerConstants{
    static Button load = new Button("加载程序文件");
    static Button ge_token = new Button("生成token_stream");
    static Button ge_tree = new Button("生成语法树");
    static Button quit = new Button("退出");

    static Label display = new Label();

    public CompilerGUI() {

        super("Compiler");
        GridBagLayout gb = new GridBagLayout();
        setLayout(gb);
        GridBagConstraints gbc = new GridBagConstraints();

       // display.setFont(new Font("TimesRoman", Font.BOLD, 18));
       // display.setAlignment(Label.RIGHT);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        //gb.setConstraints(display, gbc);
        add(display);

        Panel buttonPanel = new Panel();
        buttonPanel.setFont(new Font("TimesRoman", Font.BOLD, 14));
        buttonPanel.setLayout(new GridLayout(4,4));
        buttonPanel.add(load); buttonPanel.add(ge_token); buttonPanel.add(ge_tree); buttonPanel.add(quit);
        gbc.weighty = 1.0;
        gb.setConstraints(buttonPanel, gbc);
        add(buttonPanel);

      //  quit.setFont(new Font("TimesRoman", Font.BOLD, 14));
      //  gbc.gridheight = GridBagConstraints.REMAINDER;
      //  gbc.weighty = 0.0;
     //   gb.setConstraints(quit, gbc);
     //   add(quit);
        pack();
        show();
    }
}
