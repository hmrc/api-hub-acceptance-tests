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
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.pages.registerapplication.RegisterApplicationTeamPage._
import uk.gov.hmrc.test.ui.pages.registerapplication.RegisterApplicationTeamPage.elements._
import uk.gov.hmrc.test.ui.utilities.{Mode, Team}

class RegisterApplicationTeamPage(mode: Mode) extends BasePage[RegisterApplicationTeamPage](pageReadyTest(mode)) {

  def setTeamNormalMode(team: Team): CheckYourAnswersPage = {
    click(teamRadio(team.id))
    click(continueButton)
    CheckYourAnswersPage()
  }

  def setTeamCheckMode(team: Team): CheckYourAnswersPage = {
    click(teamRadio(team.id))
    click(continueButton)
    CheckYourAnswersPage()
  }

}

object RegisterApplicationTeamPage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url("application/register/team", mode)
  }

  object elements {
    val continueButton: By = By.id("continueButton")
    val teamIdAttribute: String = "data-team-id"
    def teamRadio(id: String): By = By.cssSelector(s"[$teamIdAttribute=\"$id\"]")
  }

  def apply(mode: Mode): RegisterApplicationTeamPage = {
    new RegisterApplicationTeamPage(mode)
  }

}
