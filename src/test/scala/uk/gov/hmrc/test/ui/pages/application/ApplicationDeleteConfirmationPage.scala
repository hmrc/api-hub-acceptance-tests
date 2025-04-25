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

package uk.gov.hmrc.test.ui.pages.application

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.application.ApplicationDeleteConfirmationPage._
import uk.gov.hmrc.test.ui.pages.application.ApplicationDeleteConfirmationPage.elements._
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.utilities.SharedState

class ApplicationDeleteConfirmationPage(sharedState: SharedState) extends BasePage[ApplicationDeleteConfirmationPage](pageReadyTest(sharedState.application.id)) with ErrorSummary {

  def acceptAndContinue(): ApplicationDeleteSuccessPage = {
    click(confirmCheckbox)
    click(acceptAndContinueButton)
    ApplicationDeleteSuccessPage(sharedState)
  }


}

object ApplicationDeleteConfirmationPage {

  // The confirmation and success pages have the same URL so test on both URL and title
  // As this is a question page we might have the error variant of title
  def pageReadyTest(id: String): PageReadyTest = {
    PageReadyTests.allOf(
      PageReadyTests.questionPage.url(s"application/delete/$id"),
      PageReadyTests.questionPage.title("Delete application")
    )
  }

  object elements {
    val confirmCheckbox: By = By.id("value_0")
    val acceptAndContinueButton: By = By.id("acceptAndContinueButton")
  }

  def apply(sharedState: SharedState): ApplicationDeleteConfirmationPage = {
    new ApplicationDeleteConfirmationPage(sharedState)
  }

}
