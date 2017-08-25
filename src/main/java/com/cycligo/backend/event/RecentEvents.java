package com.cycligo.backend.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mindaugas Urbontaitis on 25/01/2017.
 * cycligo-backend
 */
public class RecentEvents {
    private List<RecentEvent> recentEvents;

    public List<RecentEvent> getRecentEvents() {
        if (recentEvents == null) {
            recentEvents = new ArrayList<>();
        }
        return recentEvents;
    }
}
