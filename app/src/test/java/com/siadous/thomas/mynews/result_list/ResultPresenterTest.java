package com.siadous.thomas.mynews.result_list;

import com.siadous.thomas.mynews.model.ArticleSearch.Docs;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;


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
    public void getMoreDataWithoutDate_callsModelGetResultList() {
        int pageNb = 3;
        String keyword = "trump";
        String category = "arts, politics";
        resultPresenter.getMoreDataWithoutDate(pageNb, category, keyword);
        Mockito.verify(resultListModel).getResultListWithoutDate(resultPresenter, pageNb, category, keyword);
    }

    @Test
    public void getMoreData_callsModelGetResultList() {
        int pageNb = 3;
        String keyword = "trump";
        String category = "arts, politics";
        int beginDate = 20190912;
        int endDate = 20191009;
        resultPresenter.getMoreData(pageNb, category, keyword, beginDate, endDate);
        Mockito.verify(resultListModel).getResultList(resultPresenter, pageNb, category, keyword, beginDate, endDate);
    }

    @Test
    public void requestDataFromServerWithoutDate_callsModelGetResultList() {
        String keyword = "trump";
        String category = "arts, politics";
        resultPresenter.requestDataFromServerWithoutDate(category, keyword);
        Mockito.verify(resultListModel).getResultListWithoutDate(resultPresenter, 1, category, keyword);
    }

    @Test
    public void requestDataFromServer_callsModelGetResultList() {
        String keyword = "trump";
        String category = "arts, politics";
        int beginDate = 20190912;
        int endDate = 20191009;
        resultPresenter.requestDataFromServer(category, keyword, beginDate, endDate);
        Mockito.verify(resultListModel).getResultList(resultPresenter, 1, category, keyword, beginDate, endDate);
    }

    @Test
    public void getMoreDataWithoutDate_ReturnsSuccessWithResult() {
        String keyword = "trump";
        String category = "arts, politics";

        Docs docs = new Docs();
        docs.set_id("23");
        docs.setSnippet("snippet");
        docs.setPub_date("23-12-2019");
        docs.setSource("bloomberg");

        final List<Docs> docsList = new ArrayList<>();
        docsList.add(docs);
        docsList.add(docs);
        docsList.add(docs);
        docsList.add(docs);
        docsList.add(docs);

        Mockito.doAnswer(new Answer<ResultContract.Model.OnFinishedListener>() {
            @Override
            public ResultContract.Model.OnFinishedListener  answer(InvocationOnMock invocation) throws Throwable {
                ResultContract.Model.OnFinishedListener callback = invocation.getArgument(0);
                callback.onFinished(docsList);
                return null;
            }
        }).when(resultListModel).getResultListWithoutDate(resultPresenter, 2, category, keyword);

        resultPresenter.getMoreDataWithoutDate(2, category, keyword);

        Mockito.verify(resultListView).setDataToRecyclerView(docsList);
    }


    @Test
    public void getMoreData_ReturnsSuccessWithResult() {
        String keyword = "trump";
        String category = "arts, politics";
        int beginDate = 20190912;
        int endDate = 20191009;

        Docs docs = new Docs();
        docs.set_id("23");
        docs.setSnippet("snippet");
        docs.setPub_date("23-12-2019");
        docs.setSource("bloomberg");

        final List<Docs> docsList = new ArrayList<>();
        docsList.add(docs);
        docsList.add(docs);
        docsList.add(docs);
        docsList.add(docs);
        docsList.add(docs);

        Mockito.doAnswer(new Answer<ResultContract.Model.OnFinishedListener>() {
            @Override
            public ResultContract.Model.OnFinishedListener  answer(InvocationOnMock invocation) throws Throwable {
                ResultContract.Model.OnFinishedListener callback = invocation.getArgument(0);
                callback.onFinished(docsList);
                return null;
            }
        }).when(resultListModel).getResultList(resultPresenter, 2, category, keyword, beginDate, endDate);

        resultPresenter.getMoreData(2, category, keyword, beginDate, endDate);

        Mockito.verify(resultListView).setDataToRecyclerView(docsList);
    }


}