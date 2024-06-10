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

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.{ExpectedCondition, ExpectedConditions}
import uk.gov.hmrc.test.ui.utilities.{CheckMode, Mode, NormalMode}

import scala.util.matching.Regex

sealed trait PageReadyTest {

  def expectedCondition: ExpectedCondition[_]

}

case class UrlPageReadyTest(url: String) extends PageReadyTest with Robot {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.urlToBe(buildFullUrl(url))
  }

}

object UrlPageReadyTest {

  private val pathRegex: Regex = """^(.*\/)(.*)$""".r

  def withMode(url: String, mode: Mode): UrlPageReadyTest = {
    UrlPageReadyTest(urlForMode(url, mode))
  }

  def urlForMode(url: String, mode: Mode): String = {
    mode match {
      case NormalMode => url
      case CheckMode => url match {
        case pathRegex(left, right) => s"${left}change-$right"
        case _ => url
      }
    }
  }

}

case class UrlContainingPageReadyTest(url: String) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.urlContains(url)
  }

}

object UrlContainingPageReadyTest {

  def withMode(url: String, mode: Mode): UrlContainingPageReadyTest = {
    UrlContainingPageReadyTest(UrlPageReadyTest.urlForMode(url, mode))
  }

}

case class TitlePageReadyTest(title: String) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.titleIs(title)
  }

}

case class ApiHubTitlePageReadyTest(title: String) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    TitlePageReadyTest(s"$title - The API Hub - GOV.UK").expectedCondition
  }

}

case class QuestionPageTitlePageReadyTest(title: String) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    AnyOfPageReadyTest(
      Seq(
        ApiHubTitlePageReadyTest(title),
        ApiHubTitlePageReadyTest(s"Error: $title")
      )
    ).expectedCondition
  }

}

case class ElementPageReadyTest(by: By) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.visibilityOfElementLocated(by)
  }

}

case class AllOfPageReadyTest(tests: Seq[PageReadyTest]) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.and(tests.map(test => test.expectedCondition): _*)
  }

}

case class AnyOfPageReadyTest(tests: Seq[PageReadyTest]) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.or(tests.map(_.expectedCondition): _*)
  }

}
