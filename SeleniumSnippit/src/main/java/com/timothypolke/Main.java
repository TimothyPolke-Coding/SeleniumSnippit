//created by Timothy Polke

package com.timothypolke;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Main {
	
	public static void main(String [] args) {
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream("C:/Users/TimothyPolke/Desktop/Snippit/config.properties"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		SeleniumDriver driver = new SeleniumDriver(properties.getProperty("driver_path"), 1L, 10L);

		//PERFORM SEARCH
		driver.getDriver().navigate().to(properties.getProperty("page_url"));
		driver.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("element_xpath"))));
		BufferedImage snippit = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver.getDriver(), driver.getDriver().findElement(By.xpath(properties.getProperty("element_xpath")))).getImage();
		try {
			ImageIO.write(snippit, "jpg", new File(properties.getProperty("image_path")));
		}
		catch(IOException e) {
		}
		driver.getDriver().quit();
		System.exit(0);
	}
}