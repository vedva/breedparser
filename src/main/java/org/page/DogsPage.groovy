package org.page

import org.breed.Breed
import org.openqa.selenium.WebElement
import org.webservice.WebServiceI

class DogsPage {

    WebServiceI webService;

    DogsPage(WebServiceI ws) {
        this.webService = ws
    }

    static String breedBoxXpath = "//div[contains(@class, 'dog-breed-card')]"
    static String breedDescriptionXpath = "//section[@class='breed-description mt-3']"
    static String breedDescriptionXpath2 = "//div[@class='Supplies-content']"
    static String breedNameXpath = ".//h3"
    static String imageXpath = ".//img"
    static String linkXpath = ".//a"
    static String nextButtonXpath = "//a[@rel='next']"
    static String acceptCookiesButtonXpath = "//button[@id='onetrust-accept-btn-handler']"


    void clickAcceptCookies() {
        WebElement button = webService.findElement(acceptCookiesButtonXpath)
        webService.waitElementToBeClickable(button, 10)
        webService.click(button)
    }


    List<Breed> collectBreedsInfoFromOnePage() {
        List<Breed> result = []
        List<WebElement> breedBoxes = webService.findElements(breedBoxXpath)
        for (int i = 0; i < breedBoxes.size(); i++) {
            WebElement breedBox = breedBoxes.get(i)
            Breed breed = new Breed()

            def nameElement = webService.findElementInElement(breedBox, breedNameXpath)
            breed.name = webService.getText(nameElement)
            breed.image = webService.getAttribute(webService.findElementInElement(breedBox, imageXpath), "src")
            breed.link = webService.getAttribute(webService.findElementInElement(breedBox, linkXpath), "href")
            result.add(breed)
        }
        return result
    }

    boolean clickNextButton() {
        List<WebElement> elements = webService.findElements(nextButtonXpath)
        if (!elements.isEmpty()) {
            WebElement nextButton = elements.get(0)
            webService.moveToElement(nextButton)
            webService.click(nextButton)
            return true
        } else {
            return false
        }
    }

    List<Breed> collectAllBreeds() {
        List<Breed> allBreeds = []
        for (int i = 0; i < 5; i++) {
            allBreeds.addAll(collectBreedsInfoFromOnePage())
            clickNextButton()
            if (!clickNextButton()) {
                break
            }
        }
        return allBreeds
    }

    private String parseArticle() {
        List<WebElement> articleElements = webService.findElements(breedDescriptionXpath)
        String article = null
        if (!articleElements.isEmpty()) {
            article = webService.getText(articleElements.get(0))
        } else {
            List<WebElement> fallbackArticleElements = webService.findElements(breedDescriptionXpath2)
            if (!fallbackArticleElements.isEmpty()) {
                article = webService.getText(fallbackArticleElements.get(0))
            } else {
                article = "No description available"
            }
        }
        return article

    }


    List<Breed> getAllBreedsWithArticle() {
        List<Breed> allBreeds = collectAllBreeds()

        for (int i = 0; i < allBreeds.size(); i++) {
            Breed breed = allBreeds.get(i)
            webService.navigateToPage(breed.link)
            breed.article = parseArticle()
        }
        return allBreeds
    }


}
