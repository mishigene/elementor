package io.elementor.infra;

import com.google.common.base.Stopwatch;
import org.apache.http.client.methods.*;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.elementor.infra.Utils.inputStreamToString;

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static BasicCookieStore cookieStore;
    private List<AbstractMap.SimpleEntry<String, Integer>> slas = new ArrayList<>();
    private HashMap<String, String> defaultHeaders = new HashMap<>();

    public void addDefaultHeader(String name, String value){
        defaultHeaders.put(name,value);
    }
    public HttpResponseWrapper sendRequest(HttpMethod method,  String uri, HashMap<String, String> newHeaders){
        return sendRequest(method, uri, null, newHeaders);
    }

    public HttpResponseWrapper sendRequest(HttpMethod method, String uri){
        return sendRequest(method, uri, null, null);
    }

    public HttpResponseWrapper sendRequest(HttpMethod method, String uri, String jsonBody){
        return sendRequest(method, uri, jsonBody, null);
    }

    public HttpResponseWrapper sendRequest(HttpMethod method, String uri, String jsonBody, HashMap<String, String> newHeaders){
        if (cookieStore == null) {
            cookieStore = new BasicCookieStore();
        }
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
        HttpRequestBase request;

        switch (method) {
            case GET:
                request = new HttpGet(uri);
                break;
            case POST:
                request = new HttpPost(uri);
                break;
            case PUT:
                request = new HttpPut(uri);
                break;
            case DELETE:
                request = new HttpDelete(uri);
                break;
            case PATCH:
                request = new HttpPatch(uri);
                break;
            default:
                throw new IllegalArgumentException("Http method not supported");
        }
        request.setHeader("Content-type", "application/json;charset=UTF-8");
        for (String key :
                defaultHeaders.keySet()) {
            String value = defaultHeaders.get(key);
            request.addHeader(key,value);
        }
        if (jsonBody != null) {
            StringEntity entity = new StringEntity(jsonBody, "utf-8");
            ((HttpEntityEnclosingRequestBase)request).setEntity(entity);
        }

        if (newHeaders != null) {
            Iterator<Map.Entry<String, String>> iterator = newHeaders.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                request.setHeader(next.getKey(), next.getValue());
                iterator.remove();
            }
            logger.info(String.format("HTTPPOST: Uri: %s\nHEADERS:\n%s\nBODY:\n%s", uri, newHeaders.toString(), jsonBody));
        } else {
            logger.info(String.format("HTTPPOST: Uri: %s\nBODY:\n%s", uri, jsonBody));
        }

        Stopwatch stopwatch = Stopwatch.createUnstarted();
        HttpResponseWrapper responseWrapper = new HttpResponseWrapper();
        try {
            stopwatch.start();
            CloseableHttpResponse response = client.execute(request);
            slas.add(new AbstractMap.SimpleEntry(uri, stopwatch.elapsed(TimeUnit.MILLISECONDS)));
            responseWrapper.setStatusCode(response.getStatusLine().getStatusCode());
            responseWrapper.setMessage(response.getStatusLine().getReasonPhrase());
            StringBuilder stringBuilder = inputStreamToString(response.getEntity().getContent());
            String s = stringBuilder.toString();
            client.close();
            responseWrapper.setContent(s);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
        return responseWrapper;
    }


    public static void addCookie(Cookie cookie) {
        cookieStore.addCookie(cookie);
    }

    public static void clearCookies() {
        if (cookieStore != null) {
            cookieStore.clear();
        }
    }

    public static HashMap<String, Cookie> getAllCookies() {
        HashMap<String, Cookie> cookies = new HashMap<>();
        for (Cookie cookie : cookieStore.getCookies()) {
            cookies.put(cookie.getName(), cookie);
        }
        return cookies;
    }


}
