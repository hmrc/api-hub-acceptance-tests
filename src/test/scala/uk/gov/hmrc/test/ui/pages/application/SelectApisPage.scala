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
import uk.gov.hmrc.test.ui.pages.application.SelectApisPage._
import uk.gov.hmrc.test.ui.pages.application.SelectApisPage.elements._
import uk.gov.hmrc.test.ui.pages.application.SelectApisPage.elements.selectedApi
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

class SelectApisPage extends BasePage[SelectApisPage](pageReadyTest) {

  def setSelectedApi(): Unit = {
    click(selectedApi)
    click(continueButton)
  }

}

object SelectApisPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.questionPage.url("application/request-production-access/select-apis")

  object elements {
    val selectedApi: By = By.id("value_0")
    val continueButton: By = By.id("continueButton")
  }

  def apply(): SelectApisPage = {
    new SelectApisPage()
  }

}
