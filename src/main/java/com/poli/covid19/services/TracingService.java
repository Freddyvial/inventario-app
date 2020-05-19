package com.poli.covid19.services;


import com.poli.covid19.domain.Tracing;

import java.util.List;

public interface TracingService {

    List<Tracing> consultTracing();
    Tracing createTracing(Tracing tracing);
}
