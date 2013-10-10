package test;

import java.util.Scanner;

import zll.weibo4sa.api.Observer;
import zll.weibo4sa.components.Counter;
import zll.weibo4sa.components.Logger;
import zll.weibo4sa.model.User;
import zll.weibo4sa.model.WeiboItem;
import zll.weibo4sa.model.WeiboManager;


public class TestCase1 {
	static Scanner sc;
	static int op  = 1;
//		sc = new Scanner(System.in);
//		
//		WeiboManager m = new WeiboManager();
//		Observer mLogger = new Logger(); 
//		Observer mCounter = new Counter();
//		
//		m.registerObserver(mLogger);
//		m.registerObserver(mCounter);
//		User u = new User();
//		u.setID(2);
//		u.setName("liyang");
//		
//		System.out.println("---------------------------------------");
//		System.out.println("              weibo4sa 1.0           ");
//		System.out.println("       http://octsky.com/weibo4sa/     ");
//		System.out.println("---------------------------------------");
//		System.out.println("1.Next  2.Create  3.Delete  0.Quit     ");
//		System.out.println("---------------------------------------");
//		
//		while(op != 0){
//			
//			op = sc.nextInt();
//			sc.nextLine();
//			if(1 == op){
//				readNext(m,true);
//
//			}else if(2 == op){
//				create(m);
//				
//			}		
//		}
//		
//		System.out.println("---------------------------------------");
//		System.out.println("              weibo4sa 1.0           ");
//		System.out.println("       http://octsky.com/weibo4sa/     ");
//		System.out.println("                ByeBye                 ");
//		System.out.println("---------------------------------------");
//		
//
//	}
//	
//	public static void readNext(WeiboManager m,boolean order){
//		WeiboItem item = m.readNext(order);
//		if(item != null){
//			System.out.println("---------------------------------------");
//			System.out.println("@"+item.getAuthor()+" : "+item.getContent()+" "+item.getDate()+" 阅读次数: "+item.getReads());
//			System.out.println("---------------------------------------");
//			System.out.println("1.Next  2.Create  3.Delete 4.Update 0.Quit");	
//			System.out.println("---------------------------------------");
//			
//			op = sc.nextInt();
//			sc.nextLine();
//			
//			if(op == 3){
//				delete(m,item);
//			}else if(op ==1){
//				readNext(m,true);
//			}else if(op == 2){
//				create(m);
//			}else if(op == 4){
//				update(m,item);
//			}
//		}else{
//			System.out.println("---------------------------------------");
//			System.out.println("已经没有更"+(order?"旧":"新")+"的微博");
//			System.out.println("---------------------------------------");
//			System.out.println("1.Next  2.Create   0.Quit         ");	
//			System.out.println("---------------------------------------");
//		}
//	
//	}
//	
//	public static void create(WeiboManager m){
//		System.out.println("---------------------------------");
//		System.out.println("请输入微博内容:");
//		String content =sc.nextLine();
//		//System.out.println("请输入作者姓名:");
//		//sString author =sc.nextLine();
//	
//		m.createWeibo(content, user);
//		
//		System.out.println("---------------------------------");
//		System.out.println("成功发布微博");
//		System.out.println("1.Next  2.Create  0.Quit         ");	
//	}
//	
//	public static void delete(WeiboManager m,WeiboItem item){
//		m.deleteWeibo(item);
//		System.out.println("---------------------------------------");
//		System.out.println("已经删除微博-_-");
//		System.out.println("---------------------------------------");
//		System.out.println("1.Next  2.Create   0.Quit         ");	
//		System.out.println("---------------------------------------");
//	}
//	
//	public static void update(WeiboManager m,WeiboItem item){
//	
//		System.out.println("---------------------------------");
//		System.out.println("请输入新的微博内容:");
//		String content =sc.nextLine();
//	
//		item.setContent(content);
//		
//		m.updateWeibo(item);
//		System.out.println("---------------------------------------");
//		System.out.println("微博已经更新");
//		System.out.println("---------------------------------------");
//		System.out.println("1.Next  2.Create   0.Quit         ");	
//		System.out.println("---------------------------------------");
//	}
//	
	
}
