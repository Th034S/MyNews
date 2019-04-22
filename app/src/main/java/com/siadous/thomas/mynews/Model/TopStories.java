package com.siadous.thomas.mynews.Model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.top_stories_list.TopStoriesDetailsFragment;

import java.util.ArrayList;

public class TopStories {

    @SerializedName("per_facet")
    private String[] per_facet;

    @SerializedName("subsection")
    private String subsection;

    @SerializedName("item_type")
    private String item_type;

    @SerializedName("org_facet")
    private String[] org_facet;

    @SerializedName("section")
    private String section;

    @SerializedName("abstract")
    private String abstracts;

    @SerializedName("title")
    private String title;

    @SerializedName("des_facet")
    private String[] des_facet;

    @SerializedName("url")
    private String url;

    @SerializedName("short_url")
    private String short_url;

    @SerializedName("material_type_facet")
    private String material_type_facet;

    @SerializedName("multimedia")
    private Multimedia[] multimedia;

    @SerializedName("geo_facet")
    private String[] geo_facet;

    @SerializedName("updated_date")
    private String updated_date;

    @SerializedName("created_date")
    private String created_date;

    @SerializedName("byline")
    private String byline;

    @SerializedName("published_date")
    private String published_date;

    @SerializedName("kicker")
    private String kicker;



    public TopStories(String[] per_facet, String subsection, String item_type, String[] org_facet, String section, String abstracts, String title, String[] des_facet, String url,
                      String short_url, String material_type_facet, Multimedia[] multimedia, String[] geo_facet, String updated_date, String created_date, String byline, String published_date, String kicker) {
        this.per_facet = per_facet;
        this.subsection = subsection;
        this.item_type = item_type;
        this.org_facet = org_facet;
        this.section = section;
        this.abstracts = abstracts;
        this.title = title;
        this.des_facet = des_facet;
        this.url = url;
        this.short_url = short_url;
        this.material_type_facet = material_type_facet;
        this.multimedia = multimedia;
        this.geo_facet = geo_facet;
        this.updated_date = updated_date;
        this.created_date = created_date;
        this.byline = byline;
        this.published_date = published_date;
        this.kicker = kicker;
    }


    public String[] getPer_facet ()
    {
        return per_facet;
    }

    public void setPer_facet (String[] per_facet)
    {
        this.per_facet = per_facet;
    }

    public String getSubsection ()
    {
        return subsection;
    }

    public void setSubsection (String subsection)
    {
        this.subsection = subsection;
    }

    public String getItem_type ()
    {
        return item_type;
    }

    public void setItem_type (String item_type)
    {
        this.item_type = item_type;
    }

    public String[] getOrg_facet ()
    {
        return org_facet;
    }

    public void setOrg_facet (String[] org_facet)
    {
        this.org_facet = org_facet;
    }

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

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getShort_url ()
    {
        return short_url;
    }

    public void setShort_url (String short_url)
    {
        this.short_url = short_url;
    }

    public String getMaterial_type_facet ()
    {
        return material_type_facet;
    }

    public void setMaterial_type_facet (String material_type_facet)
    {
        this.material_type_facet = material_type_facet;
    }

    public Multimedia[] getMultimedia ()
    {
        return multimedia;
    }

    public void setMultimedia (Multimedia[] multimedia)
    {
        this.multimedia = multimedia;
    }

    public String[] getGeo_facet ()
    {
        return geo_facet;
    }

    public void setGeo_facet (String[] geo_facet)
    {
        this.geo_facet = geo_facet;
    }

    public String getUpdated_date ()
    {
        return updated_date;
    }

    public void setUpdated_date (String updated_date)
    {
        this.updated_date = updated_date;
    }

    public String getCreated_date ()
    {
        return created_date;
    }

    public void setCreated_date (String created_date)
    {
        this.created_date = created_date;
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

    public String getKicker ()
    {
        return kicker;
    }

    public void setKicker (String kicker)
    {
        this.kicker = kicker;
    }

    @NonNull
    @Override
    public String toString()
    {
        return "TopMovies [per_facet = "+per_facet+", subsection = "+subsection+", item_type = "+item_type+", org_facet = "+org_facet+", section = "+section+", abstract = "+abstracts+", title = "+title+", des_facet = "+des_facet+", url = "+url+", short_url = "+short_url+", material_type_facet = "+material_type_facet+", multimedia = "+multimedia+", geo_facet = "+geo_facet+", updated_date = "+updated_date+", created_date = "+created_date+", byline = "+byline+", published_date = "+published_date+", kicker = "+kicker+"]";
    }

}
