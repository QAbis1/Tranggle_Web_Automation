package tranggle_web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Id_Search_1 {
	private AppiumDriver<MobileElement> driver;

	// sleep 타임 대기 변수 -> 앱 이동 및 동작 간에 대기하는 시간이 발샏하여, 전역변수 선언을 통해 공유하여 사용
	int short_Sleep_Time = 500;
	int short_Sleep_Time_1000 = 1000;
	int normal_Sleep_Time_1500 = 1500;
	int normal_Sleep_Time_2000 = 2000;
	int long_Sleep_Time = 3000;
	int long_Sleep_Time_5000 = 5000;

	// 타임 대기 시간 변수 선언
	int short_Sleep_Seconds_1 = 1;
	int short_Sleep_Seconds_2 = 2;
	int normal_Sleep_Seconds_3 = 3;
	int long_Sleep_Seconds_5 = 5;
	int long_Sleep_Seconds_7 = 7;
	int long_Sleep_Seconds_10 = 10;

	@BeforeClass
	public void SetDriver() throws MalformedURLException {

		String mcAPPPackage = "appPackage";
		String mcAPPActivity = "appActivity";

		String pName = "Android";
		String deviceName = "emulator-5554";
		String appPackage = "com.android.chrome";
		String appActivity = "com.google.android.apps.chrome.Main";

		String serverURL = "http://127.0.0.1:4723/wd/hub";

		boolean no_Reset = true;
		boolean full_Reset = false;

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, pName);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(mcAPPPackage, appPackage);
		capabilities.setCapability(mcAPPActivity, appActivity);
		capabilities.setCapability(MobileCapabilityType.NO_RESET, no_Reset);
		capabilities.setCapability(MobileCapabilityType.FULL_RESET, full_Reset);

		// Appium 드라이버 초기화
		URL appiumServerUrl = new URL(serverURL);
		driver = new AndroidDriver<>(appiumServerUrl, capabilities);

		System.out.println("SetDriver() - Driver Created - driver : " + driver);
	}

	/*
	 * 
	 * 트랭글 웹 - 아이디 찾기 화면 이동 확인 스크립트
	 * 
	 */
	@Test
	public void ID_Search_Move_Test() throws InterruptedException {

		// Xpath 변수 선언 정리
		String tranggleURL = "https://www.tranggle.com/";

		String tranggle_Home_Xpath = "//android.view.View[@content-desc=\"Tranggle\"]/android.widget.Image";

		String tranggle_Menu_Xpath = "//android.view.View[@content-desc=\"javascript:void(0);\"]";
		String tranggle_RLB_LoginText_Xpath = "//android.view.View[@content-desc=\"로그인\"]/android.widget.TextView";
		String tranggle_Loginpage_id_Srh_Xpath = "//android.view.View[@content-desc=\"아이디 찾기\"]/android.widget.TextView";

		String tranggle_ID_Input_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]";
		String tranggle_PW_Input_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]";

		String tranggle_Loginpage_LoginBtn = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.Button[1]";

		String tranggle_RLB_LogoutText_Xpath = "//android.view.View[@content-desc=\"로그아웃\"]/android.widget.TextView";

		String tranggle_ID_Search_MobileSearch_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ListView[1]/android.view.View[1]";

		String tranggle_ID_Search_Title_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView";

		// 트랭글 홈페이지로 이동
		driver.get(tranggleURL);

		// 페이지를 띄위기 위해, 잠시 5초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_10, TimeUnit.SECONDS);

		String tranggle_Home = driver.findElementByXPath(tranggle_Home_Xpath).getText();

		// 트랭글 홈페이지 이동 확인
		if (tranggle_Home.equals("Tranggle")) {
			System.out.println("ID_Search_Move_Test() - Tranggle Homepage Move Success : " + tranggle_Home);

		} else {
			System.out.println("ID_Search_Move_Test() - Tranggle Homepage Move Failure : " + tranggle_Home);

			throw new InterruptedException("트랭글 홈페이지 이동 실패했습니다.");
		}

		// 홈페이지 > 홈 > 우측 상단 메뉴 아이콘 버튼 엘리먼트 요소 가져오기
		System.out.println("오른쪽 상단 메뉴 누르기 전입니다.");

		MobileElement menu_Icon = driver.findElementByXPath(tranggle_Menu_Xpath);

		// 메뉴 아이콘 크릭
		menu_Icon.click();

		System.out.println("오른쪽 상단 메뉴 클릭한 후입니다.");

		// 우측 RNB 화면 펼쳐질 시간을 위해 2초 대기
		Thread.sleep(long_Sleep_Time);

		// RNB 화면 > [로그인] 버튼 엘리먼트 요소 가져오기
		MobileElement Login_Text_Btn = driver.findElementByXPath(tranggle_RLB_LoginText_Xpath);

		String rnb_Login_Text = Login_Text_Btn.getText();

		// RNB 화면 오픈 및 [로그인] 버튼 확인을 위한 체크
		if (rnb_Login_Text.equals("로그인")) {
			System.out.println("ID_Search_Move_Test() - Tranggle RNB Open Success : " + rnb_Login_Text);
		} else {
			System.out.println("ID_Search_Move_Test() - Tranggle RNB Open Failure : " + rnb_Login_Text);

			throw new InterruptedException("RNB 화면 오픈 or RNB 화면 > [로그인] 버튼 앨리먼트 찾기 실패했습니다.");
		}

		// 펼쳐진게 확인 후 [로그인] 버튼을 클릭
		Login_Text_Btn.click();

		// 로그인 페이지 이동을 위한 10초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_10, TimeUnit.SECONDS);

		// 로그인 페이지 정상 이동 확인을 위한 로그인 페이지 > [아이디찾기] 버튼 앨리먼트 요소 가져오기
		MobileElement id_Search_Btn = driver.findElementByXPath(tranggle_Loginpage_id_Srh_Xpath);

		String id_Search_Text = id_Search_Btn.getText();

		// [아이디찾기] 버튼 확인 체크 - 이것으로 로그인 페이지 정상 이동 여부를 확인
		if (id_Search_Text.equals("아이디 찾기")) {
			System.out.println("ID_Search_Move_Test() - Tranggle Loginpage Move Success : " + id_Search_Text);
		} else {
			System.out.println("ID_Search_Move_Test() - Tranggle Loginpage Move Failure : " + id_Search_Text);

			throw new InterruptedException("트랭글 홈페이지 이동 실패했습니다.");

		}

		// 로그인페이지 > [아이디찾기] 버튼을 클릭
		id_Search_Btn.click();

		// 아이디찾기 페이지 이동을 위한 5초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_5, TimeUnit.SECONDS);

		// 아이디찾기 페이지 정사이동 확인을 위한 아이디 찾기 화면 > 타이틀 문구 앨리먼트 요소 가져오기
		MobileElement id_Search_Title = driver.findElementByXPath(tranggle_ID_Search_Title_Xpath);
		String id_Search_Title_Text = id_Search_Title.getText();

		// 아이디찾기 페이지로 정상이동 되었는지 확인을 위한 타이틀 문구 확인 -> 성공 시 아아디찾기 화면 정상 이동 - PASS
		if (id_Search_Title_Text.equals("아이디 찾기")) {
			System.out.println("ID_Search_Move_Test() - Tranggle ID_Search_Move Success : " + id_Search_Title_Text);
		} else {
			System.out.println("ID_Search_Move_Test() - Tranggle ID_Search_Move Failure : " + id_Search_Title_Text);

			throw new InterruptedException("아이디찾기 페이지로 이동 실패 및 타이틀 문구 앨리먼트 찾기 실패했습니다.");
		}
	}

	@AfterClass
	public void EndDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
}
