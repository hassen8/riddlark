package Riddlark;

import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

abstract class WaitableTimerTask extends TimerTask {

    private final CountDownLatch latch;
    private PrintWriter out;

    WaitableTimerTask(PrintWriter out, CountDownLatch latch) {
        super();
        this.out = out;
        this.latch = latch;

    }

}
