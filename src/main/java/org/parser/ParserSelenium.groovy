package org.parser

import org.breed.Breed
import org.page.DogsPage
import org.webservice.WebService

class ParserSelenium {

    String URL = "https://www.pedigree.com.ph/dog-breeds?page=0"

    List<Breed> parseBreedsSelenium(){
        WebService wb = new WebService()
        wb.openBrowser();
        wb.navigateToPage(URL)
        DogsPage dogsPage = new DogsPage(wb)
        dogsPage.clickAcceptCookies()
        List<Breed> breeds = dogsPage.collectBreedsInfoFromOnePage()
        wb.quiteWebDriver()
        return breeds
    }
}
