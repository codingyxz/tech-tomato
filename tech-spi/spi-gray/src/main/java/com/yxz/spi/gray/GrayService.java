package com.yxz.spi.gray;

import com.yxz.spi.ColorService;

/**
 * @Desc TODO
 * @Date 2025-07-10
 * @Created by Yolo
 */
public class GrayService implements ColorService {

    @Override
    public String printColor() {
        return "gray";
    }
}
