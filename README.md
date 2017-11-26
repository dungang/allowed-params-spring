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

> 服务端代码

* 只允许`name`这个字段被接受
* 在方法参数上注解，允许接受的参数列表，不在清单的参数不会被赋值给模型

```
public AjaxResult edit(@Valid @AllowedParams(params= {"name"}) Department department ){}
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
