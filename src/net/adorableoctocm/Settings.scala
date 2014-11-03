/*
 * Copyright (C) 2014 HMPerson1 <hmperson1@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
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
package net.adorableoctocm

import java.util.prefs.Preferences
import scala.swing.event.Key

/**
 * Manages the storage and retrieval of settings.
 */
object Settings {

  val Prefs = Preferences.userNodeForPackage(getClass)
  
  final val KeyKeyUp = "KEY_UP"
  final val KeyKeyDown = "KEY_DOWN"
  final val KeyKeyLeft = "KEY_LEFT"
  final val KeyKeyRight = "KEY_RIGHT"

  lazy val KeyUp = Key(Prefs.getInt(KeyKeyUp, Key.E.id))
  lazy val KeyDown = Key(Prefs.getInt(KeyKeyDown, Key.D.id))
  lazy val KeyLeft = Key(Prefs.getInt(KeyKeyLeft, Key.S.id))
  lazy val KeyRight = Key(Prefs.getInt(KeyKeyRight, Key.F.id))
}
