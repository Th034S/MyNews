package com.siadous.thomas.mynews.Model.MostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MostPopularList {

    @Expose
    @SerializedName("copyright")
    private String copyright;

    @Expose
    @SerializedName("results")
    private List<MostPopular> results;

    @Expose
    @SerializedName("num_results")
    private String num_results;

    @Expose
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

    public List<MostPopular> getResults ()
    {
        return results;
    }

    public void setResults (List<MostPopular> results)
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

    @Override
    public String toString()
    {
        return "ClassPojo [copyright = "+copyright+", results = "+results+", num_results = "+num_results+", status = "+status+"]";
    }
}
