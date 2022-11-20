package EventPackage;

import PointsSystem.PointsSystem;
import entities.User;
import java.util.ArrayList;

public class Event {
    /*
    Dataclass
     */
    private String eventType; //event type to be accounted for when chat ends; when chat ends, Event object initialized with explicit String
    // eventType can be one of ChatEnd, GameChatEnd, Spend

    // executing the event on the spot, not really the need for an event log or the executor is there?
    private ArrayList<User> usersInvolved;
    private boolean pointsAccounted;

    public Event(String eventType, ArrayList<User> usersInvolved, boolean pointsAccounted){
        this.eventType = eventType;
        this.usersInvolved = usersInvolved;
        this.pointsAccounted = pointsAccounted;
    }

    public void execute(PointsSystem ps){

        User user1 = usersInvolved.get(0);
        User user2 = usersInvolved.get(1);


        if(!this.eventType.contains("Spend")){
            ps.PointRenewer(user1, "ChatEnd");
            ps.PointRenewer(user2, "ChatEnd");

            if (this.eventType == "GameChatEnd") {
                ps.PointRenewer(user1, "GameChatEnd");
                ps.PointRenewer(user2, "GameChatEnd");
            }
        }
        else if (this.eventType.contains("Spend")){
            User userx = usersInvolved.get(0);
            ps.PointSpender(userx, this.eventType);
        }
        }

    }
