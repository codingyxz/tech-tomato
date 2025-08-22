package com.yxz.java.algo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Vertex<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String msisdn;
    private boolean marked=false;
    private boolean in = false;//是否属于子团伙成员,用于团伙内扩张
    private List<Edge<T>> edges;
    private int index;//在途中的下标


    public String toString(){
        return msisdn;
    }
    public Vertex(String msisdn){
        this.msisdn = msisdn;
    }

    public void addEdge(Edge<T> edge){
        if(this.edges == null){
            this.edges = new LinkedList<Edge<T>>();
        }
        this.edges.add(edge);
    }

    public void removeEdge(Edge<T> edge){
        if(this.edges != null && this.edges.size() > 0){
            this.edges.remove(edge);
        }
    }

    public Edge<T> removeEdge(Vertex<T> end){

        if(end != null && this.edges != null && this.edges.size() > 0){
            List<Edge<T>> newEdges = new ArrayList<>();
            Edge<T> toRemove = null;
            for(Edge<T> e : edges){
                if(end == e.getEnd()){
                    toRemove = e;
                }else{
                    newEdges.add(e);
                }
            }
            this.edges = newEdges;
            return toRemove;
        }else{
            return null;
        }

    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<T>> edges) {
        this.edges = edges;
    }

    public boolean isIn() {
        return in;
    }
    public void setIn(boolean in) {
        this.in = in;
    }
    public Vertex(){

    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public boolean childExist(String msisdn){
        if(edges == null || edges.size() <= 0){
            return false;
        }
        for(Edge<T> e : edges){
            if(e.other(this).getMsisdn().equals(msisdn)){
                return true;
            }
        }
        return false;
    }

    public Vertex<T> clone(){
        Vertex<T> v = new Vertex<>();
        if(msisdn != null){
            v.setMsisdn(msisdn);
        }

        if(edges != null){
            for(Edge<T> e : edges){
                v.addEdge(e.clone());
            }
        }
        v.setMarked(this.isMarked());
        return v;
    }
}
