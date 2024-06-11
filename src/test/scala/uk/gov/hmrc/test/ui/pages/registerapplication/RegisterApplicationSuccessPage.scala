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

package uk.gov.hmrc.test.ui.pages.registerapplication

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.application.ApplicationDetailsPage
import uk.gov.hmrc.test.ui.pages.registerapplication.RegisterApplicationSuccessPage._
import uk.gov.hmrc.test.ui.pages.registerapplication.RegisterApplicationSuccessPage.elements._
import uk.gov.hmrc.test.ui.pages.{ApiHubTitlePageReadyTest, BasePage, PageReadyTest}

class RegisterApplicationSuccessPage extends BasePage[RegisterApplicationSuccessPage](pageReadyTest) {

  def viewRegisteredApplication(): ApplicationDetailsPage = {
    val applicationId = getApplicationId match {
      case Some(id) => id
      case _ => throw new IllegalStateException("Application Id is missing from this page.")
    }

    click(applicationLink)
    ApplicationDetailsPage(applicationId)
  }

  private def getApplicationId: Option[String] = {
    getAttribute(applicationLink, applicationIdAttribute)
  }

}

object RegisterApplicationSuccessPage {

  // The URL contains the Application Id which we can't possibly know prior to the page being displayed
  // Therefore we'll use a title-based page ready test
  val pageReadyTest: PageReadyTest = ApiHubTitlePageReadyTest("Register Application Success")

  object elements {
    val applicationIdAttribute = "data-application-id"
    val applicationLink: By = By.id("applicationLink")
  }

  def apply(): RegisterApplicationSuccessPage = {
    new RegisterApplicationSuccessPage()
  }

}
