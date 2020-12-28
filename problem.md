##前端

* ajax中url的路径问题：
  1. http://localhost:8080/projectname/resource/index.html 页面路径
  2. 
    ```
          1、相对于网站根目录可以用"/"开始 (根目录是指服务器的根目录，不是你项目的根目录)
          $.ajax({
              url:"/getData.do"
          })
          请求的路径是：http://localhost:8080/getData.do
          公司项目使用的是"/" 开头，不过将服务器根目录除外的所有路径都谢了
  
          2、"../"表示页面目录的上一级目录
          $.ajax({
             url:"../getData.do" 
          })
          请求的路径是：http://localhost:8080/projectname/getData.do
   
          3、项目的根路径
          $.ajax({
              url:"getdata.do" 
          })
          请求的路径是：http://localhost:8080/projectname/getData.do
  
          4、全路径 
          $.ajax({
              url:"http://localhost:8080/projectname/getdata.do" 
          })
          请求的路径是：http://localhost:8080/projectname/getdata.do
  ```
  






##后端
* druid 的版本和mysql-connector-java的版本，如何找到他们最合适的版本。在github中找到druid，可以看到druid使用的mysql-connector-java版本
    1. 如果版本不一致，会出现连接池初始化异常
    
* 使用Reflections反射框架，Reflections re = new Reflections("com.zzy"); 传递参数，则扫面指定的包；不传递参数，则扫描整个项目
    1. 如果在进行初始化的时候，没有初始化成功，或者依赖没有注入，则会报NotFoundClass:org.reflections  
    
* @Autowired 参数可以选择true 和false，如果是false，则不会被扫描

* 使用注解，给每个类一个标识，然后使用反射根据标识条件进行初始化和依赖注入

* 解决java.lang.ClassNotFoundException: "com.mysql.cj.jdbc.Driver" at java.net.URLClassLoader.findClass
    1. 解决报java.lang.ClassNotFoundException: "com.mysql.cj.jdbc.Driver"错的方法
        ```
          第一步，检查 mysql-connector-java是否导进去，放在lib时候需要Add to Build path
          第二步，检查mysql-connector-java jar版本是否跟本机安装的mysql版本匹配
          第三步，如果确认包导进去，还是报错，极可能是自己编译文件出错，就比如我犯的错误，加了引号
       ```


###前后端交互
* 前端访问后台，无法访问到资源，也不报错
  1. 在资源路径正确的情况下无效果，解决：将项目clean，然后可以正常访问资源



### 数据库报错1366 字符串存入异常
* 中文编码问题，查看表的编码 ：SHOW FULL COLUMNS FROM 表名，改成utf-8



##IDEA

###将maven项目转换为web项目
* File -> Project Structure -> Modules -> 添加web【E:\soft\javasoft\private\idea2019.2.2\ideaPrivate\myspring\src\main\webapp\WEB-INF\web.xml、E:\soft\javasoft\private\idea2019.2.2\ideaPrivate\myspring\src\main\webapp】 
    1. 如果项目中存在webapp，则选中资源。如果项目中没有webapp则指定路径，会在指定的路径创建webapp

###添加tomcat 时，配置页最下面警告：Warning: No artifacts configured
* File -> Project Structure -> Artifactes  添加Web Application: Exploded   Web Application: Archive

### 再次进入到添加tomcat页面 ：Warning: No artifacts marked for deployment，就是说当前web项目没有部署到Tomcat服务器中
* deployment  添加war.exploded
    1. war模式：将WEB工程以包的形式上传到服务器 ；
    2. war exploded模式：将WEB工程以当前文件夹的位置关系上传到服务器；
    3. war模式这种可以称之为是发布模式，看名字也知道，这是先打成war包，再发布；
    4. war exploded模式是直接把文件夹、jsp页面 、classes等等移到Tomcat 部署文件夹里面，进行加载部署。因此这种方式支持热部署，一般在开发的时候也是用这种方式。
    5. 在平时开发的时候，使用热部署的话，应该对Tomcat进行相应的设置，这样的话修改的jsp界面什么的东西才可以及时的显示出来。
        1. 实现热部署：
        2. On 'Update' action:
        ``` 
          -Update resources ：如果发现有更新，而且更新的是资源文件（*.jsp，*.xml等，不包括java文件）,就会立刻生效
          -Update classes and resources ： 如果发现有更新，这个是同时包含java文件和资源文件的，就会立刻生效
           这里需要注意一下：在运行模式下，修改java文件时不会立刻生效的；而debug模式下，修改java文件时可以立刻生效的。当然，两种运行模式下，修改resources资源文件都是可以立刻生效的。
          -Redploy ： 重新部署，只是把原来的war删掉，不重启服务器
          -Restart ： 重启服务器
       ```
        3. On  frame deactivation :Update resources
        ```
          -Do nothing : 不做任何事 （一般推荐这个，因为失去焦点的几率太大）
          -Update resources : 失去焦点后，修改的resources文件都会立刻生效
          -Update classes and resources ： 失去焦点后，修改的java ，resources文件都会立刻生效（与On update action中的Update classes and resources一样，也是运行模式修改的java文件不会生效，debug模式修改的java文件会立刻生效）
       ```
       ```
        另外，如果Artifact是war包形式的话，On Update action与On frame deactivation中的选项也是不一样的：没有Update resources和 Update classes and resources这种选项，取而代之的是Hot Swap Classes选项，本质的意思是一样的。
       ```

### Intellij IDEA的Facets和Artifacts 【遇到问题再看】
* https://blog.csdn.net/gongsunjinqian/article/details/53018172





































































