package cn.edu.abtc.iem.aspect.impl;

import org.aspectj.lang.annotation.Pointcut;

import cn.edu.abtc.iem.aspect.LogAspect;

public class LogAspectImpl implements LogAspect{

	@Override /* 定义切入点 */
	@Pointcut("execution(* cn.edu.abtc.iem.service.impl.*ServiceImpl.*(..))")
	public void logPointcut() {
		
	}

}
