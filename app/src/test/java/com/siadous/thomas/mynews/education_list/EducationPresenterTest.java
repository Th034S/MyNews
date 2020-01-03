package com.siadous.thomas.mynews.education_list;

import com.siadous.thomas.mynews.model.Education.Docs;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

public class EducationPresenterTest {

    private EducationContract.View educationListView;
    private EducationContract.Model educationListModel;
    private EducationPresenter educationPresenter;


    @Before
    public void setUp() {
        educationListView = Mockito.mock(EducationContract.View.class);
        educationListModel = Mockito.mock(EducationContract.Model.class);
        educationPresenter = new EducationPresenter(educationListView, educationListModel);
    }

    @Test
    public void getMoreData_callsModelGetEducationList() {

        int pageNb = 3;
        educationPresenter.getMoreData(pageNb);
        Mockito.verify(educationListModel).getEducationList(educationPresenter, pageNb);
    }

    @Test
    public void requestDataFromServer_callsModelGetEducationList() {
        educationPresenter.requestDataFromServer();
        Mockito.verify(educationListModel).getEducationList(educationPresenter, 1);
    }

    @Test
    public void getMoreData_ReturnsSuccessWithDocs() {
        Docs docs = new Docs();
        docs.setid("23");
        docs.setSnippet("snippet");
        docs.setPub_date("23-12-2019");
        docs.setSource("bloomberg");

        final List<Docs> docsList = new ArrayList<>();
        docsList.add(docs);
        docsList.add(docs);
        docsList.add(docs);
        docsList.add(docs);
        docsList.add(docs);

        Mockito.doAnswer(new Answer<EducationContract.Model.OnFinishedListener>() {
            @Override
            public EducationContract.Model.OnFinishedListener  answer(InvocationOnMock invocation) throws Throwable {
                EducationContract.Model.OnFinishedListener callback = invocation.getArgument(0);
                callback.onFinished(docsList);
                return null;
            }
        }).when(educationListModel).getEducationList(educationPresenter, 2);

        educationPresenter.getMoreData(2);

        Mockito.verify(educationListView).setDataToRecyclerView(docsList);
    }

}