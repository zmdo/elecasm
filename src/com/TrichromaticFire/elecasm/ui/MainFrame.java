package com.TrichromaticFire.elecasm.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.TrichromaticFire.elecasm.core.beans.AbsoluteCode;
import com.TrichromaticFire.elecasm.core.woker.CodeReader;
import com.TrichromaticFire.elecasm.core.woker.CodeTranslator;

public class MainFrame {
	
	private JFrame mainFrame;
	
	private Font font;
	
	private JTextArea input;
	private JTextArea output;
	
	private JButton submitButton;
	private JButton orderButton;
	private JButton resetButton;
	
	public MainFrame(){
		mainFrame = new JFrame("机器码翻译器");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(1200, 800);
		mainFrame.setLayout(null);
		mainFrame.setResizable(false);
		init();
		mainFrame.setVisible(true);
	}

	private void init() {
		//设置转换按钮
		submitButton = new JButton("转换");
		submitButton.setSize(100, 35);
		submitButton.setLocation(20, 20);
		initSubmitButton();
		//设置顺序转换按钮
		orderButton = new JButton("顺序转换");
		orderButton.setSize(100, 35);
		orderButton.setLocation(130, 20);
		initOrderButton();
		//设置重置按钮
		resetButton = new JButton("重置");
		resetButton.setSize(100, 35);
		resetButton.setLocation(240, 20);
		initResetButton();
		
		//设置字体
		font = new Font("Times New Roma", 0, 20);

		//输入框
		input = new JTextArea();
		input.setFont(font);
		
		JScrollPane inputjsp = new JScrollPane(input);
		inputjsp.setSize(550, 650);
		inputjsp.setLocation(20,70);
		inputjsp.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
		inputjsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//输出框
		output = new JTextArea();
		output.setFont(font);
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		
		JScrollPane outputjsp = new JScrollPane(output);
		outputjsp.setSize(550, 650);
		outputjsp.setLocation(610,70);
		outputjsp.setBorder(BorderFactory.createLineBorder(Color.RED,2));
		outputjsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		outputjsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//将组件添加入面板
		mainFrame.add(submitButton);
		mainFrame.add(orderButton);
		mainFrame.add(resetButton);
		mainFrame.add(inputjsp);
		mainFrame.add(outputjsp);
	}
	
	
	private void initOrderButton() {
		orderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Queue<AbsoluteCode> queue;
				queue = CodeTranslator.translate(CodeReader.read(input.getText()));
				StringBuilder outText = new StringBuilder();
				int index = 0;
				int now = 0;
				for(AbsoluteCode code:queue){
					index ++;
					if(code.getCodes() == null){
						if(index != 1) outText.append("\n");
						outText.append("行"+index+"出错！请检查");
						break;
					}
					String tmp,nowstr;
					for(int i = 0; i < code.getSize() ; i++){
						tmp = code.getHexadecimalCode(i).toUpperCase();
						nowstr = Integer.toHexString(now).toUpperCase();
						if(nowstr.length() < 2) nowstr = "0" + nowstr;
						outText.append("[" + nowstr + "]" + "    ");
						if(tmp.length() < 2){
							outText.append("0" + tmp);
						} else {
							outText.append(tmp);
						}
						outText.append("\n");
						now ++;
					}
				}
				output.setText(outText.toString());
			}
		});
	}

	private void initResetButton() {
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "您确定重置吗", "重置", 0) == 0){
					input.setText("");
					output.setText("");
				}
			}
		});
		
	}

	private void initSubmitButton() {
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Queue<AbsoluteCode> queue;
				queue = CodeTranslator.translate(CodeReader.read(input.getText()));
				StringBuilder outText = new StringBuilder();
				int index = 0;
				for(AbsoluteCode code:queue){
					index ++;
					if(code.getCodes() == null){
						if(index != 1) outText.append("\n");
						outText.append("代码出错！请检查");
						break;
					}
					String tmp;
					for(int i = 0; i < code.getSize() ; i++){
						tmp = code.getHexadecimalCode(i).toUpperCase();
						if(tmp.length() < 2){
							outText.append("0" + tmp);
						} else {
							outText.append(tmp);
						}
						outText.append(" ");
					}
				}
				output.setText(outText.toString());
			}
		});
	}
}
