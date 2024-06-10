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
import uk.gov.hmrc.test.ui.pages.ErrorSummary._
import uk.gov.hmrc.test.ui.pages.ErrorSummary.elements._

import scala.util.matching.Regex

trait ErrorSummary {
  self: Robot =>

  def hasErrorSummary: Boolean = {
    findElements(errorSummary).nonEmpty
  }

  // Error messages at the top of the page in the summary
  def getErrorSummaryList: Seq[String] = {
    findElements(errorSummaryMessages)
      .map(_.getText.trim)
  }

  // Error messages that appear next to the input elements
  // These start "Error: \n" so a regex is used to take that out
  def getErrorMessages: Seq[String] = {
    findElements(errorMessages)
      .map(_.getText.trim)
      .map {
        case errorMessageRegex(_, error) => error
        case text => text
      }
  }

}

object ErrorSummary {

  val errorMessageRegex: Regex = "^(Error:\\s*)?(.+)$".r

  object elements {
    val errorSummary: By = By.cssSelector("[data-module='govuk-error-summary']")
    val errorSummaryMessages: By = By.cssSelector(".govuk-error-summary .govuk-error-summary__list a")
    val errorMessages: By = By.cssSelector(".govuk-error-message")
  }

}
