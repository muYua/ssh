package cn.edu.abtc.iem.action.impl;

import java.math.BigDecimal;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.edu.abtc.iem.action.BookAction;
import cn.edu.abtc.iem.action.base.BaseAction;
import cn.edu.abtc.iem.domain.Book;
import cn.edu.abtc.iem.domain.User;
import cn.edu.abtc.iem.service.BookService;
import cn.edu.abtc.iem.service.UserService;

public class BookActionImpl extends BaseAction implements BookAction,ModelDriven<Book>{
	private static final long serialVersionUID = 1L;
	private Book book=new Book();//需要手动实例化new一个model类，用于ModelDriven接口传参
	@Override/*实现ModelDriven接口方法，获取前端表单提交的Book属性数据*/
	public Book getModel() {
		return book;
	}
	private BookService<Book> bookService;
	public BookService<Book> getBookService() {
		return bookService;
	}

	public void setBookService(BookService<Book> bookService) {
		this.bookService = bookService;
	}
	private UserService<User> userService;
	
	public UserService<User> getUserService() {
		return userService;
	}

	public void setUserService(UserService<User> userService) {
		this.userService = userService;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override/*添加信息*/
	public String insert() {
		System.out.println(book+"---insertbook");
		String bookName = book.getBookName();
		BigDecimal bookPrice = book.getBookPrice();
		String author = book.getAuthor();
		String readerName = book.getReader().getUserName();
		if(bookName!=null&&bookPrice!=null&&author!=null&&!bookName.equals("")&&!author.equals(""))//图书信息不为空
		{
			if((readerName==null||readerName.equals("")))
			{
				bookService.insert(book);//sql语句插入
				return TOBOOK;
			}
			else 
			{
				if(userService.isExist(book.getReader().getUserName()))//如果这个读者存在
				{
					//通过对读者名的查询，定位到对应的user
					List<User> userlist = userService.findByName(book.getReader().getUserName());
					User user = userlist.get(0);//拿出user
					book.setReader(user);//将对应user存入book的属性reader 
					bookService.save(book);
					return TOBOOK;
				}
				else
				{
					String addError="添加图书信息失败! 读者信息不存在。";
					getRequest().setAttribute("addError", addError);
					return TOADDBOOK;
				}
			}

		}
		else
		{
			String addError="添加图书信息失败! 图书名、图书作者或图书价格为空。";
			getRequest().setAttribute("addError", addError);
			return TOADDBOOK;
		}
	}

		


	@Override/*删除信息*/
	public String delete() {
		System.out.println(book.getId()+"delete--action");
		bookService.delete(book.getId());
		return TOBOOK;
	}

	@Override/*根据关键词查询信息*/
	public String query() {
		String queryKey = getRequest().getParameter("queryKey");
		String value = book.getValue();
		if(value!=null&&value!="")
		{
			List<Book> qurey = bookService.queryBookName(value);//默认模糊搜索
			if(queryKey.equals("option1"))//图书名模糊查询
			{
				qurey = bookService.queryBookName(value);
			}
			else if(queryKey.equals("option2"))//图书名准确查询
			{
				qurey = bookService.findByKey("b.bookName", value);
			}else if(queryKey.equals("option3"))////图书作者查询
			{
				qurey = bookService.findByKey("b.author", value);
			}else if(queryKey.equals("option4"))//图书ID查询
			{
				int idValue = Integer.parseInt(value);
				qurey = bookService.findByKey("b.id", idValue);
			}
			else
			{
				String str="发生异常，请重试！";
				getRequest().setAttribute("queryError",str );
			}
			
			if(qurey.size()>0)
			{
				getRequest().setAttribute("bookList",qurey);
			}
			else
			{
				String str="没有查询到结果";
				getRequest().setAttribute("queryError",str );
			}
		}
		else
		{
			String str="请输入你想要搜索的关键词！";
			getRequest().setAttribute("queryError",str );
		}
		return BOOK;
	}

	@Override/*修改信息*/
	public String update() {
		//这里不要使用getRequest().getAttribute，取不到值
//		System.out.println(getRequest().getAttribute("oldId")+"---update");
//		int oldId = (Integer) getRequest().getAttribute("oldId");
		String oldBookName = getRequest().getParameter("oldBookName");
//		System.out.println(oldBookName+"----update oldname");
		String oldAuthor = getRequest().getParameter("oldAuthor");
//		System.out.println(oldAuthor+"----update oldAuthor");
		String oldtmpBookPrice = getRequest().getParameter("oldBookPrice");
		BigDecimal oldBookPrice = new BigDecimal(oldtmpBookPrice);
//		System.out.println(oldBookPrice+"----update oldprice");
		String oldReaderName = getRequest().getParameter("oldReaderName");
//		System.out.println(oldReaderName+"----update oldReaderName");
		//输入框内容
		int id = book.getId();
		String bookName = book.getBookName();
		String author = book.getAuthor();
		BigDecimal bookPrice = book.getBookPrice();
		String readerName = book.getReader().getUserName();
		//新容器
		Book book2=new Book();
		book2.setId(id);
		book2.setBookName(oldBookName);
		book2.setAuthor(oldAuthor);
		book2.setBookPrice(oldBookPrice);
		if(userService.isExist(oldReaderName))//如果这个读者存在
		{
			List<User> list2 = userService.findByName(oldReaderName);
			User oldReader = list2.get(0);
			book2.setReader(oldReader);
		}
//		else
//		{
//			String updateError="修改图书信息失败! 读者信息不存在。";
//			getRequest().setAttribute("updateError", updateError);
//			return TOUPDATEBOOK;
//		}
//		System.out.println(book2+"--------action update book21");
		if(bookName!=null&&!bookName.equals(""))
		{
			book2.setBookName(bookName);
		}
		if(author!=null&&!author.equals(""))
		{
			book2.setAuthor(author);
		}
		if(bookPrice!=null)
		{
			book2.setBookPrice(bookPrice);
		}
		if(readerName!=null&&!readerName.equals(""))
		{
			if(userService.isExist(readerName))//如果这个读者存在
			{
				List<User> list = userService.findByName(readerName);//在user表中找到该读者
				User reader = list.get(0);//取出读者信息
//				System.out.println(reader+"--------action update reader");
				book2.setReader(reader);
			}
			else
			{
				String updateError="修改图书信息失败! 读者信息不存在。";
				getRequest().setAttribute("updateError", updateError);
				getRequest().setAttribute("oldId", id);
				getRequest().setAttribute("oldBookName", oldBookName);
				getRequest().setAttribute("oldAuthor", oldAuthor);
				getRequest().setAttribute("oldBookPrice", oldBookPrice);
				getRequest().setAttribute("oldReaderName", oldReaderName);
//				System.out.println(book+"--------------------------------author bu zai ");
				return TOUPDATEBOOK;
			}
			
		}		
		bookService.update(book2);
//		System.out.println(book2+"--------action update book2");
		return TOBOOK;
	}

	@Override/*跳转book主页*/
	public String toBook() {
		List<Book> bookList = bookService.findAll(Book.class);
		getRequest().setAttribute("bookList", bookList);
		return BOOK;
	}

	@Override/*跳转修改页面*/
	public String toUpdate() {
		getRequest().setAttribute("oldId", book.getId());
		getRequest().setAttribute("oldBookName", book.getBookName());
		getRequest().setAttribute("oldAuthor", book.getAuthor());
		getRequest().setAttribute("oldBookPrice", book.getBookPrice());
		getRequest().setAttribute("oldReaderName", book.getReader().getUserName());
//		System.out.println(book+"------------------------------------------------------------------toupdate");
		return DEFAULT;
	}

	@Override/*跳转添加页面*/
	public String toAdd() {
		return DEFAULT;
	}
}
