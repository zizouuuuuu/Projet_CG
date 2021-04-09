package com.massil;

public class RandomGraphe extends Graphe{
    public RandomGraphe(int n, float p){
        if(n > 0 &&
            p >= 0 && p <= 1){
            float w = -1;
            int v = 1;
            while(v < n){
                float r = (float) Math.random();
                w = w + 1 + (float)(Math.log(1-r)/Math.log(1-p));

                while(w >= v && v < n){
                    w -= v;
                    v += 1;
                }

                if(v < n)
                    addArc(v, (int) Math.floor(w));
            }
        }
    }
}
