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

public class Logout {
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
	 * 트랭글 웹 - 로그아웃 테스트
	 * 
	 */
	@Test
	public void Logout_Test() throws InterruptedException {

		// Xpath 경로 변수 선언
		String tranggleURL = "https://www.tranggle.com/";

		String tranggle_Home_Xpath = "//android.view.View[@content-desc=\"Tranggle\"]/android.widget.Image";

		String tranggle_Menu_Xpath = "//android.view.View[@content-desc=\"javascript:void(0);\"]";
		String tranggle_RLB_LoginText_Xpath = "//android.view.View[@content-desc=\"로그인\"]/android.widget.TextView";
		String tranggle_Loginpage_id_Srh_Xpath = "//android.view.View[@content-desc=\"아이디 찾기\"]/android.widget.TextView";

		String tranggle_ID_Input_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]";
		String tranggle_PW_Input_Xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]";

		String tranggle_Loginpage_LoginBtn = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.Button[1]";

		String tranggle_RLB_LogoutText_Xpath = "//android.view.View[@content-desc=\"로그아웃\"]/android.widget.TextView";

		// 트랭글 홈페이지로 이동
		driver.get(tranggleURL);

		// 페이지를 띄위기 위해, 잠시 10초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_10, TimeUnit.SECONDS);

		String tranggle_Home = driver.findElementByXPath(tranggle_Home_Xpath).getText();

		// 트랭글 홈페이지 정싱이동 확인
		if (tranggle_Home.equals("Tranggle")) {
			System.out.println("Logout_Test() - Tranggle Homepage Move Success : " + tranggle_Home);

		} else {
			System.out.println("Logout_Test() - Tranggle Homepage Move Failure : " + tranggle_Home);
			
			throw new InterruptedException("트랭글 홈화면 이동에 실패했습니다.");
		}

		// 홈페이지 > 홈 > 우측 > 상단 메뉴 아이콘 클릭
		System.out.println("오른쪽 상단 메뉴 누르기 전입니다.");

		MobileElement menu_Icon = driver.findElementByXPath(tranggle_Menu_Xpath);
		menu_Icon.click();

		System.out.println("오른쪽 상단 메뉴 클릭한 후입니다.");

		//RNB 화면 출력을 위해 3초 대기
		Thread.sleep(long_Sleep_Time);

		// RNB 화면 정상적으로 펼쳐져있는지 확인
		MobileElement Login_Text_Btn = driver.findElementByXPath(tranggle_RLB_LoginText_Xpath);

		String rnb_Login_Text = Login_Text_Btn.getText();

		// RNB 화면 > [로그인] 버튼이 있는지 확인
		if (rnb_Login_Text.equals("로그인")) {
			System.out.println("Logout_Test() - Tranggle RNB Open Success : " + rnb_Login_Text);
		} else {
			System.out.println("Logout_Test() - Tranggle RNB Open Failure : " + rnb_Login_Text);
			
			throw new InterruptedException("RNB 화면 펼침 실패 or RNB 화면 > [로그인] 버튼 앨리먼튼 찾기 실패했습니다.");
		}

		// 로그인 버튼을 클릭하여, 로그인 페이지로 이동
		Login_Text_Btn.click();

		// 로그인 페이지 이동을 위한 10초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_10, TimeUnit.SECONDS);

		String id_Search_Text = driver.findElementByXPath(tranggle_Loginpage_id_Srh_Xpath).getText();

		// 로그인 페이지 정상 이동 확인 체크
		if (id_Search_Text.equals("아이디 찾기")) {
			System.out.println("Logout_Test() - Tranggle Loginpage Move Success : " + id_Search_Text);
		} else {
			System.out.println("Logout_Test() - Tranggle Loginpage Move Failure : " + id_Search_Text);
			
			throw new InterruptedException("로그인 페이지 화면 이동 실패 or [아이디 찾기] 앨리먼트 찾기 실패했습니다.");
		}

		// 로그인페이지 > 아이디 정보를 입력
		MobileElement id_Input = driver.findElementByXPath(tranggle_ID_Input_Xpath);
		id_Input.sendKeys("koreatest67");

		// 로그인페이지 > 비밀번호 정보를 입력
		MobileElement pw_Input = driver.findElementByXPath(tranggle_PW_Input_Xpath);
		pw_Input.sendKeys("test1234@");

		Thread.sleep(normal_Sleep_Time_1500);

		// 로그인페이지 > [로그인] 버튼을 클릭하여, 로그인함
		MobileElement Login_Btn = driver.findElementByXPath(tranggle_Loginpage_LoginBtn);
		Login_Btn.click();

		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_10, TimeUnit.SECONDS);

		// 로그인되어, 트랭글 홈으로 화면이 이동되었는지 확인
		if (tranggle_Home.equals("Tranggle")) {
			System.out.println("Logout_Test() - Tranggle Homepage Move Success : " + tranggle_Home);
		} else {
			System.out.println("Logout_Test() - Tranggle Homepage Move Failure : " + tranggle_Home);
			
			throw new InterruptedException("로그인 실패 or 로그인 후 홈 화면 이동 실패했습니다.");
		}

		// 다시 홈페이지 > 홈 > 상단 > 메뉴 클릭
		System.out.println("로그인 후 메뉴 버튼 누르기 전입니다.");

		MobileElement Rmenu_Icon = driver.findElementByXPath(tranggle_Menu_Xpath);
		Rmenu_Icon.click();

		System.out.println("로그인 후 메뉴 버튼 누른 후 입니다.");

		// RNB 화면 펼쳐지기 위한 3초 대기
		Thread.sleep(long_Sleep_Time);

		// 정상적으로 로그인되었는지 확인을 위한 RNB 화면 > [로그아웃] 버튼 앨리먼트 요소 가져오기
		MobileElement Logout_Text_Btn = driver.findElementByXPath(tranggle_RLB_LogoutText_Xpath);

		// [로그아웃] 버튼 확인으로 정상 로그인 확인 체크
		if (Logout_Text_Btn.getText().equals("로그아웃")) {
			System.out.println("Logout_Test() - Tranggle Normal Login Success : " + Logout_Text_Btn.getText());
		} else {
			System.out.println("Logout_Test() - Tranggle Normal Login Failure : " + Logout_Text_Btn.getText());
			
			throw new InterruptedException("RNB 화면 > [로그아웃] 버튼 앨리먼트 찾기 실패 or 일반 로그인 실패했습니다.");
		}

		// 로그아웃 처리 확인을 위한 [로그아웃] 버튼 클릭
		System.out.println("Logout_Test() - 로그아웃 버튼 누르기 전입니다.");

		Logout_Text_Btn.click();

		System.out.println("Logout_Test() - 로그아웃 버튼 누른 후입니다.");

		// 로그아웃 처리를 위한 2초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_7, TimeUnit.SECONDS);

		System.out.println("Logout_Test() - 로그아웃 버튼 누른 후 메뉴 아이콘 체크 전입니다.");

		/*
		 * 로그아웃 처리 대기 후 강제로 트랭글 홈페이지로 이동한다. (이유 -> 로그아웃 시 화면 이동이 로그인페이지로 이동되어, 재이동 작업이
		 * 필요한데, 버튼 이동 없이 홈페이지 이동만 하면 됨으로 url 이동 코드로 작성)
		 */
		driver.get(tranggleURL);

		// 페이지를 띄위기 위해, 잠시 10초 대기
		driver.manage().timeouts().implicitlyWait(long_Sleep_Seconds_7, TimeUnit.SECONDS);

		// 홈페이지 > 상단 > 우측 > 메뉴 아이콘 클릭
		MobileElement menu_Icon_RCheck = driver.findElementByXPath(tranggle_Menu_Xpath);
		menu_Icon_RCheck.click();

		System.out.println("오른쪽 상단 메뉴 클릭한 후입니다.");

		// RNB 화면이 펼쳐지기 위한 3초 대기
		Thread.sleep(long_Sleep_Time);

		MobileElement Login_Text_Btn_RCheck = driver.findElementByXPath(tranggle_RLB_LoginText_Xpath);

		String Login_Text_Btn_Text_RCheck = Login_Text_Btn_RCheck.getText();

		// RNB 화면 > [로그인] 버튼 체크하여, 로그아웃 정상 처리 확인
		if (Login_Text_Btn_Text_RCheck.equals("로그인")) {
			System.out.println("Logout_Test() - Tranggle RNB Open & Logout Success : " + Login_Text_Btn_Text_RCheck);
		} else {
			System.out.println("Logout_Test() - Tranggle RNB Open & Logout Failure : " + Login_Text_Btn_Text_RCheck);
			
			throw new InterruptedException("RNB 화면 > [로그인] 버튼 앨리먼트 찾기 실패 or 로그아웃 처리되지 않았습니다.");
		}

	}

	@AfterClass
	public void EndDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
}
