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

package uk.gov.hmrc.test.ui.pages.createapi

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.api.ApiDetailsPage
import uk.gov.hmrc.test.ui.pages.createapi.ProducerApiDetailsPage.elements.viewAsConsumerLink
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.{Mode, NormalMode, SharedState}

class ProducerApiDetailsPage(sharedState: SharedState, mode: Mode) extends BasePage[ProducerApiDetailsPage] (ProducerApiDetailsPage.pageReadyTest(mode)) {

  def viewApiAsConsumer() : ApiDetailsPage = {
    click(viewAsConsumerLink)
    switchToWindow(1)
    ApiDetailsPage(sharedState.api.id)
  }
}

object ProducerApiDetailsPage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.title("API details")
  }

  object elements {
    val viewAsConsumerLink: By = By.cssSelector("[data-nav-item-page='ViewApiAsConsumerPage']")
  }

  def apply(sharedState: SharedState, mode: Mode = NormalMode): ProducerApiDetailsPage = {
    new ProducerApiDetailsPage(sharedState, mode)
  }

}
