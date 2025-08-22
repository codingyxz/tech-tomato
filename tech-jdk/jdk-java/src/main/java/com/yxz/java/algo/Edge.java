package com.yxz.java.algo;

import java.io.Serializable;

public class Edge<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Vertex<T> start;
    private Vertex<T> end;
    private T weight;


    public T getWeight() {
        return weight;
    }
    public void setWeight(T weight) {
        this.weight = weight;
    }
    public String toString(){
        return start.getMsisdn() + "->" + end.getMsisdn();
    }
    public Vertex<T> other(Vertex<T> v){
        return v.getMsisdn().equals(start.getMsisdn())?end:start;
    }
    public Vertex<T> getStart() {
        return start;
    }
    public void setStart(Vertex<T> start) {
        this.start = start;
    }
    public Vertex<T> getEnd() {
        return end;
    }
    public void setEnd(Vertex<T> end) {
        this.end = end;
    }

    public Edge(Vertex<T> start,Vertex<T> end,T weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    public Edge(){

    }
    public Edge<T> clone(){
        Edge<T> newEdge = new Edge<T>();
        if(start != null){
            newEdge.setStart(start.clone());
        }
        if(end != null){
            newEdge.setEnd(end.clone());
        }
        if(weight != null){
            newEdge.setWeight(weight);
        }

        return newEdge;
    }
}
