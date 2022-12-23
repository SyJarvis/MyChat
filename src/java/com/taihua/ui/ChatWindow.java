package com.taihua.ui;

import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JPanel {

    private JTextField textField;
    private JButton button;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JPanel bottom_panel;

    public ChatWindow(){
        this.setSize(600, 300);
//        this.setBackground(new Color(220, 191, 157));

        init();
        addListener();
    }

    public void init(){
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


        scrollPane.setPreferredSize(new Dimension(600, 300));
        textField.setPreferredSize(new Dimension(300, 30));
        button.setPreferredSize(new Dimension(70, 30));

        // 底部面板添加组件
        bottom_panel.add(textField);
        bottom_panel.add(button);

        this.setLayout(new FlowLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottom_panel, BorderLayout.SOUTH);
    }

    public void addListener(){

    }
}
