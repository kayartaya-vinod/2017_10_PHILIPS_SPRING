package training.programs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import training.service.HelloService;

public class P01_SpringAsFactory {

	public static void main(String[] args) {
		HelloService service;

		// A reference of a Spring container (like java.util.List)
		ApplicationContext ctx;

		// one of the types of spring container (like java.util.ArrayList)
		System.out.println("Creating a spring container...");
		ctx = new ClassPathXmlApplicationContext("context1.xml");
		System.out.println("Spring container created!");

		service = ctx.getBean("english", HelloService.class);
		
		System.out.println("Before English Hello!");
		service.sayHello();
		System.out.println("After English Hello!");
		
		HelloService service1 = ctx.getBean("kannada", HelloService.class);
		HelloService service2 = ctx.getBean("shs", HelloService.class);
		
		service1.sayHello();
		service2.sayHello();
		

		System.out.println("service==service1 is " + (service == service1));
		System.out.println("service==service2 is " + (service == service2));
		System.out.println("service2==service1 is " + (service2 == service1));

		((AbstractApplicationContext) ctx).close();
	}
}
