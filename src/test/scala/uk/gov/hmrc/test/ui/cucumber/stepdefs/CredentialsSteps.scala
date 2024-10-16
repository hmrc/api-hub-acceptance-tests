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
import uk.gov.hmrc.test.ui.pages.application.{EnvironmentAndCredentialsPage, GenerateProductionCredentialsPage, ProductionCredentialsSuccessPage}
import uk.gov.hmrc.test.ui.utilities.SharedState

@ScenarioScoped
class CredentialsSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  When("""the user adds a test credential""") { () =>
    EnvironmentAndCredentialsPage(sharedState.application.id).addTestCredential()
  }

  When("""the user adds a production credential""") { () =>
    EnvironmentAndCredentialsPage(sharedState.application.id)
      .viewProductionEnvironment()
      .addProductionCredential()
  }

  Then("""the user confirms generation production credentials""") { () =>
    GenerateProductionCredentialsPage(sharedState.application.id).confirmAndContinue()
  }

  Then("the user sees the generate production credentials success page") { () =>
    ProductionCredentialsSuccessPage(sharedState.application.id)
  }

  Then("the user can select to return to the environments and credentials page") { () =>
    ProductionCredentialsSuccessPage(sharedState.application.id)
  }

  Then("the user selects to return to the environments and credentials page") { () =>
    ProductionCredentialsSuccessPage(sharedState.application.id).returnToEnvironmentsAndCredentials()
  }

  Then("""the client id should be added to the test environments credentials with count {int}""") { (expectedCount: Int) =>
    EnvironmentAndCredentialsPage(sharedState.application.id)
      .foreach { page =>
        val credentialCount = page.getSecondaryCredentialCount // Extract the credential count once
        credentialCount shouldBe expectedCount // Use the dynamic expected count
      }
  }

  Then("""the client id should be added to the Production environments credentials with count {int}""") { (expectedCount: Int) =>
    EnvironmentAndCredentialsPage(sharedState.application.id)
      .viewProductionEnvironment()
      .foreach { page =>
        val credentialCount = page.getSecondaryCredentialCount // Extract the credential count once
        credentialCount shouldBe expectedCount // Use the dynamic expected count
      }
  }

}
