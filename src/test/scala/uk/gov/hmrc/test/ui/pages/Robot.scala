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

import com.typesafe.scalalogging.LazyLogging
import org.openqa.selenium.devtools.v85.indexeddb.model.Key
import org.openqa.selenium.support.ui.{ExpectedCondition, FluentWait}
import org.openqa.selenium.{By, WebDriver, WebElement}
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.test.ui.conf.TestConfiguration

import java.time.Duration
import scala.jdk.CollectionConverters._

/**
 *  An abstraction of WebDriver.
 *
 *  Robot should be the only code that directly uses a WebDriver. It is an
 *  abstraction of best practice. As our understanding of how WebDriver works
 *  evolves we can improve Robot and all pages and steps that use it will
 *  benefit.
 *
 *  Do not use WebDriver directly outside of Robot.
 *
 *  Methods in this trait should try and translate between Java and Scala.
 *
 *  Examples of this are:
 *    - Don't return a nullable String, return Option[String]
 *    - Don't return java.util.List, return Seq
 */
trait Robot extends LazyLogging {

  /**
   * Applies a test to see if the current page is correct and ready for
   * interactions.
   *
   * @param pageReadyTest  a test of page correctness and readiness
   */
  def waitForPageReady(pageReadyTest: PageReadyTest): Unit = {
    fluentWait(pageReadyTest.expectedCondition)
  }

  /**
   * Waits for an expected condition to evaluate.
   *
   * Waits are good in that they promote tolerance between tests and the target.
   * Waits are bad if they are over-used as they can significantly impact the
   * test run time.
   *
   * The strategy here is to wait for page ready only. At that point we assume
   * all elements are available and we do not need to wait for specific elements
   * when subsequently reading from or interacting with a page.
   *
   * @param expectedCondition  the condition to wait for
   */
  private def fluentWait(expectedCondition: ExpectedCondition[_]): Unit = {
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(5))
      .pollingEvery(Duration.ofMillis(100))
      .until(expectedCondition)
  }

  /**
   * Navigates to a relative URL.
   *
   * We don't really want to navigate directly to pages. We prefer to move
   * from page to page by clicking links or performing actions.
   *
   * When we use direct navigation we do not get a reference to the target
   * page. This means we do not confirm we have successfully arrived at our
   * destination. Try and instantiate a page object immediately after
   * navigating to confirm where we are.
   *
   * It is Ok to use this method but it should be a last resort. Examples
   * of sensibly using direct navigation are:
   *   - Initial navigation to a start page
   *   - Accessing a page that is not always linked to
   *
   * Specify a relative URL, ie the bit after /service-name/
   * Pass an empty string to hit an index/landing page
   *
   * @param relativeUrl  the relative URL to navigate to
   */
  def navigateToRelativeUrl(relativeUrl: String): Unit = {
    navigateToFullUrl(buildFullUrl(relativeUrl))
  }

  /**
   * Navigates to a full URL.
   *
   * We don't really want to navigate directly to pages. We prefer to move
   * from page to page by clicking links or performing actions.
   *
   * When we use direct navigation we do not get a reference to the target
   * page. This means we do not confirm we have successfully arrived at our
   * destination. Try and instantiate a page object immediately after
   * navigating to confirm where we are.
   *
   * It is Ok to use this method but it should be a last resort. Examples
   * of sensibly using direct navigation are:
   *   - Initial navigation to a start page
   *   - Accessing a page that is not always linked to
   *
   * Specify a full URL.
   *
   * @param fullUrl  the full URL to navigate to
   */
  def navigateToFullUrl(fullUrl: String): Unit = {
    logger.info(s"Navigating to $fullUrl")

    Driver.instance.navigate().to(fullUrl)
  }

  /**
   * Uses configuration to build a full URL from a relative URL.
   *
   * Specify a relative URL, ie the bit after /service-name/
   * Pass an empty string for an index/landing page
   *
   * @param relativeUrl  the relative URL
   * @return  the full URL
   */
  def buildFullUrl(relativeUrl: String): String = {
    val baseUrl = s"${TestConfiguration.url("api-hub")}"

    if (relativeUrl.nonEmpty) {
      s"$baseUrl/$relativeUrl"
    }
    else {
      baseUrl
    }
  }

  /**
   * If this element is a form entry element, this will reset its value.
   *
   * @param by  the locating mechanism to use
   */
  def clear(by: By): Unit = {
    findElement(by).clear()
  }

  /**
   * Click this element.
   *
   * @param by  the locating mechanism to use
   */
  def click(by: By): Unit = {
    findElement(by).click()
  }

  /**
   * Use this method to simulate typing into an element, which may set its
   * value. This method will clear any existing value by default
   * unless explicitly told not to.
   *
   * @param by  the locating mechanism to use
   * @param value  character sequence to send to the element
   */
  def sendKeys(by: By, value: String, clearFirst: Boolean = true): Unit = {
    if (clearFirst) {
      clear(by)
    }
    findElement(by).sendKeys(value)
  }

  /**
   * Get the title of the current page.
   *
   * @return  The title of the current page, with leading and trailing
   *          whitespace stripped, or None if one is not already set
   */
  def getTitle: Option[String] = {
    Option(Driver.instance.getTitle)
  }

  /**
   * Get a string representing the current URL that the browser is looking at.
   *
   * @return  The URL of the page currently loaded in the browser
   */
  def getCurrentUrl: String = {
    Driver.instance.getCurrentUrl
  }

  /**
   * Get the visible (i.e. not hidden by CSS) text of this element, including
   * sub-elements.
   *
   * @param by  the locating mechanism to use
   * @return  the visible text of this element with leading and trailing
   *          whitespace stripped
   */
  def getText(by: By): String = {
    Option(findElement(by).getText)
      .map(_.trim)
      .getOrElse("")
  }

  /**
   * Get the value of the given attribute of the element. Will return the
   * current value, even if this has been modified after the page has been
   * loaded.
   *
   * @param by  the locating mechanism to use
   * @param attributeName  the name of the attribute
   * @return  the attribute/property's current value with leading and trailing
   *          whitespace stripped or None if the value is not set.
   */
  def getAttribute(by: By, attributeName: String): Option[String] = {
    Option(
      Driver.instance
        .findElement(by)
        .getAttribute(attributeName)
    ).map(_.trim)
  }

  /**
   * Find the first WebElement using the given method.
   *
   * findElement should not be used to look for non-present elements, use
   * findElements(By) and assert zero length response instead or to simply
   * check if an element exists use exists(By).
   *
   * @param by  the locating mechanism to use
   * @return  the first matching element on the current page
   * @throws NoSuchElementException  if no matching elements are found
   */
  def findElement(by: By): WebElement = {
    Driver.instance.findElement(by)
  }

  /**
   * Find all elements within the current page using the given mechanism.
   *
   * @param by  the locating mechanism to use
   * @return  A list of all matching WebElements, or an empty list if nothing
   *          matches
   */
  def findElements(by: By): Seq[WebElement] = {
    Driver.instance.findElements(by).asScala.toSeq
  }

  /**
   * Checks if an element exists on the current page.
   *
   * @param by  the locating mechanism to use
   * @return  true or false depending on whether the element was found
   */
  def exists(by: By): Boolean = {
    findElements(by).nonEmpty
  }

  /**
   * Switches to an indexed window
   *
   * @param i  the zero based index of the window
   */
  def switchToWindow(i: Int): Unit = {
    val handles = Driver.instance.getWindowHandles.asScala
    Driver.instance.switchTo().window(handles.toSeq(i))
  }

}
