package org.example.service;
import org.drools.core.io.impl.ClassPathResource;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.kie.internal.utils.KieHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class RuleService {
    private static final String RULES_PATH = "rules/helloworld.drl";
    private static final String RULES_TEMPLATE_PATH = "rules/test.drt";
    public void rule(){
        KieHelper  helper = new KieHelper();
        helper.addResource(new ClassPathResource(RULES_PATH));
        KieBase kieBase = helper.build();
        KieSession kieSession = kieBase.newKieSession();
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("server_rule执行成功");
    }

    public void rule_template() throws IOException {
        ObjectDataCompiler compiler = new ObjectDataCompiler();
        Collection<Map<String, String>> instanceCollection = new ArrayList<>();
        Map<String, String> instanceMap = new HashMap<>();
        instanceMap.put("condition","String(this == \"规则条件\")");
        instanceMap.put("execution","\"规则动作\"");
        instanceCollection.add(instanceMap);
        InputStream templateInputStream = ResourceFactory.newClassPathResource(RULES_TEMPLATE_PATH, this.getClass()).getInputStream();
        String drl = compiler.compile(instanceCollection,templateInputStream);
        System.out.println("-----模板DRT渲染后的DRL-----");
        System.out.println(drl);
        System.out.println("-----模板DRT渲染后的DRL-----");
        KieHelper helper = new KieHelper();
        helper.addContent(drl, ResourceType.DRL);
        KieSession kieSession = helper.build().newKieSession();
        kieSession.insert(new String("规则条件"));
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
