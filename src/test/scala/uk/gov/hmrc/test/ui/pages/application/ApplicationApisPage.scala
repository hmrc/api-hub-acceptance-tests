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
import uk.gov.hmrc.test.ui.pages.application.ApplicationApisPage._
import uk.gov.hmrc.test.ui.pages.application.ApplicationApisPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

class ApplicationApisPage(id: String) extends BasePage[ApplicationApisPage](pageReadyTest(id)) {

  def requestProductionAccess(): SelectApisPage = {
    click(requestProductionAccessButton)
    SelectApisPage()
  }

}

object ApplicationApisPage {

  def pageReadyTest(id: String): PageReadyTest = PageReadyTests.apiHubPage.url(s"application/apis/$id")

  object elements {
    val requestProductionAccessButton: By = By.id("requestProductionAccessButton")
  }

  def apply(id: String): ApplicationApisPage = {
    new ApplicationApisPage(id)
  }

}
