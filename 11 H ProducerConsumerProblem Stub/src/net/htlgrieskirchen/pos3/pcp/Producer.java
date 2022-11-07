/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.ArrayList;
import java.util.List;

public class Producer /* implement this */ {
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
    
}
