package EventRepository;

public class EventExecutor {
    public void EventExecutor(){
        for(Event e: EventLog.eLog){
            e.execute();
        }
    }
}
