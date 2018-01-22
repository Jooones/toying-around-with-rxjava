package excecise3_1;

import java.time.Duration;
import java.time.LocalTime;

import static excecise3_1.EventScheduler.*;
import static java.time.LocalTime.now;

public class Event {


    private final LocalTime plannedTime;

    public Event(LocalTime schedulingTime) {

        this.plannedTime = schedulingTime;
    }

    public LocalTime getPlannedTime() {
        return plannedTime;
    }

    public void executeEvent() {
        if(plannedTime.isAfter(now())) {
            throw new IllegalStateException("Event planned for: " + plannedTime + " scheduled at: " + now() + ", " +Duration.between(now(), plannedTime) + " too early");
        } if(plannedTime.isBefore((now().minusSeconds(TOLERANCE_IN_SECONDS)))){
            throw new IllegalStateException("Event scheduled" + Duration.between(plannedTime, now().minusSeconds(TOLERANCE_IN_SECONDS)) + " too late");
        }
    }
}
