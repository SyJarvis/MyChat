package com.taihua.ui;

import com.taihua.entity.User;
import com.taihua.service.UserService;
import com.taihua.utils.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    UserService userService = new UserService();
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

        chatWindow.setLoginStatus(0);
    }

    public void addListener(){
        // 监听事件
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("请输入用户名");
                if (userService.checkUser(username) != null){
                    JOptionPane.showConfirmDialog(null, "已有此用户", "提示",JOptionPane.DEFAULT_OPTION);
                    return;
                };
                String password = JOptionPane.showInputDialog("请输入密码");
                User user = new User(0, username, password, 1, Helper.getDateTime(), Helper.getDateTime());
                int result = userService.register(user);
                if (result==1){
                    JOptionPane.showConfirmDialog(null, "已注册成功", "提示",JOptionPane.DEFAULT_OPTION);
                }else {
                    JOptionPane.showConfirmDialog(null, "注册失败", "提示",JOptionPane.DEFAULT_OPTION);
                }
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("请输入用户名");
                User user = userService.checkUser(username);
                if (user == null){
                    JOptionPane.showConfirmDialog(null, "此用户未注册", "提示",JOptionPane.DEFAULT_OPTION);
                    return;
                };
                String password = JOptionPane.showInputDialog("请输入密码");
                if (user.getPassword().equals(password)){
                    JOptionPane.showConfirmDialog(null, "登录成功", "提示",JOptionPane.DEFAULT_OPTION);
                    userInfo.setText(user.getUsername());
                    chatWindow.setLoginStatus(1);
                    chatWindow.setUser(user);
                    chatWindow.connectServer();
                } else {
                    JOptionPane.showConfirmDialog(null, "登录失败", "提示",JOptionPane.DEFAULT_OPTION);
                }
            }
        });

        privateChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatWindow.setFlag(1);
                JOptionPane.showConfirmDialog(null, "私聊模式", "提示",JOptionPane.DEFAULT_OPTION);
            }
        });

        publicChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatWindow.setFlag(2);
                JOptionPane.showConfirmDialog(null, "群聊模式", "提示",JOptionPane.DEFAULT_OPTION);
            }
        });
    }
    public static void main(String[] args) {
        new ChatBox();
    }
}
