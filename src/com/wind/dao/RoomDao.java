package com.wind.dao;

import java.util.List;

import com.wind.model.Room;
import com.wind.util.DbUtil;

/**
 * 房间列表
 * @author follow
 *
 */
public class RoomDao {

	/**
	 * 查询所有房间
	 * @return
	 */
	public List<Room> listAll() {
        String sql = "select * from room where 1=1";
        return DbUtil.executeQuery(sql, ps -> {}, Room.class);
    }
}
