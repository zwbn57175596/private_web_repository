package com.haoxw.chuanmei.bean;

import java.lang.Thread.UncaughtExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 重写多线程默认异常类
 * 
 * @author xuewuhao
 * 
 */
public class SelfUncaughtExceptionHandler implements UncaughtExceptionHandler {
	private static final Logger log = LoggerFactory
			.getLogger(SelfUncaughtExceptionHandler.class);

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		e.printStackTrace();
		log.error(t.toString(), e);
	}

}
