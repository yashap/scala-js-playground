package com.github.yashap.scalajsplayground

import com.github.yashap.scalajsplayground.flappybird.FlappyBird
import org.scalajs.dom
import org.scalajs.dom.html

object Application {

  def initFullScreenRoot(): html.Element = {
    val div = dom.document.createElement("div").asInstanceOf[html.Div]
    div.style.width = "100%"
    div.style.height = "100%"
    div.style.position = "fixed"
    div.style.top = "0"
    div.style.left = "0"
    dom.document.body.appendChild(div)
    div
  }

  def main(args: Array[String]): Unit = {
    val root = initFullScreenRoot()
    // val canvas = Canvas(width = root.clientWidth, height = root.clientHeight)
    val canvas = Canvas(width = root.clientWidth, height = 400) // for FlappyBox
    root.appendChild(canvas)

    // SierpinskiTriangle.render(canvas)
    // Sketchpad.render(canvas)
    // Clock.render(canvas)
    FlappyBird.render(canvas)
  }
}
