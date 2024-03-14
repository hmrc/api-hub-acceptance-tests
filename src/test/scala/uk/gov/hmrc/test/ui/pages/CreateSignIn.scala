/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.{By, WebElement}
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.utilities.User

object CreateSignIn extends BasePage {
  private val principal: String           = "#principal"
  private val email: String               = "#email"
  private val redirectUrl: String         = "#redirectUrl"
  private val resourceType: String        = "#permissions_0_resourceTypes"
  private val resourceLocations: String   = "#permissions_0_resourceLocations"
  private val actions: String             = "#permissions_0_actions"
  private val signIn: String              = "button[name='fake-sign-in-btn']"
  private val defaultEmailAddress: String = User.Email
  private val dashboardPath: String       = "/dashboard"

  def defaultLoginUser(): Unit =
    loginWithUserEmail(defaultEmailAddress)

  def signInButton: WebElement =
    driver.findElement(By.cssSelector(signIn))

  def loginWithUserEmail(emailAddress: String): Unit =
    loginWithRoleAndEmailAddress("approvals", emailAddress)

  def loginWithRoleAndEmailAddress(role: String, emailAddress: String): Unit = {
    waitForElementPresent(driver.findElement(By.cssSelector(principal)))
    driver.findElement(By.cssSelector(principal)).sendKeys("auto-test")
    driver.findElement(By.cssSelector(email)).sendKeys(emailAddress)
    driver.findElement(By.cssSelector(redirectUrl)).sendKeys(TestConfiguration.url("api-hub") + dashboardPath)
    driver.findElement(By.cssSelector(resourceType)).sendKeys("api-hub-frontend")
    driver.findElement(By.cssSelector(resourceLocations)).sendKeys(role)
    driver.findElement(By.cssSelector(actions)).sendKeys("WRITE")
    signInButton.click()
  }
}
