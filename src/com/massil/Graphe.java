package com.massil;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Graphe {
    private LinkedList<Noeud> noeuds;
    private HashMap<Integer, Noeud> hmap;

    public Graphe(String file){
        hmap = new HashMap<>();
        noeuds = new LinkedList<>();
        try {
            Reader in = new FileReader(file);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            for (CSVRecord record : records) {
                addArc(Integer.parseInt(record.get(0)),
                        Integer.parseInt(record.get(1)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Graphe() {
        hmap = new HashMap<>();
        noeuds = new LinkedList<>();
    }

    public Graphe(int k) {
        hmap = new HashMap<>();
        noeuds = new LinkedList<>();
        for(int i = 1; i <= k; i++)
            addNoeud(i);
    }

    public void parcours(){
        for(Integer key: hmap.keySet())
            hmap.get(key).mark = false;

        for(Integer key: hmap.keySet()) {
            if(!hmap.get(key).mark){
                profR(hmap.get(key));
            }
        }
    }

    public void profR(Noeud n){
        n.mark = true;
        System.out.println(n.getId());
        for(Arc a: n.succ){
            if(!a.getCible().mark)
                profR(a.getCible());
        }
    }

    public void addNoeud(int n){
        Noeud noeud = new Noeud(n);
        if(!hmap.containsKey(n)){
            hmap.put(n, noeud);
        }
    }

    public HashMap<Integer, Noeud> getNoeuds() {
        return hmap;
    }

    public Noeud getNoeud(int n) {
        return hmap.get(n);
    }

    public void addArc(int x, int y){
        Noeud nx, ny;
        nx = getNoeud(x);
        ny = getNoeud(y);

        if(nx == null){
            addNoeud(x);
            nx = getNoeud(x);
        }

        if(ny == null){
            addNoeud(y);
            ny = getNoeud(y);
        }

        if(!nx.hasSuccesseur(y)){
            Arc a = new Arc(nx, ny);
            nx.addSucc(a);
        }

        if(!ny.hasSuccesseur(x)){
            Arc a = new Arc(ny, nx);
            ny.addSucc(a);
        }
    }

    public void export(){
        StringBuilder buff = new StringBuilder("Source,Target\n");
        String sep = ",";
        for(Integer id: this.hmap.keySet()){
            for(Arc a: this.hmap.get(id).getSucc()){
                buff.append(a.getCible().getId())
                        .append(sep)
                        .append(a.getInitial().getId())
                        .append("\n");
            }
        }
        File outputFile = new File(this.getClass() + ".csv");
        FileWriter out;
        try{
            out = new FileWriter(outputFile);
            out.write(buff.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder g = new StringBuilder();

        for(Integer n: hmap.keySet()){
            g.append(hmap.get(n)).append('\n');
        }
        return g.toString();
    }
}
