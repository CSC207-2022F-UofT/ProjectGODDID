package EventRepository;

import java.util.ArrayList;

public class EventLog {
    /*
    Fetches events that result in point changes for users, passes them onto the PointSystem to be executed.
     */

    /*
    T is an object for an event that contains:
    1. what type of event T is (chat end only, chat end with games)
    2. the users involved in the event
    3. whether the point changes for this event have been accounted for (boolean)
     */
    public static ArrayList<Event> eLog;

    public void EventLog(){
        eLog = new ArrayList<Event>();
    }

    public ArrayList<Event> eventSummary(){
        return eLog;
    }
}
