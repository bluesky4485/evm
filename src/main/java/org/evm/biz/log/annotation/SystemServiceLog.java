package org.evm.biz.log.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截Controller
 */

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
	/**
	 * 
	 * update by jerry.x 2016年11月19日
	 * 
	 * @return
	 *
	 */
	String description() default "";

	/**
	 * 
	 * update by jerry.x 2016年11月19日
	 * 
	 * @return
	 *
	 */
	String serviceid() default "";

	/**
	 * 1insert 2 delete 3update 4select update by jerry.x 2016年11月19日
	 * 
	 * @return
	 *
	 */
	String bizTypeId() default "";

	/**
	 * 权限 update by jerry.x 2016年11月24日
	 * 
	 * @return
	 *
	 */
	String functionId() default "";

	/**
	 * 是否进行权限校验
	 */
	boolean isCheckFunction() default true;
}
