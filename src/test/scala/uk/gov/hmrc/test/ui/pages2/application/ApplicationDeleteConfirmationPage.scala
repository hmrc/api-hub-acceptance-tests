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

package uk.gov.hmrc.test.ui.pages2.application

import uk.gov.hmrc.test.ui.pages2.application.ApplicationDeleteConfirmationPage._
import uk.gov.hmrc.test.ui.pages2.{BasePage, CombinedPageReadyTest, PageReadyTest, TitlePageReadyTest, UrlPageReadyTest}

class ApplicationDeleteConfirmationPage(id: String) extends BasePage[ApplicationDeleteConfirmationPage](pageReadyTest(id)) {

}

object ApplicationDeleteConfirmationPage {

  //The confirmation and success pages have the same URL so test on both URL and title
  def pageReadyTest(id: String): PageReadyTest = CombinedPageReadyTest(
    UrlPageReadyTest(s"application/delete/$id"),
    TitlePageReadyTest.forApiHubTitle("Delete application")
  )

  def apply(id: String): ApplicationDeleteConfirmationPage = {
    new ApplicationDeleteConfirmationPage(id)
  }

}
