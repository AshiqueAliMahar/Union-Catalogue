package iDao;

import java.util.List;

import model.Dept;

public interface IDepttDAO {
	public Dept findById(String code);
	public List<Dept> findAll();
	public boolean save(Dept deptCollege);
	public boolean update(Dept deptCollege);
}
