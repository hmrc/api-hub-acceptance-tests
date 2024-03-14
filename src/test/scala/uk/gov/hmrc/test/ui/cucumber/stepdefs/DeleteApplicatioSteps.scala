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

import uk.gov.hmrc.test.ui.pages.{ApplicationDeletePage, ApplicationDetailsPage}

class DeleteApplicatioSteps extends BaseStepDef {
  Given("""the user chooses {string} from the left hand nav menu""") { (string: String) =>
    ApplicationDetailsPage.chooseLhnmOption(string)
  }

  When("""deletes the application""") { () =>
    ApplicationDeletePage.confirmDeletionOfApplication()
  }

  When("""the user attempts to delete the application without confirming""") { () =>
    ApplicationDeletePage.clickAcceptAndContinueButton()
  }
  When("""the error make a selection error is displayed""") { () =>
    assert(ApplicationDeletePage.isDeleteApplicationErrorDisplayed())
    assert(ApplicationDeletePage.isConfirmCheckboxDisplayed())
  }

  When("""the user chooses to cancel the deletion of the application""") { () =>
    ApplicationDeletePage.cancel()
  }
//
//  Then("""the user is redirected to the {string} page""") { (string: String) =>
//    assert(ApplicationDetailsPage.getPageTitle() == string)
//    assert(ApplicationDetailsPage.getApplicationName == application.name)
//  }
}
