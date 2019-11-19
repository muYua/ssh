package cn.edu.abtc.iem.aspect.impl;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.edu.abtc.iem.aspect.CommonAspect;
import cn.edu.abtc.iem.utils.AspectUtil;

@Component
@Aspect
public class CommonAspectImpl extends AspectUtil implements CommonAspect {

	@Override /* ��������� */
	@Pointcut("execution(* cn.edu.abtc.iem.service.impl.*ServiceImpl.*(..))")
	public void servicePointcut() {
	}

	/* ���Ȩ�� */
	@Before(value = "servicePointcut()")
	public void jurisdictionAspect() {
		System.out.println("ģ����Ȩ��..");
	}

	/* д����־ */
	@After(value = "servicePointcut()")
	public void logAspect() {
		System.out.println("ģ��д����־..");
//		getProxy().getActionName();
	}

}
