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
import uk.gov.hmrc.test.ui.pages.Robot
import uk.gov.hmrc.test.ui.pages.admin.ManageApplicationsPage
import uk.gov.hmrc.test.ui.utilities.SharedState

@ScenarioScoped
class AdminSteps @Inject()(sharedState: SharedState) extends BaseStepDef with Robot {

  When("the user navigates to the Integration Hub Admin page") {
    navigateToRelativeUrl(s"admin/applications")
  }

  When("the user the user enters the application id to filter") { () =>
    ManageApplicationsPage()
      .setFilterText(sharedState.application.id)
  }

  Then("the api link is visible") { () =>
    ManageApplicationsPage().hasApi(sharedState.application.id)
  }

}
