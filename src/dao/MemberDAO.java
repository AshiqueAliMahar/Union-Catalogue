package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.connection.DBConnect;
import model.Member;

public class MemberDAO {
	private static String sql="";
	private static LibrarianDAO librarianDAO=new LibrarianDAO();
	public static boolean addMember(Member member) {
		boolean isAdded=false;
		sql="INSERT INTO `library`.`members`\n" + 
				"(`member_id`,\n" + 
				"`father_name`,\n" + 
				"`address`,\n" + 
				"`blood_group`,\n" + 
				"`gender`,\n" + 
				"`dept_code`,\n" + 
				"`cnic_bf_pp_no`,\n" + 
				"`dob`,\n" + 
				"`status`,\n" + 
				"`email`) VALUES (?,?,?,?,?,?,?,?,?,?);";
		
		boolean isLibAdded=!librarianDAO.save(member.getLibrarian());
		if (isLibAdded) {
			try(Connection con = DBConnect.getCon();) {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, member.getMemberId());
				ps.setString(2, member.getFatherName());
				ps.setString(3, member.getAddress());
				ps.setString(4, member.getBloodGroup().getValue());
				ps.setString(5, member.getGender().toString());
				ps.setString(6, member.getDept().getCode());
				ps.setString(7, member.getCnicBfPpNo());
				ps.setDate(8, member.getDob());
				ps.setString(9, member.getStatus().toString());
				ps.setString(10, member.getEmail());
				isAdded=!ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isAdded;
	}
	
	public static boolean updateMember(Member member) {
		boolean isAdded=false;
		sql="UPDATE `library`.`members`\n" + 
				"SET\n" + 
				"`father_name` = ?,\n" + 
				"`address` = ?,\n" + 
				"`blood_group` = ?,\n" + 
				"`gender` = ?,\n" + 
				"`dept_code` = ?,\n" + 
				"`cnic_bf_pp_no` = ?,\n" + 
				"`dob` = ?,\n" + 
				"`status` = ?\n" + 
				"WHERE `email` = ? and member_id=?;";
		
		boolean isLibAdded=librarianDAO.update(member.getLibrarian());
		if (isLibAdded) {
			try(Connection con = DBConnect.getCon();) {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(10, member.getMemberId());
				ps.setString(1, member.getFatherName());
				ps.setString(2, member.getAddress());
				ps.setString(3, member.getBloodGroup().getValue());
				ps.setString(4, member.getGender().toString());
				ps.setString(5, member.getDept().getCode());
				ps.setString(6, member.getCnicBfPpNo());
				ps.setDate(7, member.getDob());
				ps.setString(8, member.getStatus().toString());
				ps.setString(9, member.getEmail());
				isAdded=!ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isAdded;
	}
	
	public static List<Member> findAll(String collegeCode) {
		List<Member>  members=new LinkedList<>();
		sql="SELECT \n" + 
				"	`librarian`.`email`,\n" + 
				"    `librarian`.`name`,\n" + 
				"    `librarian`.`cell`,\n" + 
				"    `librarian`.`password`,\n" + 
				"    `librarian`.`pic`,\n" + 
				"    `librarian`.`post`,\n" + 
				"    `librarian`.`college_code`,\n" + 
				"    `members`.`member_id`,\n" + 
				"    `members`.`father_name`,\n" + 
				"    `members`.`address`,\n" + 
				"    `members`.`blood_group`,\n" + 
				"    `members`.`gender`,\n" + 
				"    `members`.`dept_code`,\n" + 
				"    `members`.`cnic_bf_pp_no`,\n" + 
				"    `members`.`dob`,\n" + 
				"    `members`.`status`\n" + 
				"    \n" + 
				"FROM `library`.`members`,`library`.`librarian` where `members`.`email`=`librarian`.`email` and `librarian`.`college_code`=?;";
		
		try (Connection con = DBConnect.getCon();){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, collegeCode);
			ResultSet rSet = ps.executeQuery();
			while (rSet.next()) {
				Member member=new Member(rSet.getString(1), rSet.getString(2),rSet.getString(3), rSet.getString(4), rSet.getBytes(5), rSet.getString(6), new CollegeDAO().findById(rSet.getString(7)), rSet.getString(8), rSet.getString(9), rSet.getString(10),rSet.getString(11), rSet.getString(12), new DeptDAO().findById(rSet.getString(13)), rSet.getString(14), rSet.getDate(15), rSet.getString(16));
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	public static Member findByMemberId(String memberId,String collegeCode) {
		
		sql="SELECT \n" + 
				"	`librarian`.`email`,\n" + 
				"    `librarian`.`name`,\n" + 
				"    `librarian`.`cell`,\n" + 
				"    `librarian`.`password`,\n" + 
				"    `librarian`.`pic`,\n" + 
				"    `librarian`.`post`,\n" + 
				"    `librarian`.`college_code`,\n" + 
				"    `members`.`member_id`,\n" + 
				"    `members`.`father_name`,\n" + 
				"    `members`.`address`,\n" + 
				"    `members`.`blood_group`,\n" + 
				"    `members`.`gender`,\n" + 
				"    `members`.`dept_code`,\n" + 
				"    `members`.`cnic_bf_pp_no`,\n" + 
				"    `members`.`dob`,\n" + 
				"    `members`.`status`\n" + 
				"    \n" + 
				"FROM `library`.`members`,`library`.`librarian` where `members`.`email`=`librarian`.`email` and `librarian`.`college_code`=? and member_id=?;";
		Member member=null;
		try (Connection con = DBConnect.getCon();){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, collegeCode);
			ps.setString(2, memberId);
			ResultSet rSet = ps.executeQuery();
			if(rSet.next()) {
				member=new Member(rSet.getString(1), rSet.getString(2),rSet.getString(3), rSet.getString(4), rSet.getBytes(5), rSet.getString(6), new CollegeDAO().findById(rSet.getString(7)), rSet.getString(8), rSet.getString(9), rSet.getString(10),rSet.getString(11), rSet.getString(12), new DeptDAO().findById(rSet.getString(13)), rSet.getString(14), rSet.getDate(15), rSet.getString(16));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	public static boolean isMemberExist(String email,String memberId) {
		boolean isFound=false;
		sql="SELECT * FROM `library`.`members` where `members`.`email`=? and `members`.`member_id`=?;";
		
		try(Connection con = DBConnect.getCon()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, memberId);
			isFound=ps.executeQuery().next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isFound;
	}
	public static boolean isMemberExistInCollege(String collegeCode,String memberId) {
		boolean isFound=false;
		sql="SELECT * FROM `library`.`members`,librarian where `members`.`email`=librarian.email and `members`.`member_id`=? and librarian.college_code=?;";
		
		try(Connection con = DBConnect.getCon()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, memberId);
			ps.setString(2, collegeCode);
			isFound=ps.executeQuery().next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isFound;
	}
}
