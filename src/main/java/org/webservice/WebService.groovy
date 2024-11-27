package org.webservice

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import java.time.Duration

class WebService implements WebServiceI {
    WebDriver driver;

    void openBrowser() {
        driver = new ChromeDriver()
        driver.manage().window().maximize()
    }

    boolean quiteWebDriver() {
        try {
            driver.quit()
            return true
        } catch (Exception e) {
            println "Can't quite drive"
            return false
        }
    }

    boolean navigateToPage(String URL) {
        try {
            driver.get(URL)
            return true
        }
        catch (Exception e) {
            println("Can't navigate to the page")
            return false
        }
    }

    WebElement findElement(String xpath) {
        try{
            return driver.findElement(By.xpath(xpath))
        }
        catch (Exception e) {
            println("Can not find Element by xpath - $xpath")
        }
        return null

    }

    List<WebElement> findElements(String xpath) {
        try {
            return driver.findElements(By.xpath(xpath))
        } catch(Exception e) {
            println("cant find elements with xpath: $xpath")
            return null
        }

    }

    WebElement findElementInElement(WebElement parentElement, String xpath){
        try{
            return parentElement.findElement(By.xpath(xpath))
        } catch(Exception e){
            println("can't find element with xpath: $xpath in element")
            return null
        }

    }

    boolean click(WebElement element) {
        try {
            element.click()
            return true
        } catch (Exception e) {
           println("Can't click element")
            return false
        }
    }

    boolean click(String xpath) {
        WebElement element = findElement(xpath)
        if (!element) {
            return false
        }
        return click(element)
    }


    String getText(WebElement element) {
        try {
            return element.getText()
        } catch (Exception e) {
            println("Can not get text")
            return null
        }
    }

    String getText(String xpath){
      return getText(findElement(xpath))
    }

    String getAttribute(WebElement element, String attribute){
        try{
            return element.getDomAttribute(attribute)
        } catch (Exception e){
            println("Can't get attribute")
            return false
        }
    }

    String getAttribute(String xpath, String attribute){
        try{
            return findElement(xpath).getDomAttribute(attribute)
        } catch (Exception e){
            println("Can't get attribute")
            return false
        }
    }

    Actions getActions() {
        return new Actions(driver)
    }

    boolean moveToElement(WebElement element) {
        try{
            getActions().moveToElement(element).perform()
            return true
        } catch (Exception e){
            println("Can't move to element")
            return false
        }

    }

    boolean moveToElement(String xpath) {
       def element = findElement(xpath)
        return element ? moveToElement(element) : false
    }

    WebDriverWait getWait(Integer timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
    }

    boolean waitElementToBeClickable(WebElement element, Integer timeout){
        try {
            getWait(timeout).until(ExpectedConditions.elementToBeClickable(element))
            return true
        }
        catch(Exception e) {
            println("Element is not clickable")
            return false
        }
    }

    boolean waitElementToBeClickable(String xpath, Integer timeout){
        try {
            getWait(timeout).until(ExpectedConditions.elementToBeClickable(findElement(xpath)))
            return true
        }
        catch(Exception e) {
            println("Element is not clickable")
            return false
        }
    }
}
