package org.webservice

import org.openqa.selenium.WebElement

interface WebServiceI {
    WebElement findElement(String xpath)
    boolean waitElementToBeClickable(WebElement element, Integer timeout)
    boolean click(WebElement element)
    List<WebElement> findElements(String xpath)
    WebElement findElementInElement(WebElement parentElement, String xpath)
    String getText(WebElement element)
    boolean quiteWebDriver()
    String getAttribute(WebElement element, String attribute)
    boolean moveToElement(WebElement element)
    boolean navigateToPage(String URL)

}