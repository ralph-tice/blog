/*
 * Copyright 2017 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.heikoseeberger.blog
package actormodel
package inakka

import akka.actor.Actor

object SeqNoGenerator {

  final case object GetNext
  final case class SeqNo(seqNo: Long)
}

final class SeqNoGenerator(seqNo: Long = 0) extends Actor {
  import SeqNoGenerator._

  override def receive = seqNoGenerator(seqNo)

  private def seqNoGenerator(seqNo: Long): Receive = {
    case GetNext =>
      sender() ! SeqNo(seqNo)
      context.become(seqNoGenerator(seqNo + 1))
  }
}
