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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.ErrorPage._
import uk.gov.hmrc.test.ui.pages.ErrorPage.elements._

/**
 * Represents the error template view used to render errors such as:
 *    - Technical difficulties
 *    - Application not found
 */
class ErrorPage extends BasePage[ErrorPage](pageReadyTest) {

  def getErrorHeading: String = {
    getText(errorHeading)
  }

  def getErrorMessage: String = {
    getText(errorMessage)
  }

}

object ErrorPage {

  // The URL and title of this page are variable
  // Test on the presence of the heading and message
  val pageReadyTest: PageReadyTest = {
    PageReadyTests.allOf(
      PageReadyTests.element.locator(errorHeading),
      PageReadyTests.element.locator(errorMessage)
    )
  }

  object elements {
    val errorHeading: By = By.id("errorHeading")
    val errorMessage: By = By.id("errorMessage")
  }

  def apply(): ErrorPage = {
    new ErrorPage()
  }

}
