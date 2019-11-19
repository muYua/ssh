package cn.edu.abtc.iem.domain;

import java.math.BigDecimal;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*
 * 
 * @JoinColumn����ʵ���ǹ�ϵӵ�з���name��ӵ�з���Ӧ���ο����������ơ�
	@mappedBy����ʵ���ǹ�ϵ�ı�ӵ�з���valueֵowner�б�ʾ��ӵ��������ԡ�
 * */
@Entity//��������һ��hibernateʵ������,POJO����Hibernate PO��
@Table(name="ssh_book")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//�������ɲ��ԣ��Զ�����
	@Column(name="book_id")
	private int id;//����
	@Column(name="book_name",length=30)
	private String bookName;//����
	@Column(name="book_price",scale=2)//scale->decimal�������ݵ����С��λ��
	private BigDecimal bookPrice;//��ļ۸�
	@Column(name="book_author",length=20)
	private String author;//�������
	/*������˫��N-1����,��ý���N�˿��ƹ�����ϵ��1���������set*/
	@ManyToOne(targetEntity=User.class)
	//targetEntity->ָ������ʵ���������cascade->ָ��Hibernate�Թ���ʵ����õļ�������,�����ݿⲻһ����Ĭ��null
	/*���������*/
	//����id
//	@JoinColumn(name="book_reader",nullable=true,referencedColumnName="user_name")//�Ƿ�����Ϊ��
//	private String reader;//����
//	private int readerId;//����id
	@JoinColumn(name="reader_id",referencedColumnName="user_id")//���
/*	//JoinTable������ϵ��,N-M��ϵ
	@JoinTable(name="user_book",
	joinColumns=@JoinColumn(name="book_id",referencedColumnName="book_id",unique=true),
	inverseJoinColumns=@JoinColumn(name="reader_id",referencedColumnName="user_id"))*/
	private User reader;//��N�����һ��1�˵�ʵ�����͵����ԣ�ʹÿ��Book����һ��User
	private String value;//���ڴ洢��ѯֵ

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public User getReader() {
		return reader;
	}
	public void setReader(User reader) {
		this.reader = reader;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public BigDecimal getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", bookPrice=" + bookPrice + ", author=" + author
				+ ", reader=" + reader + ", value=" + value + "]";
	}
}
