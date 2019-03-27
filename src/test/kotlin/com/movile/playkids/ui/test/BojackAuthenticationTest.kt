package com.movile.playkids.ui.test

import com.thoughtworks.selenium.SeleneseTestBase.assertEquals
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

/**
 * @author Júlio Moreira Blás de Barros (julio.barros@movile.com)
 * @since 3/27/19
 */
abstract class BojackAuthenticationTest(driver: WebDriver): Spek({

    group("Bojack Auth") {
        describe("The bojack login page") {

            driver.get("https://cms.pkds.it")

            val usernameField = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div/div[3]/div/form/div[1]/div/div[1]/input"))
            val passwordField = driver.findElement(By.xpath(
                    "//*[@id=\"app\"]/div/div[3]/div/form/div[2]/div/div/input"))
            val signInButton = driver.findElement(By.xpath(
                    "//*[@id=\"app\"]/div/div[3]/div/form/div[3]/div/button"))

            it("Displays an error message when trying to login with wrong credentials") {
                usernameField.sendKeys("teste")
                passwordField.sendKeys("testeeeeeeeeeeee")

                signInButton.click()

                val message = try {
                    driver.findElement(By.className("el-message--warning"))
                } catch (e: NoSuchElementException) {
                    null
                }

                assert(message != null && message.isDisplayed) {
                    "An error message should be displayed"
                }

                assertEquals(message?.text, "Username and password do not match a valid credential")
            }

            it("Shows user that the username is required after not filling it") {
                repeat(20) { usernameField.sendKeys(Keys.BACK_SPACE) }
                passwordField.sendKeys("aaa")

                // val actions = Actions(driver).moveToElement(usernameField).clickAndHold().dragAndDrop()

                val message = try {
                    driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div/form/div[1]/div/div[2]"))
                } catch (e: NoSuchElementException) {
                    null
                }

                assert(message != null && message.isDisplayed) {
                    "An error message should be displayed"
                }

                assertEquals(message?.text, "Username is mandatory")
            }
        }
    }

    afterGroup { driver.close() }

})