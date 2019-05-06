package com.siadous.thomas.mynews.Model.Education;

import com.google.gson.annotations.SerializedName;

public class EducationResponse {

    @SerializedName("docs")
    private Education[] docs;


    public Education[] getDocs ()
    {
        return docs;
    }

    public void setDocs (Education[] docs)
    {
        this.docs = docs;
    }


}
