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
import uk.gov.hmrc.test.ui.pages.application.ApplicationDeleteSuccessPage._
import uk.gov.hmrc.test.ui.pages.application.ApplicationDeleteSuccessPage.elements._
import uk.gov.hmrc.test.ui.pages._

class ApplicationDeleteSuccessPage(id: String) extends BasePage[ApplicationDeleteSuccessPage](pageReadyTest(id)) {

  def returnToDashboard(): DashboardPage = {
    click(dashboardLink)
    DashboardPage()
  }

}

object ApplicationDeleteSuccessPage {

  //The confirmation and success pages have the same URL so test on both URL and title
  def pageReadyTest(id: String): PageReadyTest = {
    PageReadyTests.allOf(
      PageReadyTests.apiHubPage.url(s"application/delete/$id"),
      PageReadyTests.apiHubPage.title("Application successfully deleted")
    )
  }

  object elements {
    val dashboardLink: By = By.id("returnToDashboardLink")
  }

  def apply(id: String): ApplicationDeleteSuccessPage = {
    new ApplicationDeleteSuccessPage(id)
  }

}
