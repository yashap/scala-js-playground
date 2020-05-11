package com.github.yashap.scalajsplayground

import org.scalajs.dom
import org.scalajs.dom.document

object Application {

  def appendParagraph(targetNode: dom.Node, text: String): Unit = {
    val paragraphNode = document.createElement("p")
    paragraphNode.textContent = text
    targetNode.appendChild(paragraphNode)
  }

  def addClickedMessage(): Unit = {
    appendParagraph(document.body, "You clicked the button!")
  }

  def setupUI(): Unit = {
    val button = document.createElement("button")
    button.textContent = "Click me!"
    button.addEventListener("click", { (_: dom.MouseEvent) =>
      addClickedMessage()
    })

    appendParagraph(document.body, "Hello World")
    document.body.appendChild(button)
  }

  def main(args: Array[String]): Unit = {
    setupUI()
  }
}
