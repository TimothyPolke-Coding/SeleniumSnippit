//created by Timothy Polke

package com.timothypolke;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.NoSuchElementException;

public class SeleniumDriver {
	
	private WebDriver driver = null;
	private Wait wait = null;
	
	public SeleniumDriver(String path, long polling, long timeout) {
		setDriver(path);
		setWait(polling, timeout);
	}
	
	public void setDriver(String path){
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver(options);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setWait(long polling, long timeout) {
		wait = new FluentWait<WebDriver>(driver)
		.withTimeout(Duration.ofSeconds(timeout))
		.pollingEvery(Duration.ofSeconds(polling))
		.ignoring(NoSuchElementException.class);
	}
	
	public Wait getWait(){ 
		return wait;
	}
}