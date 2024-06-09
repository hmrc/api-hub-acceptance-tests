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

package uk.gov.hmrc.test.ui.pages2

import uk.gov.hmrc.test.ui.cucumber.stepdefs.SharedState
import uk.gov.hmrc.test.ui.pages2.application.ApplicationDetailsPage

object Journeys extends Robot {

  def openStartPage(): ServiceStartPage = {
    navigateTo("")
    ServiceStartPage()
  }

  // This page is difficult to access directly
  // The dashboard link is only available when the user has more than 5 applications
  def openYourApplicationsPage(): YourApplicationsPage = {
    navigateTo("applications")
    YourApplicationsPage()
  }

  def signIn(): DashboardPage = {
    signInViaLdap()
  }

  def signInViaLdap(): DashboardPage = {
    Journeys
      .openStartPage()
      .startNow()
      .signInViaLdap()
      .signInWithDefaults()
  }

  def signInViaStride(): DashboardPage = {
    Journeys
      .openStartPage()
      .startNow()
      .signInViaStride()
      .signIn()
  }

  def registerAnApplication(sharedState: SharedState): ApplicationDetailsPage = {
    Journeys
      .openStartPage()
      .dashboard()
      .registerAnApplication()
      .setApplicationName(sharedState.application.name)
      .doNotAddTeamMembers()
      .registerApplication()
      .viewRegisteredApplication()
      .foreach(
        applicationDetailsPage =>
          sharedState.application.id = applicationDetailsPage.getApplicationId
      )
  }

  def signInAndRegisterAnApplication(sharedState: SharedState): ApplicationDetailsPage = {
    signIn()
    registerAnApplication(sharedState)
  }

}
