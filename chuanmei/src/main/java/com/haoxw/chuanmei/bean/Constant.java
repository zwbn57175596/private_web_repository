package com.haoxw.chuanmei.bean;

import java.util.Map;
import java.util.TreeMap;

/**
 * 常量定义
 * @author xuewuhao
 *
 */
public class Constant {

	/**
	 * 默认一页记录数
	 */
	public  final static int pageSize = 10; 
	/**
	 * 用户登录标记
	 */
	public final static String loginFlag = "login_flag";
	/**
	 * 新闻类型  实验中心	设备动态	 维护知识	
	 */
    public final static Map<String, String> NEWSTYPE = new TreeMap<String, String>();
    static {
    	NEWSTYPE.put("1", "实验中心");
    	NEWSTYPE.put("2", "设备动态");
    	NEWSTYPE.put("3", "维护知识");
    }
    
    /**
	 *  记录状态
	 */
    public final static Map<String, String> ROWSTATE = new TreeMap<String, String>();
    static {
    	ROWSTATE.put("0", "正常");
    	ROWSTATE.put("1", "删除");
    }
    
    /**
   	 *  0男 1女
   	 */
       public final static Map<String, String> GENDER = new TreeMap<String, String>();
       static {
    	   GENDER.put("0", "男");
    	   GENDER.put("1", "女");
       }
    
    /**
	 *  导入类型
	 */
    public final static Map<String, String> EXPORTTYPE = new TreeMap<String, String>();
    static {
    	EXPORTTYPE.put("1", "管理员");
    	EXPORTTYPE.put("2", "老师");
    	EXPORTTYPE.put("3", "学生");
    	EXPORTTYPE.put("4", "审核员");
    }
    
    /**
	 *  老师借用状态
	 */
    public final static Map<String, String> TEACHERORDERSTATE = new TreeMap<String, String>();
    static {
    	TEACHERORDERSTATE.put("-1", "不通过");
    	TEACHERORDERSTATE.put("0", "申请");
    	TEACHERORDERSTATE.put("1", "通过");
    	TEACHERORDERSTATE.put("2", "结束");
    }
    /**
	 *  学生借用状态
	 */
    public final static Map<String, String> STUDENTORDERSTATE = new TreeMap<String, String>();
    static {
    	STUDENTORDERSTATE.put("-1", "不通过");
    	STUDENTORDERSTATE.put("0", "申请");
    	STUDENTORDERSTATE.put("1", "通过");
    	STUDENTORDERSTATE.put("2", "已领取");
    	STUDENTORDERSTATE.put("3", "已归还");
    }
}
