package org

import org.breed.Breed
import org.page.DogsPage
import org.webservice.WebService

class Parser {
    static void main(String[] args) {
        println 'Groovy is working'
        String URL = "https://www.pedigree.com.ph/dog-breeds?page=0"
        WebService wb = new WebService()

        wb.openBrowser();
        wb.navigateToPage(URL)

        DogsPage dogsPage = new DogsPage(wb)
        dogsPage.clickAcceptCookies()

        List<Breed> breeds = dogsPage.getAllBreedsWithArticle()

        println(breeds)

        wb.quiteWebDriver()


    }

}
