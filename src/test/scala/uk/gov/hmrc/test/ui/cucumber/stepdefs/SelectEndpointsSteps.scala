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
import io.cucumber.guice.ScenarioScoped
import uk.gov.hmrc.test.ui.domain.AddressWeighting
import uk.gov.hmrc.test.ui.pages2.addanapi.SelectEndpointsPage
import uk.gov.hmrc.test.ui.pages2.application.ApplicationDetailsPage

@ScenarioScoped
class SelectEndpointsSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  When("""the user adds a particular api to an application""") { () =>
    ApplicationDetailsPage(sharedState.application.id)
      .addApis()
      .selectApiByTitle(AddressWeighting.Name)
      .addToAnApplication()
      .selectApplication(sharedState.application.id)
  }

  When("""the user attempts to continue without selecting an endpoint""") { () =>
    ApplicationDetailsPage(sharedState.application.id)
      .addApis()
      .selectRandomApi()
      .addToAnApplication()
      .selectApplication(sharedState.application.id)
      .selectNoEndpoints()
  }

  Then("""the correct scopes for that api should be displayed""") { () =>
    SelectEndpointsPage()
      .foreach(
        selectEndpointsPage =>
          selectEndpointsPage.getScopes should contain theSameElementsAs AddressWeighting.Scopes
      )
  }

  Then("""an error {string} should be displayed""") { (string: String) =>
    SelectEndpointsPage()
      .foreach(
        selectEndpointsPage => {
          selectEndpointsPage.hasErrorSummary shouldBe true
          selectEndpointsPage.getErrorSummaryList should contain(string)
          selectEndpointsPage.getErrorMessages should contain(string)
        }
      )
  }

}
