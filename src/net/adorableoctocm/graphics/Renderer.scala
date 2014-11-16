/*
 * Copyright (C) 2014 HMPerson1 <hmperson1@gmail.com> and nathanfei123
 *
 * This file is part of AOCM.
 *
 * AOCM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.adorableoctocm.graphics

import java.awt.Color
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage

import scala.swing.{ Component, Graphics2D }

import net.adorableoctocm.State

/**
 * Renders the game onto the screen.
 */
class Renderer extends Component {
  override def paint(g: Graphics2D): Unit = {
    repaint()

    g.drawImage(frame, 0, 0, null)

    g.setFont(font.deriveFont(20.0f))
    g.drawString("NOT YET IMPLEMENTED", (size.width - 234) / 2.0f, 30.107422f)
  }

  private lazy val frame = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB)
  def onUpdate(s: State): Unit = {
    // TODO: To be implemented
    val g = frame.createGraphics()
    g.setBackground(Color.GRAY)
    g.clearRect(0, 0, frame.getWidth, frame.getHeight)

    g.setPaint(Color.RED)
    g.setFont(font.deriveFont(15.0f))
    g.drawString(s"(${s.posx}, ${s.posy})", 20, 20)
    g.drawString(s"(${s.velx}, ${s.vely})", 20, 40)

    val af = AffineTransform.getScaleInstance(1, -1)
    af.translate(0, -frame.getHeight + 1)
    g.transform(af)

    g.setPaint(Color.WHITE)
    g.fillOval(s.posx, s.posy, 16, 32)

    s.blocks.zipWithIndex.foreach {
      case (seq, x) => seq.zipWithIndex.foreach {
        case (exists, y) => {
          if (exists) g.drawRect(x * 16, y * 16, 16, 16)
        }
      }
    }
  }
}
