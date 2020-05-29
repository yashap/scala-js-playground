package com.github.yashap.scalajsplayground

import org.scalajs.dom.document

object Application {

  def main(args: Array[String]): Unit = {
    val rootContainer = document.createElement("div")
    SierpinskiTriangle.fullScreen(rootContainer)
    document.body.appendChild(rootContainer)
  }
}
