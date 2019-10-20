package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import db.connection.DBConnect;
import iDao.IBookDAO;
import model.Book;

public class BookDAO implements IBookDAO {
	
	private static String sql = "";
	private boolean isUpdated;
	//private BkPrsnlDtlDAO bkPrsnlDtlDAO=new BkPrsnlDtlDAO();
	private SubjectDAO subjectDAO=new SubjectDAO();
	private CollegeDAO collegeDAO = new CollegeDAO();
	private DeptDAO deptDAO = new DeptDAO();
	
	public List<Book> findBy(String search, String field) {
		sql = findByQueryGenerator(field,false);
		
		List<Book> books = new LinkedList<>();
		
		try (Connection con = DBConnect.getCon();) {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+search+"%");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int i=0;
				Book book = new Book();
				book.setBarcode(rs.getInt(++i));
				book.setIsbn(rs.getString(++i));
				book.setTitle(rs.getString(++i));
				book.setAuthor(rs.getString(++i));
				book.setEdition(rs.getString(++i));
				book.setSubject_title(subjectDAO.findById(rs.getString(++i)));
				book.setPublisher(rs.getString(++i));
				book.setPublicationYear(Year.parse(rs.getDate(++i).toString(), DateTimeFormatter.ISO_LOCAL_DATE));
				book.setPublicationPlace(rs.getString(++i));
				book.setPages(rs.getInt(++i));
				book.setCollegeCode( collegeDAO.findById(rs.getString(++i)));
				book.setDeptCode(deptDAO.findById(rs.getString(++i)));
				book.setVolume(rs.getInt(++i));
				book.setCdDvd(rs.getString(++i));
				book.setImage(rs.getBytes(++i));
				book.setPdf(rs.getBytes(++i));
				book.setBkPrsnlDtls(BkPrsnlDtlDAO.getByBkBarcodeCommon(rs.getInt(1)));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	public Book findBy(String isbn) {
		sql = "SELECT `Books`.`barcode`,\n" + 
				"    `Books`.`isbn`,\n" + 
				"    `Books`.`title`,\n" + 
				"    `Books`.`author`,\n" + 
				"    `Books`.`edition`,\n" + 
				"    `Books`.`subject_title`,\n" + 
				"    `Books`.`publisher`,\n" + 
				"    `Books`.`publication_year`,\n" + 
				"    `Books`.`publication_place`,\n" + 
				"    `Books`.`pages`,\n" + 
				"    `Books`.`college_code`,\n" + 
				"    `Books`.`dept_code`,\n" + 
				"    `Books`.`volume`,\n" + 
				"    `Books`.`cDvd`,\n" + 
				"    `Books`.`image`,\n" + 
				"    `Books`.`pdf`\n" + 
				"FROM `library`.`Books` where  `Books`.`isbn`=?;";
		
		Book book = new Book();
		
		try (Connection con = DBConnect.getCon();) {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, isbn);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int i=0;
				book.setBarcode(rs.getInt(++i));
				book.setIsbn(rs.getString(++i));
				book.setTitle(rs.getString(++i));
				book.setAuthor(rs.getString(++i));
				book.setEdition(rs.getString(++i));
				book.setSubject_title(subjectDAO.findById(rs.getString(++i)));
				book.setPublisher(rs.getString(++i));
				book.setPublicationYear(Year.parse(rs.getDate(++i).toString(), DateTimeFormatter.ISO_LOCAL_DATE));
				book.setPublicationPlace(rs.getString(++i));
				book.setPages(rs.getInt(++i));
				book.setCollegeCode( collegeDAO.findById(rs.getString(++i)));
				book.setDeptCode(deptDAO.findById(rs.getString(++i)));
				book.setVolume(rs.getInt(++i));
				book.setCdDvd(rs.getString(++i));
				book.setImage(rs.getBytes(++i));
				book.setPdf(rs.getBytes(++i));
				book.setBkPrsnlDtls(BkPrsnlDtlDAO.getByBkBarcodeCommon(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	public Book findByIsbn(String isbn,String collegeCode) {
		sql = "SELECT `Books`.`barcode`,\n" + 
				"    `Books`.`isbn`,\n" + 
				"    `Books`.`title`,\n" + 
				"    `Books`.`author`,\n" + 
				"    `Books`.`edition`,\n" + 
				"    `Books`.`subject_title`,\n" + 
				"    `Books`.`publisher`,\n" + 
				"    `Books`.`publication_year`,\n" + 
				"    `Books`.`publication_place`,\n" + 
				"    `Books`.`pages`,\n" + 
				"    `Books`.`college_code`,\n" + 
				"    `Books`.`dept_code`,\n" + 
				"    `Books`.`volume`,\n" + 
				"    `Books`.`cDvd`,\n" + 
				"    `Books`.`image`,\n" + 
				"    `Books`.`pdf`\n" + 
				"FROM `library`.`Books` where  `Books`.`isbn`=? and `Books`.`college_code`=?;";
		
		Book book = new Book();
		
		try (Connection con = DBConnect.getCon();) {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setString(2, collegeCode);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int i=0;
				book.setBarcode(rs.getInt(++i));
				book.setIsbn(rs.getString(++i));
				book.setTitle(rs.getString(++i));
				book.setAuthor(rs.getString(++i));
				book.setEdition(rs.getString(++i));
				book.setSubject_title(subjectDAO.findById(rs.getString(++i)));
				book.setPublisher(rs.getString(++i));
				book.setPublicationYear(Year.parse(rs.getDate(++i).toString(), DateTimeFormatter.ISO_LOCAL_DATE));
				book.setPublicationPlace(rs.getString(++i));
				book.setPages(rs.getInt(++i));
				book.setCollegeCode( collegeDAO.findById(rs.getString(++i)));
				book.setDeptCode(deptDAO.findById(rs.getString(++i)));
				book.setVolume(rs.getInt(++i));
				book.setCdDvd(rs.getString(++i));
				book.setImage(rs.getBytes(++i));
				book.setPdf(rs.getBytes(++i));
				book.setBkPrsnlDtls(BkPrsnlDtlDAO.getByBkBarcodeCommon(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	public List<Book> searchBy(String search, String field,String college) {
		sql = findByQueryGenerator(field,true);
		
		List<Book> books = new LinkedList<>();
		
		try (Connection con = DBConnect.getCon();) {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, college);
			ps.setString(2, "%"+search+"%");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int i=0;
				Book book = new Book();
				book.setBarcode(rs.getInt(++i));
				book.setIsbn(rs.getString(++i));
				book.setTitle(rs.getString(++i));
				book.setAuthor(rs.getString(++i));
				book.setEdition(rs.getString(++i));
				book.setSubject_title(subjectDAO.findById(rs.getString(++i)));
				book.setPublisher(rs.getString(++i));
				book.setPublicationYear(Year.parse(rs.getDate(++i).toString(), DateTimeFormatter.ISO_LOCAL_DATE));
				book.setPublicationPlace(rs.getString(++i));
				book.setPages(rs.getInt(++i));
				book.setCollegeCode( collegeDAO.findById(rs.getString(++i)));
				book.setDeptCode(deptDAO.findById(rs.getString(++i)));
				book.setVolume(rs.getInt(++i));
				book.setCdDvd(rs.getString(++i));
				book.setImage(rs.getBytes(++i));
				book.setPdf(rs.getBytes(++i));
				book.setBkPrsnlDtls(BkPrsnlDtlDAO.getByBkBarcodeCommon(rs.getInt(1)));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public List<Book> findAll(String collegeCode) {
		sql = "SELECT `Books`.`barcode`,\n" + 
				"    `Books`.`isbn`,\n" + 
				"    `Books`.`title`,\n" + 
				"    `Books`.`author`,\n" + 
				"    `Books`.`edition`,\n" + 
				"    `Books`.`subject_title`,\n" + 
				"    `Books`.`publisher`,\n" + 
				"    `Books`.`publication_year`,\n" + 
				"    `Books`.`publication_place`,\n" + 
				"    `Books`.`pages`,\n" + 
				"    `Books`.`college_code`,\n" + 
				"    `Books`.`dept_code`,`Books`.`volume`,`Books`.`cDvd`,\n" + 
				"    `Books`.`image`,\n" + 
				"    `Books`.`pdf`\n" + 
				"FROM `library`.`Books`" + 
				" where `Books`.`college_code`=?";
		
		List<Book> books=new LinkedList<>();
		try (Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, collegeCode);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBarcode(rs.getInt(1));
				book.setIsbn(rs.getString(2));
				book.setTitle(rs.getString(3));
				book.setAuthor(rs.getString(4));
				book.setEdition(rs.getString(5));
				book.setSubject_title(subjectDAO.findById(rs.getString(6)));
				book.setPublisher(rs.getString(7));
				
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY");
				
				book.setPublicationYear(Year.parse(simpleDateFormat.format(rs.getDate(8)).toString()));
				book.setPublicationPlace(rs.getString(9));
				book.setPages(rs.getInt(10));
				book.setCollegeCode(collegeDAO.findById(rs.getString(11)));
				book.setDeptCode(deptDAO.findById(rs.getString(12)));
				book.setVolume(rs.getInt(13));
				book.setCdDvd(rs.getString(14));
				book.setImage(rs.getBytes(15));
				book.setPdf(rs.getBytes(16));
				book.setBkPrsnlDtls(BkPrsnlDtlDAO.getByBkBarcodeCommon(rs.getInt(1)));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public int save(Book book) {
		
		sql="INSERT INTO `library`.`Books` (`isbn`,`title`,`author`,`edition`,`subject_title`,`publisher`,`publication_year`,`publication_place`,`pages`,`college_code`,`dept_code`,`volume`,`cDvd`,`image`,`pdf`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		int barcode=0;
		try (Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);			
			ps.setString(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getEdition());
			ps.setString(5, book.getSubject_title().getTitle());
			ps.setString(6, book.getPublisher());
			ps.setDate(7, Date.valueOf(LocalDate.of(book.getPublicationYear().getValue(), 1, 1)));
			ps.setString(8, book.getPublicationPlace());
			ps.setInt(9, book.getPages());
			ps.setString(10, book.getCollegeCode().getCode());
			ps.setString(11, book.getDeptCode().getCode());
			ps.setInt(12, book.getVolume());
			ps.setString(13, book.getCdDvd().toString());
			ps.setBytes(14, book.getImage());
			ps.setBytes(15, book.getPdf());
			isUpdated=!ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				barcode=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return barcode;
	}

	@Override
	public boolean update(Book book) {
		sql="UPDATE `library`.`Books`\n" + 
				"SET "+ 
				"`isbn` = ?,\n" + 
				"`title` = ?,\n" + 
				"`author` = ?,\n" + 
				"`edition` =?,\n" + 
				"`subject_title` = ?,\n" + 
				"`publisher` = ?,\n" + 
				"`publication_year` = ?,\n" + 
				"`publication_place` = ?,\n" + 
				"`pages` = ?,\n" + 
				"`college_code` = ?,\n" + 
				"`dept_code` = ?,\n" + 
				"`volume` = ?,\n" + 
				"`cDvd` = ?";
		if (book.getPdf().length>0 && book.getImage().length>0) {
			sql=sql+", `image` = ?,`pdf` = ? WHERE `barcode` = ?;";
		}else if (book.getPdf().length>0) {
			sql=sql+",`pdf` = ? WHERE `barcode` = ?;";
		}else if (book.getImage().length>0) {
			sql=sql+", `image` = ? WHERE `barcode` = ?;";
		}else {
			sql=sql+" WHERE `barcode` = ?;";
		}
		
		try (Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getEdition());
			ps.setString(5, book.getSubject().getTitle());
			ps.setString(6, book.getPublisher());
			ps.setDate(7, Date.valueOf(LocalDate.of(book.getPublicationYear().getValue(), 1, 1)));
			ps.setString(8, book.getPublicationPlace());
			ps.setInt(9, book.getPages());
			ps.setString(10, book.getCollege().getCode());
			ps.setString(11, book.getDept().getCode());
			ps.setInt(12, book.getVolume());
			ps.setString(13, book.getCdDvd().toString());
			
			if (book.getPdf().length>0 && book.getImage().length>0) {
				ps.setBytes(14, book.getImage());
				ps.setBytes(15, book.getPdf());
				ps.setInt(16, book.getBarcode());
			}else if (book.getPdf().length>0) {
				ps.setBytes(14, book.getPdf());
				ps.setInt(15, book.getBarcode());
			}else if (book.getImage().length>0) {
				ps.setBytes(14, book.getImage());
				ps.setInt(15, book.getBarcode());
			}else {
				ps.setInt(14, book.getBarcode());
			}
			
			isUpdated=!ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	public long count() {
		long count = 0;
		sql = "SELECT count(*) FROM `library`.`bk_prsnl_dtl`;";
		try (Connection con = DBConnect.getCon();) {
			ResultSet rs = con.createStatement().executeQuery(sql);
			if (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public String findByQueryGenerator(String field,Boolean isSpecificCollege) {
		field = field.toLowerCase();
		sql = "SELECT `Books`.`barcode`,`Books`.`isbn`,`Books`.`title`,"
				+ "    `Books`.`author`,`Books`.`edition`,"
				+ "    `Books`.`subject_title`,`Books`.`publisher`,`Books`.`publication_year`,"
				+ "    `Books`.`publication_place`,`Books`.`pages`,`Books`.`college_code`,"
				+ "    `Books`.`dept_code`,`Books`.`volume`,`Books`.`cDvd`,`Books`.`image`,`Books`.`pdf` FROM `library`.`Books` where ";
		if (isSpecificCollege) {
			sql=sql.concat("`Books`.`college_code`=? and ");
		}
		switch (field) {
		case "title":
			sql = sql.concat("`Books`.`title` like ?;");
			break;
		case "isbn":
			sql = sql.concat("`Books`.`isbn` like ?;");
			break;
		case "author":
			sql = sql.concat("`Books`.`author` like ?;");
			break;
		case "subject":
			sql = sql.concat("`Books`.`subject` like ?;");
			break;
		default:
			sql = sql.concat("`Books`.`title` like ?;");
			break;
		}
		return sql;
	}

	@Override
	public Book findById(int barcode) {
		sql="SELECT `Books`.`barcode`,\n" + 
				"    `Books`.`isbn`,\n" + 
				"    `Books`.`title`,\n" + 
				"    `Books`.`author`,\n" + 
				"    `Books`.`edition`," + 
				"    `Books`.`subject_title`,\n" + 
				"    `Books`.`publisher`,\n" + 
				"    `Books`.`publication_year`,\n" + 
				"    `Books`.`publication_place`,\n" + 
				"    `Books`.`pages`,\n" + 
				"    `Books`.`college_code`,\n" + 
				"    `Books`.`dept_code`,`Books`.`volume`,`Books`.`cDvd`,\n" + 
				"    `Books`.`image`,\n" + 
				"    `Books`.`pdf`\n" + 
				"FROM `library`.`Books` where `Books`.`barcode`=? ";
		Book book = new Book();
		try (Connection con = DBConnect.getCon();) {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, barcode);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				book.setBarcode(rs.getInt(1));
				book.setIsbn(rs.getString(2));
				book.setTitle(rs.getString(3));
				book.setAuthor(rs.getString(4));
				book.setEdition(rs.getString(5));
				book.setSubject_title(subjectDAO.findById(rs.getString(6)));
				book.setPublisher(rs.getString(7));
				book.setPublicationYear(Year.parse(rs.getDate(8).toString(), DateTimeFormatter.ISO_LOCAL_DATE));
				book.setPublicationPlace(rs.getString(9));
				book.setPages(rs.getInt(10));
				book.setCollegeCode( collegeDAO.findById(rs.getString(11)));
				book.setDeptCode(deptDAO.findById(rs.getString(12)));
				book.setVolume(rs.getInt(13));
				book.setCdDvd(rs.getString(14));
				book.setImage(rs.getBytes(15));
				book.setPdf(rs.getBytes(16));
				book.setBkPrsnlDtls(BkPrsnlDtlDAO.getByBkBarcodeCommon(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public static boolean isExist(String isbn,String collegeCode) {
		boolean isExist=false;
		sql="SELECT * FROM `library`.`Books` where isbn=? and college_code=?;";
		
		try (PreparedStatement ps=DBConnect.getCon().prepareStatement(sql)){
			ps.setString(1, isbn);
			ps.setString(2, collegeCode);
			ResultSet rSet = ps.executeQuery();
			isExist=rSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}
}
