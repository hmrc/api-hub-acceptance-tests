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

import uk.gov.hmrc.test.ui.pages2.Journeys
import uk.gov.hmrc.test.ui.pages2.application._

class ProductionAccessSteps extends BaseStepDef {
  private var applicationId: String = ""

  Given("""an api is added to an application""") { () =>
    var apiId = ""
    var apiTitle = ""

    Journeys
      .signInAndRegisterAnApplication(application)
      .foreach(
        applicationDetailsPage =>
          applicationId = applicationDetailsPage.getApplicationId)
      .addApis()
      .selectRandomApi()
      .foreach(
        apiDetailsPage => {
          apiId = apiDetailsPage.getApiId
          apiTitle = apiDetailsPage.getApiTitle
        }
      )
      .addToAnApplication()
      .selectApplication(applicationId)
      .selectAllEndpoints()
      .confirmUsagePolicy()
      .continue()
      .foreach(
        addAnApiSuccessPage=>
          addAnApiSuccessPage.getSuccessSummary should startWith(apiTitle)
      )
      .viewApplication()
      .foreach(
        applicationDetailsPage =>
          applicationDetailsPage.hasApiAdded(apiId) shouldBe true
      )
  }

  //from the left hand menu the user chooses 'Application APIs'"
  When("from the left hand menu the user chooses {string}") { (lhnmOption: String) =>
    lhnmOption match {
      case "Application APIs" => ApplicationDetailsPage(applicationId).applicationApis()
      case _ => new IllegalArgumentException(s"Unknown application navigation link: $lhnmOption")
    }
  }

  When("the user requests prod access") { () =>
    ApplicationApisPage(applicationId)
      .requestProductionAccess()
      .confirmUsagePolicies()
  }

  And("the user supports the request with a reason") { () =>
    ProvideSupportingInformationPage()
      .setSupportingInformation("Lorem ipsum")
  }

  Then("the pending request is logged") { () =>
    RequestProductionAccessSuccessPage().isSuccessMessageDisplayed shouldBe true
  }

}
