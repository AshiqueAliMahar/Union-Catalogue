package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import db.connection.DBConnect;
import model.Article;

public class ArticleDAO {
	private static String sql = "";
	private static CollegeDAO collegeDAO = new CollegeDAO();

	public static List<Article> getArticle(String collegeCode) {
		sql = "SELECT `article`.`id`,\n" + "    `article`.`title`,\n" + "    `article`.`first_author`,\n"
				+ "    `article`.`second_author`,\n" + "    `article`.`third_author`,\n"
				+ "    `article`.`other_author`,\n" + "    `article`.`issuance`,\n" + "    `article`.`volume`,\n"
				+ "    `article`.`year`,\n" + "    `article`.`journal_title`,\n" + "    `article`.`E_ISSN`,\n"
				+ "    `article`.`P_ISSN`,\n" + "    `article`.`publisher`,\n" + "    `article`.`website`,\n"
				+ "    `article`.`email`,\n" + "    `article`.`college_code`\n"
				+ "FROM `library`.`article` where  `article`.`college_code`=?";
		List<Article> articles = new LinkedList<>();
		try (Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, collegeCode);
			ResultSet rSet = ps.executeQuery();
			while (rSet.next()) {
				Article article = new Article(rSet.getLong(1), rSet.getString(2), rSet.getString(3), rSet.getString(4),
						rSet.getString(5), rSet.getString(6), rSet.getInt(7), rSet.getInt(8), rSet.getDate(9),
						rSet.getString(10), rSet.getString(11), rSet.getString(12), rSet.getString(13),
						rSet.getString(14), rSet.getString(15), collegeDAO.findById(rSet.getString(16)));
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	public static Article getArticle(String collegeCode, String eIssn) {
		sql = "SELECT `article`.`id`,\n" + "    `article`.`title`,\n" + "    `article`.`first_author`,\n"
				+ "    `article`.`second_author`,\n" + "    `article`.`third_author`,\n"
				+ "    `article`.`other_author`,\n" + "    `article`.`issuance`,\n" + "    `article`.`volume`,\n"
				+ "    `article`.`year`,\n" + "    `article`.`journal_title`,\n" + "    `article`.`E_ISSN`,\n"
				+ "    `article`.`P_ISSN`,\n" + "    `article`.`publisher`,\n" + "    `article`.`website`,\n"
				+ "    `article`.`email`,\n" + "    `article`.`college_code`\n"
				+ "FROM `library`.`article` where  `article`.`college_code`=? and `article`.`E_ISSN`=?";
		Article article = null;
		try (Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, collegeCode);
			ps.setString(2, eIssn);
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				article = new Article(rSet.getLong(1), rSet.getString(2), rSet.getString(3), rSet.getString(4),
						rSet.getString(5), rSet.getString(6), rSet.getInt(7), rSet.getInt(8), rSet.getDate(9),
						rSet.getString(10), rSet.getString(11), rSet.getString(12), rSet.getString(13),
						rSet.getString(14), rSet.getString(15), collegeDAO.findById(rSet.getString(16)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}
	public static Article getArticle(Long id) {
		sql = "SELECT `article`.`id`,\n" + "    `article`.`title`,\n" + "    `article`.`first_author`,\n"
				+ "    `article`.`second_author`,\n" + "    `article`.`third_author`,\n"
				+ "    `article`.`other_author`,\n" + "    `article`.`issuance`,\n" + "    `article`.`volume`,\n"
				+ "    `article`.`year`,\n" + "    `article`.`journal_title`,\n" + "    `article`.`E_ISSN`,\n"
				+ "    `article`.`P_ISSN`,\n" + "    `article`.`publisher`,\n" + "    `article`.`website`,\n"
				+ "    `article`.`email`,\n" + "    `article`.`college_code`\n"
				+ "FROM `library`.`article` where  id=?";
		Article article = null;
		try (Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				article = new Article(rSet.getLong(1), rSet.getString(2), rSet.getString(3), rSet.getString(4),
						rSet.getString(5), rSet.getString(6), rSet.getInt(7), rSet.getInt(8), rSet.getDate(9),
						rSet.getString(10), rSet.getString(11), rSet.getString(12), rSet.getString(13),
						rSet.getString(14), rSet.getString(15), collegeDAO.findById(rSet.getString(16)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	public static long addArticle(Article article) {
		long generatedId=0;
		if (article.getId() > 0) {
			sql = "UPDATE `library`.`article`\n" + "SET\n" + "`title` = ?,\n" + "`first_author` = ?,\n"
					+ "`second_author` = ?,\n" + "`third_author` = ?,\n" + "`other_author` = ?,\n" + "`issuance` = ?,\n"
					+ "`volume` = ?,\n" + "`year` = ?,\n" + "`journal_title` =?,\n" + "`E_ISSN` = ?,\n"
					+ "`P_ISSN` = ?,\n" + "`publisher` =?,\n" + "`website` = ?,\n" + "`email` = ?,"
					+ "`college_code` = ?" + "WHERE `id` = ?;";
			generatedId=article.getId();
		} else if (article.getId() == 0) {
			sql = "INSERT INTO `library`.`article`\n" + "(`title`,\n" + "`first_author`,\n" + "`second_author`,\n"
					+ "`third_author`,\n" + "`other_author`,\n" + "`issuance`,\n" + "`volume`,\n" + "`year`,\n"
					+ "`journal_title`,\n" + "`E_ISSN`,\n" + "`P_ISSN`,\n" + "`publisher`,\n" + "`website`,\n"
					+ "`email`,\n" + "`college_code`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		}

		try (Connection con = DBConnect.getCon();) {
			PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			
			int i = 0;
			ps.setString(++i, article.getTitle());
			ps.setString(++i, article.getFirstAuthor());
			ps.setString(++i, article.getSecondAuthor());
			ps.setString(++i, article.getThirdAuthor());
			ps.setString(++i, article.getOtherAuthor());
			ps.setInt(++i, article.getIssuance());
			ps.setInt(++i, article.getVolume());
			ps.setDate(++i, article.getYear());
			ps.setString(++i, article.getJournalTitle());
			ps.setString(++i, article.geteIssn());
			ps.setString(++i, article.getpIssn());
			ps.setString(++i, article.getPublisher());
			ps.setString(++i, article.getWebsite());
			ps.setString(++i, article.getEmail());
			ps.setString(++i, article.getCollege().getCode());
			if (article.getId() > 0) {
				ps.setLong(++i, article.getId());
			}
			ps.execute();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				generatedId=generatedKeys.getLong(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedId;
	}
}
