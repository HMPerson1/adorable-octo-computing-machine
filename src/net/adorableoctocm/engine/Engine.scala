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

import scala.IndexedSeq
import scala.concurrent.duration.DurationInt

import InputEvent._
import net.adorableoctocm.State
import rx.lang.scala.Observable

/**
 * The game engine.
 */
class Engine(input: Observable[InputEvents], renderer: (State => Unit)) {
  import Engine._

  val tmpLevel = IndexedSeq[Int](0xffffffff, 0x80100001, 0x80100003, 0x80100007, 0x8090000f, 0x80900007, 0x80000003, 0x80000001, 0xffffffff).map(int => (0 to 31).map(idx => ((int >> idx) & 1) == 1))
  input.compose(sampleOnEvery[InputEvents](Observable.interval(Period))(Set())).scan(State(State())(posx = 16, posy = 16, blocks = tmpLevel))(tick).subscribe(renderer)

  private def tick(prev: State, input: InputEvents): State = {
    val jump = (s: State) => {
      if (input(Up)) {
        val x = s.posx
        val y = s.posy

        val columns = s.blocks.slice(x / BlockSize, (x - 1) / BlockSize + 2)
        if (y % 16 == 0 && columns.map(_(y / BlockSize - 1)).contains(true)) {
          State(s)(vely = JumpVel)
        } else s
      } else s
    }
    val walk = (s: State) => {
      if (input(Left) ^ input(Right)) {
        if (input(Left)) State(s)(velx = -WalkVel, facing = false) else State(s)(velx = WalkVel, facing = true)
      } else State(s)(velx = 0)
    }
    val collide = (s: State) => {
      val bx = s.posx
      val by = s.posy
      val tx = bx + BlockSize - 1
      val ty = by + BlockSize * 2 - 1
      val bxi = bx / BlockSize
      val byi = by / BlockSize
      val txi = tx / BlockSize
      val tyi = ty / BlockSize

      var vx = if (bx + s.velx <= 0) 0 - bx else s.velx
      var vy = if (by + s.vely <= 0) 0 - by else s.vely

      val columns = s.blocks.slice(bxi, txi + 1)
      if (vy < 0) {
        val below = columns.map(_.lastIndexOf(true, byi - 1)).max
        val floor = (below + 1) * 16
        vy = if (by + s.vely <= floor) floor - by else vy
      }
      if (vy > 0) {
        val above = columns.map(_.indexOf(true, tyi + 1)).min
        val ceil = above * 16
        vy = if (ty + s.vely >= ceil) ceil - ty - 1 else vy
      }

      val rows = s.blocks.transpose.slice((by + vy) / BlockSize, (ty + vy) / BlockSize + 1)
      if (vx < 0) {
        val left = rows.map(_.lastIndexOf(true, bxi - 1)).max
        val lwall = (left + 1) * 16
        vx = if (bx + s.velx <= lwall) lwall - bx else vx
      }
      if (vx > 0) {
        val right = rows.map(_.indexOf(true, txi + 1)).min
        val rwall = right * 16
        vx = if (tx + s.velx >= rwall) rwall - tx - 1 else vx
      }

      State(s)(
        velx = vx,
        vely = vy
      )
    }
    val physics = (s: State) => {
      State(s)(posx = s.posx + s.velx, posy = s.posy + s.vely, vely = s.vely - 1)
    }
    jump andThen walk andThen collide andThen physics apply prev
  }
}

object Engine {

  val Period = 20 millis
  val JumpVel = 6
  val WalkVel = 2
  val BlockSize = 16

  def sampleOnEvery[T](sampler: Observable[_])(default: T)(o: Observable[T]): Observable[T] = {
    val lock = new AnyRef
    var value = default

    o.subscribe { v => lock.synchronized { value = v } }

    Observable[T](subscriber => {
      sampler.subscribe { _ =>
        if (!subscriber.isUnsubscribed) {
          lock.synchronized {
            subscriber.onNext(value)
          }
        }
      }
    })
  }
}
