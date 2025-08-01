package com.yxz.dubbo02.extend;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Protocol;

/**
 * @Desc TODO
 * @Date 2025-07-15
 * @Created by Yolo
 */
public class ExtendMain {

    public static void main(String[] args) {

        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);

        Protocol adaptiveExtension = extensionLoader.getAdaptiveExtension();

        System.out.println(adaptiveExtension);

    }
}
