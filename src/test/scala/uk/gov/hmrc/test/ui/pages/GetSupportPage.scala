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
import uk.gov.hmrc.test.ui.pages.GetSupportPage._
import uk.gov.hmrc.test.ui.pages.GetSupportPage.elements.{getSupportConsumerBlock, getSupportProducerBlock}

class GetSupportPage extends BasePage[GetSupportPage](pageReadyTest) {
  def getSupportConsumerBlockText: String = {
    findElements(getSupportConsumerBlock)
      .map(_.getText)
      .mkString
  }

  def getSupportProducerBlockText: String = {
    findElements(getSupportProducerBlock)
      .map(_.getText)
      .mkString
  }
}

object GetSupportPage {

  val pageReadyTest: PageReadyTest = PageReadyTests.apiHubPage.url("get-support")

  object elements {
    val  getSupportConsumerBlock: By = By.id("get-support-consumer")
    val  getSupportProducerBlock: By = By.id("get-support-producer")
  }

  def apply(): GetSupportPage = {
    new GetSupportPage()
  }

}
