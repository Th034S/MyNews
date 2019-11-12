package com.siadous.thomas.mynews.model.ArticleSearch;

public class Multimedia {


    private String subtype;

    private String crop_name;

    private String width;

    private String rank;

    private String subType;

    private String type;

    private String url;

    private String height;


    public String getSubtype ()
    {
        return subtype;
    }

    public void setSubtype (String subtype)
    {
        this.subtype = subtype;
    }

    public String getCrop_name ()
    {
        return crop_name;
    }

    public void setCrop_name (String crop_name)
    {
        this.crop_name = crop_name;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getRank ()
    {
        return rank;
    }

    public void setRank (String rank)
    {
        this.rank = rank;
    }

    public String getSubType ()
    {
        return subType;
    }

    public void setSubType (String subType)
    {
        this.subType = subType;
    }


    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [subtype = "+subtype+", crop_name = "+crop_name+", width = "+width+", rank = "+rank+", subType = "+subType+", type = "+type+", url = "+url+", height = "+height+"]";
    }
}
