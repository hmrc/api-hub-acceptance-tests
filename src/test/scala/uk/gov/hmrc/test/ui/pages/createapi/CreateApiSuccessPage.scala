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
import uk.gov.hmrc.test.ui.pages.createapi.CreateApiSuccessPage.elements.dashboardLink
import uk.gov.hmrc.test.ui.pages.{BasePage, DashboardPage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.{Mode, NormalMode, SharedState}

class CreateApiSuccessPage(sharedState: SharedState, mode: Mode) extends BasePage[CreateApiSuccessPage](CreateApiSuccessPage.pageReadyTest(sharedState, mode)) {
  def clickDashboardLink(): DashboardPage = {
    click(dashboardLink)
    DashboardPage(sharedState)
  }
}

object CreateApiSuccessPage {

  def pageReadyTest(sharedState: SharedState, mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url(s"my-apis/produce/success?apiName=${sharedState.api.title}&publisherReference=apim-${sharedState.api.title}", mode)
  }

  def apply(sharedState: SharedState, mode: Mode = NormalMode): CreateApiSuccessPage = {
    new CreateApiSuccessPage(sharedState, mode)
  }

  object elements {
    def newApiDetailsLink(sharedState: SharedState): By = By.cssSelector(s"a[text()='${sharedState.api.title}']")

    val dashboardLink: By = By.id("dashboardLink")
  }

}
