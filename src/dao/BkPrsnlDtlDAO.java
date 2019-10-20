package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import db.connection.DBConnect;
import model.BkPrsnlDtl;


public class BkPrsnlDtlDAO {
	private static String sql="";
	private static CollegeDAO collegeDAO=new CollegeDAO();
	public static int countCopies(int barcode) {
		sql="SELECT count(*) FROM `library`.`bk_prsnl_dtl` where `bk_prsnl_dtl`.`barcode_common`=?;";
		int noOfCopies=0;
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,barcode);
			ResultSet rs = ps.executeQuery();
			noOfCopies=rs.next()?rs.getInt(1):0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noOfCopies;
	}
	public static List<BkPrsnlDtl> getByBkBarcodeCommon(int barcodeCommon){
		List<BkPrsnlDtl> bkPrsnlDtls=new LinkedList<>();
		sql="SELECT `bk_prsnl_dtl`.`barcode`,\n" + 
				"    `bk_prsnl_dtl`.`vendor`,\n" + 
				"    `bk_prsnl_dtl`.`bill_no`,\n" + 
				"    `bk_prsnl_dtl`.`bill_date`,\n" + 
				"    `bk_prsnl_dtl`.`accession_no`,\n" + 
				"    `bk_prsnl_dtl`.`location`,\n" + 
				"    `bk_prsnl_dtl`.`barcode_common`,"
				+ "`bk_prsnl_dtl`.`college_code`" + 
				"FROM `library`.`bk_prsnl_dtl` where `bk_prsnl_dtl`.`barcode_common`=?;";
		try(Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,barcodeCommon);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BkPrsnlDtl bkPrsnlDtl=new BkPrsnlDtl(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getString(6),rs.getInt(7),collegeDAO.findById(rs.getString(8)));
				bkPrsnlDtls.add(bkPrsnlDtl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bkPrsnlDtls;
	}
	public static int save(BkPrsnlDtl bkPrsnlDtl) {
		int barcode=0;
		sql="INSERT INTO `library`.`bk_prsnl_dtl`\n" + 
				"(`vendor`,\n" + 
				"`bill_no`,\n" + 
				"`bill_date`,\n" + 
				"`accession_no`,\n" + 
				"`location`,\n" + 
				"`barcode_common`,\n" + 
				"`college_code`) values\n" + 
				" (?,?,?,?,?,?,?)";
		//IFNULL(max(`accession_no`),0)+1 
		try (PreparedStatement ps = DBConnect.getCon().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
			ps.setString(1, bkPrsnlDtl.getVendor());
			ps.setString(2, bkPrsnlDtl.getBillNo());
			ps.setDate(3, bkPrsnlDtl.getBillDate());
			ps.setInt(4, bkPrsnlDtl.getAccessionNo());
			ps.setString(5, bkPrsnlDtl.getLocation());
			ps.setInt(6, bkPrsnlDtl.getBarcode_common());
			ps.setString(7, bkPrsnlDtl.getCollege().getCode());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				barcode=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return barcode;
	}
	public static boolean update(BkPrsnlDtl bkPrsnlDtl) {
		boolean isUpdated=false;
		sql="UPDATE `library`.`bk_prsnl_dtl`\n" + 
				"SET `vendor` = ?,\n" + 
				"`bill_no` = ?,\n" + 
				"`bill_date` = ?,accession_no=?,`location` = ?,`college_code` = ?\n" + 
				"WHERE `barcode` = ?;";
		try (PreparedStatement ps = DBConnect.getCon().prepareStatement(sql);){
			ps.setString(1, bkPrsnlDtl.getVendor());
			ps.setString(2, bkPrsnlDtl.getBillNo());
			ps.setDate(3, bkPrsnlDtl.getBillDate());
			ps.setInt(4, bkPrsnlDtl.getAccessionNo());
			ps.setString(5, bkPrsnlDtl.getLocation());
			ps.setString(6, bkPrsnlDtl.getCollege().getCode());
			ps.setInt(7, bkPrsnlDtl.getBarcode());
			isUpdated=!ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUpdated;
	}
	public boolean isAccessionExist(int start,int end,String collegeCode) {
		boolean isExist=false;
		sql="SELECT accession_no FROM library.bk_prsnl_dtl where accession_no between ? and ? and college_code=?;";
		try(Connection con = DBConnect.getCon()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ps.setString(3, collegeCode);
			ResultSet rSet = ps.executeQuery();
			isExist=rSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}
	public boolean isAccessionExist(int start,int end,String collegeCode,int barcode) {
		boolean isExist=false;
		sql="SELECT accession_no FROM library.bk_prsnl_dtl where accession_no between ? and ? and college_code=? and barcode!=?;";
		try(Connection con = DBConnect.getCon()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ps.setString(3, collegeCode);
			ps.setInt(4, barcode);
			ResultSet rSet = ps.executeQuery();
			isExist=rSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}
	
}
