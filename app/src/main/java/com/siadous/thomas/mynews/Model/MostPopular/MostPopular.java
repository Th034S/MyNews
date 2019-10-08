package com.siadous.thomas.mynews.Model.MostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MostPopular {


    @Expose
    @SerializedName("section")
    private String section;

    @Expose
    @SerializedName("abstracts")
    private String abstracts;

    @Expose
    @SerializedName("source")
    private String source;

    @Expose
    @SerializedName("asset_id")
    private String asset_id;

    @Expose
    @SerializedName("media")
    private Media[] media;

    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("uri")
    private String uri;

    @Expose
    @SerializedName("url")
    private String url;

    @Expose
    @SerializedName("adx_keywords")
    private String adx_keywords;

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("byline")
    private String byline;

    @Expose
    @SerializedName("published_date")
    private String published_date;

    @Expose
    @SerializedName("views")
    private String views;


    public String getSection ()
    {
        return section;
    }

    public void setSection (String section)
    {
        this.section = section;
    }

    public String getAbstract ()
    {
        return abstracts;
    }

    public void setAbstract (String abstracts)
    {
        this.abstracts = abstracts;
    }

    public String getSource ()
    {
        return source;
    }

    public void setSource (String source)
    {
        this.source = source;
    }

    public String getAsset_id ()
    {
        return asset_id;
    }

    public void setAsset_id (String asset_id)
    {
        this.asset_id = asset_id;
    }

    public Media[] getMedia ()
    {
        return media;
    }

    public void setMedia (Media[] media)
    {
        this.media = media;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getUri ()
    {
        return uri;
    }

    public void setUri (String uri)
    {
        this.uri = uri;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getAdx_keywords ()
    {
        return adx_keywords;
    }

    public void setAdx_keywords (String adx_keywords)
    {
        this.adx_keywords = adx_keywords;
    }


    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getByline ()
    {
        return byline;
    }

    public void setByline (String byline)
    {
        this.byline = byline;
    }

    public String getPublished_date ()
    {
        return published_date;
    }

    public void setPublished_date (String published_date)
    {
        this.published_date = published_date;
    }

    public String getViews ()
    {
        return views;
    }

    public void setViews (String views)
    {
        this.views = views;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [section = "+section+", abstract = "+abstracts+", source = "+source+", asset_id = "+asset_id+", media = "+media+", type = "+type+", title = "+title+", uri = "+uri+", url = "+url+", adx_keywords = "+adx_keywords+", geo_facet = "+", id = "+id+", byline = "+byline+", published_date = "+published_date+", views = "+views+"]";
    }
}
