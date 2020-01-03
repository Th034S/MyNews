package com.siadous.thomas.mynews.most_popular_list;

import com.siadous.thomas.mynews.model.MostPopular.MostPopular;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;



public class MostPopularPresenterTest {

    private MostPopularContract.View mostPopularListView;
    private MostPopularContract.Model mostPopularListModel;
    private MostPopularPresenter mostPopularPresenter;


    @Before
    public void setUp() {
        mostPopularListView = Mockito.mock(MostPopularContract.View.class);
        mostPopularListModel = Mockito.mock(MostPopularContract.Model.class);
        mostPopularPresenter = new MostPopularPresenter(mostPopularListView, mostPopularListModel);
    }

    @Test
    public void getMoreData_callsModelGetMostPopularList() {
        int pageNb = 3;
        mostPopularPresenter.getMoreData(pageNb);
        Mockito.verify(mostPopularListModel).getMostPopularList(mostPopularPresenter, pageNb);
    }

    @Test
    public void requestDataFromServer_callsModelGetMostPopularList() {
        mostPopularPresenter.requestDataFromServer();
        Mockito.verify(mostPopularListModel).getMostPopularList(mostPopularPresenter, 1);
    }


    @Test
    public void getMoreData_ReturnsSuccessWithMostPopular() {
        MostPopular mostPopular = new MostPopular();
        mostPopular.setSection("politics");
        mostPopular.setAbstract("abstract");
        mostPopular.setPublished_date("23-12-2019");
        mostPopular.setByline("bloomberg");

        final List<MostPopular> mostPopularList = new ArrayList<>();
        mostPopularList.add(mostPopular);
        mostPopularList.add(mostPopular);
        mostPopularList.add(mostPopular);
        mostPopularList.add(mostPopular);
        mostPopularList.add(mostPopular);

        Mockito.doAnswer(new Answer<MostPopularContract.Model.OnFinishedListener>() {
            @Override
            public MostPopularContract.Model.OnFinishedListener  answer(InvocationOnMock invocation) throws Throwable {
                MostPopularContract.Model.OnFinishedListener callback = invocation.getArgument(0);
                callback.onFinished(mostPopularList);
                return null;
            }
        }).when(mostPopularListModel).getMostPopularList(mostPopularPresenter, 2);

        mostPopularPresenter.getMoreData(2);

        Mockito.verify(mostPopularListView).setDataToRecyclerView(mostPopularList);
    }

}