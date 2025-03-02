package com.APM_web_service.APM_web.repository;

import com.APM_web_service.APM_web.domain.ApmAir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirJpaRepository extends JpaRepository<ApmAir, Long>, AirRepository {
}
