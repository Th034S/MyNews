package com.siadous.thomas.mynews.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TopStoriesListResponse {

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("last_updated")
    private String last_updated;

    @SerializedName("section")
    private String section;

    @SerializedName("results")
    private List<TopStories> results;

    @SerializedName("num_results")
    private String num_results;

    @SerializedName("status")
    private String status;

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public String getLast_updated ()
    {
        return last_updated;
    }

    public void setLast_updated (String last_updated)
    {
        this.last_updated = last_updated;
    }

    public String getSection ()
    {
        return section;
    }

    public void setSection (String section)
    {
        this.section = section;
    }

    public List<TopStories> getResults ()
    {
        return results;
    }

    public void setResults (List<TopStories> results)
    {
        this.results = results;
    }

    public String getNum_results ()
    {
        return num_results;
    }

    public void setNum_results (String num_results)
    {
        this.num_results = num_results;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

}
