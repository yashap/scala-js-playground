package com.github.yashap.scalajsplayground

import org.scalajs.dom

case class WindowDimensions(width: Int, height: Int)

object WindowDimensions {

  def current: WindowDimensions = {
    val width = Math.max(
      Option(dom.document.documentElement.clientWidth).getOrElse(0),
      Option(dom.window.innerWidth).map(_.toInt).getOrElse(0)
    )
    val height = Math.max(
      Option(dom.document.documentElement.clientHeight).getOrElse(0),
      Option(dom.window.innerHeight).map(_.toInt).getOrElse(0)
    )
    WindowDimensions(width = width, height = height)
  }
}
