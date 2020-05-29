package com.github.yashap.scalajsplayground

import org.scalajs.dom
import org.scalajs.dom.html

object Canvas {

  def apply(width: Int, height: Int): html.Canvas = {
    val canvas = dom.document.createElement("canvas").asInstanceOf[html.Canvas]
    canvas.width = width
    canvas.height = height
    canvas.style.display = "block"
    canvas
  }
}
