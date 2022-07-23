package com.test.mybatis.controller;

import javax.servlet.ServletContextListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.concurrent.Flow;

public class EventDemo implements ActionListener {


    JFrame frame;
    JPanel panel;
    JButton button1, button2;
    public EventDemo(){
        frame = new JFrame("动作事件");
        button1 = new JButton("确定");
        button2 = new JButton("取消");
        button1.addActionListener(this);
        button2.addActionListener(this);
        panel = new JPanel();
        panel.add(button1);
        panel.add(button2);
        frame.add(panel);
        frame.setSize(240,240);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1){
            frame.setTitle("单击确定");
        }else {
            frame.setTitle("单击取消");
        }

    }

    public static void main(String[] args) {
        new EventDemo();
    }
}
