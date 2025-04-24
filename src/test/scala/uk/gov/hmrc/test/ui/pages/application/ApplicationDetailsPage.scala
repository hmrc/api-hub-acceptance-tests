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
import uk.gov.hmrc.test.ui.pages.api.ExploreApisPage
import uk.gov.hmrc.test.ui.pages.application.ApplicationDetailsPage._
import uk.gov.hmrc.test.ui.pages.application.ApplicationDetailsPage.elements._
import uk.gov.hmrc.test.ui.pages.cancelaccessrequest.CancelAccessRequestSelectApiPage
import uk.gov.hmrc.test.ui.pages.{ApiHubMenu, BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.{DateFormatterUtil, SharedState}

import java.time.LocalDate

class ApplicationDetailsPage(id: String) extends BasePage[ApplicationDetailsPage](pageReadyTest(id)) with ApiHubMenu {

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

  def getOwningTeamName: String = {
    getText(owningTeam)
  }

  def hasApiAdded(id: String): Boolean = {
    exists(api(id))
  }

  def addApis(): ExploreApisPage = {
    click(exploreApisLink)
    ExploreApisPage()
  }

  def environment(environment: String): EnvironmentPage = {
    val applicationId = getApplicationId
    click(environmentLink(environment))
    EnvironmentPage(applicationId, environment)
  }

  def deleteApplication(sharedState: SharedState): ApplicationDeleteConfirmationPage = {
    click(deleteApplicationLink)
    ApplicationDeleteConfirmationPage(sharedState)
  }

  def cancelAccessRequests(): CancelAccessRequestSelectApiPage = {
    click(cancelAccessRequestsLink)
    CancelAccessRequestSelectApiPage()
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
    val exploreApisLink: By = By.id("addExploreApisLink")
    val apiIdAttribute = "data-api-id"
    def api(id: String): By = By.cssSelector(s"[$apiIdAttribute='$id']")
    val owningTeam: By = By.id("owningTeam")
    def environmentLink(environment: String): By = By.cssSelector(s"[data-nav-item-page='$environment']")
    val deleteApplicationLink: By = By.cssSelector("[data-nav-item-page='DeleteApplicationPage']")
    val cancelAccessRequestsLink: By = By.id("cancelAccessRequestsLink")
  }

  def apply(id: String): ApplicationDetailsPage = {
    new ApplicationDetailsPage(id)
  }

}
