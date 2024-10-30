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
import uk.gov.hmrc.test.ui.pages.application.EnvironmentAndCredentialsPage.TestTab
import uk.gov.hmrc.test.ui.pages.application.EnvironmentAndCredentialsPage.ProductionTab
import uk.gov.hmrc.test.ui.pages.application.{EnvironmentAndCredentialsPage, GenerateProductionCredentialsPage, ProductionCredentialsSuccessPage}
import uk.gov.hmrc.test.ui.pages.Journeys
import uk.gov.hmrc.test.ui.utilities.SharedState

@ScenarioScoped
class CredentialsSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  private var clientId: String = ""

  When("""the user creates a new test credential""") { () =>
    EnvironmentAndCredentialsPage(sharedState.application.id)
      .createNewTestCredential()
      .foreach(
        environmentAndCredentialsPage =>
          clientId = environmentAndCredentialsPage.lastTestCredentialClientId
      )
      .viewTestEnvironment()
  }

  When("""the user starts the create new production credential journey""") { () =>
    EnvironmentAndCredentialsPage(sharedState.application.id)
      .viewProductionEnvironment()
      .createNewProductionCredential()
  }

  Then("""the user confirms they meet the conditions to create a new production credential""") { () =>
    GenerateProductionCredentialsPage(sharedState.application.id).confirmAndContinue()
  }

  Then("""the client secret successfully created page is displayed""") { () =>
    ProductionCredentialsSuccessPage(sharedState.application.id)
  }

  Then("""the user returns to the environments and credentials page""") { () =>
    ProductionCredentialsSuccessPage(sharedState.application.id)
      .returnToEnvironmentsAndCredentials()
      .viewProductionEnvironment()
  }

  Then("""there is/are {int} test credential(s)""") { (expectedCount: Int) =>
    EnvironmentAndCredentialsPage(sharedState.application.id, Some(TestTab))
      .foreach { page =>
        val credentialCount = page.getTestCredentialCount // Extract the credential count once
        credentialCount shouldBe expectedCount // Use the dynamic expected count
      }
  }

  Then("""there is/are {int} production credential(s)""") { (expectedCount: Int) =>
    EnvironmentAndCredentialsPage(sharedState.application.id, Some(ProductionTab))

      .foreach { page =>
        val credentialCount = page.getProductionCredentialCount // Extract the credential count once
        credentialCount shouldBe expectedCount // Use the dynamic expected count
      }
  }

  When("""the user revokes the first test credential""") { () =>
    EnvironmentAndCredentialsPage(sharedState.application.id, Some(TestTab))
      .revokeFirstTestCredential()
  }

  Then("""the recently created test credential still exists""") { () =>
    EnvironmentAndCredentialsPage(sharedState.application.id, Some(TestTab)).hasTestCredential(clientId)
  }

  Then("""the recently created production credential still exists""") { () =>
    EnvironmentAndCredentialsPage(sharedState.application.id, Some(ProductionTab)).hasProductionCredential(clientId)
  }

  When("""the user creates TWO production credentials""") { () =>

    Journeys
      .createProductionCredential(sharedState)
    Journeys
      .createProductionCredential(sharedState)
      .viewProductionEnvironment()

  }

  When("""the user revokes the first production credential""") { () =>
    EnvironmentAndCredentialsPage(sharedState.application.id, Some(ProductionTab))

      .revokeFirstProductionCredential()
  }


}
