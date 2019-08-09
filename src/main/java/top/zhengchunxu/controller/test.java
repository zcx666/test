package top.zhengchunxu.controller;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.zhengchunxu.lock.LockSample;
import top.zhengchunxu.orderId.OrderIdGenerator;

@RestController
public class test {
	
	@Autowired
	private OrderIdGenerator orderIdGenerator;
	
	/*@Autowired
	private LockSample lockSample;*/

	
	@RequestMapping("/getOrderId")
	public void zhong() {
		
		System.out.println("生成的订单号是："+orderIdGenerator.getOrder12());
	}
	
	@RequestMapping("/getOrderId1")
	public void zhong1() throws InterruptedException, KeeperException, IOException  {
		
		LockSample lockSample=new LockSample();
		
		ExecutorService threadPool = Executors.newFixedThreadPool(50);
		threadPool.submit(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					lockSample.acquireLock();
					sell();
			        lockSample.releaseLock();
			        
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
			}
		});
		
		threadPool.shutdown();
        /*new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					lockSample.acquireLock();
					sell();
			        lockSample.releaseLock();
			        
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}									
				
			}
		}).start();*/
	}
	
	private void sell(){
        System.out.println("售票开始");
        // 线程随机休眠数毫秒，模拟现实中的费时操作
       /* int sleepMillis = (int) (Math.random() * 2000);
        try {
            //代表复杂逻辑执行了一段时间
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("售票结束");
    }
}
