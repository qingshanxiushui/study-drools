/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.example.drools;

import org.drools.core.io.impl.ClassPathResource;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.utils.KieHelper;

public class BankingExample3 {

    private static final String RULES_STUDENT_PATH = "rules/Example3.drl";

    public static void main(String[] args) {
        Number[] numbers = new Number[] {wrap(3), wrap(1), wrap(4), wrap(1), wrap(5)};

        KieHelper helper = new KieHelper();
        helper.addResource(new ClassPathResource(RULES_STUDENT_PATH));
        KieBase kieBase = helper.build();
        KieSession ksession = kieBase.newKieSession();
        for ( int i = 0; i < numbers.length; i++ ) {
            Object fact = numbers[i];
            System.out.println( "Inserting fact: " + fact );
            ksession.insert( fact );
        }
        ksession.fireAllRules();
        ksession.dispose(); // Stateful rule session must always be disposed when finished
    }
    
    private static Integer wrap(int i) {
        return Integer.valueOf(i);
    }
}
