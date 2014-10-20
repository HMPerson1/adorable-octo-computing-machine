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
package net.adorableoctocm.ui

import scala.swing.{ Component, Swing }

/**
 * The menu that is shown upon launching the game.
 * It has five options: Start, Settings, Help, About, and Quit.
 * The behavior upon selecting an option is provided by the caller.
 */
class StartMenu(onStart: => Unit, onSettings: => Unit, onHelp: => Unit, onAbout: => Unit, onQuit: => Unit) extends Component {
  // TODO: To be implemented
  Swing.onEDT(onStart)
}