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

package scala.uk.gov.hmrc.test.ui.cucumber.stepdefs

import com.google.inject.Inject
import io.cucumber.guice.ScenarioScoped
import uk.gov.hmrc.test.ui.cucumber.stepdefs.BaseStepDef
import uk.gov.hmrc.test.ui.pages.application.GenerateProductionCredentialsPage
import uk.gov.hmrc.test.ui.pages.{Journeys, Robot}
import uk.gov.hmrc.test.ui.utilities.SharedState

import java.time.LocalDate
import scala.uk.gov.hmrc.test.ui.pages.application.GenerateProductionCredentialsPage

@ScenarioScoped
class GenerateProductionCredentialsSteps @Inject()(sharedState: SharedState) extends BaseStepDef {

  Then("""the user confirms generation production credentials""") { () =>

    GenerateProductionCredentialsPage(sharedState.application.id).selectconfirmcheckbox()
    GenerateProductionCredentialsPage(sharedState.application.id).clickconfirmandcontinue()

    }

}