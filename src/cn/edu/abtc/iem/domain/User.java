package cn.edu.abtc.iem.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

public class User {
	// 无意义id用做主键key
	private int id;
	// 用户名
	private String userName;
	// 用户密码
	private String password;
	//双向1-N，必须设置mappedBy，避免两边都设置外键
	@OneToMany(targetEntity=Book.class,mappedBy="user")
	//mappedBy->当前实体不能控制关联关系，=关联实体属性名,指定哪个属性可以引用到当前实体,外键
	//Set集合无序，但可以排序order by
	//1端配置N端属性的集合
	private Set<Book> books=new HashSet<Book>();//一定要new
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}

}
