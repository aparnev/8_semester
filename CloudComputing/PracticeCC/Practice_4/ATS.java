package com.aparnev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ATS {
    public HashMap<String, String> ongoingCalls  = new HashMap<String, String>();
    public HashMap<String, ArrayList<String>> waitingCalls = new HashMap<String, ArrayList<String>>();
    
    public void startCall(String callerNumber, String recipientNumber) {
    	System.out.println("there was a call from "+callerNumber + " to "+ recipientNumber);
    	if (this.ongoingCalls.containsKey(recipientNumber)) {
    		this.addInWait(callerNumber, recipientNumber);	
        } 
    	else {
    		System.out.println(callerNumber + " started call to " + recipientNumber);
    		this.call(callerNumber, recipientNumber);
    	}
    }      
    public void endCall(String callerNumber, String recipientNumber) {
    	this.ongoingCalls.remove(recipientNumber);
        System.out.println(callerNumber + " ended call with " + recipientNumber);
        if ((this.waitingCalls.containsKey(recipientNumber))&&(this.waitingCalls.get(recipientNumber).size()>0)) { 	
        	int len = this.waitingCalls.get(recipientNumber).size()-1;
        	int rnd = (int)(Math.random() * len);
        	String newCallerNumber = this.waitingCalls.get(recipientNumber).get(rnd);
            this.waitingCalls.get(recipientNumber).remove(rnd);
            this.startCall(newCallerNumber, recipientNumber);
        }
    }
    public void addInWait(String callerNumber, String recipientNumber) {
    	ArrayList<String> callers;
		if (this.waitingCalls.containsKey(recipientNumber)) callers = this.waitingCalls.get(recipientNumber);
		else callers = new ArrayList<String>();
		callers.add(callerNumber);
        this.waitingCalls.put(recipientNumber, callers);
		System.out.println("number "+callerNumber + " await");
    }
    
    public void call(String callerNumber, String recipientNumber) {
    	this.ongoingCalls.put(recipientNumber, callerNumber);
    	ReentrantLock lock = new ReentrantLock();
    	Runnable phoneCall = () -> {
            try {
                lock.lock();
                Thread.sleep(3000);
                this.endCall(callerNumber, recipientNumber);
                lock.unlock();
            } 
            catch (InterruptedException e) {}
        };
        Thread thread = new Thread(phoneCall);
        thread.start();
    }
}
