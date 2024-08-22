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

package uk.gov.hmrc.test.ui.pages.team

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.createteam.CreateTeamNamePage
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.pages.team.ManageMyTeamsPage._
import uk.gov.hmrc.test.ui.pages.team.ManageMyTeamsPage.elements._
import uk.gov.hmrc.test.ui.utilities.NormalMode

class ManageMyTeamsPage extends BasePage[ManageMyTeamsPage](pageReadyTest) {

  def addNewTeam(): CreateTeamNamePage = {
    click(addNewTeamButton)
    CreateTeamNamePage(NormalMode)
  }

  def viewTeamWithName(teamName: String): ManageTeamPage = {
    val link = findElement(teamLinkByName(teamName))
    val teamId = link.getAttribute(teamIdAttribute)
    link.click()
    ManageTeamPage(teamId)
  }

  def hasTeamWithName(teamName: String): Boolean = {
    findElements(teamLinkByName(teamName)).nonEmpty
  }

}

object ManageMyTeamsPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("team/manage-my-teams")

  object elements {
    val addNewTeamButton: By = By.id("addNewTeamButton")
    val teamIdAttribute = "data-team-id"
    val teamNameAttribute = "data-team-name"
    def teamLinkByName(teamName: String): By = By.cssSelector(s"[$teamNameAttribute=\"$teamName\"]")
  }

  def apply(): ManageMyTeamsPage = {
    new ManageMyTeamsPage()
  }

}
