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

import akka.actor.{ ActorSystem, Props }
import akka.testkit.TestProbe
import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpec }
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

final class SeqNoGeneratorSpec extends WordSpec with Matchers with BeforeAndAfterAll {
  import SeqNoGenerator._

  private implicit val system = ActorSystem()

  "A SeqNoGenerator" should {
    "respond to GetNext with the correct SeqNo" in {
      val sender             = TestProbe()
      implicit val senderRef = sender.ref
      val seqNoGenerator     = system.actorOf(Props(new SeqNoGenerator(42)))
      seqNoGenerator ! GetNext
      sender.expectMsg(SeqNo(42))
      seqNoGenerator ! GetNext
      sender.expectMsg(SeqNo(43))
    }
  }

  override protected def afterAll() = {
    Await.ready(system.terminate(), 42.seconds)
    super.afterAll()
  }
}
