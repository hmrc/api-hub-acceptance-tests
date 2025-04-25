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
import uk.gov.hmrc.test.ui.pages.StrideSignInPage._
import uk.gov.hmrc.test.ui.pages.StrideSignInPage.elements._
import uk.gov.hmrc.test.ui.utilities.{Role, SharedState, User, UserRole}

class StrideSignInPage(sharedState: SharedState) extends BasePage[StrideSignInPage](pageReadyTest) {

  def signIn(): DashboardPage = {
    signIn(UserRole)
  }

  def signIn(role: Role): DashboardPage = {
    submitForm(Some(role))
    DashboardPage(sharedState)
  }

  def signInWithoutRole(): UnauthorisedPage = {
    submitForm(None)
    UnauthorisedPage()
  }

  private def submitForm(role: Option[Role]): Unit = {
    sendKeys(pid, "7297091")
    sendKeys(givenName, User.firstName)
    sendKeys(surName, User.lastName)
    sendKeys(emailAddress, User.email)

    role.foreach(
      role =>
        sendKeys(roles, role.srsRole)
    )

    click(submitButton)
  }

}

object StrideSignInPage {

  // The URL for this page is complex and not suitable for matching
  // Therefore we'll use a title-based page ready test
  val pageReadyTest: PageReadyTest = PageReadyTests.nonApiHubPage.title("Stride IdP Login")

  object elements {
    val pid: By = By.id("pid")
    val givenName: By = By.id("usersGivenName")
    val surName: By = By.id("usersSurname")
    val emailAddress: By = By.id("emailAddress")
    val roles: By = By.id("roles")
    val submitButton: By = By.id("continue-button")
  }

  def apply(sharedState: SharedState): StrideSignInPage = {
    new StrideSignInPage(sharedState)
  }

}
