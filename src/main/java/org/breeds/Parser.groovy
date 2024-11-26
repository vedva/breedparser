package org.breeds
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class Parser {
    static void main(String[] args) {
        println'Groovy is working'
        String URL = "https://www.pedigree.com.ph/dog-breeds?page=0"
        DogsPage dogsPage = new DogsPage()

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize()
        driver.get(URL)

       dogsPage.clickAcceptCookies(driver)

        List<Breed> breeds = dogsPage.getAllBreedsWithArticle(driver)

        println(breeds)
        driver.quit()

    }

}
