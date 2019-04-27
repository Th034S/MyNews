package com.siadous.thomas.mynews.Model.TopStories;

import com.google.gson.annotations.SerializedName;

public class Multimedia {

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("subtype")
    private String subtype;

    @SerializedName("format")
    private String format;

    @SerializedName("width")
    private String width;

    @SerializedName("caption")
    private String caption;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    @SerializedName("height")
    private String height;

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public String getSubtype ()
    {
        return subtype;
    }

    public void setSubtype (String subtype)
    {
        this.subtype = subtype;
    }

    public String getFormat ()
    {
        return format;
    }

    public void setFormat (String format)
    {
        this.format = format;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getCaption ()
    {
        return caption;
    }

    public void setCaption (String caption)
    {
        this.caption = caption;
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


}
