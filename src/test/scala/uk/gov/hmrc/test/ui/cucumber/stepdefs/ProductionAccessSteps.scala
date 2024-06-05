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

import uk.gov.hmrc.test.ui.pages2.Navigation
import uk.gov.hmrc.test.ui.pages2.application._

class ProductionAccessSteps extends BaseStepDef {
  private var applicationId: String = ""

  Given("""an api is added to an application""") { () =>
    val applicationDetailsPage = Navigation
      .openStartPage()
      .startNow()
      .signInViaLdap()
      .signInWithDefaults()
      .registerAnApplication()
      .setApplicationName(application.name)
      .doNotAddTeamMembers()
      .registerApplication()
      .viewRegisteredApplication()

    applicationId = applicationDetailsPage.getApplicationId

    val apiDetailsPage = applicationDetailsPage
      .addApis()
      .selectRandomApi()

    val apiId = apiDetailsPage.getApiId
    val apiTitle = apiDetailsPage.getApiTitle

    val addAnApiSuccessPage = apiDetailsPage
      .addToAnApplication()
      .selectApplication(applicationId)
      .selectAllEndpoints()
      .confirmUsagePolicy()
      .continue()

    assert(addAnApiSuccessPage.getSuccessSummary.startsWith(apiTitle))

    val applicationDetailsPageWithApiAdded = addAnApiSuccessPage
      .viewApplication()

    assert(applicationDetailsPageWithApiAdded.hasApiAdded(apiId))
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
    assert(RequestProductionAccessSuccessPage().isSuccessMessageDisplayed, true)
  }

}
