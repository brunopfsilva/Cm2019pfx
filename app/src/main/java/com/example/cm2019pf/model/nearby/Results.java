package com.example.cm2019pf.model.nearby;

public class Results {


    private String[] types;

    private String icon;

    private String rating;

    private Photos[] photos;

    private String reference;

    private String user_ratings_total;

    private String price_level;

    private String scope;

    private String name;

    private Geometry geometry;

    private String vicinity;

    private String place_id;

    public String[] getTypes ()
    {
        return types;
    }

    public void setTypes (String[] types)
    {
        this.types = types;
    }

    public String getIcon ()
    {
        return icon;
    }

    public void setIcon (String icon)
    {
        this.icon = icon;
    }

    public String getRating ()
    {
        return rating;
    }

    public void setRating (String rating)
    {
        this.rating = rating;
    }

    public Photos[] getPhotos ()
    {
        return photos;
    }

    public void setPhotos (Photos[] photos)
    {
        this.photos = photos;
    }

    public String getReference ()
    {
        return reference;
    }

    public void setReference (String reference)
    {
        this.reference = reference;
    }

    public String getUser_ratings_total ()
    {
        return user_ratings_total;
    }

    public void setUser_ratings_total (String user_ratings_total)
    {
        this.user_ratings_total = user_ratings_total;
    }

    public String getPrice_level ()
    {
        return price_level;
    }

    public void setPrice_level (String price_level)
    {
        this.price_level = price_level;
    }

    public String getScope ()
    {
        return scope;
    }

    public void setScope (String scope)
    {
        this.scope = scope;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }


    public Geometry getGeometry ()
    {
        return geometry;
    }

    public void setGeometry (Geometry geometry)
    {
        this.geometry = geometry;
    }

    public String getVicinity ()
    {
        return vicinity;
    }

    public void setVicinity (String vicinity)
    {
        this.vicinity = vicinity;
    }


    public String getPlace_id ()
    {
        return place_id;
    }

    public void setPlace_id (String place_id)
    {
        this.place_id = place_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [types = "+types+", icon = "+icon+", rating = "+rating+", photos = "+photos+", reference = "+reference+", user_ratings_total = "+user_ratings_total+", price_level = "+price_level+", scope = "+scope+", name = "+name+" "+", geometry = "+geometry+", vicinity = "+vicinity+", "+", place_id = "+place_id+"]";
    }

}
