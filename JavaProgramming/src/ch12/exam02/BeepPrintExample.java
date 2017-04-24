package ch12.exam02;

import java.awt.Toolkit;

public class BeepPrintExample {

	public static void main(String[] args) {
		/*
		BeepThread bpth = new BeepThread();*/
		Thread bpth = new Thread(){
			@Override
			public void run() {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				for(int i=0;i<5;i++){
					toolkit.beep();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						
					}
				}
		}};
		bpth.start();
		
		for(int i=0;i<5;i++){
			System.out.print("¶ò ");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				
			}
		}

	}

}
