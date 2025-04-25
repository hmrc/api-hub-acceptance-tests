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
import uk.gov.hmrc.test.ui.pages.addanapi.AddAnApiSuccessPage
import uk.gov.hmrc.test.ui.pages.application.ApplicationDetailsPage
import uk.gov.hmrc.test.ui.pages.{Journeys, Robot}
import uk.gov.hmrc.test.ui.utilities.SharedState

import java.time.LocalDate

@ScenarioScoped
class ApplicationDetailsSteps @Inject()(sharedState: SharedState) extends BaseStepDef with Robot {

  private val expectedApplicationApisText: String = "You have not added any APIs"
  private var apiId = ""
  private var apiTitle = ""

  Then("""the new user registers an application""") { () =>
    Journeys
      .registerAnApplication(sharedState)
  }

  Then("""the application can be viewed""") { () =>
    ApplicationDetailsPage(sharedState.application.id)
      .foreach(
        applicationDetailsPage =>
          applicationDetailsPage.getApplicationName shouldBe sharedState.application.name
      )
  }

  Then("""the user attempts to add an api to the application""") { () =>
    ApplicationDetailsPage(sharedState.application.id)
      .addApis()
      .selectRandomApi()
      .foreach(
        apiDetailsPage => {
          apiId = apiDetailsPage.getApiId
          apiTitle = apiDetailsPage.getApiTitle
        }
      )
      .addToAnApplication()
      .selectApplication(sharedState.application.id)
      .selectAllEndpoints()
      .confirmUsagePolicy()
      .continue()
  }

  When("""the application details, application apis as well as the team members sections should be correct""") { () =>
    ApplicationDetailsPage(sharedState.application.id)
      .foreach(
        applicationDetailsPage => {
          applicationDetailsPage.getApplicationName shouldBe sharedState.application.name
          applicationDetailsPage.getCreated shouldBe LocalDate.now()
          applicationDetailsPage.getNoApisMessage.toLowerCase should include(expectedApplicationApisText.toLowerCase)
          applicationDetailsPage.getOwningTeamName shouldBe sharedState.team.name
        }
      )
  }


  Then("""the api is added to the application""") { () =>
    AddAnApiSuccessPage()
      .foreach(
        addAnApiSuccessPage=>
          addAnApiSuccessPage.getSuccessSummary should startWith(apiTitle)
      )
      .viewApplication()
      .environment("test")
      .hasApi(apiId) shouldBe true
  }

  When("the user navigates to an invalid application id {string}") { (string: String) =>
    navigateToRelativeUrl(s"application/details/$string")
  }

  Given("""the user chooses {string} from the application left hand nav menu""") { (string: String) =>
    string match {
      case "Production Environment" => ApplicationDetailsPage(sharedState.application.id).environment("production")
      case "Test Environment" => ApplicationDetailsPage(sharedState.application.id).environment("test")
      case "Delete application" => ApplicationDetailsPage(sharedState.application.id).deleteApplication(sharedState)
      case _ => throw new IllegalArgumentException(s"Unknown option: $string")
    }
  }

}
