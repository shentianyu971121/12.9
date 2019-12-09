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

	//ע��redisģ��
	@Autowired
	private RedisTemplate redisTemplate;
	//3.ģ������5����User����22�֣�
	@Test
	public void testUser() {
		for (int i = 1; i <= 50000; i++) {
			User user = new User();
			//(1)IDʹ��1-5���˳��š���2�֣�
			user.setId(i);
			//(2)����ʹ��3���������ģ�⣬����ʹ����ǰ�Լ���д�Ĺ��߷�������4�֣�
			user.setName(StringUtils.getCharByN(3));
			//(3)�Ա���Ů��������ֵ���������4�֣�
			user.setGender(UserUtils.getSex());
			//(4)�ֻ���13��ͷ+9λ�����ģ�⡣��4�֣�
			user.setPhoneNum("13" + NumberUtils.getLenNum(9));
			//(5)������3-20�������ĸ + @qq.com  | @163.com | @sian.com | 
			//@gmail.com | @sohu.com | @hotmail.com | @foxmail.comģ�⡣��4�֣�
			user.setEmail(UserUtils.getMail());
			//(6)����Ҫģ��18-70��֮�䣬�����ڴ�1949�굽2001��֮�䡣��4�֣�
			user.setBirthday(UserUtils.getBirthday(1949, 2001));
			System.out.println(user);
		}
		
	}
	
	//4.ʹ��JDKϵ�л���ʽ����5���user�������Redis���������ʱ��18�֣�
	@Test
	public void testForJDK() {
		//��¼��ʼ��ʱ��
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 50000; i++) {
			User user = new User();
			//(1)IDʹ��1-5���˳��š���2�֣�
			user.setId(i);
			//(2)����ʹ��3���������ģ�⣬����ʹ����ǰ�Լ���д�Ĺ��߷�������4�֣�
			user.setName(StringUtils.getCharByN(3));
			//(3)�Ա���Ů��������ֵ���������4�֣�
			user.setGender(UserUtils.getSex());
			//(4)�ֻ���13��ͷ+9λ�����ģ�⡣��4�֣�
			user.setPhoneNum("13" + NumberUtils.getLenNum(9));
			//(5)������3-20�������ĸ + @qq.com  | @163.com | @sian.com | 
			//@gmail.com | @sohu.com | @hotmail.com | @foxmail.comģ�⡣��4�֣�
			user.setEmail(UserUtils.getMail());
			//(6)����Ҫģ��18-70��֮�䣬�����ڴ�1949�굽2001��֮�䡣��4�֣�
			user.setBirthday(UserUtils.getBirthday(1949, 2001));
			redisTemplate.opsForValue().set("user" + i, user);
		}
		//����ʱ��
		long end = System.currentTimeMillis();
		/*
		 * <property name="keySerializer" ref="stringRedisSerializer"></property> 
		 * <property name="valueSerializer" ref="jdkSerializationRedisSerializer"></property>
		 */
		System.out.println("���ε� ���л���ʽkeyΪString���л���ʽ,valueΪjdk�����л���ʽ" );
		System.out.println("���������Ϊ:5w");
		System.out.println("������ʱ��:" + (end - start) + "ms");
	}
	
	//5.ʹ��JSONϵ�л���ʽ����5���user�������Redis���������ʱ��18�֣�
		@Test
		public void testForJson() {
			//��¼��ʼ��ʱ��
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 50000; i++) {
				User user = new User();
				//(1)IDʹ��1-5���˳��š���2�֣�
				user.setId(i);
				//(2)����ʹ��3���������ģ�⣬����ʹ����ǰ�Լ���д�Ĺ��߷�������4�֣�
				user.setName(StringUtils.getCharByN(3));
				//(3)�Ա���Ů��������ֵ���������4�֣�
				user.setGender(UserUtils.getSex());
				//(4)�ֻ���13��ͷ+9λ�����ģ�⡣��4�֣�
				user.setPhoneNum("13" + NumberUtils.getLenNum(9));
				//(5)������3-20�������ĸ + @qq.com  | @163.com | @sian.com | 
				//@gmail.com | @sohu.com | @hotmail.com | @foxmail.comģ�⡣��4�֣�
				user.setEmail(UserUtils.getMail());
				//(6)����Ҫģ��18-70��֮�䣬�����ڴ�1949�굽2001��֮�䡣��4�֣�
				user.setBirthday(UserUtils.getBirthday(1949, 2001));
				redisTemplate.opsForValue().set("user" + i, user);
			}
			//����ʱ��
			long end = System.currentTimeMillis();
			/*
			 * <property name="keySerializer" ref="stringRedisSerializer"></property> 
			 * <property name="valueSerializer" ref="jdkSerializationRedisSerializer"></property>
			 */
			System.out.println("���ε� ���л���ʽkeyΪString���л���ʽ,valueΪJson�����л���ʽ" );
			System.out.println("���������Ϊ:5w");
			System.out.println("������ʱ��:" + (end - start) + "ms");
		}
	

		
		
		//5.ʹ��JSONϵ�л���ʽ����5���user�������Redis���������ʱ��18�֣�
		@Test
		public void testForHash() {
			//��¼��ʼ��ʱ��
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 50000; i++) {
				User user = new User();
				//(1)IDʹ��1-5���˳��š���2�֣�
				user.setId(i);
				//(2)����ʹ��3���������ģ�⣬����ʹ����ǰ�Լ���д�Ĺ��߷�������4�֣�
				user.setName(StringUtils.getCharByN(3));
				//(3)�Ա���Ů��������ֵ���������4�֣�
				user.setGender(UserUtils.getSex());
				//(4)�ֻ���13��ͷ+9λ�����ģ�⡣��4�֣�
				user.setPhoneNum("13" + NumberUtils.getLenNum(9));
				//(5)������3-20�������ĸ + @qq.com  | @163.com | @sian.com | 
				//@gmail.com | @sohu.com | @hotmail.com | @foxmail.comģ�⡣��4�֣�
				user.setEmail(UserUtils.getMail());
				//(6)����Ҫģ��18-70��֮�䣬�����ڴ�1949�굽2001��֮�䡣��4�֣�
				user.setBirthday(UserUtils.getBirthday(1949, 2001));
				redisTemplate.opsForHash().put("users" + i, "user" + i, user.toString());
			}
			//����ʱ��
			long end = System.currentTimeMillis();
			/*
			 <property name="keySerializer" ref="stringRedisSerializer"></property>
			<property name="valueSerializer" ref="jackson2JsonRedisSerializer"></property>
			 */
			System.out.println("���ε� ���л���ʽHashkeyΪString���л���ʽ,HashValueΪString�����л���ʽ" );
			System.out.println("���������Ϊ:5w");
			System.out.println("������ʱ��:" + (end - start) + "ms");
		
		}
			
		
		
		
	
	
	public static void main(String[] args) {
		System.out.println(UserUtils.getMail());
	}
	
	
}





























