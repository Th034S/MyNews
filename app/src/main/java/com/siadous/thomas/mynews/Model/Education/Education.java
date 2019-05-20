package com.siadous.thomas.mynews.Model.Education;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Education {
    @SerializedName("copyright")
    private String copyright;

    @SerializedName("response")
    private List<EducationResponse> response;

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

    public List<EducationResponse> getResponse ()
    {
        return response;
    }

    public void setResponse (List<EducationResponse> response)
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
