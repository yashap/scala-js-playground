package com.github.yashap.scalajsplayground

import org.scalajs.dom

object Sketchpad {

  def render(canvas: Canvas, backgroundColour: String = "#f8f8f8"): Unit = {
    val renderer = canvas.context2D

    renderer.fillStyle = backgroundColour
    renderer.fillRect(0, 0, canvas.width, canvas.height)

    renderer.fillStyle = "black"
    var down = false
    var currentX: Double = 0.0
    var currentY: Double = 0.0

    canvas.onmousedown = { event: dom.MouseEvent =>
      val rect = canvas.getBoundingClientRect()
      currentX = event.clientX - rect.left
      currentY = event.clientY - rect.top
      down = true
    }

    canvas.onmouseup = (_: dom.MouseEvent) => down = false

    canvas.onmousemove = { event: dom.MouseEvent =>
      if (down) {
        val rect = canvas.getBoundingClientRect()
        val newX = event.clientX - rect.left
        val newY = event.clientY - rect.top

        renderer.beginPath()
        renderer.moveTo(x = currentX, y = currentY)
        renderer.lineCap = "round"
        renderer.lineWidth = 3
        renderer.lineTo(x = newX, y = newY) // TODO: explore renderer.bezierCurveTo for smoother line
        renderer.stroke()

        currentX = newX
        currentY = newY
      }
    }
  }
}
