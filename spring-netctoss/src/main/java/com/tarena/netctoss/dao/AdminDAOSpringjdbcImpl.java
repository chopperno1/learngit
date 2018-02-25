package com.tarena.netctoss.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tarena.netctoss.entity.Admin;

@Repository("adminDAO")
public class AdminDAOSpringjdbcImpl 
implements AdminDAO{
	
	@Resource(name="jt")
	private JdbcTemplate jt;
	
	public Admin findByAdminCode(
			String adminCode) {
		Admin admin = null;
		String sql = 
				"select * from admin_info_lhh "
				+ "where admin_code=?";
		Object[] args = {adminCode};
		List<Admin> admins = 
				jt.query(sql, args,
						new AdminRowMapper());
		if(admins != null  && admins.size() > 0){
			admin = admins.get(0);
		}
		return admin;
	}
	
	class AdminRowMapper implements 
	RowMapper<Admin>{

		public Admin mapRow(ResultSet rs,
				int arg1) throws SQLException {
			Admin admin = new Admin();
			admin.setAdminId(rs.getInt("admin_id"));
			admin.setAdminCode(rs.getString("admin_code"));
			admin.setPassword(rs.getString("password"));
			admin.setName(rs.getString("name"));
			admin.setTelephone(rs.getString("telephone"));
			admin.setEmail(rs.getString("email"));
			admin.setEnrolldate(rs.getTimestamp("enrolldate"));
			return admin;
		}
		
		
	}

}
