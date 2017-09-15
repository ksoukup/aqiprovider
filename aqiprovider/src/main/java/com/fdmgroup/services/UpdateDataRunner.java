package com.fdmgroup.services;

import java.util.Timer;
import java.util.TimerTask;

public class UpdateDataRunner {
	
	public void UpdateDataRunner(){

		
	}
	
	public static void start(){
		Timer timer = new Timer();
		TimerTask hourlyTask = new TimerTask(){
			@Override
			public void run(){
				UpdateDataService.updateAllData();
			}
		};
		timer.schedule(hourlyTask, 01, 1000*60*60);
	}

}
