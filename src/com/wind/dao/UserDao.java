package com.wind.dao;

import java.util.List;

import com.wind.model.User;
import com.wind.util.DbUtil;

/**
 * 用户数据库操作类
 * @author follow
 *
 */
public class UserDao {
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public int insert(User user) {
        String sql = "insert into user values(?, ?, ?, ?, ?)";
        return DbUtil.executeUpdate(sql, ps -> {
        	ps.setString(1, null);
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getNickname());
            ps.setString(4, user.getPassword());
            ps.setBigDecimal(5, user.getAmount());
        });
    }
	
	/**
	 * 通过账号查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	public User findByName(String username) {
        String sql = "select * from user where username = ?";
        List<User> list =  DbUtil.executeQuery(sql, ps -> {
            ps.setString(1, username);
        }, User.class);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
	
	/**
	 * 余额排行
	 * @return
	 */
	public List<User> orderList() {
        String sql = "select * from user order by amount desc limit 10";
        return DbUtil.executeQuery(sql, ps -> {}, User.class);
    }
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        List<User> list =  DbUtil.executeQuery(sql, ps -> {
            ps.setString(1, username);
            ps.setString(2, password);
        }, User.class);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
	
	
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		User user = dao.login("123", "123");
		System.out.println(user.getAmount());
		System.out.println(user.getId());
	}

}
