package training.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

public class HelloServiceBean implements HelloService {

	public HelloServiceBean() {
		System.out.println("HelloServiceBean instantiated!");
	}

	@Async
	@Override
	public void sayHello() {
		System.out.println("Starting......Hello, world!");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		System.out.println("Finished - Hello, world!");
		new AsyncResult<Void>(null);
	}

}
