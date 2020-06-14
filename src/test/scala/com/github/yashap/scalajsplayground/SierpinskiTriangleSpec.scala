package com.github.yashap.scalajsplayground

import org.scalajs.dom

class SierpinskiTriangleSpec extends BaseSpec {

  override protected def afterEach(): Unit = {
    dom.document.body.innerHTML = ""
  }

  // TODO: get this to work (complains about lack of npm canvas package, but I install it?)
  // https://stackoverflow.com/questions/62109069/how-do-i-test-an-html5-canvas-in-scala-js
//  "render" should "render in the canvas" in {
//    val width = 512
//    val height = 256
//    val expectedCanvas = Canvas(width = width, height = height)
//    dom.document.body.appendChild(expectedCanvas)
//    SierpinskiTriangle.render(expectedCanvas, "red")
//
//    val canvas = selectCanvas
//    canvas.width shouldBe width
//    canvas.height shouldBe height
//  }
}
