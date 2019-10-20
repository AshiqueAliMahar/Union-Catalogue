package iDao;

import java.util.List;

import model.Librarian;

public interface IUsersDAO {
	public Librarian findBy(String email,String password);
	public List<Librarian> findAll();
	public boolean save(Librarian Librarian);
	public boolean update(Librarian Librarian);
}
