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
import uk.gov.hmrc.test.ui.pages.createteam.CreateTeamMembersPage._
import uk.gov.hmrc.test.ui.pages.createteam.CreateTeamMembersPage.elements._

class CreateTeamMembersPage extends BasePage[CreateTeamMembersPage](pageReadyTest){

  def addTeamMember(): CreateTeamAddTeamMemberPage = {
    click(addTeamMemberButton)
    CreateTeamAddTeamMemberPage()
  }

  def continue(): CheckYourAnswersPage = {
    click(continueButton)
    CheckYourAnswersPage()
  }

}

object CreateTeamMembersPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("team/create-team/team-members")

  object elements {
    val addTeamMemberButton: By = By.id("addTeamMemberButton")
    val continueButton: By = By.id("continueButton")
  }

  def apply(): CreateTeamMembersPage = {
    new CreateTeamMembersPage()
  }

}
