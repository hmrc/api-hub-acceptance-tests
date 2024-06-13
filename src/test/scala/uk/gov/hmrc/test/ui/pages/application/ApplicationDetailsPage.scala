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

package uk.gov.hmrc.test.ui.pages.application

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.api.HipApisPage
import uk.gov.hmrc.test.ui.pages.application.ApplicationDetailsPage._
import uk.gov.hmrc.test.ui.pages.application.ApplicationDetailsPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.DateFormatterUtil

import java.time.LocalDate

class ApplicationDetailsPage(id: String) extends BasePage[ApplicationDetailsPage](pageReadyTest(id)) {

  def getApplicationId: String = {
    getText(applicationId)
  }

  def getApplicationName: String = {
    getText(applicationName)
  }

  def getCreated: LocalDate = {
    DateFormatterUtil.parseLongDateTolerantly(getText(applicationCreated))
  }

  def getNoApisMessage: String = {
    getText(noApisMessage)
  }

  def getTeamMembers: Seq[String] = {
    findElements(teamMember)
      .map(_.getAttribute(teamMemberEmailAttribute))
  }

  def getCountOfTeamMembersFromHeading: Int = {
    val pattern     = "^.*\\((\\d+)\\)$".r

    getText(teamMembersHeading) match {
      case pattern(count) => count.toInt
      case _ => -1
    }
  }

  def hasApiAdded(id: String): Boolean = {
    exists(api(id))
  }

  def addApis(): HipApisPage = {
    click(hipApisLink)
    HipApisPage()
  }

  def applicationApis(): ApplicationApisPage = {
    val applicationId = getApplicationId
    click(applicationApisLink)
    ApplicationApisPage(applicationId)
  }

  def environmentsAndCredentials(): EnvironmentAndCredentialsPage = {
    val applicationId = getApplicationId
    click(environmentsAndCredentialsLink)
    EnvironmentAndCredentialsPage(applicationId)
  }

  def deleteApplication(): ApplicationDeleteConfirmationPage = {
    val applicationId = getApplicationId
    click(deleteApplicationLink)
    ApplicationDeleteConfirmationPage(applicationId)
  }

}

object ApplicationDetailsPage {

  def pageReadyTest(id: String): PageReadyTest = {
    PageReadyTests.apiHubPage.url(s"application/details/$id")
  }

  object elements {
    val applicationId: By = By.id("applicationId")
    val applicationName: By = By.id("applicationName")
    val applicationCreated: By = By.id("applicationCreated")
    val noApisMessage: By = By.id("noApisMessage")
    val hipApisLink: By = By.id("addHipApisLink")
    val apiIdAttribute = "data-api-id"
    def api(id: String): By = By.cssSelector(s"[$apiIdAttribute='$id']")
    val teamMembersHeading: By = By.id("teamMembersHeading")
    val teamMemberEmailAttribute = "data-team-member-email"
    val teamMember: By = By.cssSelector(s"[$teamMemberEmailAttribute]")
    val applicationApisLink: By = By.cssSelector("[data-nav-item-page='ApisPage']")
    val environmentsAndCredentialsLink: By = By.cssSelector("[data-nav-item-page='EnvironmentsAndCredentialsPage']")
    val deleteApplicationLink: By = By.cssSelector("[data-nav-item-page='DeleteApplicationPage']")
  }

  def apply(id: String): ApplicationDetailsPage = {
    new ApplicationDetailsPage(id)
  }

}
