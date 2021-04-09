package com.massil;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Noeud {
    int id;
    boolean mark;
    List<Arc> succ;

    public Noeud(int id) {
        this.id = id;
        this.succ = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Arc> getSucc(){
        return this.succ;
    }

    @Override
    public String toString() {
        StringBuilder arcs = new StringBuilder();

        arcs.append("{");
        ListIterator<Arc> iter = this.succ.listIterator();
        while(iter.hasNext()){
            arcs.append(iter.next()).append(", ");
        }
        if(succ.size() != 0)
            arcs.replace(arcs.length()-2,arcs.length()-1,"}");
        else
            arcs.append("}");

        return "(" + this.id + ") "+arcs.toString();
    }

    public void addSucc(Arc a){
        if(!this.succ.contains(a))
            this.succ.add(a);
    }

    public boolean hasSuccesseur(int j){
        ListIterator<Arc> iter = this.succ.listIterator();
        while(iter.hasNext()){
            if(iter.next().getCible().getId() == j)
                return true;
        }

        return false;
    }
}
