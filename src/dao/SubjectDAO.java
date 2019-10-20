package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import db.connection.DBConnect;
import iDao.ISubjectDAO;
import model.Subject;

public class SubjectDAO implements ISubjectDAO {
	private String sql="";
	private List<Subject> subjectList=new LinkedList<>();
	private PreparedStatement ps;
	private ResultSet rs;
	private boolean isUpdated;
	@Override
	public Subject findById(String title) {
		sql="SELECT `subjects`.`subject_title`,\n" + 
				"    `subjects`.`call_no` " + 
				"FROM `library`.`subjects` where `subjects`.`subject_title`=? ";
		Subject subject=new Subject();
		try (Connection con=DBConnect.getCon();){
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				subject.setTitle(rs.getString(1));
				subject.setCallNo(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subject;
	}

	@Override
	public List<Subject> findAll() {
		sql="SELECT `subjects`.`subject_title`,\n" + 
				"    `subjects`.`call_no`" + 
				"FROM `library`.`subjects`";
		subjectList=new LinkedList<>();
		try (Connection con=DBConnect.getCon();){
			rs =con.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				Subject subject=new Subject();
				subject.setTitle(rs.getString(1));
				subject.setCallNo(rs.getString(2));
				subjectList.add(subject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subjectList;
	}

	@Override
	public boolean save(Subject subject) {
		sql="INSERT INTO `library`.`subjects` (`subject_title`,`call_no`) VALUES (?,?,?);";
		
		try (Connection con=DBConnect.getCon();){
			ps =con.prepareStatement(sql);
			ps.setString(1, subject.getTitle());
			ps.setString(2, subject.getCallNo());
			isUpdated=!ps.execute(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public boolean update(Subject subject) {
		sql="UPDATE `library`.`subjects` SET `call_no` = ? WHERE `subject_title` = ?";
		
		try (Connection con=DBConnect.getCon();){
			ps =con.prepareStatement(sql);
			ps.setString(1, subject.getCallNo());
			ps.setString(2, subject.getTitle());
			isUpdated=!ps.execute(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

}
