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

import uk.gov.hmrc.test.ui.pages.{Homepage, StrideLogin}

class HomepageSteps extends BaseStepDef {

  Given("an unauthenticated user navigates to the homepage") { () =>
    Homepage.loadPage
  }

  When("the user fills in the required stride information") { () =>
    StrideLogin.fillInLoginDetails()
  }

  Then("the user should be directed to the api hub home page") { () =>
    //currently the api home page changes port and then doesnt redirect after the stride login, forcing the
    //page reload here
    Homepage.loadPage
    assert(Homepage.isRegisterAnApplicationDisplayed(), true)
  }

  Then("the user should be redirected to the stride login page") { () =>
    assert(StrideLogin.isPidFieldDisplayed(), true)
  }
}
