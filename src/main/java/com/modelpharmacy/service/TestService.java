package com.modelpharmacy.service;

import com.modelpharmacy.entity.Test;
import com.modelpharmacy.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public void addTest(Test test) {
        testRepository.save(test);
    }
}
