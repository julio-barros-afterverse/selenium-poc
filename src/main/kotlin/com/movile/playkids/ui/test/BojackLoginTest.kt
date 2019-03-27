package com.movile.playkids.ui.test

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.net.URI
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * @author Júlio Moreira Blás de Barros (julio.barros@movile.com)
 * @since 3/26/19
 */
class BojackLoginTest(val driver: WebDriver) {
}

fun main(args: Array<String>) {
    System.setProperty("webdriver.chrome.driver", "/Users/julio.barros/Downloads/chromedriver")
    val driver = ChromeDriver()
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
    driver.manage().window().maximize()
    driver.get("https://cms.pkds.it")

    val usernameField = driver.findElement(
        By.xpath(
            "//*[@id=\"app\"]/div/div[3]/div/form/div[1]/div/div[1]/input"
        )
    )
    val passwordField = driver.findElement(
        By.xpath(
            "//*[@id=\"app\"]/div/div[3]/div/form/div[2]/div/div/input"
        )
    )

    usernameField.sendKeys("teste")
    passwordField.sendKeys("testeeeeeeeeeeee")

    val signInButton = driver.findElement(
        By.xpath(
            "//*[@id=\"app\"]/div/div[3]/div/form/div[3]/div/button"
        )
    )

    signInButton.click()

    val message = try {
        driver.findElement(
            By.className("el-message--warning")
        )
    } catch (e: NoSuchElementException) {
        null
    } catch (e: Exception) {
        println("Exception finding element: ${e.message}")
        e.printStackTrace()
        null
    }

    require(
        message != null
            && message.isDisplayed
            && message.text == "Username and password do not match a valid credential"
    ) {
        "An error message should be displayed"
    }

    driver.close()
    System.exit(0)
  //  BojackLoginTest(driver).run()
}