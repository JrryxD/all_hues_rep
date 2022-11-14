/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.concurrent.ArrayBlockingQueue;

public class Storage { 
    private final ArrayBlockingQueue<Integer> queue;
    
    private int fetchedCounter;
    private int storedCounter;
    private int underflowCounter;
    private int overflowCounter;
    private boolean productionComplete;
    
    public Storage() {
        // implement this
        queue = new ArrayBlockingQueue<>(10);
        fetchedCounter = 0;
        storedCounter = 0;
        underflowCounter = 0;
        overflowCounter = 0;
        productionComplete = false;

    }
    
    public synchronized boolean put(Integer data) throws InterruptedException {
        // implement this
        boolean res = false;
        if (queue.offer(data))
        {
            storedCounter++;
            res = true;
        }
        else
        {
        overflowCounter++;
        }
        return res;
    }
 
    public synchronized Integer get() {
        // implement this
        Integer res = null;

        if (queue.poll() != null)
        {
            fetchedCounter++;
            res = queue.poll();
        }
        else
        {
            underflowCounter++;
        }
        return res;
    }

    public boolean isProductionComplete() {
        // implement this
        return productionComplete;
    }

    public void setProductionComplete() {
        // implement this
        productionComplete = true;
    }

    public int getFetchedCounter() {
        // implement this
        return fetchedCounter;
    }

    public int getStoredCounter() {
        // implement this
        return storedCounter;
    }

    public int getUnderflowCounter() {
        // implement this
        return underflowCounter;
    }

    public int getOverflowCounter() {
        // implement this
        return overflowCounter;
    }
}
