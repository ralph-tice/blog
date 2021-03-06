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
package inakkatyped

import akka.typed.{ ActorRef, Behavior }
import akka.typed.scaladsl.Actor

object SeqNoGenerator {

  final case class GetNext(replyTo: ActorRef[SeqNo])
  final case class SeqNo(seqNo: Long)

  def apply(seqNo: Long = 0): Behavior[GetNext] =
    Actor.immutable {
      case (_, GetNext(replyTo)) =>
        replyTo ! SeqNo(seqNo)
        SeqNoGenerator(seqNo + 1)
    }
}
