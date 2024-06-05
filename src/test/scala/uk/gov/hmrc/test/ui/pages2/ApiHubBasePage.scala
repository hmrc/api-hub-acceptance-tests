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

package uk.gov.hmrc.test.ui.pages2

import uk.gov.hmrc.test.ui.pages2.ApiHubBasePage._

abstract class ApiHubBasePage(pageReadyTest: PageReadyTest) extends BasePage(apiHubPageReadyTest(pageReadyTest)) {

  // Add common stuff like nav menu items here

}

object ApiHubBasePage {

  def apiHubTitle(title: String): String = {
    s"$title - The API Hub - GOV.UK"
  }

  def apiHubPageReadyTest(pageReadyTest: PageReadyTest): PageReadyTest = {
    pageReadyTest match {
      case TitlePageReadyTest(title) => TitlePageReadyTest(apiHubTitle(title))
      case _ => pageReadyTest
    }
  }

}
