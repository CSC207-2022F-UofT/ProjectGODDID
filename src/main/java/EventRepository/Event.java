package EventRepository;

import PointsSystem.PointsSystem;
import entities.User;
import java.util.ArrayList;

public class Event {
    /*
    Dataclass
     */
    private String eventType; //event type to be accounted for when chat ends; when chat ends, Event object initialized with explicit String
    // eventType can be one of ChatEnd, GameChatEnd, Spend
    private ArrayList<User> usersInvolved;
    private boolean pointsAccounted;

    public Event(String eventType, ArrayList<User> usersInvolved, boolean pointsAccounted){
        this.eventType = eventType;
        this.usersInvolved = usersInvolved;
        this.pointsAccounted = pointsAccounted;
    }

    public void execute(){
        if (!pointsAccounted){
            if(this.eventType != "Spend") {
                User user1 = usersInvolved.get(0);
                User user2 = usersInvolved.get(1);
                PointsSystem.PointRenewer(user1, "ChatEnd");
                PointsSystem.PointRenewer(user2, "ChatEnd");

                if (this.eventType == "GameChatEnd") {
                    PointsSystem.PointRenewer(user1, "GameChatEnd");
                    PointsSystem.PointRenewer(user2, "GameChatEnd");
                }
            }

            else if (this.eventType.contains("Spend")){
                User user1 = usersInvolved.get(0);
                PointsSystem.PointSpender(user1, this.eventType);
            }
        }

    }
}
