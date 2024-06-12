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

package uk.gov.hmrc.test.ui.pages.registerapplication

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.registerapplication.CheckYourAnswersPage._
import uk.gov.hmrc.test.ui.pages.registerapplication.CheckYourAnswersPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.CheckMode

class CheckYourAnswersPage extends BasePage[CheckYourAnswersPage](pageReadyTest) {

  def getApplicationName: String = {
    getText(applicationName)
  }

  def getTeamMembers: Seq[String] = {
    findElements(summaryRowEmail)
      .map(_.getText.trim)
      .filter(_.nonEmpty)
  }

  def getStatedNumberOfTeamMembers: String = {
    if (getTeamMembers.size > 1) {
      getText(statedNumberOfTeamMembers)
    }
    else {
      getText(noTeamMembers)
    }
  }

  def changeApplicationName(): ApplicationNamePage = {
    click(changeApplicationNameLink)
    ApplicationNamePage(CheckMode)
  }

  // Call this method only when no additional team members have been added
  def addTeamMember(): AddTeamMembersPage = {
    click(changeTeamMemberLink)
    AddTeamMembersPage(CheckMode)
  }

  // Call this method only when additional team members have been added
  def changeTeamMember(): ConfirmAddTeamMemberPage = {
    click(changeTeamMemberLink)
    ConfirmAddTeamMemberPage(CheckMode)
  }

  def registerApplication(): RegisterApplicationSuccessPage = {
    click(registerApplicationButton)
    RegisterApplicationSuccessPage()
  }

}

object CheckYourAnswersPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("application/register/check-your-answers")

  object elements {
    val applicationName: By = By.cssSelector("[data-summary-for='application-details'] .govuk-summary-list__value")
    val changeApplicationNameLink: By = By.cssSelector("[data-summary-for='application-details'] .govuk-summary-list__actions a")
    val statedNumberOfTeamMembers: By = By.cssSelector("[data-summary-for='team-members'] .govuk-summary-list__row:first-of-type .govuk-summary-list__value")
    val noTeamMembers: By = By.cssSelector("[data-summary-for='team-members'] .govuk-summary-list__row:last-of-type .govuk-summary-list__value")
    val summaryRowEmail: By = By.cssSelector("[data-summary-for='team-members'] .govuk-summary-list__key")
    val changeTeamMemberLink: By = By.cssSelector("[data-summary-for='team-members'] .govuk-summary-list__actions a:last-of-type")
    val registerApplicationButton: By = By.id("registerApplicationButton")
  }

  def apply(): CheckYourAnswersPage = {
    new CheckYourAnswersPage()
  }

}
