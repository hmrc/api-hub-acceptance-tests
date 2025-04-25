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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import com.google.inject.Inject
import uk.gov.hmrc.test.ui.pages.{DashboardPage, Journeys, SignInPage, StrideSignInPage, UnauthorisedPage}
import uk.gov.hmrc.test.ui.utilities.SharedState

class StrideLoginSteps @Inject()(sharedState: SharedState) extends BaseStepDef {
  Given("""a user navigates to the sign in page""") { () =>
    Journeys
      .openStartPage(sharedState)
      .startNow()
  }

  Given("""chooses to login via stride""") { () =>
    SignInPage(sharedState)
      .signInViaStride()
  }

  When("""the user submits valid sign in credentials""") { () =>
    StrideSignInPage(sharedState)
      .signIn()
  }

  Then("""the user should be successfully signed in via stride""") { () =>
    DashboardPage(sharedState)
      .foreach(
        dashboardPage =>
          dashboardPage.isSignedInWithStride shouldBe true
      )
  }

  When("""the user fills in all fields except role""") { () =>
    StrideSignInPage(sharedState)
      .signInWithoutRole()
  }

  Then("""the user should be on the unauthorised url page""") { () =>
    UnauthorisedPage()
  }

}
