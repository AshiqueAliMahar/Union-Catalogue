package iDao;

import java.util.List;

import model.Subject;

public interface ISubjectDAO {
	public Subject findById(String title);
	public List<Subject> findAll();
	public boolean save(Subject subject);
	public boolean update(Subject subject);
}
