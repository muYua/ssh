package cn.edu.abtc.iem.action.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HttpServletRequest getRequest()
    {
    	return ServletActionContext.getRequest();
    }
    public HttpServletResponse getResponse()
    {
    	return ServletActionContext.getResponse();
    }
    public HttpSession getSession()//”Ú∂‘œÛ
    {
    	return ServletActionContext.getRequest().getSession();
    }
}
