package com.poli.covid19.services.impl;

import com.poli.covid19.domain.Tracing;
import com.poli.covid19.repositories.TracingRepository;
import com.poli.covid19.services.TracingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TracingServiceImpl implements TracingService {

    @Autowired
    private TracingRepository tracingRepository;

    @Override
    public List<Tracing> consultTracing() {

        return tracingRepository.consultTracing();
    }

    @Override
    public Tracing createTracing(Tracing tracing) {
        return tracingRepository.createTracing(tracing);
    }

}
