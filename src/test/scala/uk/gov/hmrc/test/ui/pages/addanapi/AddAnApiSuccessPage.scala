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
import uk.gov.hmrc.test.ui.pages.addanapi.AddAnApiSuccessPage._
import uk.gov.hmrc.test.ui.pages.addanapi.AddAnApiSuccessPage.elements._
import uk.gov.hmrc.test.ui.pages.application.ApplicationDetailsPage
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

class AddAnApiSuccessPage extends BasePage[AddAnApiSuccessPage](pageReadyTest) {

  def viewApplication(): ApplicationDetailsPage = {
    val applicationId = getApplicationId match {
      case Some(id) => id
      case _ => throw new IllegalStateException("Application Id is missing from this page.")
    }

    click(applicationLink)
    ApplicationDetailsPage(applicationId)
  }

  def getSuccessSummary: String = {
    getText(successSummary)
  }

  private def getApplicationId: Option[String] = {
    getAttribute(applicationLink, applicationIdAttribute)
  }

}

object AddAnApiSuccessPage {

  // The URL for this page includes identifiers that are difficult to work with
  // Therefore we'll use a title-based page ready test
  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.title("API added successfully")

  object elements {
    val applicationIdAttribute = "data-application-id"
    val applicationLink: By = By.id("applicationLink")
    val successSummary: By = By.id("successSummary")
  }

  def apply(): AddAnApiSuccessPage = {
    new AddAnApiSuccessPage()
  }

}
