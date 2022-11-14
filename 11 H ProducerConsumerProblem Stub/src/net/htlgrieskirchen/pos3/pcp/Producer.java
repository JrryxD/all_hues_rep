/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.ArrayList;
import java.util.List;

public class Producer /* implement this */ implements Runnable {
    private final String name;
    private final Storage storage;
    private final int sleepTime;
    
    private final List<Integer> sent;
    private final int numberOfItems;
    
    public Producer(String name, Storage storage, int sleepTime, int numberOfItems) {
       // implement this
        this.name = name;
        this.storage = storage;
        this.sleepTime = sleepTime;
        this.numberOfItems = numberOfItems;
        sent = new ArrayList<>();

    }
 
    // implement this


    public List<Integer> getSent() {
        // implement this
        return sent;
    }

    @Override
    public void run() {
        synchronized (Storage.class) {
            for (int i = 0; i <= numberOfItems; i++) {
                try {
                    if (storage.put(i)) sent.add(i);
                    else
                    {
                            Thread.sleep(sleepTime);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            storage.setProductionComplete();
        }
    }
}
