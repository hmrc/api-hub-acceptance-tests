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

object TeamMembersPage extends BasePage {
  private val noRadioBtn: String        = "#value-no"
  private val yesRadioBtn: String       = "#value"
  private val continueLcr: String       = ".govuk-grid-column-two-thirds button.govuk-button"
  private val problemAlertBox: String   = ".govuk-error-summary"
  private val emailAlertMessage: String = "#email-error"

  private def noRadioButton(): WebElement =
    driver.findElement(By.cssSelector(noRadioBtn))

  private def yesRadioButton(): WebElement =
    driver.findElement(By.cssSelector(yesRadioBtn))

  private def continue(): WebElement =
    driver.findElement(By.cssSelector(continueLcr))

  def clickContinue(): Unit =
    continue().click()

  def isAlertBoxDisplayed(): Boolean =
    driver.findElement(By.cssSelector(problemAlertBox)).isDisplayed

  def isEmailAlertMessageDisplayed(): Boolean =
    driver.findElement(By.cssSelector(emailAlertMessage)).isDisplayed

  def addNoTeamMember(): CheckYourAnswersPage.type = {
    noRadioButton().click()
    continue().click()
    CheckYourAnswersPage
  }

  def addTeamMember(): CheckYourAnswersPage.type = {
    yesRadioButton().click()
    continue().click()
    CheckYourAnswersPage
  }
}
