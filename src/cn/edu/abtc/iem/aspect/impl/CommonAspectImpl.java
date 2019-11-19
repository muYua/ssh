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

	@Override /* 定义切入点 */
	@Pointcut("execution(* cn.edu.abtc.iem.service.impl.*ServiceImpl.*(..))")
	public void servicePointcut() {
	}

	/* 检查权限 */
	@Before(value = "servicePointcut()")
	public void jurisdictionAspect() {
		System.out.println("模拟检查权限..");
	}

	/* 写入日志 */
	@After(value = "servicePointcut()")
	public void logAspect() {
		System.out.println("模拟写入日志..");
//		getProxy().getActionName();
	}

}
