package top.zhengchunxu.test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

import com.sun.org.apache.bcel.internal.generic.NEW;

import org.apache.zookeeper.ZooKeeper;

public class test {
	
	/** zookeeper地址 */
	static final String CONNECT_ADDR = "localhost:2181";
	/** session超时时间 */
	static final int SESSION_OUTTIME = 5000;//ms 
	/** 信号量，阻塞程序执行，用于等待zookeeper连接成功，发送成功信号 */
	static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

	
   private static  Watcher watcher=new Watcher() {
	
	@Override
	public void process(WatchedEvent event) {
		//获取事件的状态
		KeeperState keeperState= event.getState();
		//获取事件的类型
		EventType eventType= event.getType();
		//如果是建立连接（SyncConnected表示连接成功的状态）
		if (KeeperState.SyncConnected==keeperState) {
			if (EventType.None==eventType) {
				//如果建立连接成功，则发送信号量，让后续阻塞程序向下执行
				connectedSemaphore.countDown();
				 System.out.println("zk 建立连接");
			}
		} 		
	}
};

   public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
	
	   ZooKeeper zKeeper=new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, watcher);
	   
	   connectedSemaphore.await();
	   
/**
 * /testRoot/zhong0000000005
 * 增删改查
 * */
	   /*String result=zKeeper.create("/testRoot/zhong", "zhong".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
	   System.out.println(result);*/
	   
	   List<String> list=zKeeper.getChildren("/testRoot", false);
	   Collections.sort(list);
	   System.out.println(list);
	  
	   
	   zKeeper.close();
}

}

