package service;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

public class UnitTestBase {
	private String springXmlPath="";
	private ClassPathXmlApplicationContext context;
	
	public UnitTestBase(){}
	public UnitTestBase(String springXmlPath){
		this.springXmlPath=springXmlPath;
	}
	@Before
	public void before() {
		if(StringUtils.isEmpty(this.springXmlPath)){
			this.springXmlPath="classpath*:spring-*.xml";
		}
		context=new ClassPathXmlApplicationContext(this.springXmlPath.split("[,\\s]"));
		context.start();
	}
	@After
	public void after(){
		context.destroy();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T getBean(String beanid){
		return (T)context.getBean(beanid);
	}
	
	public <T extends Object> T getBean(Class<T> clazz){
		return (T)context.getBean(clazz);
	}
	
	
}
