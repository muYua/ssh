package cn.edu.abtc.iem.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.edu.abtc.iem.domain.Admin;
//extends AbstractInterceptor
//extends MethodFilterInterceptor includeMethod、excludeMethod
//User模型权限检测拦截器
public class AdminInterceptor  extends MethodFilterInterceptor{
	private static final long serialVersionUID = 1L;

	//销毁该拦截器之前的回掉方法
	@Override
	public void destroy() {
		super.destroy();
	}
	//初始化该拦截器的回掉方法
	@Override
	public void init() {
		super.init();
	}
	//拦截器实现拦截的逻辑方法2
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		return super.intercept(invocation);
	}
	
	//拦截器实现拦截的逻辑方法
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//取得被拦截的action实例
//		UserAction<User> action = (UserAction<User>) invocation.getAction();
		//执行该拦截器的后一个拦截器
		//如果该拦截器后没有其他拦截器，则直接执行Action的被拦截方法
//		String result = invocation.invoke();
		
		/*方法一*/
		//获取了用户所要访问的路径,即在struts.xml中设置的action的name
		String url = invocation.getProxy().getActionName();
		System.out.println(url+"----Intercept-url");
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpServletResponse response = ServletActionContext.getResponse();
		//获取用户输入的用户名
		Admin admin = (Admin) session.getAttribute("sessionAdmin");
		System.out.println(admin+"----intercept-sessionAdmin");
		
		//判断是否合法打开网页（判断是否通过login存入user信息到session）
//		/*object==null判断object是不是被分配了内存空间
//		 * 假如object没有被分配内存空间，即object==null
//		 * object.equals(null)将会抛出NullPointerException
//		 * 没有被分配内存空间的对象是不能调用任何方法的。 */
		if(admin!=null&&!admin.equals(null)){
			return invocation.invoke();
		}else{
			String msg = "你还没有登录或者登录失效，请先登录!";
			request.setAttribute("error", msg);
			return Action.ERROR;
		}

		/*方法二*/
	/*	//取得请求相关的ActionContext实例
		ActionContext invocationContext = invocation.getInvocationContext();
		Map<String, Object> session1 = invocationContext.getSession();
		//取出Session里的user属性
		User user = (User) session1.get("sessionUser");
		if(!user.equals(null))
		{
			return invocation.invoke();
		}
		invocationContext.put("error", "你还没有登录或者登录失效，请先登录");
		return Action.ERROR;*/
	}

}
