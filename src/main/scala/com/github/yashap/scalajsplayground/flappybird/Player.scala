package com.github.yashap.scalajsplayground.flappybird

import com.github.yashap.scalajsplayground.Canvas

class Player(canvas: Canvas) {
  private val gravity = 0.1
  private val height = 10
  private val width = 10

  object Position {
    val X: Double = canvas.width / 2.0
    var Y: Double = canvas.height / 2.0
  }

  private var velocity: Double = 0.0

  def applyPhysics(): Unit = {
    Position.Y += velocity
    velocity += gravity
  }

  def flapWings(): Unit = {
    velocity -= 5
  }

  private var state: Player.State = Player.State.Alive

  def getState: Player.State = state

  def nextState: Player.State = state match {
    case Player.State.Alive => Player.State.Alive
    case Player.State.Dead(framesUntilRespawn) if framesUntilRespawn > 1 =>
      state = Player.State.Dead(framesUntilRespawn - 1)
      state
    case Player.State.Dead(_) =>
      state = Player.State.Alive
      state
  }

  def kill(framesUntilRespawn: Int = 50): Unit = {
    state = Player.State.Dead(framesUntilRespawn)
    velocity = 0.0
    Position.Y = canvas.height / 2.0
  }

  def render(): Unit = {
    val renderer = canvas.context2D
    renderer.fillStyle = "darkgreen"
    renderer.fillRect(
      x = Position.X - width/2,
      y = Position.Y - height/2,
      w = width,
      h = height
    )
  }
}

object Player {

  sealed abstract class State(val isAlive: Boolean)
  object State {
    object Alive extends State(isAlive = true)
    case class Dead(framesUntilRespawn: Int) extends State(isAlive = false)
  }
}
