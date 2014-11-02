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

import InputEvent.InputEvent
import rx.lang.scala.Observable

/**
 * The game engine.
 */
class Engine(input: Observable[Set[InputEvent]]) {
  import Engine._

  val tick = Observable.interval(Period)

  input.compose(sampleOnEvery(tick)(Set())).foldLeft(null)((prev, input) => {
    // TODO: To be implemented
    println(input)
    null
  })
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
