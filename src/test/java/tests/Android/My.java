package tests.Android;

import org.testng.annotations.Test;
import tests.ConfigAppium;

public class My extends ConfigAppium {

    @Test
    public void C228() {
        // 마이페이지 탭으로 이동
        myPage.clickToText("마이");
        myPage.modifyPhoto();

    }

    @Test
    public void C229() {
        myPage.modifyProfile();
    }

    @Test
    public void C230() {
        myPage.modifyAddress("test");
    }

    @Test
    public void C231() {
        myPage.clickToText("홈");
        myPage.clickToText("이벤트");

        myPage.checkAddress();

        myPage.pressBackButton();
        myPage.pressBackButton();
    }

    @Test
    public void C232() {
        myPage.clickToText("마이");
        myPage.modifyAddress("주소");
    }

    @Test
    public void C233() {
        myPage.clickToText("내 리뷰");
        myPage.compareText("내가 쓴 리뷰");
        myPage.pressBackButton();
    }

    @Test
    public void C234() {
        myPage.clickToText("스크랩");
        myPage.compareText("스크랩");
        myPage.pressBackButton();
    }

    @Test
    public void C235() {
        myPage.clickToText("화해 포인트");
        myPage.compareText("사용 가능 포인트");
        myPage.pressBackButton();
    }

    @Test
    public void C236() {
        myPage.clickToText("쿠폰함");
        myPage.compareText("보유 쿠폰");
        myPage.pressBackButton();
    }

    @Test
    public void C237() {
        myPage.clickToText("주문/배송");
        myPage.compareText("주문/배송 조회");
        myPage.pressBackButton();
    }

    @Test
    public void C238() {
        myPage.clickToText("브랜드");
        myPage.compareText("즐겨찾는 브랜드");

        myPage.clickMagnifier();

        myPage.pressBackButton();
    }

    @Test
    public void C239(){
        myPage.clickToText("제품");
        myPage.clickFolder("product");
        myPage.pressBackButton();
    }

    @Test
    public void C240(){
        myPage.clickToText("성분");
        myPage.clickFolder("ingredient");
        myPage.pressBackButton();
    }

    @Test
    public void C241(){
        myPage.clickToText("찜한 상품");
        myPage.compareText("찜한상품");
        myPage.pressBackButton();
    }

    @Test
    public void C242(){
        myPage.scrollToBottom();
        myPage.clickToText("사용자");
        myPage.compareText("소식받는 사용자");

        myPage.clickUser();

        myPage.pressBackButton();
    }

    @Test
    public void C243(){
        myPage.clickToText("에디터");
        myPage.compareText("소식받는 에디터");

        myPage.clickEditer();
        myPage.pressBackButton();
    }

    @Test
    public void C244(){
        myPage.clickToText("이벤트 신청 내역");
        myPage.compareText("이벤트 신청 내역");
        myPage.pressBackButton();
    }

    @Test
    public void C245(){
        myPage.clickToText("찜한 이벤트");
        myPage.compareText("찜한 이벤트");
        myPage.pressBackButton();
    }

    @Test
    public void C246(){
        myPage.clickToText("리서치 패널 등록");
        myPage.compareText("리서치 패널 등록하기");
        myPage.pressBackButton();
    }

    @Test
    public void C247(){
        myPage.clickToText("출석 체크");
        myPage.compareText("출석 체크"); // N월 출석체크 확인할 수 있게 변경하기
        myPage.pressBackButton();
    }

    @Test
    public void C248(){
        myPage.clickToText("발색 리뷰 쓰기");
        myPage.compareText("이벤트");

        myPage.checkColorReview();
        myPage.pressBackButton();
    }

    @Test
    public void C249(){
        myPage.clickToText("친구 초대");
//        myPage.compareText("친구 초대");
        myPage.pressBackButton();
    }

    /** 8월까지 진행하는 이벤트 **/
    @Test
    public void C250(){
        myPage.clickToText("영양제 리뷰 쓰기");
        myPage.compareText("이너뷰티 리뷰 쓰면 최대 63,000P 적립!");
        myPage.pressBackButton();
    }

    @Test
    public void C251(){
        myPage.clickToText("문의하기!");
        myPage.compareText("문의하기");
        myPage.pressBackButton();
    }

}
