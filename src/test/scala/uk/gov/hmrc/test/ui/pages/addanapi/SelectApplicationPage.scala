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

package uk.gov.hmrc.test.ui.pages.addanapi

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import uk.gov.hmrc.test.ui.pages.addanapi.SelectApplicationPage._
import uk.gov.hmrc.test.ui.pages.addanapi.SelectApplicationPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}
import uk.gov.hmrc.test.ui.utilities.{Mode, NormalMode}

class SelectApplicationPage(mode: Mode) extends BasePage[SelectApplicationPage](pageReadyTest(mode)) {

  def selectApplication(id: String): SelectEndpointsPage = {
    // Note that if there are more than 5 applications then the view changes to
    // using a dropdown instead of radios. Determine which by presence of select
    // input.
    if (exists(applicationSelect)) {
      new Select(findElement(applicationSelect)).selectByValue(id)
    }
    else {
      findElement(applicationRadio(id)).click()
    }

    click(continueButton)
    SelectEndpointsPage()
  }

}

object SelectApplicationPage {

  def pageReadyTest(mode: Mode): PageReadyTest = {
    PageReadyTests.journeyQuestionPage.url("apis/add-an-api/select-application", mode)
  }

  object elements {
    val applicationSelect: By = By.id("applicationSelect")
    def applicationRadio(id: String): By = By.cssSelector(s"[data-application-id='$id']")
    val continueButton: By = By.id("continueButton")
  }

  def apply(mode: Mode = NormalMode): SelectApplicationPage = {
    new SelectApplicationPage(mode)
  }

}
