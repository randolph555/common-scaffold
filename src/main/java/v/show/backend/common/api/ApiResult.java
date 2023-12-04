package v.show.backend.common.api;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * REST API 返回结果
 */
@Data
@Accessors(chain = true)
@Builder
public class ApiResult<T> {

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 是否成功
     */
    private boolean success;

    public ApiResult() {}

    public ApiResult(String code, String msg, T data, boolean success) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = success;
    }

    public static <T> ApiResult<T> ok() {
        return new ApiResult<>("200", "OK", null, true);
    }


    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>("200", "OK", data, true);
    }

    public static <T> ApiResult<T> fail() {
        return new ApiResult<>("500", "Internal Server Error", null, false);
    }

    public static <T> ApiResult<T> fail(String code, String msg) {
        return new ApiResult<>(code, msg, null, false);
    }

    public static <T> ApiResult<T> result(T data) {
        return new ApiResult<>("200", "OK", data, true);
    }


}
