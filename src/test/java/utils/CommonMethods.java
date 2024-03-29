package utils;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonMethods {

	/**
	 * Thread.sleep abstracted away to avoid writing try-catch everywhere
	 */
	public void pauseForSecs(long mul) {
		try {
			Thread.sleep(mul * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Util method for working with a select dropdown
	 */
	public void selectOption(WebDriver driver, WebElement dropdownElement, String optionValue) {
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByValue(optionValue);
	}

	public void changeToLatestTab(WebDriver driver) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabs.size() - 1));
	}
}
