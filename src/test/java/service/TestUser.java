package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.shizm.dao.IUserDao;
import com.shizm.hessian.service.UserService;
import com.shizm.service.IUserService;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestUser extends UnitTestBase {
	private IUserService userService;
	public TestUser(){
		super("classpath*:spring-*.xml");
	}
	@Test
	public void testUserCount(){
		System.out.println("ceshi");
		try {
			userService=super.getBean("userService");
			int count=userService.userCount();
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("2ceshi");
	}
	
}
