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
import uk.gov.hmrc.test.ui.pages.application.ApplicationDetailsPage
import uk.gov.hmrc.test.ui.pages.registerapplication.{CheckYourAnswersPage, RegisterApplicationNamePage, RegisterApplicationSuccessPage, RegisterApplicationTeamPage}
import uk.gov.hmrc.test.ui.utilities.{NormalMode, SharedState}

@ScenarioScoped
class RegisterApplicationSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  Given("the new user starts the register application journey") { () =>
    Journeys
      .openStartPage(sharedState)
      .dashboard(sharedState)
      .registerAnApplication()
  }

  When("the user enters an application name") { () =>
    RegisterApplicationNamePage(NormalMode).setApplicationNameNormalMode(sharedState.application.name)
  }

  When("the user selects an owning team for the application") { () =>
    RegisterApplicationTeamPage(NormalMode).setTeamNormalMode(sharedState.team)
  }

  When("the user registers the application on the check your answers page") { () =>
    CheckYourAnswersPage()
      .registerApplication()
      .foreach(
        registerApplicationSuccessPage =>
          sharedState.application.id = registerApplicationSuccessPage.getApplicationId
      )
  }

  When("the user clicks to view the new application on the success page") { () =>
    RegisterApplicationSuccessPage().viewApplication()
  }

  Then("the new application's details are correct") { () =>
    ApplicationDetailsPage(sharedState.application.id)
      .foreach(
        applicationDetailsPage => {
          applicationDetailsPage.getApplicationName shouldBe sharedState.application.name
          applicationDetailsPage.getOwningTeamName shouldBe sharedState.team.name
        }
      )
  }

}
