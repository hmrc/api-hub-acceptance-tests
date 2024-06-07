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

import uk.gov.hmrc.test.ui.pages2.ErrorPage

class ErrorPageSteps extends BaseStepDef {

  Then("the application not found header message should be displayed") { () =>
    ErrorPage()
      .foreach(
        errorPage =>
          errorPage.getErrorHeading shouldBe "Application not found"
      )
  }

  Then("the error message should be {string}") { (string: String) =>
    ErrorPage()
      .foreach(
        errorPage =>
          errorPage.getErrorMessage should include(string)
      )
  }

}
