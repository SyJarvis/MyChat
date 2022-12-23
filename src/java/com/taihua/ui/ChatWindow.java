package com.taihua.ui;

import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JPanel {

    private JTextField textField;
    private JButton button;
    private JTextArea textArea;

    public ChatWindow(){
        this.setSize(600, 300);
        this.setBackground(new Color(220, 191, 157));

        init();
        addListener();
    }

    public void init(){
        // 文本输入框初始化
//        String[]colors={"红色","绿色","蓝色"};
//        //声明列表框          使用重载方法传入字符串数组
//        JList<String>colorList=new JList<>(colors);
        textField = new JTextField(16);
        button = new JButton("发送");


        textField.setPreferredSize(new Dimension(300, 30));
        button.setPreferredSize(new Dimension(70, 30));
        JLabel jLabel1 = new JLabel("admin:你好呀");
        JLabel jLabel2 = new JLabel("shangye:hello");
        JLabel jLabel3 = new JLabel("admin:很高兴认识你");

        this.setLayout(new FlowLayout());
        this.add(textField);
        this.add(button);
    }

    public void addListener(){

    }
}
