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
package net.adorableoctocm

import scala.swing.{ Container, MainFrame }
import scala.swing.Swing.pair2Dimension
import scala.swing.event.ContainerEvent

import net.adorableoctocm.engine._
import net.adorableoctocm.graphics.Renderer
import net.adorableoctocm.ui._

/**
 * A platformer about an adorable octo computing machine.
 */
object AdorableOctoComputingMachine {

  lazy val frame = new MainFrame { override def closeOperation = quit }
  lazy val startMenu = new StartMenu(toLevelSelectMenu, toSettingsMenu, toHelpMenu, toAboutMenu, quit)
  lazy val levelSelectMenu = new LevelSelectMenu(startLevel, toStartMenu)
  lazy val settingsMenu = new SettingsMenu(toStartMenu)
  lazy val helpMenu = new HelpMenu(toStartMenu)
  lazy val aboutMenu = new AboutMenu(toStartMenu)

  def toStartMenu(): Unit = frame.contents = startMenu
  def toLevelSelectMenu(): Unit = frame.contents = levelSelectMenu
  def toSettingsMenu(): Unit = frame.contents = settingsMenu
  def toHelpMenu(): Unit = frame.contents = helpMenu
  def toAboutMenu(): Unit = frame.contents = aboutMenu

  def main(args: Array[String]): Unit = {
    val contentPane = new Container.Wrapper { def peer = frame.peer.getContentPane.asInstanceOf[javax.swing.JComponent] }
    contentPane.reactions += { case e: ContainerEvent => e.source.repaint }

    frame.preferredSize = (1280, 720)
    toStartMenu()
    frame.centerOnScreen()
    frame.title = "Adorable Octo Computing Machine"
    frame.open()
  }

  def startLevel(major: Int, minor: Int): Unit = {
    val renderer = new Renderer
    frame.contents = renderer
    renderer.requestFocusInWindow()
    new Engine(Input(renderer), renderer.onUpdate)
    // TODO: To be implemented
  }

  def quit(): Unit = {
    Settings.save()
    sys.exit()
  }
}
