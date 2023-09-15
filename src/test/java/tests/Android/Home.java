package tests.Android;

import org.testng.annotations.Test;
import tests.ConfigAppium;

public class Home extends ConfigAppium {
    @Test
    public void C188() {
        homePage.chkBanner();
        // 팝업 이미지가 안잡혀서 이부분 추가 수정 필요
        // 일단 [닫기] 동작으로 진행
    }


    @Test
    public void C189() {
        homePage.rollingBanner();
//        homePage.clickBanner();
        // 이동된 이벤트 페이지 확인 추가 필요
//        homePage.pressBackButton();
    }

    @Test
    public void C190() {
        homePage.clickShortCut("카테고리");
        homePage.clickShortCut("스킨케어");

        homePage.checkBottomSheet();
        homePage.cmpCategoryContent();

        homePage.pressBackButton();
        homePage.pressBackButton();

    }

    @Test
    public void C191() {
        homePage.clickShortCut("화해랭킹");
        homePage.compareShortCut("카테고리별");
        homePage.clickHomeButton();
    }
    @Test
    public void C192() {
        homePage.clickShortCut("이너뷰티");
        homePage.compareShortCut("이너뷰티");

        homePage.cmpCategoryContent();
        homePage.pressBackButton();

        homePage.pressBackButton();
    }
    @Test
    public void C193() {
        homePage.clickShortCut("ONLY화해");
        homePage.compareShortCut("ONLY화해");
        homePage.clickHomeButton();
    }
    @Test
    public void C194() {
        homePage.clickShortCut("화해배송");
        homePage.compareShortCut("화해배송");
        homePage.clickHomeButton();
    }
    @Test
    public void C195() {
        homePage.clickShortCut("성분비슷템");
        homePage.similarItem();
        homePage.pressBackButton();
    }
    @Test
    public void C196() {
        homePage.swipeShortcut();

        homePage.clickShortCut("스킨케어");
        homePage.pressBackButton();
    }
    @Test
    public void C197() {
        homePage.compareShortCut("화해 고객들이 직접 선택한 랭킹");
        homePage.cmpRankContent();
        homePage.pressBackButton();
        homePage.compareShortCut("화해 고객들이 직접 선택한 랭킹");
    }

    @Test
    public void C198() {
        homePage.scrollToBottom();
        homePage.compareShortCut("나에게 맞는 제품 랭킹");
        homePage.clickShortCut("카테고리별");
        homePage.compareShortCut("카테고리별 랭킹");
        homePage.pressBackButton();
        homePage.compareShortCut("나에게 맞는 제품 랭킹");
    }


    @Test
    public void C199() {
        homePage.compareShortCut("나에게 맞는 제품 랭킹");
        homePage.clickShortCut("복합성");
        homePage.compareShortCut("피부별 랭킹");
        homePage.pressBackButton();
        homePage.compareShortCut("나에게 맞는 제품 랭킹");
    }

    @Test
    public void C200() {
        homePage.compareShortCut("나에게 맞는 제품 랭킹");
        homePage.clickShortCut("브랜드별");
        homePage.compareShortCut("브랜드 랭킹");
        homePage.pressBackButton();
        homePage.compareShortCut("나에게 맞는 제품 랭킹");
    }

    @Test
    public void C201() {
        homePage.compareShortCut("나에게 맞는 제품 랭킹");
        homePage.clickShortCut("10대");
        homePage.compareShortCut("연령대별 랭킹");
        homePage.pressBackButton();
        homePage.compareShortCut("나에게 맞는 제품 랭킹");
    }

    @Test
    public void C202() {
        homePage.clickShortCut("나에게 맞는 제품 랭킹 알고 싶다면?");
        homePage.checkSelectedTab("랭킹");
        homePage.clickHomeButton();
        homePage.checkSelectedTab("홈");
    }

    @Test
    public void C203() {
        homePage.scrollToId("화해쇼핑");
        homePage.compareShortCut("이제 쇼핑까지 한 번에, 화해쇼핑");
        homePage.cmpShoppingContent();
        homePage.pressBackButton();
        homePage.compareShortCut("이제 쇼핑까지 한 번에, 화해쇼핑");
    }
    @Test
    public void C204() {
        homePage.clickShortCut("이제 쇼핑까지 한 번에, 화해쇼핑");
        homePage.compareShortCut("쇼핑홈");
        homePage.clickHomeButton();
        homePage.checkSelectedTab("홈");
    }

    @Test
    public void C205(){
        homePage.scrollToId("뷰티ON");
        homePage.compareShortCut("뷰티ON");
        homePage.checkBeautyOn();
        homePage.pressBackButton();
        homePage.checkSelectedTab("홈");
    }

    @Test
    public void C206(){
        homePage.scrollToBottom();
        homePage.compareShortCut("친구에게 추천할래요");
        homePage.checkFooter();
    }

    @Test
    public void C207(){
        homePage.clickShortCut("샘플 체험");
        homePage.checkSelectedTab("샘플 체험");
    }

    @Test
    public void C208(){
        homePage.clickShortCut("이벤트");
        homePage.checkSelectedTab("이벤트");
        homePage.clickEvent();
        homePage.checkSelectedTab("이벤트");
    }
    @Test
    public void C209(){
        homePage.clickShortCut("랭킹");
        homePage.checkSelectedTab("랭킹");

        homePage.clickShortCut("카테고리별");
        homePage.cmpRankingTap("전체");

        homePage.pressBackButton();
    }

    @Test
    public void C210(){
        homePage.clickShortCut("카테고리 전체");
        homePage.clickShortCut("스킨케어");
        homePage.clickShortCut("전체");
        homePage.compareShortCut("스킨케어");

        homePage.cmpRankingTap("옵션");
        homePage.pressBackButton();
        homePage.pressBackButton();
    }

    @Test
    public void C211(){
        homePage.clickShortCut("피부별");
        homePage.clickShortCut("건성");

        homePage.cmpRankingTap("옵션");
        homePage.pressBackButton();

        homePage.clickShortCut("피부별 랭킹");
        homePage.compareShortCut("어떤 랭킹을 찾으세요?");

        homePage.clickShortCut("연령대별");
        homePage.compareShortCut("연령대별 랭킹");
    }

    @Test
    public void C212(){
        homePage.cmpRankingTap("옵션");
        homePage.pressBackButton();
        homePage.compareShortCut("연령대별 랭킹");
        homePage.pressBackButton();
    }

    @Test
    public void C213(){
        homePage.clickShortCut("조회수급상승");
        homePage.compareShortCut("조회수 급상승 랭킹");
        homePage.pressBackButton();
    }

    @Test
    public void C214(){
        homePage.clickShortCut("베스트신제품");
        homePage.compareShortCut("베스트 신제품 랭킹");
        homePage.pressBackButton();
    }

    @Test
    public void C215(){
        homePage.clickShortCut("쇼핑급상승");
        homePage.compareShortCut("쇼핑 급상승 랭킹");
        homePage.pressBackButton();
    }

    @Test
    public void C216(){
        homePage.clickShortCut("브랜드");
        homePage.clickShortCut("브랜드를 검색하세요");

        homePage.searchBrand();
        homePage.pressBackButton();
        homePage.pressBackButton();
    }

    @Test
    public void C217(){
        homePage.clickShortCut("어워드");
        homePage.clickShortCut("상반기 베스트 신제품");

        homePage.cmpRankingTap("어워드");

        homePage.pressBackButton();
        homePage.compareShortCut("상반기 베스트 신제품");

        homePage.pressBackButton();
    }

    @Test
    public void C218(){
        homePage.shoppingCart();
        homePage.compareShortCut("장바구니");
        homePage.pressBackButton();
    }
}
