/*
 * Copyright 2024 HM Revenue & Customs
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

object TeamMembersOverviewPage extends BasePage {
  private val teamMembersHeading       = ".govuk-heading-xl"
  private val teamMembersRows          = ".govuk-summary-list__row"
  private val yesRadioBtn              = "#value"
  private val noRadioBtn               = "#value-no"
  private val continueBtn              = ".govuk-button"
  private val changeLink               = ".govuk-summary-list__actions-list-item:last-of-type > a"
  private val removeLink               = ".govuk-summary-list__actions-list-item:first-of-type > a"
  private val lastAddedTeamMemberEmail = ".govuk-summary-list__row:last-of-type dt.govuk-summary-list__key"

  private def noRadioButton(): WebElement =
    driver.findElement(By.cssSelector(noRadioBtn))

  private def yesRadioButton(): WebElement =
    driver.findElement(By.cssSelector(yesRadioBtn))

  private def continue(): WebElement =
    driver.findElement(By.cssSelector(continueBtn))

  def change(): Unit =
    driver.findElement(By.cssSelector(changeLink)).click()

  def remove(): Unit =
    driver.findElement(By.cssSelector(removeLink)).click()

  def getPageTitle(): String =
    driver.findElement(By.cssSelector(teamMembersHeading)).getText

  def addTeamMember(): CheckYouAnswersPage.type = {
    yesRadioButton().click()
    continue().click()
    CheckYouAnswersPage
  }

  def getLastAddedTeamMembersEmail(): String =
    driver.findElement(By.cssSelector(lastAddedTeamMemberEmail)).getText.trim

  def getNumberOfTeamMemberRows(): Integer =
    driver.findElements(By.cssSelector(teamMembersRows)).size()

  def addNoTeamMember(): CheckYouAnswersPage.type = {
    noRadioButton().click()
    continue().click()
    CheckYouAnswersPage
  }
}
