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

import java.net.URI
import java.util

object ApplicationDetailsPage extends BasePage {
  private val applicationName      = "div[class='govuk-grid-row govuk-!-margin-bottom-2']:first-of-type p"
  private val addApisLink          = ".govuk-body a[href='/api-hub/apis']"
  private val addedApiNameRows     = "th[scope='row']"
  private val lhnmLinks            = ".side-nav__component .side-nav__link"
  private val requestProdAccessBtn = ".govuk-grid-column-one-half a.govuk-button"
  private val pageTitle            = "h1.govuk-heading-l"
  private val teamMembers          =
    ".govuk-grid-column-two-thirds .govuk-table:last-of-type .govuk-table__body .govuk-table__row td"
  private val teamMembersHeading   = "h2[class='govuk-heading-m govuk-!-margin-top-2']"
  private val applicationId        = "div:nth-child(4) > div.govuk-grid-column-three-quarters > p"
  private val createdDate          = "div:nth-child(3) > div.govuk-grid-column-three-quarters > p"
  private val applicationApisText  = "div.govuk-grid-column-two-thirds > p.govuk-body"

  def getApplicationApisText(): String =
    driver.findElement(By.cssSelector(applicationApisText)).getText.trim()

  def getCreatedDate(): String        =
    driver.findElement(By.cssSelector(createdDate)).getText.trim()

  def getCountOfTeamMembersFromHeading(): Int = {
    val headingText = driver.findElement(By.cssSelector(teamMembersHeading)).getText.trim()
    val pattern     = "\\(\\d+\\)".r
    s"${pattern.findFirstMatchIn(headingText).head}".replace("(", "").replace(")", "").toInt
  }
  def getTeamMembers(): Array[AnyRef] =
    driver
      .findElements(By.cssSelector(teamMembers))
      .stream()
      .map(i => i.getText.trim)
      .toArray

  def getTeamOwner(): AnyRef =
    getTeamMembers().head

  def getPageTitle(): String =
    driver.findElement(By.cssSelector(pageTitle)).getText.trim()

  def getApplicationIdFromUi(): String =
    driver.findElement(By.cssSelector(applicationId)).getText.trim()

  def getApplicationIdFromUrl(): String = {
    val urlPaths = new URI(driver.getCurrentUrl).getPath.split("/")
    urlPaths.last
  }

  def getApplicationName: String = {
    val element = driver.findElement(By.cssSelector(applicationName))
    waitForElementPresent(element)
    element.getText.trim()
  }

  def addApis(): HipApisPage.type = {
    driver.findElement(By.cssSelector(addApisLink)).click()
    HipApisPage
  }

  def isApiNameAddedToApplication(input: String): Boolean = {
    val elements: util.List[WebElement] = driver.findElements(By.cssSelector(addedApiNameRows))
    elements.stream().anyMatch((element: WebElement) => element.getText.trim().equals(input));
  }

  //TODO move to section, this is also used in SelectApplicationPage class, so could go to base class too
  def chooseLhnmOption(input: String): this.type = {
    val elements: util.List[WebElement] = driver.findElements(By.cssSelector(lhnmLinks))
    elements.forEach((element: WebElement) => println(element.getText.trim))
    val result                          =
      elements.stream().filter((element: WebElement) => element.getText.trim == input).findFirst().orElse(null)
    try result.click()
    catch {
      case _: NoSuchElementException => println("element could not be found")
    }
    this
  }

  def requestProductionAccess(): RequestProductionAccessPage.type = {
    waitForElementPresentAndClick(driver.findElement(By.cssSelector(requestProdAccessBtn)))
    RequestProductionAccessPage
  }
}
