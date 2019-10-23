package com.example.amazon;

public class Content
{
    private String images;
    private String names;
    private int prices;

    public Content()
    {
        //Empty Constructor
    }

    public Content(String images, String names, int prices)
    {
        this.images = images;
        this.names = names;
        this.prices = prices;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
