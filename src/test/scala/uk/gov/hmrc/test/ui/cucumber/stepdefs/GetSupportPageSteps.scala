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

import uk.gov.hmrc.test.ui.pages.{GetSupportPage, Journeys}



class GetSupportPageSteps extends BaseStepDef {

  Given("""the user navigate to get support page""") { () =>
    Journeys
      .openStartPage()
      .getSupport()
  }


  Then("the get support page has the following headings {string} and {string}") {
    (linkOne: String, linkTwo: String) =>
      GetSupportPage()
        .foreach(
          GetSupportPage => {
            GetSupportPage.getSupportConsumerBlockText should include(linkOne)
            GetSupportPage.getSupportProducerBlockText should include(linkTwo)
          }
        )
  }

}