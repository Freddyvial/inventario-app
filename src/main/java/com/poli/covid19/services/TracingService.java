package com.poli.covid19.services;


import com.poli.covid19.domain.DetailTracing;
import com.poli.covid19.domain.Tracing;

import java.util.List;

public interface TracingService {
    List<DetailTracing> consultDetailTracing(String id);
    List<Tracing> consultTracing(String id);
    Tracing createTracing(Tracing tracing);
    DetailTracing createDetailTracing(DetailTracing detailTracing);
}
