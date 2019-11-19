package cn.edu.abtc.iem.action.impl;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

import cn.edu.abtc.iem.service.UserService;
import cn.edu.abtc.iem.action.UserAction;
import cn.edu.abtc.iem.action.base.BaseAction;
import cn.edu.abtc.iem.domain.User;
//չʾ�㣬���÷����ʹ���
public class UserActionImpl extends BaseAction implements UserAction,ModelDriven<User>{
	private static final long serialVersionUID = 1L;
	private UserService<User> userService;
	
	public UserService<User> getUserService() {
		return userService;
	}

	public void setUserService(UserService<User> userService) {
		this.userService = userService;
	}
	
	private User user=new User();//struts2 ����ע�룬��Ҫgetter��setter������û�н���spring����action���ǽ���spring�ģ�

//	//�õ�request��response����
//	HttpServletRequest request = ServletActionContext.getRequest();
//    HttpServletResponse response = ServletActionContext.getResponse();
//    HttpSession session = request.getSession();// �����
    
    //����߼������setter��getter����������spring-bean������ע�루��Ҫ����setter������
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override//ModelDriven���ݽӿ�
	public User getModel() {
		//ModelDriven����ǰ�˵Ĳ���
		return user;
	}	

	@Override/*����û�*/
	public String insertMethod() {
		if(!user.getUserName().equals("")&&!user.getPassword().equals(""))
		{
			if(userService.isExist(user.getUserName()))//�ж����ݿ��Ƿ����ͬ�����û���
			{
				String msg="����û���Ϣʧ�ܣ��Ѵ��ڸ��û���Ϣ��";
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
			String msg="����û���Ϣʧ�ܣ��û�����������Ϣ����Ϊ�գ�";
			getRequest().setAttribute("addError", msg);
			return ADD;
		}
	}

	@Override/*ɾ���û�*/
	public String deleteMethod() {
		userService.delete(user.getId());//href��url�Ĵ��������ã����Ĳ�������Ȼ�����model�����Զ���Stringת��Ϊint��modeldriven�Ĺ�����
//		System.out.println("<><"+user.getId()+"<><>");
		//�õ���������
		List<User> userList=userService.findAll(User.class);
		getRequest().setAttribute("userList",userList);
		
		return MAIN;
	}
	
	@Override/*�޸��û�*/
	public String updateMethod() {
		String oldUserName = getRequest().getParameter("oldUserName");//��ȡ���û���
		String oldPassword = getRequest().getParameter("oldPassword");//��ȡ������
		if(!user.getUserName().equals("")&&user.getPassword().equals(""))//ֻ���û���
		{
			if(!userService.isExist(user.getUserName()))//�ж�������û��Ƿ��ظ�
			{
				userService.update(user, user.getId(), user.getUserName(), oldPassword);
				//�õ���������
				List<User> userList=userService.findAll(User.class);
				getRequest().setAttribute("userList",userList);
				return MAIN;
			}
			else {
				String msg = "�޸�ʧ�ܡ��û����Ѵ��ڣ������ԣ�";
				getRequest().setAttribute("updateError", msg);
				getRequest().setAttribute("oldUserName", oldUserName);
				getRequest().setAttribute("oldPassword", oldPassword);
				return UPDATE;
			}
			
		}
		else if(user.getUserName().equals("")&&!user.getPassword().equals(""))//ֻ������
		{

			userService.update(user, user.getId(), oldUserName, user.getPassword());
			//�õ���������
			List<User> userList=userService.findAll(User.class);
			getRequest().setAttribute("userList",userList);
			return MAIN;
		}
		else if(!user.getUserName().equals("")&&!user.getPassword().equals(""))//����
		{
			if(!userService.isExist(user.getUserName()))//�ж�������û��Ƿ��ظ�
			{
				userService.update(user, user.getId(), user.getUserName(), user.getPassword());
				//�õ���������
				List<User> userList=userService.findAll(User.class);
				getRequest().setAttribute("userList",userList);
				return MAIN;
			}
			else {
				String msg = "�޸�ʧ�ܡ��û����Ѵ��ڣ������ԣ�";
				getRequest().setAttribute("updateError", msg);
				getRequest().setAttribute("oldUserName", oldUserName);
				getRequest().setAttribute("oldPassword", oldPassword);
				return UPDATE;
			}
		}
		else
		{
//			return "updateFail";
			String msg = "�޸�ʧ�ܡ�������޸Ĳ����������ԣ�";
			getRequest().setAttribute("updateError", msg);
			getRequest().setAttribute("oldUserName", oldUserName);
			getRequest().setAttribute("oldPassword", oldPassword);
			return UPDATE;
		}
	}
	@Override/*�����û�����ѯ�û���Ϣ*/
	public String queryMethod()
	{
		String queryKey = getRequest().getParameter("queryKey");
//		System.out.println(queryKey+"---query---------");
		List<User> qurey = userService.qurey(user.getUserName());//Ĭ��ģ������
		if(queryKey.equals("option1"))//ģ����ѯ
		{
			qurey = userService.qurey(user.getUserName());
		}
		else if(queryKey.equals("option2"))//׼ȷ��ѯ
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
				String str="û�в�ѯ�����";
				getRequest().setAttribute("queryError",str );
//				//������ʾ�����û�
//				List<User> userList=userService.findAll(User.class);
//				request.setAttribute("userList",userList);
//				return "query";
			}
		}
		else
		{
			String str="����������Ҫ�������û�����֧��ģ�����ؼ��ʣ�����Ŷ��";
			getRequest().setAttribute("queryError",str );
//			//������ʾ�����û�
//			List<User> userList=userService.findAll(User.class);
//			request.setAttribute("userList",userList);
//			return "query";
		}
		return QUERY;
	}
	
	@Override/*��ת��ҳ���棬��ѯ�����û���Ϣ*/
	public String toMainMethod()
	{
		List<User> userList=userService.findAll(User.class);
		getRequest().setAttribute("userList",userList);
		return Action.SUCCESS;
		
	}
	
	@Override/*��ת���޸�ҳ��*/
	public String toUpdateMethod() {
		//�����û��������봫��ǰ��->${XX}��ȡ��ֵ
		getRequest().setAttribute("oldUserName", user.getUserName());
		getRequest().setAttribute("oldPassword", user.getPassword());		
		return DEFAULT;
	}
	
	@Override/*��ת�����ҳ��*/
	public String toAddMethod()
	{
		return DEFAULT;
	}
	
}
