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
	private Book book=new Book();//��Ҫ�ֶ�ʵ����newһ��model�࣬����ModelDriven�ӿڴ���
	@Override/*ʵ��ModelDriven�ӿڷ�������ȡǰ�˱��ύ��Book��������*/
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

	@Override/*�����Ϣ*/
	public String insert() {
		System.out.println(book+"---insertbook");
		String bookName = book.getBookName();
		BigDecimal bookPrice = book.getBookPrice();
		String author = book.getAuthor();
		String readerName = book.getReader().getUserName();
		if(bookName!=null&&bookPrice!=null&&author!=null&&!bookName.equals("")&&!author.equals(""))//ͼ����Ϣ��Ϊ��
		{
			if((readerName==null||readerName.equals("")))
			{
				bookService.insert(book);//sql������
				return TOBOOK;
			}
			else 
			{
				if(userService.isExist(book.getReader().getUserName()))//���������ߴ���
				{
					//ͨ���Զ������Ĳ�ѯ����λ����Ӧ��user
					List<User> userlist = userService.findByName(book.getReader().getUserName());
					User user = userlist.get(0);//�ó�user
					book.setReader(user);//����Ӧuser����book������reader 
					bookService.save(book);
					return TOBOOK;
				}
				else
				{
					String addError="���ͼ����Ϣʧ��! ������Ϣ�����ڡ�";
					getRequest().setAttribute("addError", addError);
					return TOADDBOOK;
				}
			}

		}
		else
		{
			String addError="���ͼ����Ϣʧ��! ͼ������ͼ�����߻�ͼ��۸�Ϊ�ա�";
			getRequest().setAttribute("addError", addError);
			return TOADDBOOK;
		}
	}

		


	@Override/*ɾ����Ϣ*/
	public String delete() {
		System.out.println(book.getId()+"delete--action");
		bookService.delete(book.getId());
		return TOBOOK;
	}

	@Override/*���ݹؼ��ʲ�ѯ��Ϣ*/
	public String query() {
		String queryKey = getRequest().getParameter("queryKey");
		String value = book.getValue();
		if(value!=null&&value!="")
		{
			List<Book> qurey = bookService.queryBookName(value);//Ĭ��ģ������
			if(queryKey.equals("option1"))//ͼ����ģ����ѯ
			{
				qurey = bookService.queryBookName(value);
			}
			else if(queryKey.equals("option2"))//ͼ����׼ȷ��ѯ
			{
				qurey = bookService.findByKey("b.bookName", value);
			}else if(queryKey.equals("option3"))////ͼ�����߲�ѯ
			{
				qurey = bookService.findByKey("b.author", value);
			}else if(queryKey.equals("option4"))//ͼ��ID��ѯ
			{
				int idValue = Integer.parseInt(value);
				qurey = bookService.findByKey("b.id", idValue);
			}
			else
			{
				String str="�����쳣�������ԣ�";
				getRequest().setAttribute("queryError",str );
			}
			
			if(qurey.size()>0)
			{
				getRequest().setAttribute("bookList",qurey);
			}
			else
			{
				String str="û�в�ѯ�����";
				getRequest().setAttribute("queryError",str );
			}
		}
		else
		{
			String str="����������Ҫ�����Ĺؼ��ʣ�";
			getRequest().setAttribute("queryError",str );
		}
		return BOOK;
	}

	@Override/*�޸���Ϣ*/
	public String update() {
		//���ﲻҪʹ��getRequest().getAttribute��ȡ����ֵ
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
		//���������
		int id = book.getId();
		String bookName = book.getBookName();
		String author = book.getAuthor();
		BigDecimal bookPrice = book.getBookPrice();
		String readerName = book.getReader().getUserName();
		//������
		Book book2=new Book();
		book2.setId(id);
		book2.setBookName(oldBookName);
		book2.setAuthor(oldAuthor);
		book2.setBookPrice(oldBookPrice);
		if(userService.isExist(oldReaderName))//���������ߴ���
		{
			List<User> list2 = userService.findByName(oldReaderName);
			User oldReader = list2.get(0);
			book2.setReader(oldReader);
		}
//		else
//		{
//			String updateError="�޸�ͼ����Ϣʧ��! ������Ϣ�����ڡ�";
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
			if(userService.isExist(readerName))//���������ߴ���
			{
				List<User> list = userService.findByName(readerName);//��user�����ҵ��ö���
				User reader = list.get(0);//ȡ��������Ϣ
//				System.out.println(reader+"--------action update reader");
				book2.setReader(reader);
			}
			else
			{
				String updateError="�޸�ͼ����Ϣʧ��! ������Ϣ�����ڡ�";
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

	@Override/*��תbook��ҳ*/
	public String toBook() {
		List<Book> bookList = bookService.findAll(Book.class);
		getRequest().setAttribute("bookList", bookList);
		return BOOK;
	}

	@Override/*��ת�޸�ҳ��*/
	public String toUpdate() {
		getRequest().setAttribute("oldId", book.getId());
		getRequest().setAttribute("oldBookName", book.getBookName());
		getRequest().setAttribute("oldAuthor", book.getAuthor());
		getRequest().setAttribute("oldBookPrice", book.getBookPrice());
		getRequest().setAttribute("oldReaderName", book.getReader().getUserName());
//		System.out.println(book+"------------------------------------------------------------------toupdate");
		return DEFAULT;
	}

	@Override/*��ת���ҳ��*/
	public String toAdd() {
		return DEFAULT;
	}
}
