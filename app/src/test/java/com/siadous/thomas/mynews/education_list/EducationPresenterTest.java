package com.siadous.thomas.mynews.education_list;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

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


}