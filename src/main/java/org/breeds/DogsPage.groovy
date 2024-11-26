package org.breeds


import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import org.openqa.selenium.support.ui.ExpectedConditions

class DogsPage {

    static String breedBoxXpath ="//div[contains(@class, 'dog-breed-card')]"
    static String breedDescriptionXpath = "//section[@class='breed-description mt-3']"
    static String breedDescriptionXpath2 = "//div[@class='Supplies-content']"
    static String breedNameXpath = ".//h3"
    static String imageXpath = ".//img"
    static String linkXpath = ".//a"
    static String nextButtonXpath = "//a[@rel='next']"
    static String acceptCookiesButtonXpath = "//button[@id='onetrust-accept-btn-handler']"

    String getButtonText(WebDriver driver){
        WebElement button = driver.findElement(By.xpath(nextButtonXpath));

        // Move to the element to make it visible/interactable
        Actions actions = new Actions(driver)
        actions.moveToElement(button).perform()

        return button.getText().toString()
    }

   List<Breed> collectBreedsInfo(WebDriver driver){
       List<Breed> result = []
       List<WebElement> breedBoxes = driver.findElements(By.xpath(breedBoxXpath))
       for (int i =0; i< breedBoxes.size(); i++){
           WebElement breedBox = breedBoxes.get(i)
           Breed breed = new Breed()
           breed.name = breedBox.findElement(By.xpath(breedNameXpath)).getText()
           breed.image = breedBox.findElement(By.xpath(imageXpath)).getAttribute("src").toString()
           breed.link = breedBox.findElement(By.xpath(linkXpath)).getAttribute("href").toString()

          result.add(breed)

       }
       return result
   }

    List<Breed> collectAllBreeds(WebDriver driver) {
        List<Breed> allBreeds = []
        for (int i = 0; i < 50; i++) {
            allBreeds.addAll(collectBreedsInfo(driver))
            clickNextButton(driver)

            if (!clickNextButton(driver)){
                break
            }
        }
        return allBreeds
    }

    List<Breed> getAllBreedsWithArticle(WebDriver driver) {
        List<Breed> allBreeds = collectAllBreeds(driver)

        for (int i = 0; i < allBreeds.size(); i++) {
            Breed breed = allBreeds.get(i)
            driver.get(breed.link)

            // Try to find the first article content
            List<WebElement> articleElements = driver.findElements(By.xpath(breedDescriptionXpath))
            String article = null

            if (!articleElements.isEmpty()) {
                article = articleElements.get(0).getText()
            } else {
                List<WebElement> fallbackArticleElements = driver.findElements(By.xpath(breedDescriptionXpath2))
                if (!fallbackArticleElements.isEmpty()) {
                    article = fallbackArticleElements.get(0).getText()
                }
            }

            breed.article = article ?: "No description available" // Fallback text if both XPaths fail
        }

        return allBreeds
    }



    void clickAcceptCookies(WebDriver driver){
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10))
          WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(acceptCookiesButtonXpath)))
          button.click()
    }


    boolean clickNextButton(WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.xpath(nextButtonXpath))
        if (!elements.isEmpty()) {
            WebElement nextButton = elements.get(0)

            Actions actions = new Actions(driver)
            actions.moveToElement(nextButton).perform()

            nextButton.click()
            return true
        } else {
            return false
        }
    }









}
