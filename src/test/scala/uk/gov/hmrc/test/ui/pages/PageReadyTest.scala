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
import org.openqa.selenium.support.ui.{ExpectedCondition, ExpectedConditions}
import uk.gov.hmrc.test.ui.utilities.{CheckMode, Mode, NormalMode}

import scala.util.matching.Regex

/**
 * A test that the current page meets expectations of what it is and is also
 * ready for interactions.
 *
 * There is a precedence of "correctness" and you should use the highest one
 * possible:
 *    - URL
 *    - URL containing
 *    - Title or element
 *
 * It is possible to combine tests using and or or logic using container tests:
 *    - AllOfPageReadyTest (and)
 *    - AnyOfPageReadyTest (or)
 *
 * When using weaker tests such as title or element consider using a combined
 * test.
 *
 * If a page has some delay before all elements are visible and ready for
 * interaction then consider using a combined test including an element test.
 * Pick an element that has some delay being visible.
 */
sealed trait PageReadyTest {

  /**
   * An expected condition that must be met for the current page to be
   * considered correct and ready.
   *
   * @return  the expected condition
   */
  def expectedCondition: ExpectedCondition[_]

}

/**
 * A PageReadyTest that checks the current page has the correct URL.
 *
 * @param url  the URL, relative to the base service URL
 */
case class UrlPageReadyTest(url: String) extends PageReadyTest with Robot {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.urlToBe(buildFullUrl(url))
  }

}

object UrlPageReadyTest {

  private val pathRegex: Regex = """^(.*\/)(.*)$""".r

  /**
   * Creates a UrlPageReadyTest for a given URL and Mode. The Mode can change
   * the URL when in CheckMode.
   *
   * @param url  the URL, relative to the base service URL
   * @param mode  Mode that the page is called in
   * @return  UrlPageReadyTest configured with a URL correct for the Mode
   */
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

/**
 * A PageReadyTest that tests that the current page's URL contains a given URL.
 *
 * Use this when the URL is not 100% predictable.
 *
 * @param url  the URL, relative to the base service URL
 */
case class UrlContainingPageReadyTest(url: String) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.urlContains(url)
  }

}

object UrlContainingPageReadyTest {

  /**
   * Creates a UrlContainingPageReadyTest for a given URL and Mode. The Mode can
   * change the URL when in CheckMode.
   *
   * @param url  the URL, relative to the base service URL
   * @param mode  Mode that the page is called in
   * @return  UrlContainingPageReadyTest configured with a URL correct for the
   *          Mode
   */
  def withMode(url: String, mode: Mode): UrlContainingPageReadyTest = {
    UrlContainingPageReadyTest(UrlPageReadyTest.urlForMode(url, mode))
  }

}

/**
 * A PageReadyTest that tests that the current page's title matches a given
 * value.
 *
 * @param title  the value to match
 */
case class TitlePageReadyTest(title: String) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.titleIs(title)
  }

}

/**
 * An extension to TitlePageReadyTest that adds the common title components of
 * a standard API Hub/HMRC page.
 *
 * An API Hub page title follows the format:
 *    [Page title] - The API Hub - GOV.UK
 *
 * @param title  the page title
 */
case class ApiHubTitlePageReadyTest(title: String) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    TitlePageReadyTest(s"$title - The API Hub - GOV.UK").expectedCondition
  }

}

/**
 * An extension to ApiHubTitlePageReadyTest for API Hub question pages that
 * recognises that a page failing validation will have a different title.
 *
 * A question page failing validation will have a title in this format:
 *    Error: [Page title]
 *
 * @param title  the page title
 */
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

/**
 * A PageReadyTest that the current page has an element matching the given
 * locator.
 *
 * @param by  the locating mechanism to use
 */
case class ElementPageReadyTest(by: By) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.visibilityOfElementLocated(by)
  }

}

/**
 * A combined PageReadyTest that applies AND logic to a list of individual
 * tests.
 *
 * The page is only ready if all tests pass.
 *
 * @param tests  the tests to AND together
 */
case class AllOfPageReadyTest(tests: Seq[PageReadyTest]) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.and(tests.map(test => test.expectedCondition): _*)
  }

}

/**
 * A combined PageReadyTest that applies OR logic to a list of individual tests.
 *
 * The page is ready if any test passes.
 *
 * @param tests  the tests to OR together
 */
case class AnyOfPageReadyTest(tests: Seq[PageReadyTest]) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.or(tests.map(_.expectedCondition): _*)
  }

}
