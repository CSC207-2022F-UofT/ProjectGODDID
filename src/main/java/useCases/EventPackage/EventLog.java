package useCases.EventPackage;

import java.util.ArrayList;

public class EventLog {
    /**
     * Dataclass to keep track of all the events that have taken place since the program began.
     * No other functional purpose, other than to keep track of the flow of events.
     */
    public ArrayList<Event> events;

    public EventLog(){
        events = new ArrayList<Event>();
    }

}
