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

import uk.gov.hmrc.test.ui.domain.AddressWeighting
import uk.gov.hmrc.test.ui.pages.SelectEndpointsPage

class SelectEndpointsSteps extends BaseStepDef {
  Given("""an error {string} should be displayed""") { (string: String) =>
    assert(SelectEndpointsPage.isErrorSummaryBoxDisplayed)
    assert(SelectEndpointsPage.getErrorSummaryMessage.toLowerCase() == string.toLowerCase(), true)
    assert(SelectEndpointsPage.getErrorMessage.toLowerCase().contains(string.toLowerCase()))
  }

  Then("""the correct scopes for that api should be displayed""") { () =>
    assert(SelectEndpointsPage.getScopeTexts.length == AddressWeighting.Scopes.length)
    AddressWeighting.Scopes.foreach(item => assert(SelectEndpointsPage.getScopeTexts.contains(item)))
  }
}
