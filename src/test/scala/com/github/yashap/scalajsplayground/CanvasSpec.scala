package com.github.yashap.scalajsplayground

import org.scalajs.dom

class CanvasSpec extends BaseSpec {

  override protected def afterEach(): Unit = {
    dom.document.body.innerHTML = ""
  }

  "apply" should "create a blank canvas of the specified size" in {
    val width = 500
    val height = 200
    dom.document.body.appendChild(Canvas(width = width, height = height))

    val canvas = selectCanvas
    canvas.width shouldBe width
    canvas.height shouldBe height
  }
}
