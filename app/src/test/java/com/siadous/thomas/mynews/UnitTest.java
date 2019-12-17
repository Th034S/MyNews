package com.siadous.thomas.mynews;

import com.siadous.thomas.mynews.result_list.ResultContract;
import com.siadous.thomas.mynews.result_list.ResultModel;
import com.siadous.thomas.mynews.top_stories_list.TopStoriesContract;
import com.siadous.thomas.mynews.top_stories_list.TopStoriesModel;
import static org.mockito.Mockito.*;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {

    @Test
    public void getTopStories_isNotCorrect() {
        int numberOfArticle;
        TopStoriesContract.Model.OnFinishedListener onFinishedListener = mock(TopStoriesContract.Model.OnFinishedListener.class);
        TopStoriesModel topStoriesModel = mock(TopStoriesModel.class);

        numberOfArticle = topStoriesModel.getTopStoriesList(onFinishedListener, 1);
        assertNotEquals(-1, numberOfArticle);
    }

    @Test
    public void getResultListWithoutDate_isNotCorrect() {
        String keyword = "paris";
        String categories = "politics, business";
        int numberOfArticle;
        ResultContract.Model.OnFinishedListener onFinishedListener = null;

        ResultModel resultModel = new ResultModel();

        numberOfArticle = resultModel.getResultListWithoutDate(onFinishedListener, 1, keyword, categories);
        assertNotEquals(-1, numberOfArticle);
    }

    @Test
    public void ResultList_isFilled() {
        String keyword = "paris";
        String categories = "politics, business";
        int numberOfArticle;
        int beginDate = 20190315;
        int endDate = 20190923;
        ResultContract.Model.OnFinishedListener onFinishedListener = null;

        ResultModel resultModel = new ResultModel();

        numberOfArticle = resultModel.getResultList(onFinishedListener, 1, keyword, categories, beginDate, endDate);
        assertNotEquals(-1, numberOfArticle);
    }
}