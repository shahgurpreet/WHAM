package eventbrite.operation;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

/**
 * @Author: yummin Date: 15/11/7
 */
public class EventRequest extends BaseRequest {

    private long id;

    @Override
    protected String getAPIName() {
        return "events/" + id;
    }

    @Override
    protected List<NameValuePair> getQueryParameters() {
        // Always expand venue
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        addParameter("expand", "venue", params);
        return params;
    }

    public void setId(long id) {
        this.id = id;
    }
}
