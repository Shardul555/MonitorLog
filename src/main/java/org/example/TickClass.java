package org.example;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

//public class tickClass implements Runnable {
//    private File file;
//    private Long tickInterval;
//    public tickClass(String path, long tickInterval) {
//        this.file = new File(path);
//        this.tickInterval = tickInterval;
//    }
//    public void solve() {
//
//    }
//    @Override
//    public void run() {
//        Long lastModified=0L;
//        Long prevLength=0L;
//        while(true) {
//            Long currLastModified = this.file.lastModified();
//            long currLength = this.file.length();
//         //   System.out.println(currLastModified + " Coming here " + lastModified + " " + currLength);
//            if(!currLastModified.equals(lastModified)) {
//                try {
//                    ReverseInputStream reverseInputStream = new ReverseInputStream(this.file, prevLength);
//                    reverseInputStream.pushLastLines();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                lastModified = currLastModified;
//                prevLength = currLength;
//            }
//            try {
//                Thread.sleep(tickInterval);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}


public class TickClass implements Runnable{
    private final File file;
    private final Long tickInterval;
    public String currentLogs;
    public TickClass(String path, long tickInterval, String currentLogs) {
        this.file = new File(path);
        this.tickInterval = tickInterval;
        this.currentLogs = currentLogs;
    }

    @Override
    public void run() {
        Long lastModified=0L;
        Long prevLength=0L;
        while(true) {
            Long currLastModified = this.file.lastModified();
            long currLength = this.file.length();
            //   System.out.println(currLastModified + " Coming here " + lastModified + " " + currLength);
            if(!currLastModified.equals(lastModified)) {
                try {
                    ReverseInputStream reverseInputStream = new ReverseInputStream(this.file, prevLength);
                    lastModified = currLastModified;
                    prevLength = currLength;
                    currentLogs = reverseInputStream.pushLastLines();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(tickInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
