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

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.DashboardPage._
import uk.gov.hmrc.test.ui.pages.DashboardPage.elements.{registerAnApplicationButton, registerTeamButton}
import uk.gov.hmrc.test.ui.pages.createteam.CreateTeamNamePage
import uk.gov.hmrc.test.ui.pages.registerapplication.RegisterApplicationNamePage
import uk.gov.hmrc.test.ui.utilities.NormalMode

class DashboardPage extends BasePage[DashboardPage](pageReadyTest) with ApiHubMenu {

  def registerAnApplication(): RegisterApplicationNamePage = {
    click(registerAnApplicationButton)
    RegisterApplicationNamePage(NormalMode)
  }

  def createTeam(): CreateTeamNamePage = {
    // TODO: dashboard hack, fix with HIPP-1042, the create team button should be on the dashboard
    click(registerTeamButton)
    CreateTeamNamePage(NormalMode)
  }

}

object DashboardPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("dashboard")

  object elements {
    val registerAnApplicationButton: By = By.id("registerAnApplicationButton")
    val registerTeamButton: By = By.id("registerTeamButton")
  }

  def apply(): DashboardPage = {
    new DashboardPage()
  }

}
