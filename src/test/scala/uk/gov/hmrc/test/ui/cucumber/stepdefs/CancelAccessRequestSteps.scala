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
import uk.gov.hmrc.test.ui.pages.application.ApplicationDetailsPage
import uk.gov.hmrc.test.ui.pages.cancelaccessrequest.{CancelAccessRequestConfirmPage, CancelAccessRequestSelectApiPage, CancelAccessRequestSuccessPage}
import uk.gov.hmrc.test.ui.utilities.SharedState

@ScenarioScoped
class CancelAccessRequestSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  When("the user selects the cancel access requests link") { () =>
    ApplicationDetailsPage(sharedState.application.id).cancelAccessRequests()
  }

  When("the user selects to cancel the access request for the first API") { ()=>
    CancelAccessRequestSelectApiPage().selectApiAndContinue()
  }

  When("the user confirms the access request cancellations") { () =>
    CancelAccessRequestConfirmPage().confirmAndContinue()
  }

  Then("the user is shown the cancel access request success page") { () =>
    CancelAccessRequestSuccessPage()
  }

  Then("the user can select to return to the application details page") { () =>
    CancelAccessRequestSuccessPage().viewApplication()
  }

}
