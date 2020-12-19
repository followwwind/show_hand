package com.wind.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.wind.dao.UserDao;
import com.wind.model.User;

/**
 * 游戏大厅
 * @author follow
 *
 */
public class RoomFrame extends JFrame{
	
	private JPanel centerPanel = new JPanel();
	
	private JPanel eastPanel = new JPanel();
	
	private UserDao userDao = new UserDao();
	
	public RoomFrame(){
		setTitle("游戏大厅");
		setSize(600, 400);
		init();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * 初始化游戏界面
	 */
	public void init(){
		setLayout(new BorderLayout());
		List<User> orderList = userDao.orderList();
		List<String> strList = orderList.stream().map(o -> String.format("%d %s %d", o.getId(), o.getNickname(), 
				(int)o.getAmount().doubleValue())).collect(Collectors.toList());
		JList<String> personList = new JList<String>();
		personList.setModel(new AbstractListModel<String>() {
			@Override
			public int getSize() {
				return strList.size();
			}

			@Override
			public String getElementAt(int index) {
				return strList.get(index);
			}
		});
		eastPanel.setLayout(new CardLayout());
		eastPanel.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 0));
		eastPanel.setPreferredSize(new Dimension(200, 400));
		eastPanel.add(personList);
		add(centerPanel, BorderLayout.CENTER);
		add(eastPanel, BorderLayout.EAST);
	}
	
	
	public static void main(String[] args) {
		new RoomFrame();
	}
}
