package iDao;

import java.util.List;

import model.College;

public interface ICollegeDAO {
	public College findById(String collegeCode);
	public List<College> findAll();
	public boolean save(College college);
}
