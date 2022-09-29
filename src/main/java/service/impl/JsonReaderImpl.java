package service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import service.JsonReader;

public class JsonReaderImpl implements JsonReader {
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    public JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try (is) {
            GZIPInputStream gzip = new GZIPInputStream(is);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(gzip, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject jsonObject = new JSONObject(jsonText);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            return jsonArray;
        }
    }
}
