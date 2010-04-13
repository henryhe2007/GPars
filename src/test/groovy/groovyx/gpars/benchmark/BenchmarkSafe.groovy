// GPars (formerly GParallelizer)
//
// Copyright © 2008-10  The original author or authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package groovyx.gpars.benchmark

import groovyx.gpars.actor.Agent
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

final ExecutorService pool = Executors.newFixedThreadPool(10)

final long t1 = System.currentTimeMillis()
final Agent safe = new Agent<Long>(0L)
300.times {
    1000.times {
        pool.submit {safe << {updateValue(it + 1)}}
    }
    safe.await()
}
final long t2 = System.currentTimeMillis()
println "Result: ${safe.val}"
println "Time: ${t2 - t1}"
pool.shutdown()