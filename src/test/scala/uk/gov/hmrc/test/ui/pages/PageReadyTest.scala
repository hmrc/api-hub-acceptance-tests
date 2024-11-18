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
 * A fluent interface for picking the PageReadyTest to use.
 *
 * All page objects should pick their test from here.
 */
object PageReadyTests {

  /**
   * A non-Integration Hub page is any page without the standard Integration Hub menu at the top.
   *
   * Examples are the Stride and LDAP sign-in stubs.
   */
  object nonApiHubPage {
    def url(url: String): PageReadyTest = {
      UrlPageReadyTest(url)
    }

    def urlContaining(url: String): PageReadyTest = {
      UrlContainingPageReadyTest(url)
    }

    def title(title: String): PageReadyTest = {
      TitlePageReadyTest(title)
    }

    def titleContaining(title: String): PageReadyTest = {
      TitleContainingPageReadyTest(title)
    }
  }

  /**
   * An Integration Hub page with the standard Integration Hub menu at the top
   *
   * An Integration Hub page title follows the format:
   *    [Page title] - The Integration Hub - GOV.UK
   *
   */
  object apiHubPage {
    def url(url: String): PageReadyTest = {
      UrlPageReadyTest(url)
    }

    def urlContaining(url: String): PageReadyTest = {
      UrlContainingPageReadyTest(url)
    }

    def title(title: String): PageReadyTest = {
      TitlePageReadyTest(s"$title - The Integration Hub - GOV.UK")
    }
  }

  /**
   * An extension to Integration Hub page for question pages that recognises that a page
   * failing validation will have a different title.
   *
   * A question page failing validation will have a title in this format:
   *    Error: [Page title]
   *
   */
  object questionPage {
    def url(url: String): PageReadyTest = {
      apiHubPage.url(url)
    }

    def urlContaining(url: String): PageReadyTest = {
      apiHubPage.urlContaining(url)
    }

    def title(title: String): PageReadyTest = {
      AnyOfPageReadyTest(
        Seq(
          apiHubPage.title(title),
          apiHubPage.title(s"Error: $title")
        )
      )
    }
  }

  /**
   * An extension to Integration Hub Question page that adds in awareness of Mode for
   * questions that can be revisited from a Check Your Answers page.
   *
   * Mode can alter the URL of the page:
   *    NormalMode: /service-name/question
   *    CheckMode:  /service-name/change-question
   */
  object journeyQuestionPage {
    def url(url: String, mode: Mode): PageReadyTest = {
      questionPage.url(urlForMode(url, mode))
    }

    def urlContaining(url: String, mode: Mode): PageReadyTest = {
      questionPage.urlContaining(urlForMode(url, mode))
    }

    def title(title: String): PageReadyTest = {
      questionPage.title(title)
    }

    //noinspection RegExpRedundantEscape
    private val pathRegex: Regex = """^(.*\/)(.*)$""".r

    private def urlForMode(url: String, mode: Mode): String = {
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
   * Page ready tests for individual elements on a page.
   */
  object element {
    def locator(by: By): PageReadyTest = {
      ElementPageReadyTest(by)
    }
  }

  /**
   * Wraps a series of page ready tests inside AND logic.
   *
   * The page is only ready if all tests pass.
   *
   * @param tests  the tests to AND together
   * @return  a PageReadyTest
   */
  def allOf(tests: PageReadyTest*): PageReadyTest = {
    AllOfPageReadyTest(tests)
  }

  /**
   * Wraps a series of page ready tests inside OR logic.
   *
   * The page is ready if any test passes.
   *
   * @param tests  the tests to OR together
   * @return  a PageReadyTest
   */
  def anyOf(tests: PageReadyTest*): PageReadyTest = {
    AnyOfPageReadyTest(tests)
  }

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
 * A PageReadyTest that tests that the current page's title contains a given
 * value.
 *
 * @param title  the value to match
 */
case class TitleContainingPageReadyTest(title: String) extends PageReadyTest {

  override def expectedCondition: ExpectedCondition[_] = {
    ExpectedConditions.titleContains(title)
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
