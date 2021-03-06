package site.dungang.allowedparams.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 对绑定到模型的参数做过滤
 * </p>
 * <p>
 * spring 提供的动态绑定
 * 如果是使用的数据表模型，则导致一些不应该在前端修改参数而出现不安全隐患。
 * 比如，用户模型，我在更新用户年龄的时候，不允许更新生日
 * 如下代码就会导致，只要客户端恶意传递了参数 birthday,如果模型有对应的属性的话就可能被更新到。
 * </p>
 * <code>
 * &#64;PostMapping("/update")
 * public String update(User user, &#64;PathVariable("id") Long id) {
 * 	userService.update(user,id);
 *  return "update/success";
 * }
 * </code>
 * 
 * <p>过滤的方式有3种方式:</p>
 * <dl>
 * <dt>
 * 当自动绑定请求的参数的时候，可以定义一个form模型，只接受固定的属性参数
 * </dt>
 * <dd>
 * <code>
 * class UserFrom { 
 * 	private Integer age;
 *  public Integer getAge(){
 *  	return age;
 *  }
 *  public setAge(Integer age){
 *  	this.age = age;
 *  }
 * }
 * 
 * &#64;PostMapping("/update")
 * public String update(UserForm user, &#64;PathVariable("id") Long id) {
 * 	userService.update(user,id);
 *  return "update/success";
 * }
 * </code>
 * </dd>
 * <dt>
 * 不自动绑定参数，而是获取参数的全部，在代码逻辑上做赋值过滤
 * </dt>
 * <dd>
 * <code>
 * &#64;PostMapping("/update")
 * public String update(HtttpServletRequest request, &#64;PathVariable("id") Long id) {
 *  User user = new User();
 *  user.setAge(request.getParameterValue("age"));
 * 	userService.update(user,id);
 *  return "update/success";
 * }
 * </code>
 * </dd>
 * <dt>
 * 使用本文件定义的注解 &#64;AllowedParams
 * </dt>
 * <dd>
 * <p>先配置参数解析器</p>
 * <code>
 *   &#64;Bean
 *   public WebMvcConfigurer corsConfigurer() {
 *      return new WebMvcConfigurerAdapter() {
 *		&#64;Override
 *		public void addArgumentResolvers(List&lt;HandterMethodArgumentResolver&gt; argumentResolvers) {
 *			//添加自定义的参数解析注解器
 *			argumentResolvers.add(new AllowedParamsModelAttributeMethodProcessor());
 *			super.addArgumentResolvers(argumentResolvers);
 *		}
 *   };
 * }
 * </code>
 * <p>给方法参数添加注解</p>
 * <code>
 * &#64;PostMapping("/update")
 * public String update( &#64;AllowedParams(params={"age"}) User user, &#64;PathVariable("id") Long id) {
 * 	userService.update(user,id);
 *  return "update/success";
 * }
 * </code>
 * </dd>
 * </dl>
 * 
 * 
 * @author dungang
 *
 */
@Documented
@Target({ ElementType.PARAMETER })
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedParams {

	/**
	 * 设置允许的参数名称
	 * 
	 * @return 参数名称数组
	 */
	String[] params() default "";
}
