package com.wind.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

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
 * 注册框
 * @author follow
 *
 */
public class RegFrame extends JFrame{
	
	
	private JLabel userLabel = new JLabel("玩家账号");
	
	private JTextField userField = new JTextField();
	
	private JLabel nickLabel = new JLabel("昵称");
	
	private JTextField nickField = new JTextField();
	
	private JLabel pwdLabel = new JLabel("登录密码");
	
	private JPasswordField pwdField = new JPasswordField();
	
	private JButton reg = new JButton("注册");
	
	private UserDao userDao = new UserDao();
	
	public RegFrame(){
		setTitle("梭哈注册");
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
		userLabel.setBounds(20, 50, 100, 30);
		add(userLabel);
		userField.setBounds(150, 50, 180, 30);
		add(userField);
		
		nickLabel.setBounds(20, 150, 100, 30);
		add(nickLabel);
		nickField.setBounds(150, 150, 180, 30);
		add(nickField);
		
		pwdLabel.setBounds(20, 250, 100, 30);
		add(pwdLabel);
		pwdField.setBounds(150, 250, 180, 30);
		add(pwdField);
		
		reg.setBounds(150, 320, 100, 30);
		add(reg);
		//注册
		reg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = userField.getText().trim();
				String nickname = nickField.getText().trim();
				String pwd = new String(pwdField.getPassword()).trim();
				if(StringUtil.isBlank(username)){
					JOptionPane.showMessageDialog(null, "账号不能为空");
					return;
				}
				if(StringUtil.isBlank(nickname)){
					JOptionPane.showMessageDialog(null, "昵称不能为空");
					return;
				}
				if(StringUtil.isBlank(pwd)){
					JOptionPane.showMessageDialog(null, "密码不能为空");
					return;
				}
				User user = userDao.findByName(username);
				if(user != null){
					JOptionPane.showMessageDialog(null, "账号已存在");
					return;
				}
				user = new User();
				user.setUsername(username);
				user.setNickname(nickname);
				user.setPassword(pwd);
				user.setAmount(BigDecimal.valueOf(10000));
				userDao.insert(user);
				JOptionPane.showMessageDialog(null, "注册成功");
				close();
			}
		});
	}
	
	public void close(){
		this.dispose();
	}
	
	public static void main(String[] args) {
		new RegFrame();
	}
}
