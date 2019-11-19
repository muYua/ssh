package cn.edu.abtc.iem.action;

public interface BookAction {
	//定义一些需要用到的结果字符串
//	public static final String ERROR="error";//跳转错误页面
//	public static final String LOGIN="login";//跳转登陆页面
	public static final String TOADMIN="toAdmin";//带默认查询结果（所有信息）跳转
	public static final String ADMIN="admin";//跳转admin主页
	public static final String TOBOOK="toBook";//带默认查询结果（所有信息）跳转
	public static final String BOOK="book";//跳转book主页
	public static final String TOADDBOOK="toAddBook";//用于跳转到对应添加页面
	public static final String TOUPDATEBOOK="toUpdateBook";//用于跳转到对应修改页面
	public static final String DEFAULT="default";//跳转默认页面
	
	//添加图书信息
	public String insert();
	//删除图书信息
	public String delete();
	//查询图书信息
	public String query();
	//修改图书信息
	public String update();
	//跳转图书主页，查询所有图书信息
	public String toBook();
	//出发修改事件，跳转修改界面
	public String toUpdate();
	//跳转添加图书信息页面
	public String toAdd();
}
