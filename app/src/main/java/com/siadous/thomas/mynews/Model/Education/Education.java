package com.siadous.thomas.mynews.Model.Education;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Education {
    @SerializedName("snippet")
    @Expose
    private String snippet;

    @SerializedName("print_page")
    @Expose
    private String print_page;

    @SerializedName("section_name")
    @Expose
    private String section_name;

    @SerializedName("abstracts")
    @Expose
    private String abstracts;

    @SerializedName("source")
    @Expose
    private String source;

    @SerializedName("blog")
    @Expose
    private String blog;

    @SerializedName("uri")
    @Expose
    private String uri;

    @SerializedName("news_desk")
    @Expose
    private String news_desk;

    @SerializedName("pub_date")
    @Expose
    private String pub_date;

    @SerializedName("multimedia")
    @Expose
    private Multimedia[] multimedia;

    @SerializedName("word_count")
    private String word_count;

    @SerializedName("lead_paragraph")
    private String lead_paragraph;

    @SerializedName("type_of_material")
    private String type_of_material;

    @SerializedName("web_url")
    private String web_url;

    @SerializedName("id")
    private String id;

    @SerializedName("subsection_name")
    @Expose
    private String subsection_name;

    @SerializedName("document_type")
    @Expose
    private String document_type;

    public Education() {
    }

    public Education(String snippet, String print_page, String section_name, String abstracts, String source, String blog, String uri, String news_desk, String pub_date, Multimedia[] multimedia, String word_count, String lead_paragraph, String type_of_material, String web_url, String id, String subsection_name, String document_type) {
        this.snippet = snippet;
        this.print_page = print_page;
        this.section_name = section_name;
        this.abstracts = abstracts;
        this.source = source;
        this.blog = blog;
        this.uri = uri;
        this.news_desk = news_desk;
        this.pub_date = pub_date;
        this.multimedia = multimedia;
        this.word_count = word_count;
        this.lead_paragraph = lead_paragraph;
        this.type_of_material = type_of_material;
        this.web_url = web_url;
        this.id = id;
        this.subsection_name = subsection_name;
        this.document_type = document_type;
    }

    public String getSnippet ()
    {
        return snippet;
    }

    public void setSnippet (String snippet)
    {
        this.snippet = snippet;
    }

    public String getPrint_page ()
    {
        return print_page;
    }

    public void setPrint_page (String print_page)
    {
        this.print_page = print_page;
    }


    public String getSection_name ()
    {
        return section_name;
    }

    public void setSection_name (String section_name)
    {
        this.section_name = section_name;
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

    public String getBlog ()
    {
        return blog;
    }

    public void setBlog (String blog)
    {
        this.blog = blog;
    }

    public String getUri ()
    {
        return uri;
    }

    public void setUri (String uri)
    {
        this.uri = uri;
    }

    public String getNews_desk ()
    {
        return news_desk;
    }

    public void setNews_desk (String news_desk)
    {
        this.news_desk = news_desk;
    }

    public String getPub_date ()
    {
        return pub_date;
    }

    public void setPub_date (String pub_date)
    {
        this.pub_date = pub_date;
    }

    public Multimedia[] getMultimedia ()
    {
        return multimedia;
    }

    public void setMultimedia (Multimedia[] multimedia)
    {
        this.multimedia = multimedia;
    }

    public String getWord_count ()
    {
        return word_count;
    }

    public void setWord_count (String word_count)
    {
        this.word_count = word_count;
    }

    public String getLead_paragraph ()
    {
        return lead_paragraph;
    }

    public void setLead_paragraph (String lead_paragraph)
    {
        this.lead_paragraph = lead_paragraph;
    }

    public String getType_of_material ()
    {
        return type_of_material;
    }

    public void setType_of_material (String type_of_material)
    {
        this.type_of_material = type_of_material;
    }

    public String getWeb_url ()
    {
        return web_url;
    }

    public void setWeb_url (String web_url)
    {
        this.web_url = web_url;
    }

    public String getid ()
    {
        return id;
    }

    public void setid (String _id)
    {
        this.id = id;
    }

    public String getSubsection_name ()
    {
        return subsection_name;
    }

    public void setSubsection_name (String subsection_name)
    {
        this.subsection_name = subsection_name;
    }


    public String getDocument_type ()
    {
        return document_type;
    }

    public void setDocument_type (String document_type)
    {
        this.document_type = document_type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [snippet = "+snippet+", print_page = "+print_page+", section_name = "+section_name+", abstracts = "+abstracts+", source = "+source+", blog = "+blog+", uri = "+uri+", news_desk = "+news_desk+", pub_date = "+pub_date+", multimedia = "+multimedia+", word_count = "+word_count+", lead_paragraph = "+lead_paragraph+", type_of_material = "+type_of_material+", web_url = "+web_url+", id = "+id+", subsection_name = "+subsection_name+", document_type = "+document_type+"]";
    }
}
