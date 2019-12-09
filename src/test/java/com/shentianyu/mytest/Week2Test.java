package com.shentianyu.mytest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shentianyu.domain.User;
import com.sty.utils.NumberUtils;
import com.sty.utils.StringUtils;
import com.sty.utils.UserUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class Week2Test {

	//注入redis模板
	@Autowired
	private RedisTemplate redisTemplate;
	//3.模拟生成5万条User对象（22分）
	@Test
	public void testUser() {
		for (int i = 1; i <= 50000; i++) {
			User user = new User();
			//(1)ID使用1-5万的顺序号。（2分）
			user.setId(i);
			//(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
			user.setName(StringUtils.getCharByN(3));
			//(3)性别在女和男两个值中随机。（4分）
			user.setGender(UserUtils.getSex());
			//(4)手机以13开头+9位随机数模拟。（4分）
			user.setPhoneNum("13" + NumberUtils.getLenNum(9));
			//(5)邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | 
			//@gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟。（4分）
			user.setEmail(UserUtils.getMail());
			//(6)生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
			user.setBirthday(UserUtils.getBirthday(1949, 2001));
			System.out.println(user);
		}
		
	}
	
	//4.使用JDK系列化方式保存5万个user随机对象到Redis，并计算耗时（18分）
	@Test
	public void testForJDK() {
		//记录开始的时间
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 50000; i++) {
			User user = new User();
			//(1)ID使用1-5万的顺序号。（2分）
			user.setId(i);
			//(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
			user.setName(StringUtils.getCharByN(3));
			//(3)性别在女和男两个值中随机。（4分）
			user.setGender(UserUtils.getSex());
			//(4)手机以13开头+9位随机数模拟。（4分）
			user.setPhoneNum("13" + NumberUtils.getLenNum(9));
			//(5)邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | 
			//@gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟。（4分）
			user.setEmail(UserUtils.getMail());
			//(6)生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
			user.setBirthday(UserUtils.getBirthday(1949, 2001));
			redisTemplate.opsForValue().set("user" + i, user);
		}
		//结束时间
		long end = System.currentTimeMillis();
		/*
		 * <property name="keySerializer" ref="stringRedisSerializer"></property> 
		 * <property name="valueSerializer" ref="jdkSerializationRedisSerializer"></property>
		 */
		System.out.println("本次的 序列化方式key为String序列化方式,value为jdk的序列化方式" );
		System.out.println("保存的数量为:5w");
		System.out.println("所消耗时间:" + (end - start) + "ms");
	}
	
	//5.使用JSON系列化方式保存5万个user随机对象到Redis，并计算耗时（18分）
		@Test
		public void testForJson() {
			//记录开始的时间
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 50000; i++) {
				User user = new User();
				//(1)ID使用1-5万的顺序号。（2分）
				user.setId(i);
				//(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
				user.setName(StringUtils.getCharByN(3));
				//(3)性别在女和男两个值中随机。（4分）
				user.setGender(UserUtils.getSex());
				//(4)手机以13开头+9位随机数模拟。（4分）
				user.setPhoneNum("13" + NumberUtils.getLenNum(9));
				//(5)邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | 
				//@gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟。（4分）
				user.setEmail(UserUtils.getMail());
				//(6)生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
				user.setBirthday(UserUtils.getBirthday(1949, 2001));
				redisTemplate.opsForValue().set("user" + i, user);
			}
			//结束时间
			long end = System.currentTimeMillis();
			/*
			 * <property name="keySerializer" ref="stringRedisSerializer"></property> 
			 * <property name="valueSerializer" ref="jdkSerializationRedisSerializer"></property>
			 */
			System.out.println("本次的 序列化方式key为String序列化方式,value为Json的序列化方式" );
			System.out.println("保存的数量为:5w");
			System.out.println("所消耗时间:" + (end - start) + "ms");
		}
	

		
		
		//5.使用JSON系列化方式保存5万个user随机对象到Redis，并计算耗时（18分）
		@Test
		public void testForHash() {
			//记录开始的时间
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 50000; i++) {
				User user = new User();
				//(1)ID使用1-5万的顺序号。（2分）
				user.setId(i);
				//(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
				user.setName(StringUtils.getCharByN(3));
				//(3)性别在女和男两个值中随机。（4分）
				user.setGender(UserUtils.getSex());
				//(4)手机以13开头+9位随机数模拟。（4分）
				user.setPhoneNum("13" + NumberUtils.getLenNum(9));
				//(5)邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | 
				//@gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟。（4分）
				user.setEmail(UserUtils.getMail());
				//(6)生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
				user.setBirthday(UserUtils.getBirthday(1949, 2001));
				redisTemplate.opsForHash().put("users" + i, "user" + i, user.toString());
			}
			//结束时间
			long end = System.currentTimeMillis();
			/*
			 <property name="keySerializer" ref="stringRedisSerializer"></property>
			<property name="valueSerializer" ref="jackson2JsonRedisSerializer"></property>
			 */
			System.out.println("本次的 序列化方式Hashkey为String序列化方式,HashValue为String的序列化方式" );
			System.out.println("保存的数量为:5w");
			System.out.println("所消耗时间:" + (end - start) + "ms");
		
		}
			
		
		
		
	
	
	public static void main(String[] args) {
		System.out.println(UserUtils.getMail());
	}
	
	
}





























