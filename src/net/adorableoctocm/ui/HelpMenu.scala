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

import scala.swing.{ Action, Button, GridBagPanel, Label, TextArea }

/**
 * A menu that displays information on how to play this game.
 */
class HelpMenu(onBack: => Unit) extends GridBagPanel {
  // TODO: To be implemented
  // TODO: Prettification
  // TODO: I18N and L10N

  add(new Label("Help"), (0, 0))
  add(new TextArea("Lots of information."), (0, 1))
  add(new Button(Action("Back")(onBack)), (0, 2))
}
