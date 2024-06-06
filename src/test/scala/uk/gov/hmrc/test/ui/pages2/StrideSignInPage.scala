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

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages2.StrideSignInPage._
import uk.gov.hmrc.test.ui.pages2.StrideSignInPage.elements._

class StrideSignInPage extends BasePage[StrideSignInPage](pageReadyTest) {

  // TODO: standardise sign-in methods
  def signIn(): DashboardPage = {
    sendKeys(pid, "7297091")
    sendKeys(givenName, "sarita")
    sendKeys(surName, "parigi")
    sendKeys(emailAddress, "sarita.reddy.parigi@digital.hmrc.gov.uk")
    sendKeys(roles, "api_hub_approver")
    click(submitButton)
    DashboardPage()
  }

  def signInWithoutRole(): UnauthorisedPage = {
    sendKeys(pid, "7297091")
    sendKeys(givenName, "sarita")
    sendKeys(surName, "parigi")
    sendKeys(emailAddress, "sarita.reddy.parigi@digital.hmrc.gov.uk")
    click(submitButton)
    UnauthorisedPage()
  }

}

object StrideSignInPage {

  // The URL for this page is complex and not suitable for matching
  // Therefore we'll use a title-based page ready test
  val pageReadyTest: PageReadyTest = TitlePageReadyTest("Stride IdP Login")

  object elements {
    val pid: By = By.id("pid")
    val givenName: By = By.id("usersGivenName")
    val surName: By = By.id("usersSurname")
    val emailAddress: By = By.id("emailAddress")
    val roles: By = By.id("roles")
    val submitButton: By = By.id("continue-button")
  }

  def apply(): StrideSignInPage = {
    new StrideSignInPage()
  }

}
