/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.ArrayList;
import java.util.List;


public class Consumer /* implement this */ implements Runnable{
    private final String name;
    private final Storage storage;
    private final int sleepTime;
    
    private final List<Integer> received;
    private boolean running = true;
    
    public Consumer(String name, Storage storage, int sleepTime) {
        // implement this
        this.name = name;
        this.storage = storage;
        this.sleepTime = sleepTime;
        received = new ArrayList<>();
    }
 
    // implement this

    public List<Integer> getReceived() {
        // implement this
        return received;
    }

    @Override
    public void run() {

        synchronized (Storage.class)
        {
        while (storage.isProductionComplete() && running)
        {
            Integer pro = storage.get();
            if (pro != null)
            {
                received.add(pro);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else
            {

                break;
            }

        }
        }
    }
}

