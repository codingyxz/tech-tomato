package com.yxz.java.jvm;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Desc 使用深度优先模拟可达性算法
 * @Date 2025-07-30
 * @Created by Yolo
 */
@Slf4j
public class ReachabilityAnalysisTest {


    public static void main(String[] args) {
        // 创建一些对象节点
        ObjectNode nodeA = new ObjectNode("A");
        ObjectNode nodeB = new ObjectNode("B");
        ObjectNode nodeC = new ObjectNode("C");
        ObjectNode nodeD = new ObjectNode("D");

        // 设置引用关系
        nodeA.addReference(nodeB);
        nodeA.addReference(nodeC);
        nodeB.addReference(nodeD);

        // 从根节点开始进行可达性分析
        Set<ObjectNode> reachableObjects = ObjectNode.reachabilityAnalysis(nodeA);

        // 输出可达的对象
        System.out.println("可达的对象: ");
        for (ObjectNode node : reachableObjects) {
            log.info(node.name);
        }
    }


    /**
     * 模拟对象节点
     */
    static class ObjectNode {
        String name;
        List<ObjectNode> references;

        ObjectNode(String name) {
            this.name = name;
            this.references = new ArrayList<>();
        }

        // 添加引用
        void addReference(ObjectNode node) {
            references.add(node);
        }

        // 进行可达性分析
        public static Set<ObjectNode> reachabilityAnalysis(ObjectNode root) {
            Set<ObjectNode> reachable = new HashSet<>();
            // 使用堆栈进行深度优先遍历
            Stack<ObjectNode> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                ObjectNode current = stack.pop();
                if (!reachable.contains(current)) {
                    reachable.add(current);
                    for (ObjectNode reference : current.references) {
                        stack.push(reference);
                    }
                }
            }
            return reachable;
        }
    }

}
