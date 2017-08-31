package org.evm.biz.log.aop;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.evm.biz.func.entity.FuncVO;
import org.evm.biz.log.annotation.SystemControllerLog;
import org.evm.biz.log.annotation.SystemServiceLog;
import org.evm.biz.log.entity.LogVO;
import org.evm.biz.log.service.ILogDbService;
import org.evm.biz.user.entity.UserVO;
import org.evm.core.consts.SystemConsts;
import org.evm.core.exception.SmartFunctionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class SystemLogAspect {
	// 注入Service用于把日志保存数据库
	// @Resource
	private ILogDbService logDbService;

	public void setLogDbService(ILogDbService logDbService) {
		this.logDbService = logDbService;
	}

	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

	@Pointcut("@annotation(org.evm.biz.log.annotation.SystemServiceLog)")
	public void serviceAspect() {
	}

	// Controller层切点
	@Pointcut("@annotation(org.evm.biz.log.annotation.SystemControllerLog)")
	public void controllerAspect() {
	}

	/**
	 * 前置通知 用于拦截service层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@Before("serviceAspect()")
	public void doServiceBefore(JoinPoint joinPoint) {
		boolean result = false;
		String[] res = null;

		try {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			if(servletRequestAttributes==null) {
				//TODO:APP访问调用WebService时，不进行权限校验
				return;
			}
			HttpServletRequest request = servletRequestAttributes.getRequest();
			HttpSession session = request.getSession();
			// 读取session中的用户
			UserVO user = (UserVO) session.getAttribute(SystemConsts.LOGIN_INFO);
			res = getServiceMthodDescription(joinPoint);
			String isCheck = res[3];
			if (isCheck.equals("false")) {
				logger.info("操作" + res[0] + "不需要验证权限!");
				return;
			}
			List<FuncVO> funcList = user.getFuncList();
			for (FuncVO func : funcList) {
				if (func.getFuncId().equals(res[2])) {
					result = true;
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("AOP doServiceBefore 计算服务权限错误异常！ ", e);
		}
		if (!result) {
			throw new SmartFunctionException("您没有操作" + res[0] + "的权限!");
		}
	}

	@After("serviceAspect()")
	public void doServiceAfter(JoinPoint joinPoint) {
		try {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			if(servletRequestAttributes==null) {
				//TODO:APP访问调用WebService时，不进行权限校验
				return;
			}
			HttpServletRequest request = servletRequestAttributes.getRequest();
			HttpSession session = request.getSession();
			// 读取session中的用户
			UserVO user = (UserVO) session.getAttribute(SystemConsts.LOGIN_INFO);
			String[] res = getServiceMthodDescription(joinPoint);
			//TODO:web和mis查询不记录日志
			if ("10002".equals(res[2])||"20002".equals(res[2])) {
				return;
			}
			String inputPara = getServiceMthodPara(joinPoint);
			String para = inputPara;
			if (inputPara != null && inputPara.length() > 600) {
				para = inputPara.substring(0, 599);
			}
			LogVO whereCause = new LogVO();
			whereCause.setLogPara(para);
			whereCause.setLogContent(res[0]);
			if (!"".equals(res[1])) {
				whereCause.setLogBizType(res[1]);
			} else {
				whereCause.setLogBizType("0");
			}
			 
			if (!"".equals(res[2])) {
				whereCause.setLogBizId(Long.parseLong(res[2]));
			} else {
				whereCause.setLogBizId(0);
			}

			whereCause.setInsUser(user.getUid() + "");
			logDbService.insertLog(whereCause);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("存储日志异常", e);
		}
	}

	@Before("controllerAspect()")
	public void doControllerBefore(JoinPoint joinPoint) {

	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String[] getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
		String[] result = new String[4];
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		String bizTypeId = "";
		String serviceid = "";
		boolean isCheck = true;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemServiceLog.class).description();
					bizTypeId = method.getAnnotation(SystemServiceLog.class).bizTypeId();
					serviceid = method.getAnnotation(SystemServiceLog.class).functionId();
					result[0] = description;
					result[1] = bizTypeId;
					result[2] = serviceid;
					result[3] = method.getAnnotation(SystemServiceLog.class).isCheckFunction() + "";
					break;
				}
			}
		}
		return result;
	}

	// public static String[] getServiceMethodFunctionId(JoinPoint joinPoint)
	// throws Exception {
	// String targetName = joinPoint.getTarget().getClass().getName();
	// String methodName = joinPoint.getSignature().getName();
	// Object[] arguments = joinPoint.getArgs();
	// Class targetClass = Class.forName(targetName);
	// Method[] methods = targetClass.getMethods();
	// String[] result = new String[2];
	// String funcId, description = "";
	// for (Method method : methods) {
	// if (method.getName().equals(methodName)) {
	// Class[] clazzs = method.getParameterTypes();
	// if (clazzs.length == arguments.length) {
	// funcId = method.getAnnotation(SystemServiceLog.class).functionId();
	// description = method.getAnnotation(SystemServiceLog.class).description();
	// result[0] = funcId;
	// result[1] = description;
	// break;
	// }
	// }
	// }
	// return result;
	// }

	public static String getServiceMthodPara(JoinPoint joinPoint) throws Exception {
		Object para = "";
		Object[] arguments = joinPoint.getArgs();
		if (arguments != null && arguments.length > 0) {
			para = arguments[0];
		}
		return para.toString();
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemControllerLog.class).description();
					break;
				}
			}
		}
		return description;
	}

}
