package cn.edu.abtc.iem.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.edu.abtc.iem.domain.Admin;
//extends AbstractInterceptor
//extends MethodFilterInterceptor includeMethod��excludeMethod
//Userģ��Ȩ�޼��������
public class AdminInterceptor  extends MethodFilterInterceptor{
	private static final long serialVersionUID = 1L;

	//���ٸ�������֮ǰ�Ļص�����
	@Override
	public void destroy() {
		super.destroy();
	}
	//��ʼ�����������Ļص�����
	@Override
	public void init() {
		super.init();
	}
	//������ʵ�����ص��߼�����2
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		return super.intercept(invocation);
	}
	
	//������ʵ�����ص��߼�����
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//ȡ�ñ����ص�actionʵ��
//		UserAction<User> action = (UserAction<User>) invocation.getAction();
		//ִ�и��������ĺ�һ��������
		//�������������û����������������ֱ��ִ��Action�ı����ط���
//		String result = invocation.invoke();
		
		/*����һ*/
		//��ȡ���û���Ҫ���ʵ�·��,����struts.xml�����õ�action��name
		String url = invocation.getProxy().getActionName();
		System.out.println(url+"----Intercept-url");
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpServletResponse response = ServletActionContext.getResponse();
		//��ȡ�û�������û���
		Admin admin = (Admin) session.getAttribute("sessionAdmin");
		System.out.println(admin+"----intercept-sessionAdmin");
		
		//�ж��Ƿ�Ϸ�����ҳ���ж��Ƿ�ͨ��login����user��Ϣ��session��
//		/*object==null�ж�object�ǲ��Ǳ��������ڴ�ռ�
//		 * ����objectû�б������ڴ�ռ䣬��object==null
//		 * object.equals(null)�����׳�NullPointerException
//		 * û�б������ڴ�ռ�Ķ����ǲ��ܵ����κη����ġ� */
		if(admin!=null&&!admin.equals(null)){
			return invocation.invoke();
		}else{
			String msg = "�㻹û�е�¼���ߵ�¼ʧЧ�����ȵ�¼!";
			request.setAttribute("error", msg);
			return Action.ERROR;
		}

		/*������*/
	/*	//ȡ��������ص�ActionContextʵ��
		ActionContext invocationContext = invocation.getInvocationContext();
		Map<String, Object> session1 = invocationContext.getSession();
		//ȡ��Session���user����
		User user = (User) session1.get("sessionUser");
		if(!user.equals(null))
		{
			return invocation.invoke();
		}
		invocationContext.put("error", "�㻹û�е�¼���ߵ�¼ʧЧ�����ȵ�¼");
		return Action.ERROR;*/
	}

}
