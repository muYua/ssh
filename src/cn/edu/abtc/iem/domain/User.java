package cn.edu.abtc.iem.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

public class User {
	// ������id��������key
	private int id;
	// �û���
	private String userName;
	// �û�����
	private String password;
	//˫��1-N����������mappedBy���������߶��������
	@OneToMany(targetEntity=Book.class,mappedBy="user")
	//mappedBy->��ǰʵ�岻�ܿ��ƹ�����ϵ��=����ʵ��������,ָ���ĸ����Կ������õ���ǰʵ��,���
	//Set�������򣬵���������order by
	//1������N�����Եļ���
	private Set<Book> books=new HashSet<Book>();//һ��Ҫnew
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
