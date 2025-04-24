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

import uk.gov.hmrc.test.ui.pages.application.{ApplicationDetailsPage, EnvironmentPage, YourApplicationsPage}
import uk.gov.hmrc.test.ui.pages.team.{ManageMyTeamsPage, ManageTeamPage}
import uk.gov.hmrc.test.ui.utilities.{Role, SharedState, UserRole}

/**
 * Journeys are effectively shortcuts that can be used by step definition
 * classes to perform multiple-action sequences across page objects.
 *
 * By defining shortcuts we can reduce the need to refactor features and step
 * definitions when the website changes. We simply need to change the
 * implementation of the shortcut.
 */
object Journeys extends Robot {

  def openStartPage(sharedState: SharedState): ServiceStartPage = {
    navigateToRelativeUrl("")
    ServiceStartPage(sharedState)
  }

  // This page is difficult to access directly as the dashboard link is only
  // available when the user has more than 5 applications
  def openYourApplicationsPage(): YourApplicationsPage = {
    navigateToRelativeUrl("applications")
    YourApplicationsPage()
  }

  // This page is difficult to access directly as the dashboard link is only
  // available when the user has more than 5 teams
  def openManageMyTeamsPage(): ManageMyTeamsPage = {
    navigateToRelativeUrl("team/manage-my-teams")
    ManageMyTeamsPage()
  }

  def signIn(sharedState: SharedState): DashboardPage = {
    signInViaStride(sharedState)
  }

  def signIn(role: Role, sharedState: SharedState): DashboardPage = {
    signInViaStride(role, sharedState)
  }

  def signInViaLdap(sharedState: SharedState): DashboardPage = {
    signInViaLdap(UserRole, sharedState)
  }

  def signInViaLdap(role: Role, sharedState: SharedState): DashboardPage = {
    Journeys
      .openStartPage(sharedState)
      .startNow()
      .signInViaLdap()
      .signIn(role)
  }

  def signInViaStride(sharedState: SharedState): DashboardPage = {
    signInViaStride(UserRole, sharedState)
  }

  def signInViaStride(role: Role, sharedState: SharedState): DashboardPage = {
    Journeys
      .openStartPage(sharedState)
      .startNow()
      .signInViaStride()
      .signIn(role)
  }

  def registerAnApplication(sharedState: SharedState): ApplicationDetailsPage = {
    Journeys
      .openStartPage(sharedState)
      .dashboard(sharedState)
      .registerAnApplication()
      .setApplicationNameNormalMode(sharedState.application.name)
      .setTeamNormalMode(sharedState.team)
      .registerApplication()
      .viewApplication()
      .foreach(
        applicationDetailsPage =>
          sharedState.application.id = applicationDetailsPage.getApplicationId
      )
  }

  def signInAndRegisterAnApplication(sharedState: SharedState): ApplicationDetailsPage = {
    signIn(sharedState)
    checkUserHasATeam(sharedState)
    registerAnApplication(sharedState)
  }

  def signInWithRoleAndRegisterAnApplication(sharedState: SharedState, role: Role): ApplicationDetailsPage = {
    signIn(role, sharedState)
    checkUserHasATeam(sharedState)
    registerAnApplication(sharedState)
  }

  def signInAndEnsureTeam(sharedState: SharedState): DashboardPage = {
    signIn(sharedState)
    checkUserHasATeam(sharedState)
    navigateToRelativeUrl("dashboard")
    DashboardPage(sharedState)
  }

  def createTeam(sharedState: SharedState): ManageTeamPage = {
    Journeys
      .openStartPage(sharedState)
      .dashboard(sharedState)
      .createTeam()
      .setTeamNameNormalMode(sharedState.team.name)
      .setProducer()
      .continue()
      .continue()
      .createTeam()
      .foreach(
        createTeamSuccessPage =>
          sharedState.team.id = createTeamSuccessPage.getTeamId
      )
      .viewManageTeams()
      .viewTeamWithId(sharedState.team.id)
  }

  def checkUserHasATeam(sharedState: SharedState): Unit = {
    if (!Journeys.openManageMyTeamsPage().hasTeamWithName(sharedState.team.name)) {
      createTeam(sharedState)
    }
  }

  def addAnApi(sharedState: SharedState): ApplicationDetailsPage = {
    ApplicationDetailsPage(sharedState.application.id)
      .addApis()
      .selectRandomApi()
      .addToAnApplication()
      .selectApplication(sharedState.application.id)
      .selectAllEndpoints()
      .confirmUsagePolicy()
      .continue()
      .viewApplication()
  }

  def requestProductionAccess(sharedState: SharedState): ApplicationDetailsPage = {
    ApplicationDetailsPage(sharedState.application.id)
      .environment("production")
      .requestAccess()
      .setSelectedApi()
      .setSupportingInformation("test-supporting-information")
      .confirmAnswers()
      .viewApplication()
  }

  def swapRole(role: Role, sharedState: SharedState): Unit = {
    val url = getCurrentUrl

    navigateToRelativeUrl("sign-in")

    SignInPage(sharedState)
      .signInViaStride()
      .signIn(role)

    navigateToFullUrl(url)
  }

  def createProductionCredential(sharedState: SharedState): EnvironmentPage = {
    EnvironmentPage(sharedState.application.id, "production")
      .createNewProductionCredential()
      .confirmAndContinue()
      .returnToEnvironmentPage()
  }

}
