package ru.baranova.NauJava.repository;
import org.springframework.data.repository.CrudRepository;

import ru.baranova.NauJava.entity.Report;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="reports")
public interface ReportRepo extends CrudRepository<Report, Long> {
}