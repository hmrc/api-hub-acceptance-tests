package uk.gov.hmrc.test.ui.pages2

case class WrongPageException(message: String) extends Exception(message)

object WrongPageException extends Robot {

  def expecting(expectedPage: String): WrongPageException = {
    WrongPageException(s"Expected to be on page $expectedPage but currently on page $getCurrentUrl")
  }

}
