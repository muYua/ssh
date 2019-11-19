package cn.edu.abtc.iem.action.impl;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

import cn.edu.abtc.iem.action.AdminAction;
import cn.edu.abtc.iem.action.base.BaseAction;
import cn.edu.abtc.iem.domain.Admin;
import cn.edu.abtc.iem.domain.User;
import cn.edu.abtc.iem.service.AdminService;
import cn.edu.abtc.iem.service.UserService;
//展示层，调用方法和传参
public class AdminActionImpl extends BaseAction implements AdminAction,ModelDriven<Admin>{
	private static final long serialVersionUID = 1L;
	private AdminService<Admin> adminService;
	
	public AdminService<Admin> getAdminService() {
		return adminService;
	}
	public void setAdminService(AdminService<Admin> adminService) {
		this.adminService = adminService;
	}
	private UserService<User> userService;
	public UserService<User> getUserService() {
		return userService;
	}
	public void setUserService(UserService<User> userService) {
		this.userService = userService;
	}
	private Admin admin=new Admin();//用于模型驱动 ，必须要手动new实例化一个
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
//	private UserService<User>
	
	@Override//模型驱动接口
	public Admin getModel() {
		return admin;
	}

	@Override/*登录*/
	public String login(){
		if(adminService.check(admin.getAdminName(), admin.getAdminPassword()))//getAdminName()
		{
			/*得到准确的登录的user对象*/
			List<Admin> adminlist = adminService.findByName(admin.getAdminName());
			Admin sessionAdmin = adminlist.get(0);//从list中取出第一个元素
			getSession().setAttribute("sessionAdmin",sessionAdmin);//存储本次登录的用户对象到域对象session，前端通过${session}获得
			//得到表格的数据 ，并将userList的值传到前端//最好用Json数据接口实现前端后台数据的交互
//			List<User> userList=userService.findAll(User.class);
//			request.setAttribute("userList",userList);
			
			return TOMAIN;
		}
		else
		{
//			return "loginFail";
			String msg = "登录失败！";
			getRequest().setAttribute("loginError", msg);
			getRequest().setAttribute("loginFlag", true);//前端用来判断是否登录失败
			return Action.LOGIN;
		}
	}

	@Override/*注册*/
	public String reg() {
		String adminPassword2 = getRequest().getParameter("adminPassword2");//获取前端第二次校验密码的值
		if(admin.getAdminPassword().equals(adminPassword2)&&!adminPassword2.equals("")&&!admin.getAdminPassword().equals("")&&!admin.getAdminName().equals(""))//判断两次输入密码是否相同
		{
			//判断是否两次输入密码正确
			//判断是否输入为空
			//判断用户名是否已存在
			if(adminService.isExist(admin.getAdminName()))//判断数据库是否存在同样的用户名
			{
				String msg="注册失败，已存在该用户信息！";
				getRequest().setAttribute("regError", msg);
				return REG;
			}
			else
			{
				adminService.insert(admin);
				return Action.LOGIN;
			}
		}
		else{ 
			String msg="注册失败，两次密码输入不一致或为用户名、密码为空！";
			getRequest().setAttribute("regError", msg);
			return REG;
		}
	}

	@Override/*添加*/
	public String insert() {
		if(!admin.getAdminName().equals("")&&!admin.getAdminPassword().equals(""))
		{
			if(adminService.isExist(admin.getAdminName()))//判断数据库是否存在同样的用户名
			{
				String msg="添加管理员信息失败，已存在该用户信息！";
				getRequest().setAttribute("addError", msg);
				return ADDADMIN;
			}
			else
			{
				adminService.insert(admin);
				return TOADMIN;
			}
		}
		else{
			String msg="添加管理员信息失败，用户名或密码信息不能为空！";
			getRequest().setAttribute("addError", msg);
			return ADDADMIN;
		}
	}

	@Override/*删除*/
	public String delete() {
		adminService.delete(admin.getId());//href中url的传参数（用？传的参数）仍然会存入model对象，自动将String转化为int（modeldriven的工作）
//		System.out.println("<><"+user.getId()+"<><>");
		//得到表格的数据
		List<Admin> adminList=adminService.findAll(Admin.class);
		getRequest().setAttribute("adminList",adminList);
		
		return TOADMIN;
	}

	@Override/*查询*/
	public String query() {
		String queryKey = getRequest().getParameter("queryKey");
//		System.out.println(queryKey+"---query---------");
		List<Admin> qurey = adminService.qurey(admin.getAdminName());//默认模糊搜索
		if(queryKey.equals("option1"))//模糊查询
		{
			qurey = adminService.qurey(admin.getAdminName());
		}
		else if(queryKey.equals("option2"))//准确查询
		{
			qurey = adminService.findByName(admin.getAdminName());
		}
		else
		{
			String str="发生异常！";
			getRequest().setAttribute("queryError",str );
		}
//		List<User> qurey = userService.qurey(user.getUserName());
		if(!admin.getAdminName().equals(""))
		{
			if(qurey.size()>0)
			{
				getRequest().setAttribute("adminList",qurey);
//				return "query";
			}
			else
			{
				String str="没有查询到结果";
				getRequest().setAttribute("queryError",str );
			}
		}
		else
		{
			String str="请输入你想要搜索的用户名，支持模糊（关键词）搜索哦！";
			getRequest().setAttribute("queryError",str );
		}
		return ADMIN;
	}

	@Override/*修改*/
	public String update() {
		String oldAdminName = getRequest().getParameter("oldAdminName");//获取旧用户名
		String oldAdminPassword = getRequest().getParameter("oldAdminPassword");//获取旧密码
		if(!admin.getAdminName().equals("")&&admin.getAdminPassword().equals(""))//只改用户名
		{
			if(!adminService.isExist(admin.getAdminName()))//判断输入的用户是否重复
			{
				adminService.update(admin, admin.getId(), admin.getAdminName(), oldAdminPassword);
				//得到表格的数据
				List<Admin> adminList=adminService.findAll(Admin.class);
				getRequest().setAttribute("userList",adminList);
				return TOADMIN;
			}
			else {
				String msg = "修改失败。用户名已存在，请重试！";
				getRequest().setAttribute("updateError", msg);
				getRequest().setAttribute("oldAdminName", oldAdminName);
				getRequest().setAttribute("oldAdminPassword", oldAdminPassword);
				return UPDATEADMIN;
			}
			
		}
		else if(admin.getAdminName().equals("")&&!admin.getAdminPassword().equals(""))//只改密码
		{

			adminService.update(admin, admin.getId(), oldAdminName, admin.getAdminPassword());
			//得到表格的数据
			List<Admin> adminList=adminService.findAll(Admin.class);
			getRequest().setAttribute("adminList",adminList);
			return TOADMIN;
		}
		else if(!admin.getAdminName().equals("")&&!admin.getAdminPassword().equals(""))//都改
		{
			if(!adminService.isExist(admin.getAdminName()))//判断输入的用户是否重复
			{
				adminService.update(admin, admin.getId(), admin.getAdminName(), admin.getAdminPassword());
				//得到表格的数据
				List<Admin> adminList=adminService.findAll(Admin.class);
				getRequest().setAttribute("adminList",adminList);
				return MAIN;
			}
			else {
				String msg = "修改失败。用户名已存在，请重试！";
				getRequest().setAttribute("updateError", msg);
				getRequest().setAttribute("oldAdminName", oldAdminName);
				getRequest().setAttribute("oldAdminPassword", oldAdminPassword);
				return UPDATEADMIN;
			}
		}
		else
		{
//			return "updateFail";
			String msg = "修改失败。请进行修改操作，请重试！";
			getRequest().setAttribute("updateError", msg);
			getRequest().setAttribute("oldAdminName", oldAdminName);
			getRequest().setAttribute("oldAdminPassword", oldAdminPassword);
			return UPDATEADMIN;
		}
	}

	@Override/*注销*/
	public String logout() {
		getSession().setAttribute("sessionAdmin",null);
		return Action.LOGIN;
	}

	@Override/*添加管理员页面跳转*/
	public String toAddAdmin() {
		return ADDADMIN;
	}

	@Override/*跳转admin主页（带全部信息）*/
	public String toAdmin() {
		List<Admin> list = adminService.findAll(Admin.class);
		getRequest().setAttribute("adminList",list);
		return ADMIN;
	}

	@Override/*跳转修改页面*/
	public String toUpdateAdmin() {
		//将旧用户、旧密码传到前端->${XX}获取该值
		getRequest().setAttribute("oldAdminName", admin.getAdminName());
		getRequest().setAttribute("oldAdminPassword", admin.getAdminPassword());		
		return DEFAULT;
	}

	@Override/*跳转main页面（带全部页面）*/
	public String toMain() {
		List<User> userList = userService.findAll(User.class);
		getRequest().setAttribute("userList", userList);
		return MAIN;
	}
	
}
