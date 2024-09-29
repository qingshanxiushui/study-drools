package org.example.service;

import org.drools.core.io.impl.ClassPathResource;
import org.drools.template.ObjectDataCompiler;
import org.example.model.Student;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class HelloService {

    @Cacheable("id")
    public String getById(int id) {
        int idAdd = id + 10;
        int idPlus = id * 10;
        String idString = String.valueOf(idAdd + idPlus);
        return idString;
    }

}
