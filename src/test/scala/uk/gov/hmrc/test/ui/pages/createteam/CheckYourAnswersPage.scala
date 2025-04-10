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

package uk.gov.hmrc.test.ui.pages.createteam

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.createteam.CheckYourAnswersPage._
import uk.gov.hmrc.test.ui.pages.createteam.CheckYourAnswersPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

class CheckYourAnswersPage extends BasePage[CheckYourAnswersPage](pageReadyTest) {

  def createTeam(): CreateTeamSuccessPage = {
    findElements(createTeamCheckBox).headOption.foreach(_.click())
    click(createTeamButton)
    CreateTeamSuccessPage()
  }

  def getTeamMembers: Seq[String] = {
    findElements(teamMemberRow).map(_.getText.trim)
  }

  def changeTeamMembers(): CreateTeamMembersPage = {
    click(changeTeamMembersLink)
    CreateTeamMembersPage()
  }

}

object CheckYourAnswersPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("team/create-team/check-your-answers")

  object elements {
    val createTeamButton: By = By.id("createTeamButton")
    val createTeamCheckBox: By = By.id("value")
    val teamMemberRow: By = By.cssSelector("[data-summary-for='team-members'] .govuk-summary-list__key")
    val changeTeamMembersLink: By = By.id("changeTeamMembersLink")
  }

  def apply(): CheckYourAnswersPage = {
    new CheckYourAnswersPage()
  }

}
