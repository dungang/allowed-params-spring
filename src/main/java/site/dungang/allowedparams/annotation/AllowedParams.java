package site.dungang.allowedparams.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedParams {

	/**
	 * 设置允许的参数名称
	 * @return
	 */
	String[] params() default "";
}
