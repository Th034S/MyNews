package com.siadous.thomas.mynews.Model.ArticleSearch;

import java.util.List;

public class ArticleSearchResponse {

    private List<Docs> docs;


    public List<Docs> getDocs ()
    {
        return docs;
    }

    public void setDocs (List<Docs> docs)
    {
        this.docs = docs;
    }



}
