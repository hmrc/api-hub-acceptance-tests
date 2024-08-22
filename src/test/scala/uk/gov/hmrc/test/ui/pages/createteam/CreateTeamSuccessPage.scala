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
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.pages.createteam.CreateTeamSuccessPage._
import uk.gov.hmrc.test.ui.pages.createteam.CreateTeamSuccessPage.elements._
import uk.gov.hmrc.test.ui.pages.team.ManageMyTeamsPage

class CreateTeamSuccessPage extends BasePage[CreateTeamSuccessPage](pageReadyTest) {

  def viewManageTeams(): ManageMyTeamsPage = {
    click(manageTeamsLink)
    ManageMyTeamsPage()
  }

  def getTeamId: String = {
    findElement(teamIdInput).getAttribute("value")
  }

}

object CreateTeamSuccessPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("team/create-team/create-team")

  object elements {
    val manageTeamsLink: By = By.id("manageTeamsLink")
    val teamIdInput: By = By.id("teamId")
  }

  def apply(): CreateTeamSuccessPage = {
    new CreateTeamSuccessPage()
  }

}
