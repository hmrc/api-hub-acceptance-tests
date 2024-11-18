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
import uk.gov.hmrc.test.ui.pages.LdapSignInPage._
import uk.gov.hmrc.test.ui.pages.LdapSignInPage.elements._
import uk.gov.hmrc.test.ui.utilities.{Role, User, UserRole}

class LdapSignInPage extends BasePage[LdapSignInPage](pageReadyTest) {

  def signIn(): DashboardPage = {
    signIn(UserRole)
  }

  def signIn(role: Role): DashboardPage = {
    sendKeys(principal, "auto-test")
    sendKeys(email, User.email)
    sendKeys(redirectUrl, buildFullUrl("dashboard"))

    role.ldapResourceLocation.foreach(
      resourceLocation => {
        sendKeys(resourceTypes, "api-hub-frontend")
        sendKeys(resourceLocations, resourceLocation)
        sendKeys(actions, "WRITE")
      }
    )

    click(signInButton)
    DashboardPage()
  }

}

object LdapSignInPage {

  // The URL is different for local-dev vs local testing so is not stable
  // Therefore we'll use a title-based page ready test
  val pageReadyTest: PageReadyTest = PageReadyTests.nonApiHubPage.titleContaining("Create test sign in")

  object elements {
    val principal: By = By.id("principal")
    val email: By = By.id("email")
    val redirectUrl: By = By.id("redirectUrl")
    val resourceTypes: By = By.id("permissions_0_resourceTypes")
    val resourceLocations: By = By.id("permissions_0_resourceLocations")
    val actions: By = By.id("permissions_0_actions")
    val signInButton: By = By.name("fake-sign-in-btn")
  }

  def apply(): LdapSignInPage = {
    new LdapSignInPage()
  }

}
