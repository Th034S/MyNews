package com.siadous.thomas.mynews.top_stories_list;

import com.siadous.thomas.mynews.model.TopStories.TopStories;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TopStoriesPresenterTest {

    private TopStoriesContract.View topStoriesListView;
    private TopStoriesContract.Model topStoriesListModel;
    private TopStoriesPresenter topStoriesPresenter;


    @Before
    public void setUp() {
        topStoriesListView = Mockito.mock(TopStoriesContract.View.class);
        topStoriesListModel = Mockito.mock(TopStoriesContract.Model.class);
        topStoriesPresenter = new TopStoriesPresenter(topStoriesListView, topStoriesListModel);
    }

    @Test
    public void getMoreData_callsModelGetTopStoriesList() {
        int pageNb = 3;
        topStoriesPresenter.getMoreData(pageNb);
        Mockito.verify(topStoriesListModel).getTopStoriesList(topStoriesPresenter, pageNb);
    }

    @Test
    public void requestDataFromServer_callsModelGetTopStoriesList() {
        topStoriesPresenter.requestDataFromServer();
        Mockito.verify(topStoriesListModel).getTopStoriesList(topStoriesPresenter, 1);
    }


    @Test
    public void getMoreData_ReturnsSuccessWithTopStories() {
        TopStories topStories = new TopStories();
        topStories.setSection("politics");
        topStories.setShort_url("short-url");
        topStories.setCreated_date("23-12-2019");
        topStories.setItem_type("bloomberg");

        final List<TopStories> topStoriesList = new ArrayList<>();
        topStoriesList.add(topStories);
        topStoriesList.add(topStories);
        topStoriesList.add(topStories);
        topStoriesList.add(topStories);
        topStoriesList.add(topStories);

        Mockito.doAnswer(new Answer<TopStoriesContract.Model.OnFinishedListener>() {
            @Override
            public TopStoriesContract.Model.OnFinishedListener  answer(InvocationOnMock invocation) throws Throwable {
                TopStoriesContract.Model.OnFinishedListener callback = invocation.getArgument(0);
                callback.onFinished(topStoriesList);
                return null;
            }
        }).when(topStoriesListModel).getTopStoriesList(topStoriesPresenter, 2);

        topStoriesPresenter.getMoreData(2);

        Mockito.verify(topStoriesListView).setDataToRecyclerView(topStoriesList);
    }

}