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

/**
 * The base page object. Provides common functionality for all page objects
 * and forms a pattern to follow.
 *
 * Calls the page ready check on instantiation to confirm the current page
 * matches any expectations and is ready for interaction.
 *
 * Page objects should expose two basic types of method:
 *    1) Getters to access state
 *    2) Actions to move from one page object to another
 *
 * All actions should return a reference to the next page object. This means we
 * should think of actions as mini scenarios. A submit button for example may
 * move to the next page or present the current page with validation failures.
 * Rather than a simple "submit(): NextPage" action we might prefer to
 * implement the separate mini scenarios "submitValidData(): NextPage" and
 * "submitInvalidData(): ThisPage".
 *
 * @param pageReadyTest  a test of page correctness and readiness
 * @tparam T  the precise type of a sub-class: used for self-referencing to
 *            create a fluent API
 */
abstract class BasePage[T](pageReadyTest: PageReadyTest) extends Robot with LazyLogging {
  self: T =>

  waitForPageReady(pageReadyTest)

  logger.info(s"Current page title: $getTitle")
  logger.info(s"Current page URL: $getCurrentUrl")

  /**
   * Apply the given procedure f to the page object. A side-effecting method.
   *
   * The side-effects would typically be an assertion within a step def.
   *
   * Returns a reference to 'this' to allow methods to be chained together in a
   * fluent style.
   *
   * @param f  the procedure to apply
   * @return  a self-reference supporting fluent APIs
   */
  def foreach[U](f: T => U): this.type = {
    f.apply(self)
    self
  }

}
