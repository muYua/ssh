package cn.edu.abtc.iem.action;
public interface UserAction{
	//定义一些需要用到的结果字符串
//	public static final String ERROR="error";//跳转错误页面
	public static final String MAIN="toMain";//跳转主页（带默认参数）
//	public static final String LOGIN="login";//跳转登陆页面
	public static final String REG="reg";//跳转注册页面
	public static final String ADD="add";//跳转添加页面
	public static final String UPDATE="update";//跳转修改页面
	public static final String QUERY="query";//跳转查询后的页面main主页
	public static final String DEFAULT="default";//默认跳转方式
	
	//添加用户
	public String insertMethod();
	//删除用户
	public String deleteMethod();
	//根据用户名查询用户信息
	public String queryMethod();
	//修改用户
	public String updateMethod();
	//注销
//	public String logoutMethod();
	//跳转主页界面,查询所有用户信息
	public String toMainMethod();
	//出发修改事件，跳转修改界面
	public String toUpdateMethod();
	//跳转添加用户信息页面
	public String toAddMethod();

	
}
