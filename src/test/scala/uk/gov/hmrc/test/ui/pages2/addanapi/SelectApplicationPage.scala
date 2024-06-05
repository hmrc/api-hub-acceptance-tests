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

package uk.gov.hmrc.test.ui.pages2.addanapi

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages2.addanapi.SelectApplicationPage._
import uk.gov.hmrc.test.ui.pages2.addanapi.SelectApplicationPage.elements._
import uk.gov.hmrc.test.ui.pages2.{ApiHubBasePage, PageReadyTest, UrlPageReadyTest}

class SelectApplicationPage extends ApiHubBasePage(pageReadyTest) {

  def selectApplication(id: String): SelectEndpointsPage = {
    // Note that if there are more than 5 applications then the view changes to
    // using a dropdown instead of radios.
    val radio = findElement(By.cssSelector(s"[data-application-id='$id']"))
    radio.click()
    click(continueButton)
    SelectEndpointsPage()
  }

}

object SelectApplicationPage {

  val pageReadyTest: PageReadyTest = UrlPageReadyTest("apis/add-an-api/select-application")

  object elements {
    val continueButton: By = By.id("continueButton")
  }

  def apply(): SelectApplicationPage = {
    new SelectApplicationPage()
  }

}
