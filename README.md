# run-adb-command-java
Java class to execute adb commands. For automation scripts (selenium, appium etc.) written in java

### Setup
1. Include class AdbCommand.java in the project
2. Pass the adb directory location as a parameter while creating an object of the class
3. Pass the desired adb command to method runCommand to execute this command

### Example
```
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Test {
	AndroidDriver driver;
	DesiredCapabilities dc = new DesiredCapabilities();

	public void RunADBCommand() throws InterruptedException, MalformedURLException {
		dc.setCapability("deviceName", "Nexus5");
		dc.setCapability("platformName", "Android");
		dc.setCapability("app", "/Users/gaurav.gulati/Downloads/Build_viki_3.6.apk");
		dc.setCapability("appPackage", "tv.accedo.vdkmob.viki");
		dc.setCapability("appActivity", ".activities.SplashActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ImageButton")));
		String adbDirectory = "/Users/gaurav.gulati/Library/Android/sdk/platform-tools/";
		AdbCommand command = new AdbCommand(adbDirectory);
		command.runCommand("adb reboot");
		Thread.sleep(20000); // Giving enough time for the device reboot to complete
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		command.runCommand("adb devices");
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ImageButton")));
		command.runCommand(
				"adb shell am start -a android.intent.action.VIEW -d 'https://www.youtube.com/watch?v=TcMBFSGVi1c'");

	}

}
```
