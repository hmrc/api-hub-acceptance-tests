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

object CheckYourAnswersPage extends BasePage {
  private val genericBtn: String                    = ".govuk-button"
  private val applicationName: String               = ".govuk-summary-list:first-of-type .govuk-summary-list__value"
  private val changeApplicationNameLink: String     = ".govuk-summary-list:first-of-type .govuk-link"
  private val noTeamMemberText: String              =
    ".govuk-summary-list:last-of-type .govuk-summary-list__row:last-of-type .govuk-summary-list__value"
  private val changeTeamMemberLink: String          =
    ".govuk-summary-list:last-of-type .govuk-summary-list__actions:last-of-type .govuk-link"
  private val applicationDetailsHeadingText: String = ".govuk-heading-m"
  private val teamOwnerName: String                 =
    "div[class='govuk-summary-list__row govuk-summary-list__row--no-actions'] > dt.govuk-summary-list__key"

  def getTeamOwnerEmailText: String =
    driver.findElement(By.cssSelector(teamOwnerName)).getText.trim

  def getApplicationNameText: String =
    driver.findElement(By.cssSelector(applicationName)).getText.trim

  private def changeApplicationNameElement: WebElement =
    driver.findElement(By.cssSelector(changeApplicationNameLink))
  def isChangeApplicationNameLinkDisplayed: Boolean    =
    changeApplicationNameElement.isDisplayed

  def clickChangeApplicationName(): Unit =
    changeApplicationNameElement.click()
  def getNoTeamMembersText: String       =
    driver.findElement(By.cssSelector(noTeamMemberText)).getText.trim

  private def teamMembersChangeLink: WebElement =
    driver.findElement(By.cssSelector(changeTeamMemberLink))

  def clickChangeTeamMember(): Unit =
    teamMembersChangeLink.click()

  def isTeamMembersChangeLinkDisplayed: Boolean =
    teamMembersChangeLink.isDisplayed

  def getApplicationDetailsHeadingText: String =
    driver.findElement(By.cssSelector(applicationDetailsHeadingText)).getText.trim

  private def getRegisterApplicationButton: WebElement =
    driver.findElement(By.cssSelector(genericBtn))

  def isRegisterApplicationButtonDisplayed: Boolean =
    getRegisterApplicationButton.isDisplayed

  def registerApplication(): ApplicationSuccessPage.type = {
    waitForElementPresent(getRegisterApplicationButton)
    getRegisterApplicationButton.click()
    ApplicationSuccessPage
  }

  def continue(): Unit = {
    val ele: WebElement = driver.findElement(By.cssSelector(genericBtn))
    waitForElementPresent(ele)
    ele.click()
  }
}
