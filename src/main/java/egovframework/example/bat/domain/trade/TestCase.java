package egovframework.example.bat.domain.trade;

import java.io.FileReader;
import java.io.Reader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	JavascriptExecutor js;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

		baseUrl = "https://www.google.com/";
		js = (JavascriptExecutor) driver;
	}

	@Test
	public ResultVO testTestCase(FileVO file) throws Exception {
		ResultVO result = new ResultVO();
		LocalDateTime start = LocalDateTime.now();
		String shortMsg = "";

		try {
			Reader reader = new FileReader("C:\\egovframework\\upload\\" + file.getFileName());

			JSONParser parser = new JSONParser();
			JSONArray jsonArr = (JSONArray) parser.parse(parser.parse(reader).toString());
			reader.close();
			System.out.println("Size : " + jsonArr.size());
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject obj = (JSONObject) jsonArr.get(i);
				String value = (String) obj.get("value");
				String command = (String) obj.get("command");
				String target = (String) obj.get("target");
				target.replace("\\", "");

				System.out.println(value + " " + command + " " + target);

				if (command.equals("open")) {
					driver.get(target);
				} else if (command.equals("click")) {
					if (target.contains("name=")) {
						target = target.replace("name=", "");
					} else if (target.contains("link=")) {
						target = target.replace("link=", "");
						driver.findElement(By.linkText(target)).click();
					} else if (target.contains("xpath=")) {
						target = target.replace("xpath=", "");
						driver.findElement(By.xpath(target)).click();
					} else {
						driver.findElement(By.name(target)).click();
					}
				} else if (command.equals("type")) {
					if (target.contains("name=")) {
						target = target.replace("name=", "");
						driver.findElement(By.name(target)).clear();
						driver.findElement(By.name(target)).sendKeys(value);
					}
				} else if (command.equals("assertAlert") || command.equals("assertEquals")) {
					Thread.sleep(1000);
					if (isAlertPresent()) {
						if (!target.equals(closeAlertAndGetItsText())) {
							shortMsg = "There is no alert : " + target;
							break;
						}
					} else {
						shortMsg = "There is no alert : " + target;
						break;
					}
				} else {
					shortMsg = "Unknown command";

					break;
				}
			}
//			driver.get("http://ksh-starbucks-clone.herokuapp.com/");
//			driver.findElement(By.linkText("Sign in")).click();
//			driver.get("https://ksh-starbucks-clone.herokuapp.com/loginForm");
//			driver.findElement(By.name("id")).click();
//			driver.findElement(By.name("id")).clear();
//			driver.findElement(By.name("id")).sendKeys("test");
//			driver.findElement(By.name("password")).click();
//			driver.findElement(By.name("password")).clear();
//			driver.findElement(By.name("password")).sendKeys("test");
//			driver.findElement(By.xpath("//button[@onclick='login(this.form)']")).click();
//			assertEquals("test님 환영합니다.", closeAlertAndGetItsText());
//			driver.get("https://ksh-starbucks-clone.herokuapp.com/index");

		} catch (Exception e) {
			driver.close();
			String errormsg = e.getMessage();
			System.out.println(errormsg);
			shortMsg = errormsg.split("\n")[0];
			System.out.println(shortMsg);
			result.setResulttext(shortMsg);

		}
		LocalDateTime end = LocalDateTime.now();
		long resptime = ChronoUnit.MILLIS.between(start, end);
		System.out.println("resp time : " + resptime);
		result.setResptime((int) resptime);

		DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyyMMdd").withLocale(Locale.KOREA)
				.withZone(ZoneOffset.UTC);
		DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HHmmss").withLocale(Locale.KOREA)
				.withZone(ZoneOffset.UTC);

		result.setCdate(Integer.valueOf(start.format(dateformat)));
		result.setCtime(Integer.valueOf(start.format(timeformat)));
		
		if (shortMsg.equals("")) {
			result.setResult("TRUE");
			System.out.println("success!");
		} else {
			result.setResult("FALSE");
			System.out.println("fail!");
		}

		return result;
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
