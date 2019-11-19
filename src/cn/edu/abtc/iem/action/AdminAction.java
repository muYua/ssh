package cn.edu.abtc.iem.action;
public interface AdminAction{
	//定义一些需要用到的结果字符串
	public static final String MAIN="main";//跳转main主页
	public static final String TOMAIN="toMain";//带默认参数跳转main主页
	public static final String TOADMIN="toAdmin";//带默认参数（所有信息）跳转admin主页
	public static final String ADMIN="admin";//直接跳转admin主页
	public static final String REG="reg";//跳转注册页面
	public static final String ADDADMIN="addAdmin";//跳转admin的添加页面
	public static final String UPDATEADMIN="updateAdmin";//跳转admin修改页面
	public static final String DEFAULT="default";//跳转默认界面
	
	//登录校验
	public String login();
	//注册
	public String reg();
	//添加
	public String insert();
	//删除
	public String delete();
	//查询信息
	public String query();
	//修改
	public String update();
	//注销
	public String logout();
	//跳转主页界面,查询所有用户信息
	public String toAdmin();
	//出发修改事件，跳转修改界面
	public String toUpdateAdmin();
	//跳转添加管理员信息页面
	public String toAddAdmin();
	//跳转主页
	public String toMain();
}
