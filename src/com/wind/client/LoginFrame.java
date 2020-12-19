package com.wind.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.wind.dao.UserDao;
import com.wind.model.User;
import com.wind.util.StringUtil;

/**
 * 登录框
 * @author follow
 *
 */
public class LoginFrame extends JFrame{
	
	private JLabel ipLabel = new JLabel("服务器游戏地址");
	
	private JTextField ipField = new JTextField();
	
	private JLabel userLabel = new JLabel("玩家账号");
	
	private JTextField userField = new JTextField();
	
	private JLabel pwdLabel = new JLabel("登录密码");
	
	private JPasswordField pwdField = new JPasswordField();
	
	private JButton login = new JButton("登录");
	
	private JButton reg = new JButton("注册");
	
	private UserDao userDao = new UserDao();
	
	public LoginFrame(){
		setTitle("梭哈登录");
		setSize(400, 500);
		init();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * 初始化组件
	 */
	public void init(){
		setLayout(null);
		ipLabel.setBounds(20, 50, 100, 30);
		add(ipLabel);
		ipField.setBounds(150, 50, 180, 30);
		add(ipField);
		
		userLabel.setBounds(20, 150, 100, 30);
		add(userLabel);
		userField.setBounds(150, 150, 180, 30);
		add(userField);
		
		pwdLabel.setBounds(20, 250, 100, 30);
		add(pwdLabel);
		pwdField.setBounds(150, 250, 180, 30);
		add(pwdField);
		
		login.setBounds(100, 320, 100, 30);
		add(login);
		
		//登录
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = userField.getText().trim();
				String ip = ipField.getText().trim();
				String pwd = new String(pwdField.getPassword()).trim();
				if(StringUtil.isBlank(username)){
					JOptionPane.showMessageDialog(null, "账号不能为空");
					return;
				}
				if(StringUtil.isBlank(ip)){
					JOptionPane.showMessageDialog(null, "服务器游戏地址不能为空");
					return;
				}
				if(StringUtil.isBlank(pwd)){
					JOptionPane.showMessageDialog(null, "密码不能为空");
					return;
				}
				User user = userDao.login(username, pwd);
				if(user == null){
					JOptionPane.showMessageDialog(null, "账号或密码错误");
					return;
				}
				
			}
		});
		
		reg.setBounds(200, 320, 100, 30);
		add(reg);
		
		//去注册
		reg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegFrame();
			}
		});
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}
}
