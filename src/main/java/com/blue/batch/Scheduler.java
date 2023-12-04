package com.blue.batch;


import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {
	
	//@Scheduled(cron=" 0/30 * * * * * ")
	@Scheduled(cron=" 0 10 * * * * ")
	public void cronTabStat() {
		
		String ipInfo = serverIpInfo();
		
		if(ipInfo.equals("192.168.1.38")) {
        	
			System.out.println("배치미수행");
			
        	return;
        	
        }else {
        	
        	System.out.println("배치수행");
        	
        }

	}
	
	
	// 서버IP 조회
	public String serverIpInfo() {
		
		String serverIP = "";
		
		try{
			
	        InetAddress ownIP = InetAddress.getLocalHost();
	        serverIP = ownIP.getHostAddress();
	        
	        System.out.println("## serverIp :: "+serverIP);
	        
	    }catch(UnknownHostException e){
	    	
	        e.printStackTrace();
	        
	    }
		
		return serverIP;
	}
	
}
