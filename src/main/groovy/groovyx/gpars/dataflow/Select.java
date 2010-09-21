// GPars - Groovy Parallel Systems
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

package groovyx.gpars.dataflow;

import groovyx.gpars.group.PGroup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vaclav Pech
 *         Date: 21st Sep 2010
 */
@SuppressWarnings({"RawUseOfParameterizedType"})
public final class Select extends AbstractSelect {
    private final DataFlowStream<Object> outputChannel;

    Select(final PGroup parallelGroup, final DataFlowChannel... channels) {
        outputChannel = new DataFlowStream<Object>();
        //todo javadoc
        //todo demo on prioritizing real and speculative input, user guide
        final Map<String, List<? extends DataFlowChannel>> params = new HashMap<String, List<? extends DataFlowChannel>>(2);
        params.put("inputs", Arrays.asList(channels));
        params.put("outputs", Arrays.asList(outputChannel));
        selector = parallelGroup.selector(params);
    }

    @Override
    Object doSelect() throws InterruptedException {
        return outputChannel.getVal();
    }

    @Override
    public DataFlowChannel<?> getOutputChannel() {
        return outputChannel;
    }
}