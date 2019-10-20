package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.connection.DBConnect;
import iDao.IUsersDAO;
import model.College;
import model.Librarian;

public class LibrarianDAO implements IUsersDAO {
	private static String sql="";
	private CollegeDAO collegeDAO = new CollegeDAO();
	private Librarian librarian=new Librarian();
	@Override
	public Librarian findBy(String email, String password) {
		sql="SELECT `librarian`.`email`,\n" + 
				"    `librarian`.`name`,\n" + 
				"    `librarian`.`cell`,\n" + 
				"    `librarian`.`password`,\n" + 
				"    `librarian`.`pic`,\n" + 
				"    `librarian`.`post`,\n" + 
				"    `librarian`.`college_code`\n" + 
				"FROM `library`.`librarian` where `librarian`.`email`=? and `librarian`.`password`=?;";
		Librarian users=null;
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2,password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				users=new Librarian();
				users.setEmail(rs.getString("email"));
				users.setName( rs.getString("name"));
				users.setCell( rs.getString("cell"));
				users.setPassword( rs.getString("password"));
				users.setPic(rs.getBytes("pic"));;
				users.setPost(rs.getString("post"));
				users.setCollege(collegeDAO.findById(rs.getString("college_code")));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	public boolean isLibExist(String email) {
		sql="SELECT `librarian`.`email` FROM `library`.`librarian` where `librarian`.`email`=?";
		boolean isExist=false;
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			isExist=rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isExist;
	}
	@Override
	public List<Librarian> findAll() {
		sql="SELECT `librarian`.`email`,`librarian`.`name`,`librarian`.`cell`,`librarian`.`password`,`librarian`.`pic`,`librarian`.`post`,`librarian`.`college_code` FROM `library`.`librarian`;";
		List<Librarian> librarians=new LinkedList<>();
		try(Connection con = DBConnect.getCon();) {
			ResultSet rs = con.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				librarian=new Librarian();
				librarian.setEmail(rs.getString("email"));
				librarian.setName(rs.getString("name"));
				librarian.setCell(rs.getString("cell"));
				librarian.setPassword(rs.getString("password"));
				librarian.setPic(rs.getBytes("pic"));
				librarian.setPost(rs.getString("post"));
				College college=collegeDAO.findById(rs.getString("college_code"));
				librarian.setCollege(college);
				librarians.add(librarian);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return librarians;
	}
	public List<Librarian> findAll(String collegeCode,String post) {
		sql="SELECT `librarian`.`email`,`librarian`.`name`,`librarian`.`cell`,`librarian`.`password`,`librarian`.`pic`,`librarian`.`post`,`librarian`.`college_code` FROM `library`.`librarian` where `librarian`.`college_code`=? and `librarian`.`post`!=?;";
		List<Librarian> librarians=new LinkedList<>();
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setString(1, collegeCode);
			ps.setString(2, post);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				librarian=new Librarian();
				librarian.setEmail(rs.getString("email"));
				librarian.setName(rs.getString("name"));
				librarian.setCell(rs.getString("cell"));
				librarian.setPassword(rs.getString("password"));
				librarian.setPic(rs.getBytes("pic"));
				librarian.setPost(rs.getString("post"));
				College college=collegeDAO.findById(rs.getString("college_code"));
				librarian.setCollege(college);
				librarians.add(librarian);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return librarians;
	}

	@Override
	public boolean save(final Librarian librarian) {
		sql="INSERT INTO `library`.`librarian` (`email`,`name`,`cell`,`password`,`pic`,`post`,`college_code`) VALUES(?,?,?,?,?,?,?);";
		boolean isSaved=false;
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, librarian.getEmail());
			ps.setString(2, librarian.getName());
			ps.setString(3, librarian.getCell());
			ps.setString(4, librarian.getPassword());
			ps.setBytes(5, librarian.getPic());
			ps.setString(6, librarian.getPost().toString());
			ps.setString(7, librarian.getCollege().getCode());
			
			isSaved=!ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSaved;
	}

	@Override
	public boolean update(Librarian librarian) {
		
		if (librarian.getPic().length>0) {
			sql="UPDATE `library`.`librarian`\n" + 
					"SET\n" + 
					"`email` = ?,\n" + 
					"`name` = ?,\n" + 
					"`cell` = ?,\n" + 
					"`password` = ?,\n" + 
					"`pic` = ?,\n" + 
					"`post` = ?," + 
					"`college_code` = ?" + 
					"WHERE `email` = ?;";
		}else {
			sql="UPDATE `library`.`librarian`\n" + 
					"SET \n" + 
					"`email` = ?,\n" + 
					"`name` = ?,\n" + 
					"`cell` = ?,\n" + 
					"`password` = ?,"+ 
					"`post` = ?," + 
					"`college_code` = ? " + 
					"WHERE `email` = ?;";
		}
		boolean isUpdated=false;
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, librarian.getEmail());
			ps.setString(2, librarian.getName());
			ps.setString(3, librarian.getCell());
			ps.setString(4, librarian.getPassword());
			
			if (librarian.getPic().length>0) {
				ps.setBytes(5, librarian.getPic());
				ps.setString(6, librarian.getPost().toString());
				ps.setString(7, librarian.getCollege().getCode());
				ps.setString(8, librarian.getEmail());
			}else {
				ps.setString(5, librarian.getPost().toString());
				ps.setString(6, librarian.getCollege().getCode());
				ps.setString(7, librarian.getEmail());
			}
			isUpdated=!ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}
	public boolean delete(String email) {
		sql="DELETE FROM `library`.`librarian` WHERE email=?;";
		boolean isDel=false;
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			isDel=!ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDel;
	}
	public static boolean changePassword(String email,String password) {
		sql="UPDATE `library`.`librarian` SET `password` = ? WHERE `email` = ?;";
		boolean isPwdChanged=false;
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, email);
			isPwdChanged=!ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isPwdChanged;
	}
}
