package com.botaro.portifolio.repository;

import com.botaro.portifolio.domain.Project;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
