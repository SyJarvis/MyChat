package com.taihua.ui;

import javax.swing.*;
import java.awt.*;

public class ChatBox extends JFrame {

    private JMenuBar bar; // 菜单栏
    private JMenu system; //系统功能
    private JMenu chat; // 聊天类型

    private JMenuItem login; // 登录功能
    private JMenuItem register; //注册功能
    private JMenuItem privateChat; // 私聊
    private JMenuItem publicChat; // 群聊

    private ChatWindow chatWindow; // 聊天窗口

    public static JLabel userInfo; // 用户信息，聊天窗口北边

    public JTextField textField; // 文本输入框
    public JButton button; // 发送框



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
        getContentPane();
        addListener();
    }

    public void init(){

        // 初始化
        bar = new JMenuBar();
        system = new JMenu("系统功能");
        chat = new JMenu("聊天方式");
        register = new JMenuItem("注册");
        login = new JMenuItem("登录");
        privateChat = new JMenuItem("私聊");
        publicChat = new JMenuItem("群聊");

        userInfo = new JLabel("未登录");
        // 设置标签的位置
        userInfo.setHorizontalAlignment(JLabel.CENTER);
        // 显示聊天的信息
        chatWindow = new ChatWindow();


        userInfo.setPreferredSize(new Dimension(600, 50));

        // 组件注册
        bar.add(system);
        bar.add(chat);
        system.add(register);
        system.add(login);
        chat.add(privateChat);
        chat.add(publicChat);

        // 主空间
        this.setJMenuBar(bar);
        this.setLayout(new BorderLayout());
        this.add(userInfo, BorderLayout.NORTH); // 北方
        this.add(chatWindow, BorderLayout.CENTER); // 中间


    }

    public void addListener(){
        // 监听事件
    }
    public static void main(String[] args) {
        new ChatBox();
    }
}
