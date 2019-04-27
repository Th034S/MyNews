package com.siadous.thomas.mynews.Model.MostPopular;

public class MostPopularList {

    private String copyright;

    private MostPopular[] mostPopular;

    private String num_results;

    private String status;

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public MostPopular[] getResults ()
    {
        return mostPopular;
    }

    public void setResults (MostPopular[] results)
    {
        this.mostPopular = results;
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
        return "ClassPojo [copyright = "+copyright+", results = "+mostPopular+", num_results = "+num_results+", status = "+status+"]";
    }
}
