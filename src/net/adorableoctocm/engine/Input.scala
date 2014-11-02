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
package net.adorableoctocm.engine

import scala.collection.mutable
import scala.swing.Component
import scala.swing.event.{ Key, KeyPressed, KeyReleased }

import rx.lang.scala.Observable

/**
 * Takes a {@link Component} and generates an {@link Observable} from the component's events.
 */
object Input {
  import InputEvent._

  def apply(comp: Component): Observable[Set[InputEvent]] = {
    Observable(subscriber => {
      val keys = mutable.Set[InputEvent]()
      comp.keys.reactions += {
        case e: KeyPressed => {
          e.key match {
            case Key.E => keys += Up
            case Key.D => keys += Down
            case Key.S => keys += Right
            case Key.F => keys += Left
          }
          subscriber.onNext(keys.toSet)
        }
        case e: KeyReleased => {
          e.key match {
            case Key.E => keys -= Up
            case Key.D => keys -= Down
            case Key.S => keys -= Right
            case Key.F => keys -= Left
          }
          subscriber.onNext(keys.toSet)
        }
      }
    })
  }
}

/**
 * Represents the possible inputs from the user.
 */
object InputEvent extends Enumeration {
  type InputEvent = Value
  val Up, Down, Left, Right = Value
}
