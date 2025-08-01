package com.yxz.dubbo02.filter;

import org.apache.dubbo.rpc.*;

/**
 * @Desc TODO
 * @Date 2025-07-15
 * @Created by Yolo
 */
public class MyProFilter implements Filter {

    public MyProFilter() {
        System.out.println("==================MyProFilter--constructor======================");
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        System.out.println("==================MyProFilter--invoke======================");

        return invoker.invoke(invocation);
    }


}
