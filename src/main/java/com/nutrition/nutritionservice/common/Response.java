package com.nutrition.nutritionservice.common;

import lombok.Data;

/**
 * 响应体。
 *
 * @author heng.liu
 * @since 2020/9/28
 */
@Data
public class Response {
    /**
     * 结果内容
     */
    private Object data;

    /**
     * true 成功 false 失败
     */
    private boolean result;

    /**
     * 提示信息
     */
    private String msg;
    /**
     * code码
     *
     * @see StateCodeEnum
     */
    private int state;

    public static Response success() {
        return success(StateCodeEnum.SUCCESS, "");
    }

    public static Response success(StateCodeEnum state, String msg) {
        Response response = new Response();
        response.setResult(true);
        response.setState(state.getNameEn());
        response.setMsg(msg);
        return response;
    }

    public static Response success(Object data) {
        return success(StateCodeEnum.SUCCESS, "", data);
    }

    public static Response success(StateCodeEnum state, String msg, Object data) {
        Response response = new Response();
        response.setResult(true);
        response.setState(state.getNameEn());
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static Response failure(String errorMessage) {
        return failure(StateCodeEnum.SYSTEM_ERROR, errorMessage);
    }

    public static Response failure(StateCodeEnum state, String msg) {
        Response response = new Response();
        response.setResult(false);
        response.setState(state.getNameEn());
        response.setMsg(msg);
        return response;
    }

    public static Response failure(Object data) {
        return failure(StateCodeEnum.SYSTEM_ERROR, "", data);
    }

    public static Response failure(StateCodeEnum state, String msg, Object data) {
        Response response = new Response();
        response.setResult(false);
        response.setState(state.getNameEn());
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

}
