/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import com.google.inject.Inject
import io.cucumber.guice.ScenarioScoped
import uk.gov.hmrc.test.ui.pages.application.ApplicationDetailsPage
import uk.gov.hmrc.test.ui.pages.{DashboardPage, Journeys, LdapSignInPage, SignInPage}
import uk.gov.hmrc.test.ui.utilities.SharedState

@ScenarioScoped
class SignInSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  Given("""a user is on the sign in page""") { () =>
    Journeys
      .openStartPage()
      .startNow()
  }

  Given("""the user decides to login via ldap""") { () =>
    SignInPage()
      .signInViaLdap()
  }

  When("""the user submits valid LDAP details""") { () =>
    LdapSignInPage()
      .signIn()
  }

  Then("""the user should be authenticated""") { () =>
    DashboardPage()
  }

  Then("""the application should be registered""") { () =>
    ApplicationDetailsPage(sharedState.application.id)
  }

  Then("your applications has the following header links {string} {string} {string} {string} {string}") {
    (linkOne: String, linkTwo: String, linkThree: String, linkFour: String, linkFive: String) =>
      DashboardPage()
        .foreach(
          dashboardPage => {
            dashboardPage.getHeaderLinkTexts should contain(linkOne)
            dashboardPage.getHeaderLinkTexts should contain(linkTwo)
            dashboardPage.getHeaderLinkTexts should contain(linkThree)
            dashboardPage.getHeaderLinkTexts should contain(linkFour)

            if (linkFive.nonEmpty) {
              dashboardPage.getHeaderLinkTexts should contain(linkFive)
            }
          }
        )
  }

}
