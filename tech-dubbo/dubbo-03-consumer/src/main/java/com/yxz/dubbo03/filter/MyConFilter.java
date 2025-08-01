package com.yxz.dubbo03.filter;

import org.apache.dubbo.rpc.*;

/**
 * @Desc TODO
 * @Date 2025-07-15
 * @Created by Yolo
 */
public class MyConFilter implements Filter {

    public MyConFilter() {
        System.out.println("==================MyConFilter--constructor======================");
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("==================MyConFilter--invoke======================");

        return invoker.invoke(invocation);
    }
}
