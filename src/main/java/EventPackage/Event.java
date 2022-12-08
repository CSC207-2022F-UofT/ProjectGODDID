package EventPackage;

import PointSystem.PointSystem;
import entities.User;

import java.io.IOException;
import java.util.ArrayList;

public class Event {
    /**
     * Dataclass to act as an interface between the UI Controllers and PointSystem.
     * Everytime an event involving spending/renewing points occurs, an Event object is created. This Event object contains
     * the users involved in the event and the type of Event (Spending/Renewing and subtypes thereof).
     * Executing this instance of the Event class calls the PointSystem and makes the requisite changes to the points of
     * the users involved .
     */
    private String eventType;
    // eventType can be one of ChatEnd, GameChatEnd, SpendSkip, SpendChoose or SpendExtend

    // executing the event on the spot, eliminates the need for event log or loop/index-based executor
    private ArrayList<User> usersInvolved;

    public Event(String eventType, ArrayList<User> usersInvolved){
        this.eventType = eventType;
        this.usersInvolved = usersInvolved;
    }

    public void execute(PointSystem ps) throws IOException {
        /*
        Polymorphic function, handles execution of both Spend-type and Renew-type events
        Argument is strictly an instance of either: PointSystemS or PointSystemR.
         */

        if (this.usersInvolved.size() == 2){  //two users involved implies a chat end, i.e., Renew-type event.
            User user1 = usersInvolved.get(0);
            User user2 = usersInvolved.get(1);

            ps.adjustPoints(user1, this.eventType);
            ps.adjustPoints(user2, this.eventType);
        }


        else if (this.usersInvolved.size() == 1){ //only one user involved implies Spend-type event
            User userx = usersInvolved.get(0);
            ps.adjustPoints(userx, this.eventType);
        }
        }

    }
