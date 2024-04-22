package me.mathanalysis.it.uhc.utils.workload;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Deque;

public class BlockLoadRunnable implements Runnable {

    private static final double MAX_MILLIS_PER_TICK = 45;
    private static final int MAX_NANOS_PER_TICK = (int) (MAX_MILLIS_PER_TICK * 1E6);

    @Getter private boolean completed;

    private final Deque<Workload> workloadDeque = new ArrayDeque<>();

    public void addWorkload(Workload workload) {
        this.workloadDeque.add(workload);
        completed = false;
    }

    @Override
    public void run() {
        long stopTime = System.nanoTime() + MAX_NANOS_PER_TICK;

        Workload nextLoad;

        // Note: Don't permute the conditions because sometimes the time will be over but the queue will still be polled then.
        while (System.nanoTime() <= stopTime && (nextLoad = this.workloadDeque.poll()) != null) {
            nextLoad.compute();
        }

        if(workloadDeque.isEmpty() && !completed) {
            this.completed = true;

            //Border Shrink Event
        }
    }

}