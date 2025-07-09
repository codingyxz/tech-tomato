package com.yxz.java.json.fastjson;

import lombok.Data;

/**
 * @Description 泛型对象
 * @Date 2025-04-28
 * @Created by Yolo
 */
@Data
public class ResultVO<T> {

    private Boolean success = Boolean.TRUE;
    private T data;

    private ResultVO() {
    }

    public static <T> ResultVO<T> buildSuccess(T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(data);
        return resultVO;
    }
}
