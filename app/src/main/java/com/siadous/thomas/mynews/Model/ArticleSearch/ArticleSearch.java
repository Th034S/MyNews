package com.siadous.thomas.mynews.Model.ArticleSearch;

public class ArticleSearch {
    private String copyright;

    private ArticleSearchResponse response;

    private String status;

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public ArticleSearchResponse getResponse ()
    {
        return response;
    }

    public void setResponse (ArticleSearchResponse response)
    {
        this.response = response;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [copyright = "+copyright+", response = "+response+", status = "+status+"]";
    }
}
