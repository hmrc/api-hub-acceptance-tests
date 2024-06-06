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

package uk.gov.hmrc.test.ui.pages2.registerapplication

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages2.{BasePage, PageReadyTest, TitlePageReadyTest}
import uk.gov.hmrc.test.ui.pages2.application.ApplicationDetailsPage
import uk.gov.hmrc.test.ui.pages2.registerapplication.RegisterApplicationSuccessPage._
import uk.gov.hmrc.test.ui.pages2.registerapplication.RegisterApplicationSuccessPage.elements._

class RegisterApplicationSuccessPage extends BasePage[RegisterApplicationSuccessPage](pageReadyTest) {

  def viewRegisteredApplication(): ApplicationDetailsPage = {
    val applicationId = getApplicationId
    click(applicationLink)
    ApplicationDetailsPage(applicationId)
  }

  private def getApplicationId: String = {
    getAttribute(applicationLink, "data-application-id")
  }

}

object RegisterApplicationSuccessPage {

  // The URL contains the Application Id which we can't possibly know prior to the page being displayed
  // Therefore we'll use a title-based page ready test
  val pageReadyTest: PageReadyTest = TitlePageReadyTest.forApiHubTitle("Register Application Success")

  object elements {
    val applicationLink: By = By.id("applicationLink")
  }

  def apply(): RegisterApplicationSuccessPage = {
    new RegisterApplicationSuccessPage()
  }

}
