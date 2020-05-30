package com.github.yashap.scalajsplayground

import org.scalajs.dom

import scala.util.Random

object SierpinskiTriangle {

  /**
   * Render an animated Sierpinski Triangle within a canvas
   *
   * @param canvas The canvas to render the Sierpinski Triangle animation within
   * @param backgroundColour The background colour of the animation
   */
  def render(
    canvas: Canvas,
    backgroundColour: String = "black"
  ): Unit = {
    val renderer = canvas.context2D
    var count = 0

    val topLeft = Point(0, canvas.height)
    val topRight = Point(canvas.width, canvas.height)
    val bottomMiddle = Point(canvas.width / 2, 0)

    // This forms an inverted triangle
    val corners = Seq(topLeft, topRight, bottomMiddle)
    var p = Point(0, 0) // bottom left

    def clear(): Unit = {
      renderer.fillStyle = backgroundColour
      renderer.fillRect(0, 0, canvas.width, canvas.height)
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
      renderer.fillStyle = s"rgb($g, $r, $b)"
      renderer.fillRect(p.x, p.y, 1, 1)
    }

    dom.window.setInterval(() => run(), 50)
  }
}
