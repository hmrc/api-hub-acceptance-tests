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
  private val yourApplicationsTitle: String        = ".govuk-heading-l"
  private val registerAnApplication: String        = ".govuk-button--primary .govuk-button"
  private val registerAnotherApplication: String   = ".hip-card-container-bottom .govuk-link--no-visited-state"
  private val youApplicationsTitleText: String     = "our Applications"
  private val headerLinksTexts: String             = ".custom-second-nav li a.govuk-link"
  private val registeredApplications: String       = ".hip-card-container-top .govuk-link"
  private val registeredApplicationMessage: String = ".align-left p.govuk-body"
  private val strideLogo: String                   = ".govuk-tag"

  def getRegisteredApplicationNames: Array[AnyRef] =
    driver
      .findElements(By.cssSelector(registeredApplications))
      .stream()
      .map(i => i.getText)
      .toArray

  def getHeaderLinkTexts(): Array[AnyRef] =
    driver
      .findElements(By.cssSelector(headerLinksTexts))
      .stream()
      .map(i => i.getText)
      .toArray

  def getRegisteredApplicationMessage: String =
    driver.findElement(By.cssSelector(registeredApplicationMessage)).getText.trim

  private def registerApplicationButton(): WebElement =
    driver.findElement(By.cssSelector(registerAnApplication))

  private def registerAnotherApplicationButton(): WebElement =
    driver.findElement(By.cssSelector(registerAnotherApplication))
  private def clickRegisterAnApplication(): Unit             =
    registerApplicationButton().click()

  private def clickRegisterAnotherApplication(): Unit =
    registerAnotherApplicationButton().click()

  def registerApplication(): ApplicationNamePage.type = {
    if (driver.findElements(By.cssSelector(registerAnApplication)).size() > 0) {
      waitForElementPresent(registerApplicationButton())
      scrollIntoView(registerApplicationButton())
      clickRegisterAnApplication()
    } else {
      waitForElementPresent(registerAnotherApplicationButton())
      scrollIntoView(registerAnotherApplicationButton())
      clickRegisterAnotherApplication()
    }
    ApplicationNamePage
  }

  private def getYourApplicationsHeading: WebElement =
    driver.findElement(By.cssSelector(yourApplicationsTitle))

  def yourApplicationsIsDisplayed(): Boolean = {
    waitForElementPresent(getYourApplicationsHeading)
    getYourApplicationsHeading.getText.trim.contains(youApplicationsTitleText)
  }

  def isStrideLogoDisplayed(): Boolean =
    driver.findElement(By.cssSelector(strideLogo)).isDisplayed
}
