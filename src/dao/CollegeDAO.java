package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.connection.DBConnect;
import iDao.ICollegeDAO;
import model.College;

public class CollegeDAO implements ICollegeDAO {
	
	private String sql="";
	private boolean isPersist;
	
	@Override
	public College findById(String collegeCode) {
		College college = new College();
		sql="SELECT `colleges`.`code`,\n" + 
				"    `colleges`.`name`,\n" + 
				"    `colleges`.`website`,\n" + 
				"    `colleges`.`email`,\n" + 
				"    `colleges`.`contact`,\n" + 
				"    `colleges`.`address`,\n" + 
				"    `colleges`.`logo`"
				+ "FROM `library`.`colleges` where `colleges`.`code`=?;";
		
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, collegeCode);
			ResultSet rs =ps.executeQuery();
			if (rs.next()) {
				college=new College(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBytes(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return college;
	}

	@Override
	public List<College> findAll() {
		sql="SELECT `colleges`.`code`,\n" + 
				"    `colleges`.`name`,\n" + 
				"    `colleges`.`website`,\n" + 
				"    `colleges`.`email`,\n" + 
				"    `colleges`.`contact`,\n" + 
				"    `colleges`.`address`,\n" + 
				"    `colleges`.`logo`"
				+ "FROM `library`.`colleges`;";
		List<College> collegeList=new LinkedList<>();
		try(Connection con = DBConnect.getCon();) {
			ResultSet rs = con.createStatement().executeQuery(sql);
			while (rs.next()) {
				College college=new College(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBytes(7));
				collegeList.add(college);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return collegeList;
	}
	public List<College> findAll(String collegeCode) {
		sql="SELECT `colleges`.`code`,\n" + 
				"    `colleges`.`name`,\n" + 
				"    `colleges`.`website`,\n" + 
				"    `colleges`.`email`,\n" + 
				"    `colleges`.`contact`,\n" + 
				"    `colleges`.`address`,\n" + 
				"    `colleges`.`logo`"
				+ "FROM `library`.`colleges` where `colleges`.`code`=?;";
		List<College> collegeList=new LinkedList<>();
		try(Connection con = DBConnect.getCon();) {
			 PreparedStatement ps= con.prepareStatement(sql);
			 ps.setString(1, collegeCode);
			 ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				College college=new College(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBytes(7));
				collegeList.add(college);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return collegeList;
	}
	@Override
	public boolean save(College college) {
		sql="INSERT INTO `library`.`colleges` (`code`,`name`,`website`,`email`,`contact`,`address`,`logo`) VALUES (?,?,?,?,?,?,?);";
		isPersist=false;
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, college.getCode());
			ps.setString(2, college.getName());
			ps.setString(3, college.getWebsite());
			ps.setString(4, college.getEmail());
			ps.setString(5, college.getContact());
			ps.setString(6, college.getAddress());
			ps.setBytes(7,college.getLogo());
			isPersist=!ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isPersist;
	}
	public boolean update(College college) {
		isPersist=false;
		if (college.getLogo().length>0) {
			sql="UPDATE `library`.`colleges`\n" + 
					"SET\n" + 
					"`code` = ?,\n" + 
					"`name` = ?,\n" + 
					"`website` = ?,\n" + 
					"`email` = ?,\n" + 
					"`contact` = ?,\n" + 
					"`address` = ?,\n" + 
					"`logo` = ?\n" + 
					"WHERE `code` = ?;";
		}else {
			sql="UPDATE `library`.`colleges`\n" + 
					"SET\n" + 
					"`code` = ?,\n" + 
					"`name` = ?,\n" + 
					"`website` = ?,\n" + 
					"`email` = ?,\n" + 
					"`contact` = ?,\n" + 
					"`address` = ?" + 
					"WHERE `code` = ?;";
		}
		
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, college.getCode());
			ps.setString(2, college.getName());
			ps.setString(3, college.getWebsite());
			ps.setString(4, college.getEmail());
			ps.setString(5, college.getContact());
			ps.setString(6, college.getAddress());
			if (college.getLogo().length>0) {
				ps.setBytes(7,college.getLogo());
				ps.setString(8, college.getCode());
			}else {
				ps.setString(7, college.getCode());
			}
			isPersist=!ps.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return isPersist;
	}
	public long count() {
		long count=0;
		sql="SELECT count(*) FROM library.colleges;";
		try(Connection con = DBConnect.getCon();) {
			ResultSet rs = con.createStatement().executeQuery(sql);
			if (rs.next()) {
				count=rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public boolean isExist(String code) {
		isPersist=false;
		sql="select * from library.colleges where colleges.code=?";
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			isPersist=rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isPersist;
	}
}
