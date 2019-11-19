package cn.edu.abtc.iem.action.impl;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

import cn.edu.abtc.iem.service.UserService;
import cn.edu.abtc.iem.action.UserAction;
import cn.edu.abtc.iem.action.base.BaseAction;
import cn.edu.abtc.iem.domain.User;
//展示层，调用方法和传参
public class UserActionImpl extends BaseAction implements UserAction,ModelDriven<User>{
	private static final long serialVersionUID = 1L;
	private UserService<User> userService;
	
	public UserService<User> getUserService() {
		return userService;
	}

	public void setUserService(UserService<User> userService) {
		this.userService = userService;
	}
	
	private User user=new User();//struts2 进行注入，需要getter、setter方法（没有交由spring管理，action类是交由spring的）

//	//得到request和response方法
//	HttpServletRequest request = ServletActionContext.getRequest();
//    HttpServletResponse response = ServletActionContext.getResponse();
//    HttpSession session = request.getSession();// 域对象
    
    //相关逻辑组件的setter、getter方法，用于spring-bean的依赖注入（主要调用setter方法）
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override//ModelDriven数据接口
	public User getModel() {
		//ModelDriven接收前端的参数
		return user;
	}	

	@Override/*添加用户*/
	public String insertMethod() {
		if(!user.getUserName().equals("")&&!user.getPassword().equals(""))
		{
			if(userService.isExist(user.getUserName()))//判断数据库是否存在同样的用户名
			{
				String msg="添加用户信息失败，已存在该用户信息！";
				getRequest().setAttribute("addError", msg);
				return ADD;
			}
			else
			{
				userService.insert(user);
				return MAIN;
			}
		}
		else{
			String msg="添加用户信息失败，用户名或密码信息不能为空！";
			getRequest().setAttribute("addError", msg);
			return ADD;
		}
	}

	@Override/*删除用户*/
	public String deleteMethod() {
		userService.delete(user.getId());//href中url的传参数（用？传的参数）仍然会存入model对象，自动将String转化为int（modeldriven的工作）
//		System.out.println("<><"+user.getId()+"<><>");
		//得到表格的数据
		List<User> userList=userService.findAll(User.class);
		getRequest().setAttribute("userList",userList);
		
		return MAIN;
	}
	
	@Override/*修改用户*/
	public String updateMethod() {
		String oldUserName = getRequest().getParameter("oldUserName");//获取旧用户名
		String oldPassword = getRequest().getParameter("oldPassword");//获取旧密码
		if(!user.getUserName().equals("")&&user.getPassword().equals(""))//只改用户名
		{
			if(!userService.isExist(user.getUserName()))//判断输入的用户是否重复
			{
				userService.update(user, user.getId(), user.getUserName(), oldPassword);
				//得到表格的数据
				List<User> userList=userService.findAll(User.class);
				getRequest().setAttribute("userList",userList);
				return MAIN;
			}
			else {
				String msg = "修改失败。用户名已存在，请重试！";
				getRequest().setAttribute("updateError", msg);
				getRequest().setAttribute("oldUserName", oldUserName);
				getRequest().setAttribute("oldPassword", oldPassword);
				return UPDATE;
			}
			
		}
		else if(user.getUserName().equals("")&&!user.getPassword().equals(""))//只改密码
		{

			userService.update(user, user.getId(), oldUserName, user.getPassword());
			//得到表格的数据
			List<User> userList=userService.findAll(User.class);
			getRequest().setAttribute("userList",userList);
			return MAIN;
		}
		else if(!user.getUserName().equals("")&&!user.getPassword().equals(""))//都改
		{
			if(!userService.isExist(user.getUserName()))//判断输入的用户是否重复
			{
				userService.update(user, user.getId(), user.getUserName(), user.getPassword());
				//得到表格的数据
				List<User> userList=userService.findAll(User.class);
				getRequest().setAttribute("userList",userList);
				return MAIN;
			}
			else {
				String msg = "修改失败。用户名已存在，请重试！";
				getRequest().setAttribute("updateError", msg);
				getRequest().setAttribute("oldUserName", oldUserName);
				getRequest().setAttribute("oldPassword", oldPassword);
				return UPDATE;
			}
		}
		else
		{
//			return "updateFail";
			String msg = "修改失败。请进行修改操作，请重试！";
			getRequest().setAttribute("updateError", msg);
			getRequest().setAttribute("oldUserName", oldUserName);
			getRequest().setAttribute("oldPassword", oldPassword);
			return UPDATE;
		}
	}
	@Override/*根据用户名查询用户信息*/
	public String queryMethod()
	{
		String queryKey = getRequest().getParameter("queryKey");
//		System.out.println(queryKey+"---query---------");
		List<User> qurey = userService.qurey(user.getUserName());//默认模糊搜索
		if(queryKey.equals("option1"))//模糊查询
		{
			qurey = userService.qurey(user.getUserName());
		}
		else if(queryKey.equals("option2"))//准确查询
		{
			qurey = userService.findByName(user.getUserName());
		}
//		List<User> qurey = userService.qurey(user.getUserName());
		if(!user.getUserName().equals(""))
		{
			if(qurey.size()>0)
			{
				getRequest().setAttribute("userList",qurey);
//				return "query";
			}
			else
			{
				String str="没有查询到结果";
				getRequest().setAttribute("queryError",str );
//				//错误显示所有用户
//				List<User> userList=userService.findAll(User.class);
//				request.setAttribute("userList",userList);
//				return "query";
			}
		}
		else
		{
			String str="请输入你想要搜索的用户名，支持模糊（关键词）搜索哦！";
			getRequest().setAttribute("queryError",str );
//			//错误显示所有用户
//			List<User> userList=userService.findAll(User.class);
//			request.setAttribute("userList",userList);
//			return "query";
		}
		return QUERY;
	}
	
	@Override/*跳转主页界面，查询所有用户信息*/
	public String toMainMethod()
	{
		List<User> userList=userService.findAll(User.class);
		getRequest().setAttribute("userList",userList);
		return Action.SUCCESS;
		
	}
	
	@Override/*跳转到修改页面*/
	public String toUpdateMethod() {
		//将旧用户、旧密码传到前端->${XX}获取该值
		getRequest().setAttribute("oldUserName", user.getUserName());
		getRequest().setAttribute("oldPassword", user.getPassword());		
		return DEFAULT;
	}
	
	@Override/*跳转到添加页面*/
	public String toAddMethod()
	{
		return DEFAULT;
	}
	
}
