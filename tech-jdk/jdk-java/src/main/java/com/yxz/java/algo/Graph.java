package com.yxz.java.algo;

import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class Graph<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private List<Edge<T>> edges = new LinkedList<Edge<T>>();
    private List<Vertex<T>> vertexes = new LinkedList<Vertex<T>>();
    private int[][] A;//邻接矩阵


    public String toString() {
        if (edges == null) {
            return null;
        }
        StringBuilder strbld = new StringBuilder();
        for (Edge<T> e : edges) {
            strbld.append(e.toString());
            strbld.append("\r\n");
        }
        return strbld.toString();
    }

    public Graph<T> deepClone() throws Exception {

        ByteArrayOutputStream byteOut = null;
        ByteArrayInputStream byteIn = null;
        ObjectInputStream objIn = null;
        ObjectOutputStream objOut = null;
        try {
            byteOut = new ByteArrayOutputStream();
            objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(this);

            byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            objIn = new ObjectInputStream(byteIn);
            return (Graph<T>) objIn.readObject();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            byteIn = null;
            byteOut = null;
            try {
                if (objOut != null) {
                    objOut.close();
                }
                if (objIn != null) {
                    objIn.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }

        }
    }


    public Vertex<T> findVertex(String msisdn) {
        if (this.edges == null) {
            return null;
        }
        for (Edge<T> e : edges) {
            if (e.getStart().getMsisdn().equals(msisdn)) {
                return e.getStart();
            } else if (e.getEnd().getMsisdn().equals(msisdn)) {
                return e.getEnd();
            }
        }
        return null;
    }

    /**
     * 是否有向
     *
     * @param msisdn_1
     * @param msisdn_2
     * @param derect
     * @return
     */
    public Edge<T> findEdge(String msisdn_1, String msisdn_2, boolean derect) {
        if (this.edges == null) {
            return null;
        }
        if (derect) {
            for (Edge<T> e : edges) {
                if (e.getStart().getMsisdn().equals(msisdn_1) && e.getEnd().getMsisdn().equals(msisdn_2)) {
                    return e;
                }
            }
        } else {
            for (Edge<T> e : edges) {
                String start = e.getStart().getMsisdn();
                String end = e.getEnd().getMsisdn();
                if ((start.equals(msisdn_1) && end.equals(msisdn_2))
                        || (end.equals(msisdn_1) && start.equals(msisdn_2))) {
                    return e;
                }
            }
        }

        return null;
    }

    /**
     * 两点之间只允许添加一条边
     *
     * @param msisdn_1
     * @param msisdn_2
     * @param weight
     */
    public void addEdge(String msisdn_1, String msisdn_2, T weight, boolean derect) {
        Edge<T> e = findEdge(msisdn_1, msisdn_2, derect);
        if (e == null) {
            Vertex<T> v1 = findVertex(msisdn_1);
            if (v1 == null) {
                v1 = new Vertex<T>(msisdn_1);
                v1.setIndex(vertexes.size());
                vertexes.add(v1);

            }
            Vertex<T> v2 = findVertex(msisdn_2);
            if (v2 == null) {
                v2 = new Vertex<T>(msisdn_2);
                v2.setIndex(vertexes.size());
                vertexes.add(v2);

            }
            e = new Edge<T>(v1, v2, weight);
            v1.addEdge(e);
            this.edges.add(e);
        }

    }

    public List<Vertex<T>> getNextLevel(List<Vertex<T>> starts) {
        List<Vertex<T>> nextVs = null;
        if (starts != null && starts.size() > 0) {
            nextVs = new ArrayList<>();
            for (Vertex<T> v : starts) {
                List<Edge<T>> es = v.getEdges();
                if (es != null && es.size() > 0) {
                    for (Edge<T> e : es) {
                        nextVs.add(e.getEnd());
                    }
                }
            }
        }
        return nextVs;
    }

    public void addEdge(String msisdn_1, String msisdn_2, T weight) {
        addEdge(msisdn_1, msisdn_2, weight, true);
//		Edge e2 = v1.findEdge(msisdn_2, msisdn_1);
//		if(e2 == null){
//			e2 = new Edge(v2, v1);
//			v2.addEdge(e2);
//		}
    }


    public void converse() {
        for (Vertex<T> v : vertexes) {
            if (v.getEdges() != null) {
                v.getEdges().clear();
            }
        }
        if (edges != null) {
            for (Edge<T> e : edges) {
                Vertex<T> v = e.getStart();
                e.setStart(e.getEnd());
                e.setEnd(v);
                e.getStart().addEdge(e);
            }
        }
    }


    public void reversePostOrderTar(Vertex<T> root, LinkedList<Vertex<T>> stack) {
        if (!root.isMarked()) {
            root.setMarked(true);
            if (root.getEdges() != null) {
                for (Edge<T> e : root.getEdges()) {
                    reversePostOrderTar(e.other(root), stack);
                }
            }
            stack.push(root);
        }
    }

    public LinkedList<Vertex<T>> reversePostOrder() {
        LinkedList<Vertex<T>> stack = new LinkedList<Vertex<T>>();
        for (Vertex<T> v : vertexes) {
            reversePostOrderTar(v, stack);
        }
        return stack;
    }

    /**
     * 查找最顶层的节点（没有被指向的节点）
     *
     * @return
     */
    public List<Vertex<T>> getRoot() {
        List<Vertex<T>> tops = new ArrayList<>();
        List<Vertex<T>> notTop = new ArrayList<>();
        for (Edge<T> e : edges) {
            if (!notTop.contains(e.getEnd())) {
                notTop.add(e.getEnd());
            }
        }
        for (Vertex<T> v : vertexes) {
            if (!notTop.contains(v)) {
                tops.add(v);
            }
        }
        return tops;
    }


    public void dijkstra() {

        List<Vertex<T>> roots = getRoot();
        //初始化矩阵，设置每条边的值为1
        int n = vertexes.size();
        A = new int[n][n];
        for (int i = 0; i < n; i++) {
            List<Edge<T>> edges = vertexes.get(i).getEdges();
            if (edges != null && edges.size() > 0) {
                for (Edge<T> e : edges) {
                    A[i][e.getEnd().getIndex()] = 1;
                }
            }
        }
        for (Vertex<T> v : roots) {
            dijkstra(v.getIndex());
        }
    }

    private void dijkstra(int v) {
        int n = vertexes.size();
        boolean[] s = new boolean[n];
        int[] dist = new int[n];
        int[] prev = new int[n];

        //查找顶点v到每个点的最大路径
        for (int i = 0; i < n; i++) {
            dist[i] = A[v][i];
            s[i] = false;
            if (dist[i] == 0) {
                prev[i] = -1;
            } else {
                prev[i] = v;
            }
        }
        dist[v] = 0;
        s[v] = true;
        for (int i = 0; i < n; i++) {
            int mindist = 0;
            int u = v;
            for (int j = 0; j < n; j++) {
                if (!s[j] && dist[j] > mindist) {
                    u = j;
                    mindist = dist[j];
                }
            }
            s[u] = true;
            for (int j = 0; j < n; j++) {
                if (!s[j] && A[u][j] > 0) {
                    if (dist[u] + A[u][j] > dist[j]) {
                        dist[j] = dist[u] + A[u][j];
                        prev[j] = u;
                    }
                }
            }
        }
        String path = "";
        //重置矩阵
        A = new int[n][n];
        for (int i = 0; i < n; i++) {
            if (s[i]) {
                int k1 = i;//当前步
                int k2 = i;//上一步
                while (k2 != v) {
                    k1 = k2;
                    k2 = prev[k2];
                    A[k2][k1] = 1;
                    path += vertexes.get(k2).getMsisdn() + "->" + vertexes.get(k1).getMsisdn();
                    path += ",";
                }
                System.out.println(path);
                path = "";
            }
        }
    }


    public void DFS(Vertex<T> root, List<String> circleMember) {
        if (!root.isMarked()) {
            List<Edge<T>> edges = root.getEdges();
            circleMember.add(root.getMsisdn());
            root.setMarked(true);
            if (edges != null && edges.size() > 0) {
                for (Edge<T> e : edges) {
                    DFS(e.other(root), circleMember);
//					root.setMarked(false);
                }
            }
        }

    }


    /**
     * 强通联圈(有向图)，从任意一点出发能够到达另外一点
     *
     * @param leastMembers
     * @return
     */
    public List<List<String>> getStrongCircles(int leastMembers) {
        List<List<String>> listCircles = new ArrayList<List<String>>();
        converse();
        LinkedList<Vertex<T>> stack = reversePostOrder();
        converse();
        for (Vertex<T> v : vertexes) {
            v.setMarked(false);
        }
        for (Vertex<T> v : stack) {
            List<String> circleMember = new LinkedList<String>();
            DFS(v, circleMember);
            if (circleMember.size() < leastMembers) {
                continue;
            }
            listCircles.add(circleMember);
        }
        return listCircles;
    }

    public void rebuildByA() {
        for (Vertex<T> v1 : vertexes) {
            for (Vertex<T> v2 : vertexes) {
                if (A[v1.getIndex()][v2.getIndex()] != 1) {
                    Edge<T> toRemove = v1.removeEdge(v2);
                    if (toRemove != null) {
                        edges.remove(toRemove);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph<String> g = new Graph<String>();

        g.addEdge("A", "B", "");
        g.addEdge("B", "C", "");
        g.addEdge("C", "D", "");
        g.addEdge("E", "D", "");
//		g.addEdge("M", "C", "");


        g.dijkstra();
        g.rebuildByA();
        List<Vertex<String>> current = g.getRoot();
        int i = 1;
        String tmp = "";
        while (current != null && current.size() > 0) {
            tmp += i + ":";
            for (Vertex<String> v : current) {
                tmp += v.getMsisdn() + ",";
            }
            System.out.println(tmp);
            tmp = "";
            current = g.getNextLevel(current);
            i++;
        }

//		System.out.println(g);
//		System.out.println("强：" + g.dijkstra(0));

    }
}
