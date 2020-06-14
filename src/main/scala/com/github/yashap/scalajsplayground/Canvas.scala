package com.github.yashap.scalajsplayground

import org.scalajs.dom
import org.scalajs.dom.html

case class Canvas(private val underlying: html.Canvas) {

  lazy val context2D: dom.CanvasRenderingContext2D =
    underlying.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
}

object Canvas {

  def apply(width: Int, height: Int): Canvas = {
    val canvas = dom.document.createElement("canvas").asInstanceOf[html.Canvas]
    canvas.width = width
    canvas.height = height
    canvas.style.display = "block"
    Canvas(canvas)
  }

  implicit def asHtmlCanvas(canvas: Canvas): html.Canvas = canvas.underlying
}
