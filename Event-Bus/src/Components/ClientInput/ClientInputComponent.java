package Components.ClientInput;

import Common.EventPackage.Event;
import Common.EventPackage.EventBusUtil;
import Common.EventPackage.EventId;
import Common.EventPackage.EventQueue;

class ClientInputComponent extends Thread {
	private EventBusUtil eventBusInterface = null;
	private long componentId;
	boolean registered = true;
	
	/**
	 * Constructor. 
	 */
	public ClientInputComponent() {
	    try {
	        eventBusInterface = new EventBusUtil();
	    } catch(Exception e) {
	        e.printStackTrace();
	        registered = false;
	    }
	    this.componentId = eventBusInterface.getComponentId();
	}
	
	public void run() {
	    Event event = null;
	    EventQueue eventQueue = null;
	    boolean done = false;
	    
	    if(eventBusInterface != null) {
	        while(!done) {
	            try {
	                eventQueue = eventBusInterface.getEventQueue();
	            } catch(Exception e) {
	                e.printStackTrace();
	            }
	            
	            int queueSize = eventQueue.getSize();
	            
	            for(int i = 0; i < queueSize; i++) {
	                event = eventQueue.getEvent();
	                if(event.getEventId() == EventId.QuitTheSystem) {
	                    done = true;
	                }
	            }
	        }
	    } else {
	        System.out.println("Unable to register this component");
	    }
	}
	
	public void sendClientInput(EventId sentEventId, String sentMessage) {
	    Event newEvent = new Event(sentEventId, sentMessage);
        try {
            eventBusInterface.sendEvent(newEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public long getComponentId() {
		return this.componentId;
	}
} 