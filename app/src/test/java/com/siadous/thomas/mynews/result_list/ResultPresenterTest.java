package com.siadous.thomas.mynews.result_list;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class ResultPresenterTest {

    private ResultContract.View resultListView;
    private ResultContract.Model resultListModel;
    private ResultPresenter resultPresenter;


    @Before
    public void setUp() {
        resultListView = Mockito.mock(ResultContract.View.class);
        resultListModel = Mockito.mock(ResultContract.Model.class);
        resultPresenter = new ResultPresenter(resultListView, resultListModel);
    }


    @Test
    public void getMoreData_callsModelGetResultList() {
        int pageNb = 3;
        String keyword = "trump";
        String category = "arts, politics";
        resultPresenter.getMoreDataWithoutDate(pageNb, category, keyword);
        Mockito.verify(resultListModel).getResultListWithoutDate(resultPresenter, pageNb, category, keyword);
    }

    @Test
    public void requestDataFromServer_callsModelGetResultList() {
        String keyword = "trump";
        String category = "arts, politics";
        resultPresenter.requestDataFromServerWithoutDate(category, keyword);
        Mockito.verify(resultListModel).getResultListWithoutDate(resultPresenter, 1, category, keyword);
    }

}