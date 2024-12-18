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
import io.cucumber.guice.ScenarioScoped
import uk.gov.hmrc.test.ui.pages.DashboardPage
import uk.gov.hmrc.test.ui.pages.admin.{AccessRequestApprovedPage, AccessRequestPage, AccessRequestRejectedPage, AccessRequestsPage}
import uk.gov.hmrc.test.ui.pages.application._
import uk.gov.hmrc.test.ui.utilities.SharedState

@ScenarioScoped
class ProductionAccessSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  var accessRequestId: String = _

  When("the user requests prod access") { () =>
    EnvironmentPage(sharedState.application.id, "production")
      .requestAccess()
  }

  And("the user selects which API") { () =>
    SelectApisPage()
      .setSelectedApi()
  }

  And("the user supports the request with a reason") { () =>
    ProvideSupportingInformationPage()
      .setSupportingInformation("Lorem ipsum")
  }

  And("the user checks their answers") { () =>
    RequestProductionAccessCYAPage()
      .confirmAnswers()
  }

  Then("the production access request is successful") { () =>
    RequestProductionAccessSuccessPage()
  }

  Then("follow the application link") { () =>
    RequestProductionAccessSuccessPage().viewApplication()
  }

  And("views the access requests page") { () =>
    ApplicationDetailsPage(sharedState.application.id).apiHubAdmin()
  }

  And("opens the first access request") { () =>
    AccessRequestsPage()
      .openFirstAccessRequest()
      .foreach(
        accessRequestPage =>
          accessRequestId = accessRequestPage.getId
      )
  }

  When("the user approves the access request") { () =>
    AccessRequestPage(accessRequestId).approveAccessRequest()
  }

  When("the user rejects the access request with reason {string}") { (rejectedReason: String) =>
    AccessRequestPage(accessRequestId).rejectAccessRequest(rejectedReason)
  }

  Then("the access request approved page is displayed") { () =>
    AccessRequestApprovedPage(accessRequestId)
  }

  Then("the access request rejected page is displayed") { () =>
    AccessRequestRejectedPage(accessRequestId)
  }

  Then("returns to the access requests page") { () =>
    AccessRequestApprovedPage(accessRequestId).returnToAccessRequests()
  }

  Then("returns to the access requests page after rejection") { () =>
    AccessRequestRejectedPage(accessRequestId).returnToAccessRequests()
  }

}
