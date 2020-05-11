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
}
