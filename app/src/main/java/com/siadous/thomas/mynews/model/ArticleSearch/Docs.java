package com.siadous.thomas.mynews.model.ArticleSearch;

public class Docs {

    private String snippet;

    private String print_page;

   // private Keywords[] keywords;

    private String section_name;

    private String abstracts;

    private String source;

   // private String blog;

    private String uri;

    private String news_desk;

    private String pub_date;

    private Multimedia[] multimedia;

    private String word_count;

    private String lead_paragraph;

    private String type_of_material;

    private String web_url;

    private String _id;

    private String subsection_name;
/**
    private Headline headline;

    private Byline byline;
**/
    private String document_type;

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
/**
    public Keywords[] getKeywords ()
    {
        return keywords;
    }

    public void setKeywords (Keywords[] keywords)
    {
        this.keywords = keywords;
    }
**/
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
/**
    public String getBlog ()
    {
        return blog;
    }

    public void setBlog (String blog)
    {
        this.blog = blog;
    }
**/
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

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getSubsection_name ()
    {
        return subsection_name;
    }

    public void setSubsection_name (String subsection_name)
    {
        this.subsection_name = subsection_name;
    }
/**
    public Headline getHeadline ()
    {
        return headline;
    }

    public void setHeadline (Headline headline)
    {
        this.headline = headline;
    }

    public Byline getByline ()
    {
        return byline;
    }

    public void setByline (Byline byline)
    {
        this.byline = byline;
    }
**/
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
        return "ClassPojo [snippet = "+snippet+", print_page = "+print_page+/**", keywords = "+keywords+**/", section_name = "+section_name+", abstract = "+abstracts+", source = "+source+/** ", blog = "+blog+**/", uri = "+uri+", news_desk = "+news_desk+", pub_date = "+pub_date+", multimedia = "+multimedia+", word_count = "+word_count+", lead_paragraph = "+lead_paragraph+", type_of_material = "+type_of_material+", web_url = "+web_url+", _id = "+_id+", subsection_name = "+subsection_name+/**", headline = "+headline+", byline = "+byline+**/", document_type = "+document_type+"]";
    }
}
