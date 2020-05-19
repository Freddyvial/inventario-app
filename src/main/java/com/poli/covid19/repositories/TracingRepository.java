package com.poli.covid19.repositories;


import com.poli.covid19.domain.Tracing;

import java.util.List;

public interface TracingRepository {

    List<Tracing> consultTracing();
    Tracing createTracing(Tracing tracing);
}
