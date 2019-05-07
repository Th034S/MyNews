package com.siadous.thomas.mynews.Model.Education;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EducationResponse {

    @SerializedName("docs")
    @Expose
    private List<Education> docs = null;


    public List<Education> getDocs ()
    {
        return docs;
    }

    public void setDocs (List<Education> docs)
    {
        this.docs = docs;
    }


}
