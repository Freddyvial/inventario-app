package com.poli.covid19.repositories;


import com.poli.covid19.domain.DetailTracing;
import com.poli.covid19.domain.Tracing;

import java.util.List;

public interface TracingRepository {
    List<DetailTracing> consultDetailTracing(String id);
    List<Tracing> consultTracing(String id);
    Tracing createTracing(Tracing tracing);
    DetailTracing createDetailTracing(DetailTracing detailTracing);
    int idMedicalByTracing();
}
