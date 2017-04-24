package ch18.exam26;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecuteExample {
    public static void main(String[] args) throws IOException {
            //스레드풀 생성
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            
            long start = System.currentTimeMillis();
            long end = 0;
            //작업생성 코드
            for(int i=0; i<1000; i++){
            Runnable task = ()->{
                System.out.println(Thread.currentThread().getName()+"작업 처리");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ExecuteExample.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
            
            //작업큐에 넣기(작업처리 지시)
            executorService.submit(task);
            end = System.currentTimeMillis();
            System.out.println("1:"+(end-start)+" ms");
            }
            end = System.currentTimeMillis();
            System.out.println("2:"+(end-start)+" ms");
            //스레드풀 종료
            //System.in.read();
            executorService.shutdown();
            
    }
}
