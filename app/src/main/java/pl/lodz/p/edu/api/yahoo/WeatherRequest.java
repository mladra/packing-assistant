package pl.lodz.p.edu.api.yahoo;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

public class WeatherRequest extends JsonRequest<WeatherResponse> {

    private static final String BASE_URL = "https://weather-ydn-yql.media.yahoo.com/forecastrss";

    private final String appId;
    private final String consumerKey;
    private final String consumerSecret;
    private final String location;

    public WeatherRequest(int method, String url, @Nullable String requestBody, Response.Listener<WeatherResponse> listener, @Nullable Response.ErrorListener errorListener, String appId, String consumerKey, String consumerSecret, String location) {
        super(method, url, requestBody, listener, errorListener);
        this.appId = appId;
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.location = location;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        final Map<String, String> headers = new HashMap<>();
        final OAuthConsumer consumer = new OAuthConsumer(null, consumerKey, consumerSecret, null);
        consumer.setProperty(OAuth.OAUTH_SIGNATURE_METHOD, OAuth.HMAC_SHA1);

        final OAuthAccessor accessor = new OAuthAccessor(consumer);
        try {
            final OAuthMessage request = accessor.newRequestMessage(OAuthMessage.GET, getUrl(), null);
            String authorization = request.getAuthorizationHeader(null);
            headers.put("Authorization", authorization);
        } catch (OAuthException | IOException | URISyntaxException  e) {
            throw new AuthFailureError(e.getMessage());
        }

        headers.put("Yahoo-App-Id", appId);
        headers.put("Content-Type", "application/json");
        return headers;
    }

    @Override
    public String getUrl() {
        return BASE_URL + "?location=" + location + "&format=json&u=c";
    }

    @Override
    protected Response<WeatherResponse> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            WeatherResponse parsedResponse = parseResponse(json);
            return Response.success(
                    parsedResponse,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException | JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    private WeatherResponse parseResponse(String jsonObject) {
        return new Gson().fromJson(jsonObject, WeatherResponse.class);
    }

}
