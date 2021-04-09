package com.massil;

public class Arc {
    private Noeud initial;
    private Noeud cible;

    public Arc(Noeud initial, Noeud cible) {
        this.initial = initial;
        this.cible = cible;
    }

    public Noeud getInitial() {
        return initial;
    }

    public void setInitial(Noeud initial) {
        this.initial = initial;
    }

    public Noeud getCible() {
        return cible;
    }

    public void setCible(Noeud cible) {
        this.cible = cible;
    }

    @Override
    public String toString() {
        return "(" + initial.getId() + "," + cible.getId()   + ')';
    }
}
