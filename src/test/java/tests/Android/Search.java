package tests.Android;

import org.testng.ITestResult;
import org.testng.annotations.Test;
import tests.ConfigAppium;

public class Search extends ConfigAppium {

    @Test
    public void C252(){
        searchPage.clickToText("홈");
        searchPage.clickSearch();
    }

    @Test
    public void C253(){
        searchPage.clickToText("성분으로 검색");
        searchPage.compareText("성분 선택");
        searchPage.pressBackButton();
    }
    @Test
    public void C254(){
        searchPage.clickToText("즐겨찾는 브랜드");
        searchPage.compareText("즐겨찾는 브랜드 제품검색");
        searchPage.pressBackButton();
    }

    @Test
    public void C255(){
        searchPage.clickToText("성분사전");
        searchPage.compareText("성분 사전");
        searchPage.pressBackButton();
    }

    @Test
    public void C256(){
        searchPage.bestItem();
        searchPage.pressBackButton();
    }

    //@Test
    public void C257(){
        searchPage.clickBottomBanner();
        searchPage.clickSearch();
    }

    @Test
    public void C258(){
        searchPage.clickSearchTextView();
        searchPage.clickFirstRecommendItem();
        searchPage.pressBackButton();
    }

    @Test
    public void C259(){
        searchPage.clickSearchTextView();
        searchPage.inputSearchTextView();
        searchPage.pressBackButton();
    }

    @Test
    public void C260(){
        searchPage.cmpItem();
        searchPage.pressBackButton();
    }

    @Test
    public void C261(){
        searchPage.clickToText("제품");
        searchPage.cmpItemDetail();

        searchPage.pressBackButton();
        searchPage.pressBackButton();
    }

    @Test
    public void C262(){
        searchPage.clickToText("카테고리");
        searchPage.clickCategory();
        searchPage.compareText("스킨케어");
        searchPage.pressBackButton();
    }

    @Test
    public void C263(){
        searchPage.clickToText("등록 요청");
        searchPage.compareText("화해에 없는 화장품/이너뷰티 제품의 정보를 요청해주세요");
        searchPage.pressBackButton();
    }

    @Test
    public void C264(){
        searchPage.clickToText("최근 본 제품");
        searchPage.compareText("님이 최근 본 제품");

        searchPage.pressBackButton();
        searchPage.pressBackButton();
    }
}
