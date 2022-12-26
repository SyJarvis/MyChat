package com.taihua.ui;

import com.taihua.entity.ChatData;
import com.taihua.entity.Message;
import com.taihua.entity.User;
import com.taihua.utils.IOUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.List;

public class ChatWindow extends JPanel {

    private JTextField textField;
    private JButton button;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JPanel bottom_panel;

    private JComboBox<String> online_users;
    // 信息
//    private Map<String, String> messages;
    private List<String> messages;
    // 客户端
    private Socket s;
    // 用户
    private User user;

    private int flag; // 1私聊 2群聊

    private int loginStatus; // 1 已登录，0是未登录



    public ChatWindow(){
        messages = new ArrayList<String>();
        this.setSize(600, 300);
//        this.setBackground(new Color(220, 191, 157));
        user = new User();
        init();
        addListener();
    }

    public void init(){
        // 所有在线用户
        online_users = new JComboBox<String>();
        // 文本域
        textArea = new JTextArea();
        // 设置文本域默认不能编辑
        textArea.setEditable(false);
        // 滚动面板
        scrollPane = new JScrollPane(textArea);
        // 底部面板，放置输入框和按钮
        bottom_panel = new JPanel();
        // 文本输入框初始化
        textField = new JTextField(16);
        button = new JButton("发送");


        scrollPane.setPreferredSize(new Dimension(560, 300));
        textField.setPreferredSize(new Dimension(300, 30));
        button.setPreferredSize(new Dimension(70, 30));

        // 底部面板添加组件
        bottom_panel.add(textField);
        bottom_panel.add(button);

        this.setLayout(new FlowLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottom_panel, BorderLayout.SOUTH);

        writeText();
    }

    /* 编写信息 */
    public void writeText(){
        String text = "";
        for (String t : messages){
            text += t + "\n";
        }
        System.out.println("编写信息");
        textArea.setText(text);
    }

    public void addListener(){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("发送消息");
                String text = textField.getText();
                System.out.println("text:" + text);
                Message msg = new Message();
                msg.setText(text);
                msg.setFlag(flag);
                ChatData chatData = new ChatData();
                System.out.println(user.getUsername());
                chatData.setUser(user);
                chatData.setMessage(msg);
                sendInfo2Server(chatData);
                // 编写信息
                messages.add(user.getUsername() + ":" + msg.getText());
                writeText();
            }
        });
    }

    public void connectServer(){
        try {
            s = new Socket("127.0.0.1", 10001);
            ChatData d = new ChatData();
            d.setUser(user);
            IOUtil.writeObject(d, s.getOutputStream());
            System.out.println("连接服务器");
            // 启动读数据的线程
            new Thread(new GetInfoFromServer()).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // 给服务器发信息
    public void sendInfo2Server(ChatData chatData){
        try {
            IOUtil.writeObject(chatData, s.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    // 从服务器读数据的线程
    class GetInfoFromServer implements Runnable {
        @Override
        public void run() {
            while (true){
                try {
                    ChatData chatData = (ChatData) IOUtil.readObject(s.getInputStream());
                    System.out.println("服务器返回数据啦");
                    System.out.println(chatData.getMessage().getText());
                    String text = chatData.getUser().getUsername() + ":" + chatData.getMessage().getText();
                    messages.add(text);
//                    messages.put(chatData.getUser().getUsername(), chatData.getMessage().getText());
                    writeText();
//                    Set<User> users = chatData.getOnline_users();
//                    Iterator<User> it = users.iterator();

//                    while (it.hasNext()){
//                        System.out.println(it.next().getUsername());
//                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // 1是私聊，2是群聊
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }
}
