package com.github.yashap.scalajsplayground

import org.scalajs.dom

import scala.scalajs.js

object Clock {

  def render(canvas: Canvas): Unit = {
    val renderer = initializeRenderer(canvas)

    val doRender: () => Unit = () => {
      val now = new js.Date() // TODO: looking into using java.time instead
      renderer.clearRect(x = 0, y = 0, w = canvas.width, h = canvas.height)
      renderer.fillText(
        text = formatDate(now),
        x = canvas.width / 2,
        y = canvas.height / 2
      )
    }

    doRender()
    dom.window.setInterval(doRender, timeout = 1000)
  }

  private def initializeRenderer(canvas: Canvas): dom.CanvasRenderingContext2D = {
    val renderer = canvas.context2D

    val gradient = renderer.createLinearGradient(
      x0 = canvas.width * 1.0/5,
      y0 = 0,
      x1 = canvas.width * 4.0/5,
      y1 = 0
    )
    gradient.addColorStop(0, "red")
    gradient.addColorStop(0.5, "green")
    gradient.addColorStop(1, "blue")
    renderer.fillStyle = gradient

    renderer.textAlign = "center"
    renderer.textBaseline = "middle"
    renderer.font = s"${canvas.width / 5}px sans-serif"

    renderer
  }

  private def formatDate(date: js.Date): String = {
    def formatSection(x: Double): String = f"${x.toInt}%02d"

    Seq(date.getHours(), date.getMinutes(), date.getSeconds())
      .map(formatSection)
      .mkString(":")
  }
}
