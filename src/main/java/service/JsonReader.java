package service;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;

public interface JsonReader {
    JSONArray readJsonFromUrl(String url) throws IOException, JSONException;

}
