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
import uk.gov.hmrc.test.ui.pages.{DashboardPage, GetHelpGuidePage, Journeys, SignInPage, StrideSignInPage}
import uk.gov.hmrc.test.ui.utilities.SharedState

class HomepageSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  Given("""^an unauthenticated user navigates to the homepage$""") { () =>
    Journeys
      .openStartPage(sharedState)
      .startNow()
  }

  Given("""^an authenticated user navigates to the homepage$""") { () =>
    Journeys
      .openStartPage(sharedState)
      .startNow()
  }

  When("the user fills in the required stride information") { () =>
    StrideSignInPage(sharedState)
      .signIn()
  }

  Then("the user should be directed to the integration hub home page") { () =>
    DashboardPage(sharedState)
  }

  When("the user selects to sign-in with Stride") { () =>
    SignInPage(sharedState)
      .signInViaStride()
  }

  Then("the user should be redirected to the stride login page") { () =>
    StrideSignInPage(sharedState)
  }

  Then("the user should be redirected to the Help Guide page") { () =>
    GetHelpGuidePage()
  }

}
