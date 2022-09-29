package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.Tag;
import org.json.JSONArray;
import org.json.JSONObject;
import service.Parser;

public class ParserUserTagImpl implements Parser<Tag> {
    private static final List<String> TAGS_FOR_FOUND = List.of("java", ".net", "docker", "C#");

    @Override
    public List<Tag> parse(JSONArray jsonArray) {
        List<Tag> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Tag tag = new Tag();

            tag.setUserId(jsonObject.getLong("user_id"));
            if (!TAGS_FOR_FOUND.contains(jsonObject.getString("name"))) {
                continue;
            }
            tag.setName(jsonObject.getString("name"));
            list.add(tag);
        }
        return list;
    }
}
