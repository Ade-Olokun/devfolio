package com.raygraves.devfolio.repository;

import com.raygraves.devfolio.model.Education;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

    // Standard CRUD operations
    <S extends Education> S save(S entity);
    <S extends Education> List<S> saveAll(Iterable<S> entities);
    Optional<Education> findById(Long id);
    boolean existsById(Long id);
    List<Education> findAll();
    List<Education> findAllById(Iterable<Long> ids);
    long count();
    void deleteById(Long id);
    void delete(Education entity);
    void deleteAllById(Iterable<? extends Long> ids);
    void deleteAll(Iterable<? extends Education> entities);
    void deleteAll();

    // JPA specific methods
    void flush();
    <S extends Education> S saveAndFlush(S entity);
    <S extends Education> List<S> saveAllAndFlush(Iterable<S> entities);
    void deleteAllInBatch(Iterable<Education> entities);
    void deleteAllByIdInBatch(Iterable<Long> ids);
    void deleteAllInBatch();

    // Querying methods
    List<Education> findAll(Sort sort);
    Page<Education> findAll(Pageable pageable);
    <S extends Education> Optional<S> findOne(Example<S> example);
    <S extends Education> List<S> findAll(Example<S> example);
    <S extends Education> List<S> findAll(Example<S> example, Sort sort);
    <S extends Education> Page<S> findAll(Example<S> example, Pageable pageable);
    <S extends Education> long count(Example<S> example);
    <S extends Education> boolean exists(Example<S> example);
    <S extends Education, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    // Custom query methods
    List<Education> findByContactInfoId(Long contactInfoId);
    List<Education> findByInstitutionContaining(String institution);
}