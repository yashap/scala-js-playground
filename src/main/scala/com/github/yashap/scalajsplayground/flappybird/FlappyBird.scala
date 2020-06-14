package com.github.yashap.scalajsplayground.flappybird

import com.github.yashap.scalajsplayground.Canvas
import org.scalajs.dom

import scala.collection.mutable.{Queue => MutableQueue}
import scala.util.Random

object FlappyBird {
  private val obstacleGap = 200 // Gap between the approaching obstacles
  private val holeSize = 50     // Size of the hole in each obstacle you must go through

  def render(canvas: Canvas): Unit = {
    val renderer = initializeRenderer(canvas)
    val player = new Player(canvas)

    // What frame this is; used to keep track
    // of where the obstacles should be positioned
    var frame = -50

    // List of each obstacle, storing only the Y position of the hole.
    // The X position of the obstacle's hole is calculated by its position in the
    // queue and in the current frame.
    // An obstacle is basically a wall with a hole in it, that the player must fly
    // through
    val obstacles = MutableQueue.empty[Int]

    def runLive(): Unit = {
      frame += 2

      // Create new obstacles, or kill old ones as necessary
      if (frame >= 0 && frame % obstacleGap == 0) {
        obstacles.enqueue(
          Random.nextInt(canvas.height - 2 * holeSize) + holeSize
        )
      }
      if (obstacles.length > 7) {
        obstacles.dequeue()
        frame -= obstacleGap
      }

      player.applyPhysics()

      // Render obstacles, check for collision
      renderer.fillStyle = "darkblue"
      obstacles.zipWithIndex.foreach { case (holeYPosition: Int, obstacleIndex: Int) =>
        // Where each obstacle appears depends on what frame it is
        // This is what keeps the obstacles moving to the left as time passes
        val obstacleXPosition = obstacleIndex * obstacleGap - frame + canvas.width
        // Lower half of the obstacle, below the hole
        renderer.fillRect(
          x = obstacleXPosition,
          y = 0,
          w = 5,
          h = holeYPosition - holeSize
        )
        // Upper half of the obstacle, above the hole
        renderer.fillRect(
          x = obstacleXPosition,
          y = holeYPosition + holeSize,
          w = 5,
          h = canvas.height - holeYPosition - holeSize
        )

        // If the player has hit an obstacle, kill them
        val isPlayerXPositionAtObstacle = math.abs(obstacleXPosition - player.Position.X) < 5
        val isPlayerYPositionOutsideHole = math.abs(holeYPosition - player.Position.Y) > holeSize
        if (isPlayerXPositionAtObstacle && isPlayerYPositionOutsideHole) {
          player.kill()
        }
      }

      // Render player
      player.render()

      // Check for out-of-bounds player
      if (player.Position.Y < 0 || player.Position.Y > canvas.height) {
        player.kill()
      }
    }

    def runDead(): Unit = {
      frame = -50
      obstacles.clear()
      player.nextState
      renderer.fillStyle = "darkred"
      renderer.fillText(
        text = "Game Over",
        x = canvas.width / 2,
        y = canvas.height / 2
      )
    }

    def run(): Unit = {
      renderer.clearRect(x = 0, y = 0, w = canvas.width, h = canvas.height)
      if (player.getState.isAlive) runLive()
      else runDead()
    }

    dom.window.setInterval(handler = () => run(), timeout = 20)

    canvas.onclick = (_: dom.MouseEvent) => {
      player.flapWings()
    }
  }

  private def initializeRenderer(canvas: Canvas): dom.CanvasRenderingContext2D = {
    val renderer = canvas.context2D
    renderer.font = "50px sans-serif"
    renderer.textAlign = "center"
    renderer.textBaseline = "middle"
    renderer
  }
}
