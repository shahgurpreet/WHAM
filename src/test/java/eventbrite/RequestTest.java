package eventbrite;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.junit.Test;

import eventbrite.exception.RequestException;
import eventbrite.model.Category;
import eventbrite.operation.EventRequest;
import eventbrite.operation.OrganizerRequest;
import eventbrite.operation.SearchRequest;
import eventbrite.operation.VenueRequest;

public class RequestTest extends TestBase {

    @Test
    public void testCategory() {
        assertEquals(103, Category.getCategoryID("Music"));
        assertEquals(10003, Category.getCategoryID("Food"));
        assertEquals("Music", Category.getCategoryName(103));
    }

    @Test
    public void testGetEventRequest() throws URISyntaxException, RequestException {
        EventRequest request = new EventRequest();
        request.setId(19104519131l);
        assertEquals("https://www.eventbriteapi.com/v3/events/19104519131?expand=venue", request.getUri().toString());
    }

    @Test
    public void testGetVenueRequest() throws URISyntaxException, RequestException {
        VenueRequest request = new VenueRequest();
        request.setId(11768639);
        assertEquals("https://www.eventbriteapi.com/v3/venues/11768639", request.getUri().toString());
    }

    @Test
    public void testGetOrganizerRequest() throws URISyntaxException, RequestException {
        OrganizerRequest request = new OrganizerRequest();
        request.setId(4706628377l);
        assertEquals("https://www.eventbriteapi.com/v3/organizers/4706628377/", request.getUri().toString());
    }

    @Test
    public void testEventSearchRequest() throws URISyntaxException, RequestException {
        SearchRequest request = new SearchRequest();
        request.setKeywords(new String[] { "Rock", "Music" });
        request.setVenue_city("Boston");
        assertEquals("https://www.eventbriteapi.com/v3/events/search?q=Rock%2BMusic&venue.city=Boston",
                request.getUri().toString());
    }

    @Test
    public void testEventSearchRequestWithCategories() throws URISyntaxException, RequestException {
        SearchRequest request = new SearchRequest();
        request.setSubCategories("3017,10002".split(","));
        assertEquals("https://www.eventbriteapi.com/v3/events/search?subcategories=3017%2C10002",
                request.getUri().toString());
    }

}
