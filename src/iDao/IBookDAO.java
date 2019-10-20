package iDao;

import java.util.List;

import model.Book;

public interface IBookDAO {
	
	public Book findById(int barcode);
	public List<Book> findAll();
	public int save(Book book);
	public boolean update(Book book);
	List<Book> findAll(String collegeCode);
}
