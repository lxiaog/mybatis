# springboot 搭建ssm框架
#### 新建Springboot项目，导入依赖
    <dependencies>
        <!--应用监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--应用管客户端理端-->
        <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-client</artifactId>
        </dependency>
        <!--应用管理服务点端-->
        <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.1</version>
        </dependency>
        <!--数据库连接驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!--lombok工具-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

#### 在build标签中加入resources，是将directory指定路径下的文件全部打包在运行包中
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>*.*</include>
                    <include>**.*</include>
                    <include>**/*.*</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
        ...
    </build>


#### application.yml配置内容
    #项目启动接口
    server:
      port: 9001
    #spring相关配置
    spring:
      application:
        name: springboot-mybatis
      profiles:
        active: dev # 多环境配置切换   与之对应的是application-dev.yml
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver #数据库驱动
        initialization-mode: always #加载项目中的数据文件
        schema: classpath:schema.sql # 加载数据库创建语句
        data: classpath:data.sql # 加载初始化数据
        type: com.zaxxer.hikari.HikariDataSource #数据库连接池
        hikari: # 数据库连接池相关信息配置
          minimum-idle: 5
          maximum-pool-size: 20
          auto-commit: true
          idle-timeout: 30000
          pool-name: DatebookHikariCP
          max-lifetime: 1800000
          connection-timeout: 30000
          connection-test-query: SELECT 1
    # mybatis配置
    mybatis:
      type-aliases-package: com.springboot.demo.repository.entity # 映射数据库表的实体类包
      mapper-locations: com/springboot/demo/repository/mapper/*Mapper.xml # 映射数据库SQL的xml包
    # 应用监控所有节点 actuator
    management:
      endpoints:
        web:
          exposure:
            include: '*'
      endpoint:
        health:
          show-details: always



####    application-dev.yml开发环境配置
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8  #数据库地址+库名
        username: root #数据库名称
        password: mysql #数据库密码
    # 日志打印级别
    logging:
      level:
        root: debug


#### mybatis分页插件配置 pom.xml引入
    <!-- mybatis分页插件-->
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper-spring-boot-starter</artifactId>
        <version>1.2.13</version>
    </dependency>

### application.xml配置
    # mybatis分页插件配置
    pagehelper:
      helper-dialect: mysql
      reasonable: true
      support-methods-arguments: true
      params: count=countSql


###批量执行
    @Configuration
    public class DataSourceConfig {
    
    
        @Autowired
        private SqlSessionFactory sqlSessionFactory;
    
        @Bean
        public SqlSessionTemplate sqlSessionTemplate() {
            return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
        }
    
        @Bean
        public SqlSession sqlSession() {
            return sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        }
    }
#### service中调用
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private SqlSession sqlSession;
    
    public Long batch() {
        //System.out.println(sqlSessionTemplate.getExecutorType());
        //SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        PersonDao personDao = sqlSession.getMapper(PersonDao.class);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Person person = new Person();
            person.setName("name-" + i)
                    .setAddress("成都-" + i)
                    .setAge(new Random(1).nextInt(99));
            personDao.insertSelective(person);
        }
        sqlSession.commit();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }


