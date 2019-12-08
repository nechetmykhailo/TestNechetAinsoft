package com.example.testnechetainsoft.singleton;

public class Shops {
    private static Shops instance = null;

    private String name;
    private String descriptions;
    private String image;
    private int FLAG_CHANGE;
    private int FLAG_ADD;

    public static synchronized Shops getInstance(){
        if(instance == null){
            instance = new Shops();
        }
        return instance;
    }

    private Shops() {
    }

    public static void reset() {
        instance = new Shops();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFLAG_CHANGE() {
        return FLAG_CHANGE;
    }

    public void setFLAG_CHANGE(int FLAG_CHANGE) {
        this.FLAG_CHANGE = FLAG_CHANGE;
    }

    public int getFLAG_ADD() {
        return FLAG_ADD;
    }

    public void setFLAG_ADD(int FLAG_ADD) {
        this.FLAG_ADD = FLAG_ADD;
    }
}
