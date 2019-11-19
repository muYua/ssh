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
//չʾ�㣬���÷����ʹ���
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
	private Admin admin=new Admin();//����ģ������ ������Ҫ�ֶ�newʵ����һ��
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
//	private UserService<User>
	
	@Override//ģ�������ӿ�
	public Admin getModel() {
		return admin;
	}

	@Override/*��¼*/
	public String login(){
		if(adminService.check(admin.getAdminName(), admin.getAdminPassword()))//getAdminName()
		{
			/*�õ�׼ȷ�ĵ�¼��user����*/
			List<Admin> adminlist = adminService.findByName(admin.getAdminName());
			Admin sessionAdmin = adminlist.get(0);//��list��ȡ����һ��Ԫ��
			getSession().setAttribute("sessionAdmin",sessionAdmin);//�洢���ε�¼���û����������session��ǰ��ͨ��${session}���
			//�õ��������� ������userList��ֵ����ǰ��//�����Json���ݽӿ�ʵ��ǰ�˺�̨���ݵĽ���
//			List<User> userList=userService.findAll(User.class);
//			request.setAttribute("userList",userList);
			
			return TOMAIN;
		}
		else
		{
//			return "loginFail";
			String msg = "��¼ʧ�ܣ�";
			getRequest().setAttribute("loginError", msg);
			getRequest().setAttribute("loginFlag", true);//ǰ�������ж��Ƿ��¼ʧ��
			return Action.LOGIN;
		}
	}

	@Override/*ע��*/
	public String reg() {
		String adminPassword2 = getRequest().getParameter("adminPassword2");//��ȡǰ�˵ڶ���У�������ֵ
		if(admin.getAdminPassword().equals(adminPassword2)&&!adminPassword2.equals("")&&!admin.getAdminPassword().equals("")&&!admin.getAdminName().equals(""))//�ж��������������Ƿ���ͬ
		{
			//�ж��Ƿ���������������ȷ
			//�ж��Ƿ�����Ϊ��
			//�ж��û����Ƿ��Ѵ���
			if(adminService.isExist(admin.getAdminName()))//�ж����ݿ��Ƿ����ͬ�����û���
			{
				String msg="ע��ʧ�ܣ��Ѵ��ڸ��û���Ϣ��";
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
			String msg="ע��ʧ�ܣ������������벻һ�»�Ϊ�û���������Ϊ�գ�";
			getRequest().setAttribute("regError", msg);
			return REG;
		}
	}

	@Override/*���*/
	public String insert() {
		if(!admin.getAdminName().equals("")&&!admin.getAdminPassword().equals(""))
		{
			if(adminService.isExist(admin.getAdminName()))//�ж����ݿ��Ƿ����ͬ�����û���
			{
				String msg="��ӹ���Ա��Ϣʧ�ܣ��Ѵ��ڸ��û���Ϣ��";
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
			String msg="��ӹ���Ա��Ϣʧ�ܣ��û�����������Ϣ����Ϊ�գ�";
			getRequest().setAttribute("addError", msg);
			return ADDADMIN;
		}
	}

	@Override/*ɾ��*/
	public String delete() {
		adminService.delete(admin.getId());//href��url�Ĵ��������ã����Ĳ�������Ȼ�����model�����Զ���Stringת��Ϊint��modeldriven�Ĺ�����
//		System.out.println("<><"+user.getId()+"<><>");
		//�õ���������
		List<Admin> adminList=adminService.findAll(Admin.class);
		getRequest().setAttribute("adminList",adminList);
		
		return TOADMIN;
	}

	@Override/*��ѯ*/
	public String query() {
		String queryKey = getRequest().getParameter("queryKey");
//		System.out.println(queryKey+"---query---------");
		List<Admin> qurey = adminService.qurey(admin.getAdminName());//Ĭ��ģ������
		if(queryKey.equals("option1"))//ģ����ѯ
		{
			qurey = adminService.qurey(admin.getAdminName());
		}
		else if(queryKey.equals("option2"))//׼ȷ��ѯ
		{
			qurey = adminService.findByName(admin.getAdminName());
		}
		else
		{
			String str="�����쳣��";
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
				String str="û�в�ѯ�����";
				getRequest().setAttribute("queryError",str );
			}
		}
		else
		{
			String str="����������Ҫ�������û�����֧��ģ�����ؼ��ʣ�����Ŷ��";
			getRequest().setAttribute("queryError",str );
		}
		return ADMIN;
	}

	@Override/*�޸�*/
	public String update() {
		String oldAdminName = getRequest().getParameter("oldAdminName");//��ȡ���û���
		String oldAdminPassword = getRequest().getParameter("oldAdminPassword");//��ȡ������
		if(!admin.getAdminName().equals("")&&admin.getAdminPassword().equals(""))//ֻ���û���
		{
			if(!adminService.isExist(admin.getAdminName()))//�ж�������û��Ƿ��ظ�
			{
				adminService.update(admin, admin.getId(), admin.getAdminName(), oldAdminPassword);
				//�õ���������
				List<Admin> adminList=adminService.findAll(Admin.class);
				getRequest().setAttribute("userList",adminList);
				return TOADMIN;
			}
			else {
				String msg = "�޸�ʧ�ܡ��û����Ѵ��ڣ������ԣ�";
				getRequest().setAttribute("updateError", msg);
				getRequest().setAttribute("oldAdminName", oldAdminName);
				getRequest().setAttribute("oldAdminPassword", oldAdminPassword);
				return UPDATEADMIN;
			}
			
		}
		else if(admin.getAdminName().equals("")&&!admin.getAdminPassword().equals(""))//ֻ������
		{

			adminService.update(admin, admin.getId(), oldAdminName, admin.getAdminPassword());
			//�õ���������
			List<Admin> adminList=adminService.findAll(Admin.class);
			getRequest().setAttribute("adminList",adminList);
			return TOADMIN;
		}
		else if(!admin.getAdminName().equals("")&&!admin.getAdminPassword().equals(""))//����
		{
			if(!adminService.isExist(admin.getAdminName()))//�ж�������û��Ƿ��ظ�
			{
				adminService.update(admin, admin.getId(), admin.getAdminName(), admin.getAdminPassword());
				//�õ���������
				List<Admin> adminList=adminService.findAll(Admin.class);
				getRequest().setAttribute("adminList",adminList);
				return MAIN;
			}
			else {
				String msg = "�޸�ʧ�ܡ��û����Ѵ��ڣ������ԣ�";
				getRequest().setAttribute("updateError", msg);
				getRequest().setAttribute("oldAdminName", oldAdminName);
				getRequest().setAttribute("oldAdminPassword", oldAdminPassword);
				return UPDATEADMIN;
			}
		}
		else
		{
//			return "updateFail";
			String msg = "�޸�ʧ�ܡ�������޸Ĳ����������ԣ�";
			getRequest().setAttribute("updateError", msg);
			getRequest().setAttribute("oldAdminName", oldAdminName);
			getRequest().setAttribute("oldAdminPassword", oldAdminPassword);
			return UPDATEADMIN;
		}
	}

	@Override/*ע��*/
	public String logout() {
		getSession().setAttribute("sessionAdmin",null);
		return Action.LOGIN;
	}

	@Override/*��ӹ���Աҳ����ת*/
	public String toAddAdmin() {
		return ADDADMIN;
	}

	@Override/*��תadmin��ҳ����ȫ����Ϣ��*/
	public String toAdmin() {
		List<Admin> list = adminService.findAll(Admin.class);
		getRequest().setAttribute("adminList",list);
		return ADMIN;
	}

	@Override/*��ת�޸�ҳ��*/
	public String toUpdateAdmin() {
		//�����û��������봫��ǰ��->${XX}��ȡ��ֵ
		getRequest().setAttribute("oldAdminName", admin.getAdminName());
		getRequest().setAttribute("oldAdminPassword", admin.getAdminPassword());		
		return DEFAULT;
	}

	@Override/*��תmainҳ�棨��ȫ��ҳ�棩*/
	public String toMain() {
		List<User> userList = userService.findAll(User.class);
		getRequest().setAttribute("userList", userList);
		return MAIN;
	}
	
}
