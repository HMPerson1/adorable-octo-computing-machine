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

import scala.swing.{ Component, Graphics2D }

/**
 * Renders the game onto the screen.
 */
class Renderer extends Component {
  preferredSize = new scala.swing.Dimension(254, 45)
  override def paint(g: Graphics2D): Unit = {
    // TODO: To be implemented
    g.setFont(font.deriveFont(20.0f))
    g.drawString("NOT YET IMPLEMENTED", 10.0f, 30.107422f)
  }
}