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

/**
 * Stores state information
 */
class State(
    val posx: Int,
    val posy: Int,
    val velx: Int,
    val vely: Int,
    val facing: Boolean) {
}

object State {
  def apply(): State =
    new State(0, 0, 0, 0, false)
  def apply(s: State)(posx: Int = s.posx, posy: Int = s.posy, velx: Int = s.velx, vely: Int = s.vely, facing: Boolean = s.facing): State =
    new State(posx, posy, velx, vely, facing)
}
