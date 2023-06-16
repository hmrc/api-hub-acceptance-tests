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

object YourApplicationPage extends BasePage {
  private val yourApplications           = ".custom-second-nav li:first-of-type .govuk-link"
  private val registerAnApplication      = ".govuk-button--primary .govuk-button"
  private val registerAnotherApplication = ".hip-card-container-bottom .govuk-link--no-visited-state"

  private def registerApplicationButton(): WebElement =
    driver.findElement(By.cssSelector(registerAnApplication))

  private def registerAnotherApplicationButton(): WebElement =
    driver.findElement(By.cssSelector(registerAnotherApplication))
  private def clickRegisterAnApplication(): Unit             =
    registerApplicationButton().click()

  private def clickRegisterAnotherApplication(): Unit =
    registerAnotherApplicationButton().click()

  def registerApplication(): ApplicationName.type = {
    if (driver.findElements(By.cssSelector(registerAnApplication)).size() > 0) {
      print("This is the size: " + driver.findElements(By.cssSelector(registerAnApplication)).size())
      waitForElementPresent(registerApplicationButton())
      scrollIntoView(registerApplicationButton())
      clickRegisterAnApplication()
    } else {
      print(
        "this is the number of register another app size" + driver
          .findElements(By.cssSelector(registerAnotherApplication))
          .size()
      )
      waitForElementPresent(registerAnotherApplicationButton())
      scrollIntoView(registerAnotherApplicationButton())
      clickRegisterAnotherApplication()
    }
    ApplicationName
  }

  private def getYourApplications: WebElement =
    driver.findElement(By.cssSelector(yourApplications))

  def yourApplicationsIsDisplayed(): Boolean = {
    waitForElementPresent(getYourApplications)
    getYourApplications.isDisplayed
  }
}
