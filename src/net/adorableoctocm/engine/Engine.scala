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

import scala.concurrent.duration.DurationInt

import InputEvent._
import net.adorableoctocm.State
import rx.lang.scala.Observable

/**
 * The game engine.
 */
class Engine(input: Observable[InputEvents], renderer: (State => Unit)) {
  import Engine._

  input.compose(sampleOnEvery[InputEvents](Observable.interval(Period))(Set())).scan(State())(tick).subscribe(renderer)

  private def tick(prev: State, input: InputEvents): State = {
    val jump = (s: State) => {
      // TODO: To be implemented
      if (input(Up)) State(s)(vely = 5) else s
    }
    val walk = (s: State) => {
      if (input(Left) ^ input(Right)) {
        if (input(Left)) State(s)(velx = -2, facing = false) else State(s)(velx = 2, facing = true)
      } else State(s)(velx = 0)
    }
    val collide = (s: State) => {
      // TODO: To be implemented
      State(s)(
        velx = if (s.posx + s.velx <= 0) 0 - s.posx else s.velx,
        vely = if (s.posy + s.vely <= 0) 0 - s.posy else s.vely
      )
    }
    val physics = (s: State) => {
      State(s)(posx = s.posx + s.velx, posy = s.posy + s.vely, vely = s.vely - 1)
    }
    jump andThen walk andThen collide andThen physics apply prev
  }
}

object Engine {

  val Period = 50 millis

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
