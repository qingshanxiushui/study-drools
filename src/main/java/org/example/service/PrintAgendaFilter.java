package org.example.service;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
//过滤,只有返会true的规则才会被执行
public class PrintAgendaFilter implements AgendaFilter {
    @Override
    public boolean accept(Match match) {
        System.out.println("规则名："+match.getRule().getName());
        return true;
    }
}
