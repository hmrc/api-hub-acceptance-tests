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
import uk.gov.hmrc.test.ui.pages.Journeys
import uk.gov.hmrc.test.ui.pages.application.{ApplicationDeleteConfirmationPage, ApplicationDeleteSuccessPage}
import uk.gov.hmrc.test.ui.utilities.SharedState

@ScenarioScoped
class DeleteApplicatioSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  When("""deletes the application""") { () =>
    ApplicationDeleteConfirmationPage(sharedState.application.id)
      .acceptAndContinue()
  }

  When("""the user attempts to delete the application without confirming""") { () =>
    ApplicationDeleteConfirmationPage(sharedState.application.id)
      .acceptAndContinueWithoutConfirm()
  }

  When("""the error make a selection error is displayed""") { () =>
    ApplicationDeleteConfirmationPage(sharedState.application.id)
      .hasErrorSummary
  }

  When("""the user chooses to cancel the deletion of the application""") { () =>
    ApplicationDeleteConfirmationPage(sharedState.application.id)
      .cancel()
  }

  Then("""the previously registered application should no longer be listed in all applications""") { () =>
    ApplicationDeleteSuccessPage(sharedState.application.id)
      .returnToDashboard()

    Journeys
      .openYourApplicationsPage()
      .foreach(
        yourApplicationsPage =>
          yourApplicationsPage.hasApplication(sharedState.application.id) shouldBe false
      )
  }

}
