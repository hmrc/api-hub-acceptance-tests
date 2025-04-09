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
import uk.gov.hmrc.test.ui.pages.createteam.CreateTeamNamePage._
import uk.gov.hmrc.test.ui.pages.createteam.CreateTeamNamePage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.Mode

class CreateTeamNamePage(mode: Mode) extends BasePage[CreateTeamNamePage](pageReadyTest(mode)) {

  def setTeamNameNormalMode(teamName: String): CreateTeamApiProducerConsumerPage = {
    sendKeys(teamNameInput, teamName)
    click(continueButton)
    new CreateTeamApiProducerConsumerPage()
  }

  def setTeamNameCheckMode(teamName: String): CreateTeamApiProducerConsumerPage = {
    sendKeys(teamNameInput, teamName)
    click(continueButton)
    CreateTeamApiProducerConsumerPage()
  }

}

object CreateTeamNamePage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url("team/create-team/team-name", mode)
  }

  object elements {
    val teamNameInput: By = By.id("value")
    val continueButton: By = By.id("continueButton")
  }

  def apply(mode: Mode): CreateTeamNamePage = {
    new CreateTeamNamePage(mode)
  }

}
