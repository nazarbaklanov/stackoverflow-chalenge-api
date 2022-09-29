package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.Item;
import org.json.JSONArray;
import org.json.JSONObject;
import service.Parser;

public class ParserItemImpl implements Parser<Item> {
    private static final int MIN_ANSWER_COUNT = 1;
    private static final String COUNTRY_FOR_FOUND_ONE = "Moldova";
    private static final String COUNTRY_FOR_FOUND_TWO = "Romania";

    @Override
    public List<Item> parse(JSONArray jsonArray) {
        List<Item> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            if (!jsonObject.has("location")) {
                continue;
            }
            if (jsonObject.getLong("answer_count") < MIN_ANSWER_COUNT) {
                continue;
            }
            if (!jsonObject.getString("location").contains(COUNTRY_FOR_FOUND_ONE)
                    && !jsonObject.getString("location").contains(COUNTRY_FOR_FOUND_TWO)) {
                continue;
            }
            Item item = new Item();
            item.setAnswerCount(jsonObject.getLong("answer_count"));
            item.setQuestionCount(jsonObject.getLong("question_count"));
            item.setAccountId(jsonObject.getLong("account_id"));
            item.setReputation(jsonObject.getLong("reputation"));
            item.setUserId(jsonObject.getLong("user_id"));
            item.setLocation(jsonObject.getString("location"));
            item.setProfileImage(jsonObject.getString("profile_image"));
            item.setLink(jsonObject.getString("link"));
            item.setDisplayName(jsonObject.getString("display_name"));
            list.add(item);
        }
        return list;
    }
}
