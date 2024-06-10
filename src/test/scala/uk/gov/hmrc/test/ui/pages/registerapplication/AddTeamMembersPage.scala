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
import uk.gov.hmrc.test.ui.pages.registerapplication.AddTeamMembersPage._
import uk.gov.hmrc.test.ui.pages.registerapplication.AddTeamMembersPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, ErrorSummary, PageReadyTest, UrlPageReadyTest}
import uk.gov.hmrc.test.ui.utilities.{Mode, NormalMode}

class AddTeamMembersPage(mode: Mode) extends BasePage[AddTeamMembersPage](pageReadyTest(mode)) with ErrorSummary {

  def addTeamMembers(): AddTeamMemberDetailsPage = {
    click(yesRadio)
    click(continueButton)
    AddTeamMemberDetailsPage(NormalMode)
  }

  def doNotAddTeamMembers(): CheckYourAnswersPage = {
    click(noRadio)
    click(continueButton)
    CheckYourAnswersPage()
  }

  def continueWithoutSelection(): AddTeamMembersPage = {
    click(continueButton)
    AddTeamMembersPage(mode)
  }

}

object AddTeamMembersPage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    UrlPageReadyTest.withMode("application/register/add-team-members", mode)
  }

  object elements  {
    val yesRadio: By = By.id("value")
    val noRadio: By = By.id("value-no")
    val continueButton: By = By.id("continueButton")
  }

  def apply(mode: Mode): AddTeamMembersPage = {
    new AddTeamMembersPage(mode)
  }

}
