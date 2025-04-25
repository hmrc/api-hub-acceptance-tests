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
import uk.gov.hmrc.test.ui.pages.DashboardPage.elements.{newApiButton, newApiDetailsLink, registerAnApplicationButton, registerTeamButton}
import uk.gov.hmrc.test.ui.pages.api.ApiDetailsPage
import uk.gov.hmrc.test.ui.pages.createapi.{CreateApiPage, ProducerApiDetailsPage}
import uk.gov.hmrc.test.ui.pages.createteam.CreateTeamNamePage
import uk.gov.hmrc.test.ui.pages.registerapplication.RegisterApplicationNamePage
import uk.gov.hmrc.test.ui.utilities.{NormalMode, SharedState}

import java.util.regex.{Matcher, Pattern}
import scala.util.matching.Regex

class DashboardPage(sharedState: SharedState) extends BasePage[DashboardPage](pageReadyTest) with ApiHubMenu {

  def registerAnApplication(): RegisterApplicationNamePage = {
    click(registerAnApplicationButton)
    RegisterApplicationNamePage(NormalMode)
  }

  def createTeam(): CreateTeamNamePage = {
    // TODO: dashboard hack, fix with HIPP-1042, the create team button should be on the dashboard
    click(registerTeamButton)
    CreateTeamNamePage(NormalMode)
  }

  def createApi(): CreateApiPage = {
    click(newApiButton)
    CreateApiPage(sharedState, NormalMode)
  }

  def selectNewApi() : ProducerApiDetailsPage = {
    val href = findElement(newApiDetailsLink(sharedState)).getAttribute("href")
    val numberPattern: Regex = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}".r
    numberPattern.findFirstMatchIn(href) match {
      case Some(id) => sharedState.api.id = id.toString()
      case _ =>
    }

    click(newApiDetailsLink(sharedState))
    ProducerApiDetailsPage(sharedState)
  }
}

object DashboardPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("dashboard")

  object elements {
    val registerAnApplicationButton: By = By.id("registerAnApplicationButton")
    val registerTeamButton: By = By.id("registerTeamButton")
    val newApiButton: By = By.id("createAnApiButton")
    def newApiDetailsLink(sharedState: SharedState): By = By.xpath(s"//a[text()='${sharedState.api.title}']")
  }

  def apply(sharedState: SharedState): DashboardPage = {
    new DashboardPage(sharedState)
  }

}
