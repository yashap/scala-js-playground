package com.github.yashap.scalajsplayground

import org.scalajs.dom
import org.scalajs.dom.ext._
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class ApplicationTest extends FlatSpec with Matchers {

  // Initialize app
  Application.setupUI()

  "setupUI" should "contain 'Hello World' text in its body" in {
    val paragraphs = dom.document.querySelectorAll("p")
    paragraphs.count(_.textContent == "Hello World") shouldBe 1
  }

  "clicking the button" should "append a 'You clicked the button!' node" in {
    def messageCount: Int =
      dom.document.querySelectorAll("p").count(_.textContent == "You clicked the button!")

    val button = dom.document.querySelector("button").asInstanceOf[dom.html.Button]
    Option(button) shouldNot be(empty)
    button.textContent shouldBe "Click me!"
    messageCount shouldBe 0

    (1 to 5).foreach { expectedCount =>
      button.click()
      messageCount shouldBe expectedCount
    }
  }
}
