package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class ArmadioScarpe {
    private static ArmadioScarpe INSTANCE;
    private List<Scarpe> listaScarpe = new ArrayList<>();
    private Gson gson = new GsonBuilder()
            .create();

    private ArmadioScarpe() {
        listaScarpe.add(new Scarpe(1, "man shoes for winter", 133,  "man"));
        listaScarpe.add(new Scarpe(2, "Football shoes 2009", 120,  "man"));
        listaScarpe.add(new Scarpe(69, "Black ceremony shoes Walch 2018", 400,  "woman"));
        listaScarpe.add(new Scarpe(120, "woman shoes for winter", 30,  "woman"));
    }

    public static ArmadioScarpe getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ArmadioScarpe();
        }
        return INSTANCE;
    }

    public String getMan(){
        List<Scarpe> listCopy = new ArrayList<>();
        for (Scarpe s: listaScarpe) {
            if(s.getGenere().equals("man")){
                listCopy.add(s);
            }
        }
        return gson.toJson(listCopy);
    }

    public String getWoman(){
        List<Scarpe> listCopy = new ArrayList<>();
        for (Scarpe s: listaScarpe) {
            if(s.getGenere().equals("woman")){
                listCopy.add(s);
            }
        }
        return gson.toJson(listCopy);
    }

    public String getSortedName() {
        List<Scarpe> listCopy = new ArrayList<>();
        listCopy.addAll(listaScarpe);

        listCopy.sort((o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });

        return gson.toJson(listCopy);
    }

    public String getSortedPrice() {
        List<Scarpe> listCopy = new ArrayList<>();
        listCopy.addAll(listaScarpe);

        listCopy.sort((o1, o2) -> {
            if (o1.getPrice()>o2.getPrice())
                return -1;
            if (o1.getPrice()<o2.getPrice())
                return 1;
            return 0;
        });

        String res = gson.toJson(listCopy);
        return res;
    }

}
