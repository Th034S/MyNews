package com.siadous.thomas.mynews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TopStoriesData {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("last_updated")
    @Expose
    private String last_updated;
    @SerializedName("num_results")
    @Expose
    private float num_results;

    ArrayList < Object > results = new ArrayList< Object >();


    // Getter Methods

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getSection() {
        return section;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public float getNum_results() {
        return num_results;
    }

    // Setter Methods

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public void setNum_results(float num_results) {
        this.num_results = num_results;
    }
}