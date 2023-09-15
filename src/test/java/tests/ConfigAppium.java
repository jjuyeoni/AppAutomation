package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageObjects.Android.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import testrail.TestRailAPI;
import tests.Android.*;

public class ConfigAppium {

    public AndroidDriver androidDriver;
    public AppiumDriverLocalService service;

    /** TESTRAIL **/
    private TestRailAPI tr;
    public String trAddress = "https://birdview.testrail.io";
    public String trUser = "qa3@birdview.kr";
    public String trPassword = "Birdviewqa1!";
    public String projectName = "자동화 테스트";
    public String runName = null;

    /** Page Objects **/
    public LoginPage loginPage;
    public HomePage homePage;
    public BenefitPage benefitPage;
    public ShoppingPage shoppingPage;
    public MyPage myPage;
    public SearchPage searchPage;
    public ProductPage productPage;
    public DetailPage detailPage;
    public SamplePage samplePage;

    public CanRecordScreen screenRecorder;


    @BeforeSuite
    public void init() {
        runName = "Android 8.32.0 RC1";

        tr = TestRailAPI.getInstance();
        tr.connectTestRail(trAddress,trUser,trPassword);
        tr.setTestRailRun(projectName,runName);
    }

    @BeforeClass
    public void ConfigureAppium() throws MalformedURLException {
        service = new AppiumServiceBuilder().withAppiumJS(new File("//opt//homebrew//lib//node_modules//appium//build//lib//main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("R3CN907CG2L");
        options.setChromedriverExecutable("//Users//juyeon//Downloads//chromedriver");
        options.setApp("//Users//juyeon//Automation//hwahae-production-release.apk");

        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // AndroidDriver를 CanRecordScreen으로 형변환
        //screenRecorder = (CanRecordScreen) androidDriver;

        tr = TestRailAPI.getInstance();

        loginPage = new LoginPage(androidDriver);
        homePage = new HomePage(androidDriver);
        benefitPage = new BenefitPage(androidDriver);
        shoppingPage = new ShoppingPage(androidDriver);
        myPage = new MyPage(androidDriver);
        searchPage = new SearchPage(androidDriver);
        productPage = new ProductPage(androidDriver);
        detailPage = new DetailPage(androidDriver);
        samplePage = new SamplePage(androidDriver);

        // login class 에서 화면 녹화 시작
        if(this.getClass() == Login.class)
            screenRecorder.startRecordingScreen();

        if (this.getClass() != Login.class && this.getClass() != Home.class) {
            loginPage.clickEmailSignIn();
            loginPage.setIdField("qa4@birdview.kr");
            loginPage.setPasswordField("birdviewqa1!");
            homePage.chkBanner();
        }
        else if(this.getClass() == Home.class){
            loginPage.clickEmailSignIn();
            loginPage.setIdField("qa4@birdview.kr");
            loginPage.setPasswordField("birdviewqa1!");
        }
    }


    @AfterMethod
    public void afterMethod(Method caller, ITestResult result) {
        int caseId = Integer.parseInt(result.getName().replace("C", ""));
        tr.setResult(caseId, result);
    }

    @AfterClass
    public void tearDown(){
        androidDriver.quit();
        service.stop();

    }

    //@AfterSuite
    public void afterSuite() throws IOException {
        // 화면 녹화 중지
        String base64Recording = screenRecorder.stopRecordingScreen();

        // 녹화된 동영상을 파일로 저장
        saveRecordingToFile(base64Recording, "screenRecording.mp4");
    }

    private static void saveRecordingToFile(String base64Recording, String filePath) throws IOException {
        byte[] videoData = java.util.Base64.getDecoder().decode(base64Recording);
        try (FileOutputStream stream = new FileOutputStream(filePath)) {
            stream.write(videoData);
        }
        System.out.println("녹화된 동영상을 " + filePath + " 파일로 저장했습니다.");
    }
}
