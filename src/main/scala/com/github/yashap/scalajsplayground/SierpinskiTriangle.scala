package com.github.yashap.scalajsplayground

import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.raw.UIEvent

import scala.util.Random

object SierpinskiTriangle {

  /**
   * Render an animated Sierpinski Triangle within a dom node
   *
   * @param container The dom node to render the Sierpinski Triangle animation within
   * @param width The width of the Sierpinski Triangle animation
   * @param height The height of the Sierpinski Triangle animation
   * @param backgroundColour The background colour of the animation
   */
  def render(
    container: dom.raw.Node,
    width: Int = 255,
    height: Int = 255,
    backgroundColour: String = "black"
  ): Unit = {
    val canvas = Canvas(width = width, height = height)
    renderInCanvas(canvas, backgroundColour)
    container.appendChild(canvas)
  }

  /**
   * Render a full screen animated Sierpinski Triangle
   * @param container The dom node to render the Sierpinski Triangle animation within
   * @param backgroundColour The background colour of the animation
   */
  def fullScreen(container: dom.raw.Node, backgroundColour: String = "black"): Unit = {
    def appendCanvas: html.Canvas = {
      val dimensions = WindowDimensions.current
      val canvas = Canvas(width = dimensions.width, height = dimensions.height)
      renderInCanvas(canvas, backgroundColour)
      container.appendChild(canvas)
      canvas
    }
    var currentNode = appendCanvas

    dom.window.onresize = { _: UIEvent =>
      container.removeChild(currentNode)
      currentNode = appendCanvas
    }
  }

  /**
   * Render an animated Sierpinski Triangle within a canvas
   *
   * @param canvas The canvas to render within
   */
  private def renderInCanvas(canvas: html.Canvas, backgroundColour: String): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    var count = 0

    val topLeft = Point(0, canvas.height)
    val topRight = Point(canvas.width, canvas.height)
    val bottomMiddle = Point(canvas.width / 2, 0)

    // This forms an inverted triangle
    val corners = Seq(topLeft, topRight, bottomMiddle)
    var p = Point(0, 0) // bottom left

    def clear(): Unit = {
      ctx.fillStyle = backgroundColour
      ctx.fillRect(0, 0, canvas.width, canvas.height)
    }

    def run(): Unit = 0 until 10 foreach { _ =>
      // Every 5000th dot drawn, clear the triangle
      if (count % 5000 == 0) clear()
      count += 1

      // Pick a random corner
      p = (p + corners(Random.nextInt(3))) / 2

      val height: Double = (canvas.height * 2).toDouble / (canvas.height + p.y)
      val r: Int = (p.x * height).toInt
      val g: Int = ((canvas.height - p.x) * height).toInt
      val b: Int = p.y
      ctx.fillStyle = s"rgb($g, $r, $b)"
      ctx.fillRect(p.x, p.y, 1, 1)
    }

    dom.window.setInterval(() => run(), 50)
  }
}
