package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.connection.DBConnect;
import model.Journal;

public class JournalDAO {
	static private  String  sql="";
	private static CollegeDAO collegeDAO=new CollegeDAO();
	public static List<Journal> findByCollegeCode(String collegeCode) {
		List<Journal> journals=new LinkedList<>();
		sql="SELECT `journal`.`id`,\n" + 
				"    `journal`.`title`,\n" + 
				"    `journal`.`issuance`,\n" + 
				"    `journal`.`volume`,\n" + 
				"    `journal`.`year`,\n" + 
				"    `journal`.`pIssn`,\n" + 
				"    `journal`.`eIssn`,\n" + 
				"    `journal`.`publisher`,\n" + 
				"    `journal`.`website`,\n" + 
				"    `journal`.`email`,\n" + 
				"    `journal`.`college_code`\n" + 
				"FROM `library`.`journal` where college_code=?;\n";
		
		try(Connection con = DBConnect.getCon();) {
			  PreparedStatement pStatement = con.prepareStatement(sql);
			  pStatement.setString(1, collegeCode);
			  ResultSet rSet =pStatement.executeQuery();
			 while (rSet.next()) {
				Journal journal=new Journal(rSet.getLong(1), rSet.getString(2), rSet.getInt(3), rSet.getInt(4), rSet.getDate(5), rSet.getString(6), rSet.getString(7), rSet.getString(8), rSet.getString(9), rSet.getString(10), collegeDAO.findById(rSet.getString(11)));
				journals.add(journal);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return journals;
	}
	public static Journal findByCollegeCode(String collegeCode,String eIssn) {
		
		sql="SELECT `journal`.`id`,\n" + 
				"    `journal`.`title`,\n" + 
				"    `journal`.`issuance`,\n" + 
				"    `journal`.`volume`,\n" + 
				"    `journal`.`year`,\n" + 
				"    `journal`.`pIssn`,\n" + 
				"    `journal`.`eIssn`,\n" + 
				"    `journal`.`publisher`,\n" + 
				"    `journal`.`website`,\n" + 
				"    `journal`.`email`,\n" + 
				"    `journal`.`college_code`\n" + 
				"FROM `library`.`journal` where `journal`.`college_code`=? and `journal`.`eIssn`=?;\n";
		Journal journal=null;
		try(Connection con = DBConnect.getCon();) {
			  PreparedStatement ps = con.prepareStatement(sql);
			  ps.setString(1, collegeCode);
			  ps.setString(2, eIssn);
			  ResultSet rSet =ps.executeQuery();
			 if (rSet.next()) {
				journal=new Journal(rSet.getLong(1), rSet.getString(2), rSet.getInt(3), rSet.getInt(4), rSet.getDate(5), rSet.getString(6), rSet.getString(7), rSet.getString(8), rSet.getString(9), rSet.getString(10), collegeDAO.findById(rSet.getString(11)));
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return journal;
	}
	public static boolean save(Journal journal) {
		boolean isSaved=false;
		if (journal.getId()==0) {
			sql="INSERT INTO `library`.`journal`\n" + 
					"(`title`,\n" + 
					"`issuance`,\n" + 
					"`volume`,\n" + 
					"`year`,\n" + 
					"`pIssn`,\n" + 
					"`eIssn`,\n" + 
					"`publisher`,\n" + 
					"`website`,\n" + 
					"`email`,\n" + 
					"`college_code`)\n" + 
					"VALUES (?,?,?,?,?,?,?,?,?,?);";
		}else if (journal.getId()>0) {
			sql="UPDATE `library`.`journal`\n" + 
					"SET\n" + 
					"`title` = ?,\n" + 
					"`issuance` = ?,\n" + 
					"`volume` = ?,\n" + 
					"`year` = ?,\n" + 
					"`pIssn` = ?,\n" + 
					"`eIssn` = ?,\n" + 
					"`publisher` = ?,\n" + 
					"`website` = ?,\n" + 
					"`email` = ?,\n" + 
					"`college_code` = ?\n" + 
					"WHERE `id` = ?;";
		}
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			int i=0;
			ps.setString(++i, journal.getTitle());
			ps.setInt(++i, journal.getIssuance());
			ps.setInt(++i, journal.getVolume());
			ps.setDate(++i, journal.getYear());
			ps.setString(++i, journal.getpIssn());
			ps.setString(++i, journal.geteIssn());
			ps.setString(++i, journal.getPublisher());
			ps.setString(++i, journal.getWebsite());
			ps.setString(++i, journal.getEmail());
			ps.setString(++i, journal.getCollege().getCode());
			if (journal.getId()>0) ps.setLong(++i, journal.getId());
			isSaved=!ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSaved;
	}
}
