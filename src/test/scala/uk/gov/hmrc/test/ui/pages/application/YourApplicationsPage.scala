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
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.pages.application.YourApplicationsPage._
import uk.gov.hmrc.test.ui.pages.application.YourApplicationsPage.elements._

class YourApplicationsPage extends BasePage[YourApplicationsPage](pageReadyTest) {

  def hasApplication(id: String): Boolean = {
    exists(application(id))
  }

}

object YourApplicationsPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("applications")

  object elements {
    val applicationIdAttribute = "data-application-id"
    def application(id: String): By = By.cssSelector(s"[$applicationIdAttribute='$id']")
  }

  def apply(): YourApplicationsPage = {
    new YourApplicationsPage()
  }

}
