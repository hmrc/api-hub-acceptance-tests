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

import uk.gov.hmrc.test.ui.pages.{Homepage, ServiceStartPage, StrideLoginPage}

class HomepageSteps extends BaseStepDef {
  var actualAppName: String = _

  Given("""^an unauthenticated user navigates to the homepage$""") { () =>
    ServiceStartPage
      .loadPage()
      .startNow()

    assert(Homepage.isRegisterAnApplicationDisplayed(), "Homepage should be displayed")
  }

  Given("""^an authenticated user navigates to the homepage$""") { () =>
    ServiceStartPage
      .loadPage()
      .startNow()

    assert(Homepage.isRegisterAnApplicationDisplayed())
  }

  When("the user fills in the required stride information") { () =>
    StrideLoginPage.fillInLoginDetails()
  }

  Then("the user should be directed to the api hub home page") { () =>
    logger.info(driver.getCurrentUrl)
    assert(Homepage.isRegisterAnApplicationDisplayed(), "Homepage should be displayed")
  }

  Then("the user should be redirected to the stride login page") { () =>
    assert(StrideLoginPage.isPidFieldDisplayed, true)
  }
}
