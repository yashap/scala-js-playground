package com.github.yashap.scalajsplayground

import org.scalajs.dom
import org.scalajs.dom.html
import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

class BaseSpec extends AnyFlatSpecLike with Matchers with BeforeAndAfterEach {

  protected def selectCanvas: Canvas = {
    val canvas = dom.document.querySelector("canvas")
    canvas shouldBe a[html.Canvas]
    Canvas(canvas.asInstanceOf[html.Canvas])
  }
}
