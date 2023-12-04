package v.show.backend.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类，基于Jackson封装
 */
public class JsonUtil {

    private static Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转为JSON字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        String result;
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("toJson error", e);
            return null;
        }
        return result;
    }

    /**
     * JSON字符串转为对象
     *
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T json2Obj(String jsonStr, Class<T> clazz) {
        if (jsonStr == null) {
            return null;
        }
        try {
            T result = mapper.readValue(jsonStr, clazz);
            return result;
        } catch (Exception e) {
            log.error("json2Obj error", e);
            return null;
        }
    }

    public static <T> List<T> json2List(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            log.error("json2Obj error", e);
            return null;
        }
    }

    public static <V> Map<String, V> json2Map(String json, Class<V> v) {
        return json2Map(json, String.class, v);
    }

    public static <K, V> Map<K, V> json2Map(String json, Class<K> k, Class<V> v) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        JavaType javaType = mapper.getTypeFactory().constructParametricType(Map.class, k, v);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            log.error("json2Obj error", e);
            return null;
        }
    }

    /**
     * JSON对象转为自定义对象
     *
     * @param jsonNode
     * @param clazz
     * @return
     */
    public static <T> T json2Obj(TreeNode jsonNode, Class<T> clazz) {
        if (jsonNode == null) {
            return null;
        }
        try {
            T result = mapper.treeToValue(jsonNode, clazz);
            return result;
        } catch (Exception e) {
            log.error("json2Obj error", e);
            return null;
        }
    }

    /**
     * 普通对象转为JSON对象
     *
     * @param obj
     * @return
     */
    public static <T extends JsonNode> T convertObj(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            T result = mapper.valueToTree(obj);
            return result;
        } catch (Exception e) {
            log.error("convertObj error", e);
            return null;
        }
    }


    public static <T> T jsonToObject(String src, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(src) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : mapper.readValue(src, typeReference));
        } catch (Exception e) {
            log.error("jsonToObject error", e);
            return null;
        }
    }

    /**
     * 创建JSON数组对象
     *
     * @return
     */
    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    /**
     * 创建JSON对象
     *
     * @return
     */
    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }
}
