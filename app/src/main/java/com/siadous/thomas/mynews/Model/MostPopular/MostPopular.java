package com.siadous.thomas.mynews.Model.MostPopular;

public class MostPopular {

    private String per_facet;

    private String[] org_facet;

    //private null column;

    private String section;

    private String abstracts;

    private String source;

    private String asset_id;

    private Media[] media;

    private String type;

    private String title;

    private String[] des_facet;

    private String uri;

    private String url;

    private String adx_keywords;

    private String geo_facet;

    private String id;

    private String byline;

    private String published_date;

    private String views;

    public String getPer_facet ()
    {
        return per_facet;
    }

    public void setPer_facet (String per_facet)
    {
        this.per_facet = per_facet;
    }

    public String[] getOrg_facet ()
    {
        return org_facet;
    }

    public void setOrg_facet (String[] org_facet)
    {
        this.org_facet = org_facet;
    }
/**
    public null getColumn ()
    {
        return column;
    }

    public void setColumn (null column)
    {
        this.column = column;
    }
**/
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

    public String[] getDes_facet ()
    {
        return des_facet;
    }

    public void setDes_facet (String[] des_facet)
    {
        this.des_facet = des_facet;
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

    public String getGeo_facet ()
    {
        return geo_facet;
    }

    public void setGeo_facet (String geo_facet)
    {
        this.geo_facet = geo_facet;
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
        return "ClassPojo [per_facet = "+per_facet+", org_facet = "+org_facet+/**", column = "+column+**/", section = "+section+", abstract = "+abstracts+", source = "+source+", asset_id = "+asset_id+", media = "+media+", type = "+type+", title = "+title+", des_facet = "+des_facet+", uri = "+uri+", url = "+url+", adx_keywords = "+adx_keywords+", geo_facet = "+geo_facet+", id = "+id+", byline = "+byline+", published_date = "+published_date+", views = "+views+"]";
    }
}
