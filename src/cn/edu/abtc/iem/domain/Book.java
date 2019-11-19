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
 * @JoinColumn所在实体是关系拥有方，name即拥有方对应表到参考表的外键名称。
	@mappedBy所在实体是关系的被拥有方，value值owner中表示被拥有类的属性。
 * */
@Entity//声明这是一个hibernate实例化类,POJO类变成Hibernate PO类
@Table(name="ssh_book")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//主键生成策略，自动增长
	@Column(name="book_id")
	private int id;//主键
	@Column(name="book_name",length=30)
	private String bookName;//书名
	@Column(name="book_price",scale=2)//scale->decimal类型数据的最大小数位数
	private BigDecimal bookPrice;//书的价格
	@Column(name="book_author",length=20)
	private String author;//书的作者
	/*无联表，双向N-1关联,最好交由N端控制关联关系，1端配个集合set*/
	@ManyToOne(targetEntity=User.class)
	//targetEntity->指定关联实体的类名，cascade->指定Hibernate对关联实体采用的级联策略,与数据库不一样，默认null
	/*定义外键列*/
	//读者id
//	@JoinColumn(name="book_reader",nullable=true,referencedColumnName="user_name")//是否允许为空
//	private String reader;//读者
//	private int readerId;//读者id
	@JoinColumn(name="reader_id",referencedColumnName="user_id")//外键
/*	//JoinTable创建关系表,N-M关系
	@JoinTable(name="user_book",
	joinColumns=@JoinColumn(name="book_id",referencedColumnName="book_id",unique=true),
	inverseJoinColumns=@JoinColumn(name="reader_id",referencedColumnName="user_id"))*/
	private User reader;//在N端添加一个1端的实体类型的属性，使每个Book持有一个User
	private String value;//用于存储查询值

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
