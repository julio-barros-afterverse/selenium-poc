package com.movile.playkids.ui.test

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit

/**
 * @author Júlio Moreira Blás de Barros (julio.barros@movile.com)
 * @since 3/27/19
 */
object TestCommons {
    init {
        System.setProperty("webdriver.chrome.driver", "/Users/julio.barros/Downloads/chromedriver")
        System.setProperty("webdriver.gecko.driver", "/Users/julio.barros/Downloads/geckodriver")
    }

    val chromeDriver = ChromeDriver().also {
        configureTestDriver(it)
    }

    /*val firefoxDriver = FirefoxDriver().also {
        configureTestDriver(it)
    }*/

    private fun configureTestDriver(it: WebDriver) {
        it.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        it.manage().window().maximize()
    }
}