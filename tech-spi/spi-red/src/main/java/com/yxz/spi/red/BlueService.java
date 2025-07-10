package com.yxz.spi.red;

import com.yxz.spi.ColorService;

/**
 * @Desc TODO
 * @Date 2025-07-10
 * @Created by Yolo
 */
public class BlueService implements ColorService {

    @Override
    public String printColor() {
        return "blue";
    }
}
