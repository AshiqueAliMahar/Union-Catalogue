package model;

public class Librarian {
	private String email;
	private String name;
	private String cell;
	private String password;
	private byte[] pic;
	private Post post;
	public static enum Post{Admin,Librarian,SuperAdmin,Patron}
	private College college;
	
	public Librarian() {
		// TODO Auto-generated constructor stub
	}
	
	public Librarian(String email, String name, String cell, String password, byte[] pic, Post post, College college) {
		super();
		this.email = email;
		this.name = name;
		this.cell = cell;
		this.password = password;
		this.pic = pic;
		this.post = post;
		this.college = college;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}
	public void setPost(String post) {
		if (post.equalsIgnoreCase(Post.Admin.toString())) {
			this.post =Post.Admin;
		}else if (post.equalsIgnoreCase(Post.Librarian.toString())) {
			this.post =Post.Librarian;
		}else if (post.equalsIgnoreCase(Post.SuperAdmin.toString())) {
			this.post =Post.SuperAdmin;
		}else if (post.equalsIgnoreCase(Post.Patron.toString())) {
			this.post =Post.Patron;
		}
	}
	
}
