package ui;

import javax.swing.*;
import java.awt.*;

public class ChatBox extends JFrame {

    private JMenuBar bar; // 菜单栏
    private JMenu system; //系统功能

    private JMenuItem login; // 登录功能
    private JMenuItem register; //注册功能

    private ChatWindow chatWindow; // 聊天窗口

    public ChatBox(){
        super("聊天程序v1.0");

        init();
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        // 界面定位
        this.setLocation((width-600)/2, (height-600)/2);
        // 让界面显示
        this.setVisible(true);

        addListener();
    }

    public void init(){

        // 初始化
        bar = new JMenuBar();
        system = new JMenu("系统功能");
        register = new JMenuItem("注册");
        login = new JMenuItem("登录");


        // 组件注册
        bar.add(system);
        system.add(register);
        system.add(login);

        //
        this.setJMenuBar(bar);
        this.setLayout(new BorderLayout());
    }

    public void addListener(){
        // 监听事件
    }
    public static void main(String[] args) {
        new ChatBox();
    }
}
