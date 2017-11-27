spring boot allowed params
--

基于 spring boot 封装的 表单请求的参数检查，必须在允许的参数列表才可以

> pom.xml

```
	<dependency>
		<groupId>site.dungang</groupId>
		<artifactId>allowed-params-spring</artifactId>
		<version>0.0.1</version>
	</dependency>
```

> 如何使用


 **对绑定到模型的参数做过滤**

spring 提供的动态绑定, 如果是使用的数据表模型，则导致一些不应该在前端修改的不安全。
比如，用户模型，我在更新用户年龄的时候，不允许更新生日。
 
 如下代码就会导致，只要客户端传递了参数 birthday,如果模型有对应的属性的话就可能被更新到。
 
```
 @PostMapping("/update")
 public String update(User user, @PathVariable("id") Long id) {
 	userService.update(user,id);
  return "update/success";
 }
```
 
**过滤的方式有3种方式:**

*当自动绑定请求的参数的时候，可以定义一个form模型，只接受固定的属性参数
 
```
 class UserFrom { 
 	private Integer age;
  public Integer getAge(){
  	return age;
  }
  public setAge(Integer age){
  	this.age = age;
  }
 }
```

````
 @PostMapping("/update")
 public String update(UserForm user, @PathVariable("id") Long id) {
 	userService.update(user,id);
  return "update/success";
 }
```

*不自动绑定参数，而是获取参数的全部，在代码逻辑上做赋值过滤
 
```
 @PostMapping("/update")
 public String update(HtttpServletRequest request, @PathVariable("id") Long id) {
  User user = new User();
  user.setAge(request.getParameterValue("age"));
 	userService.update(user,id);
  return "update/success";
 }
```

*使用本文件定义的注解 @AllowedParams

**先配置参数解析器**

```
   @Bean
   public WebMvcConfigurer corsConfigurer() {
      return new WebMvcConfigurerAdapter() {
		@Override
		public void addArgumentResolvers(List&lt;HandlerMethodArgumentResolver&gt; argumentResolvers) {
			//添加自定义的参数解析注解器
			argumentResolvers.add(new AllowedParamsModelAttributeMethodProcessor());
			super.addArgumentResolvers(argumentResolvers);
		}
   };
 }
```

**给方法参数添加注解**

```
 @PostMapping("/update")
 public String update( @AllowedParams(params={"age"}) User user, @PathVariable("id") Long id) {
 	userService.update(user,id);
  return "update/success";
 }
```
 

## 协议

The MIT License (MIT)

Copyright (c) 2017 dungang

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
