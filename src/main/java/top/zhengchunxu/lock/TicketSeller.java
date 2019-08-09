package top.zhengchunxu.lock;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;

public class TicketSeller {

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
	 
	    public void sellTicketWithLock() throws KeeperException, InterruptedException, IOException {
	        LockSample lock = new LockSample();
	        lock.acquireLock();
	        sell();
	        lock.releaseLock();
	    }
	 
	    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
	        TicketSeller ticketSeller = new TicketSeller();
	        for(int i=0;i<50;i++){
	            ticketSeller.sellTicketWithLock();
	 
	        }
	    }

}
