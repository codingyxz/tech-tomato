package com.yxz.java.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc TODO
 * @Date 2025-07-25
 * @Created by Yolo
 */
public class HeapOOM {

    static class OOMObject {
    }

    /**
     * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    public static void main(String[] args) {
        List<OOMObject> oomObjectList = new ArrayList<>();
        while (true) {
            oomObjectList.add(new OOMObject());
        }
    }

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.util.Arrays.copyOf(Arrays.java:3210)
     * 	at java.util.Arrays.copyOf(Arrays.java:3181)
     * 	at java.util.ArrayList.grow(ArrayList.java:267)
     * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:241)
     * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:233)
     * 	at java.util.ArrayList.add(ArrayList.java:464)
     * 	at com.yxz.java.jvm.oom.HeapOOM.main(HeapOOM.java:22)
     */

}
