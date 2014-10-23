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

import scala.swing.{ Action, Button, GridBagPanel, Swing }

/**
 * The menu that is shown upon launching the game.
 * It has five options: Start, Settings, Help, About, and Quit.
 * The behavior upon selecting an option is provided by the caller.
 */
class StartMenu(onStart: => Unit, onSettings: => Unit, onHelp: => Unit, onAbout: => Unit, onQuit: => Unit) extends GridBagPanel {
  // TODO: Prettification
  // TODO: I18N and L10N

  // Buttons
  add(new Button(Action("Start")(onStart)), (0, 1))
  add(new Button(Action("Settings")(onSettings)), (0, 2))
  add(new Button(Action("Help")(onHelp)), (0, 3))
  add(new Button(Action("About")(onAbout)), (0, 4))
  add(new Button(Action("Quit")(onQuit)), (0, 5))

  // Spacing
  val c: Constraints = new Constraints
  add(Swing.Glue, { c.grid = (0, 0); c.weighty = .3; c })
  add(Swing.Glue, { c.grid = (0, 6); c.weighty = .1; c })
}
