package me.oguzhanozer.musfer.userservice.util;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class JsonHelper {

    public static String emptyJson() {
        return JsonNodeFactory.instance.objectNode()
                .toString();
    }

    public static String createRequestJsonOf(TestUser testUser) {
        return JsonNodeFactory.instance.objectNode()
                .put("email", testUser.getEmail())
                .put("username", testUser.getUsername())
                .toString();
    }

    public static String responseJsonOf(TestUser testUser) {
        return JsonNodeFactory.instance.objectNode()
                .put("id", testUser.getId())
                .put("email", testUser.getEmail())
                .put("username", testUser.getUsername())
                .toString();
    }

    public static ResultMatcher keysOfJsonMatchesProblemDetailStandard() {
        return result -> {
            jsonPath("$.type").exists().match(result);
            jsonPath("$.status").exists().match(result);
            jsonPath("$.title").exists().match(result);
            jsonPath("$.instance").exists().match(result);
        };
    }

}
