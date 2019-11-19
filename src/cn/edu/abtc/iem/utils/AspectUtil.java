package cn.edu.abtc.iem.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class AspectUtil {
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
