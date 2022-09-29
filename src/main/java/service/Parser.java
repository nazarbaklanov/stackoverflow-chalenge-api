package service;

import java.util.List;
import org.json.JSONArray;

public interface Parser<V> {
    List<V> parse(JSONArray jsonArray);
}
