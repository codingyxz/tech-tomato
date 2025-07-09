package com.yxz.sboot.security.jwt;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Date 2025-06-13
 * @Created by Yolo
 */
public interface TokenExtractor {

    String extract(HttpServletRequest request);


}
