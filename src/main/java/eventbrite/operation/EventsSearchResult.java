package eventbrite.operation;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import eventbrite.model.Event;

/**
 * Represents the result from a request to the events/search API.
 *
 * @Author: yummin Date: 15/11/6
 */

public class EventsSearchResult {
    private String json;

    public EventsSearchResult(String json) {
        this.json = json;
    }

    /**
     * Get List<Event> from EventsSearchResult
     *
     * @return
     */
    public List<Event> getEvents() {
        JSONObject result = new JSONObject(json);
        JSONArray es = result.getJSONArray("events");
        ArrayList<Event> events = new ArrayList<Event>();
        for (int i = 0, size = es.length(); i < size; i++) {
            JSONObject e = es.getJSONObject(i);
            Event tmp = new Event();
            events.add(tmp.deserialize(e));
        }
        return events;
    }

    /**
     * Searialize events of search result
     *
     * @return
     */
    public String serialize() {
        // array to store events of search result
        JSONArray eventList = new JSONArray();
        // search result
        JSONObject result = new JSONObject(json);
        JSONArray es = result.getJSONArray("events");
        // Loop events
        for (int i = 0, size = es.length(); i < size; i++) {
            JSONObject e = es.getJSONObject(i);
            Event tmp = new Event();
            tmp.deserialize(e);
            // Only store attributes we want
            eventList.put(tmp.extractAttributes());
        }
        // Wrap json array with key 'records'
        JSONObject ret = new JSONObject();
        ret.putOnce("records", eventList);
        return ret.toString();
    }

    /**
     *
     * @return next page number if the result has more page; Otherwise, return -1.
     */
    public int nextPage() {
        JSONObject result = new JSONObject(json);
        int totalPage = result.getJSONObject("pagination").getInt("page_count");
        int currentPage = result.getJSONObject("pagination").getInt("page_number");
        if (currentPage >= totalPage)
            return -1;
        else
            return ++currentPage;
    }
}
