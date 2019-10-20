package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.connection.DBConnect;
import iDao.IDepttDAO;
import model.Dept;

public class DeptDAO implements IDepttDAO {

	private String sql="";
	private List<Dept> deptColleges=new LinkedList<>();
	private PreparedStatement ps;
	private ResultSet rs;
	private boolean isUpdated;
	
	@Override
	public Dept findById(String code) {
		sql="SELECT `dept`.`code`,\n" + 
				"    `dept`.`name`,\n" + 
				"    `dept`.`website`,\n" + 
				"    `dept`.`email`,\n" + 
				"    `dept`.`contact`,\n" + 
				"    `dept`.`address`,`dept`.`logo`"+ 
				"FROM `library`.`dept` where `dept`.`code`=? ;";
		Dept deptt=new Dept();
		try(Connection con = DBConnect.getCon();) {
			ps=con.prepareStatement(sql);
			ps.setString(1, code);
			rs=ps.executeQuery();
			if (rs.next()) {
				
				deptt.setCode(rs.getString(1));
				deptt.setName(rs.getString(2));
				deptt.setWebsite(rs.getString(3));
				deptt.setEmail(rs.getString(4));
				deptt.setContact(rs.getString(5));
				deptt.setAddress(rs.getString(6));
				deptt.setLogo(rs.getBytes("logo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deptt;
	}
	
	public Dept findByName(String name) {
		sql="SELECT `dept`.`code`,\n" + 
				"    `dept`.`name`,\n" + 
				"    `dept`.`website`,\n" + 
				"    `dept`.`email`,\n" + 
				"    `dept`.`contact`,\n" + 
				"    `dept`.`address`,`dept`.`logo`"+ 
				"FROM `library`.`dept` where `dept`.`name`=? ;";
		Dept deptt=new Dept();
		try(Connection con = DBConnect.getCon();) {
			ps=con.prepareStatement(sql);
			ps.setString(1, name);
			rs=ps.executeQuery();
			if (rs.next()) {
				
				deptt.setCode(rs.getString(1));
				deptt.setName(rs.getString(2));
				deptt.setWebsite(rs.getString(3));
				deptt.setEmail(rs.getString(4));
				deptt.setContact(rs.getString(5));
				deptt.setAddress(rs.getString(6));
				deptt.setLogo(rs.getBytes("logo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deptt;
	}
	
	@Override
	public List<Dept> findAll() {
		sql="SELECT `dept`.`code`,`dept`.`name`,`dept`.`website`,`dept`.`email`,`dept`.`contact`,`dept`.`address`,`dept`.`logo` FROM `library`.`dept`";
		try(Connection con = DBConnect.getCon();) {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			deptColleges=new LinkedList<>();
			while (rs.next()) {
				Dept dept=new Dept();
				dept.setCode(rs.getString(1));
				dept.setName(rs.getString(2));
				dept.setWebsite(rs.getString(3));
				dept.setEmail(rs.getString(4));
				dept.setContact(rs.getString(5));
				dept.setAddress(rs.getString(6));
				dept.setLogo(rs.getBytes("logo"));
				deptColleges.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deptColleges;
	}

	@Override
	public boolean save(Dept dept) {
		sql="INSERT INTO `library`.`dept` (`code`,`name`,`website`,`email`,`contact`,`address`,`logo`) VALUES (?,?,?,?,?,?,?);";
		try(Connection con = DBConnect.getCon();) {
			ps=con.prepareStatement(sql);
			ps.setString(1, dept.getCode());
			ps.setString(2, dept.getName());
			ps.setString(3, dept.getWebsite());
			ps.setString(4, dept.getEmail());
			ps.setString(5, dept.getContact());
			ps.setString(6, dept.getAddress());
			ps.setBytes(7, dept.getLogo());
			isUpdated=!ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public boolean update(Dept dept) {
		
		if (dept.getLogo().length>0) {
			sql="UPDATE `library`.`dept` SET `name` = ?,`website` = ?,`email` = ?,`contact` = ?,`address` = ?,`logo` = ? WHERE `code` = ?;";
		}else {
			sql="UPDATE `library`.`dept` SET `name` = ?,`website` = ?,`email` = ?,`contact` = ?,`address` = ? WHERE `code` = ?;";
		}
		try(Connection con = DBConnect.getCon();) {
			ps=con.prepareStatement(sql);
			
			ps.setString(1, dept.getName());
			ps.setString(2, dept.getWebsite());
			ps.setString(3, dept.getEmail());
			ps.setString(4, dept.getContact());
			ps.setString(5, dept.getAddress());
			if (dept.getLogo().length>0) {
				ps.setBytes(6 ,dept.getLogo());
				ps.setString(7, dept.getCode());
			}else {
				ps.setString(6, dept.getCode());
			}
			isUpdated=!ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	public boolean isExist(String code) {
		sql="select code from dept where code=?";
		boolean isExist=false;
		try(Connection con = DBConnect.getCon();) {
			ps=con.prepareStatement(sql);
			ps.setString(1, code);
			isExist=ps.executeQuery().next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}

}
