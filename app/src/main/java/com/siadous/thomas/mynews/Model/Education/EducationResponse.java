package com.siadous.thomas.mynews.Model.Education;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EducationResponse {

    @SerializedName("educations")
    @Expose
    private ArrayList<Education> educations;


    public ArrayList<Education> getEducations ()
    {
        return educations;
    }

    public void setDocs (ArrayList<Education> educations)
    {
        this.educations = educations;
    }


}
