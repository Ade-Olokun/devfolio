package com.raygraves.devfolio.repository;

import com.raygraves.devfolio.model.WorkHistory;
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
public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Long> {

    // Standard CRUD operations
    <S extends WorkHistory> S save(S entity);
    <S extends WorkHistory> List<S> saveAll(Iterable<S> entities);
    Optional<WorkHistory> findById(Long id);
    boolean existsById(Long id);
    List<WorkHistory> findAll();
    List<WorkHistory> findAllById(Iterable<Long> ids);
    long count();
    void deleteById(Long id);
    void delete(WorkHistory entity);
    void deleteAllById(Iterable<? extends Long> ids);
    void deleteAll(Iterable<? extends WorkHistory> entities);
    void deleteAll();

    // JPA specific methods
    void flush();
    <S extends WorkHistory> S saveAndFlush(S entity);
    <S extends WorkHistory> List<S> saveAllAndFlush(Iterable<S> entities);
    void deleteAllInBatch(Iterable<WorkHistory> entities);
    void deleteAllByIdInBatch(Iterable<Long> ids);
    void deleteAllInBatch();

    // Querying methods
    List<WorkHistory> findAll(Sort sort);
    Page<WorkHistory> findAll(Pageable pageable);
    <S extends WorkHistory> Optional<S> findOne(Example<S> example);
    <S extends WorkHistory> List<S> findAll(Example<S> example);
    <S extends WorkHistory> List<S> findAll(Example<S> example, Sort sort);
    <S extends WorkHistory> Page<S> findAll(Example<S> example, Pageable pageable);
    <S extends WorkHistory> long count(Example<S> example);
    <S extends WorkHistory> boolean exists(Example<S> example);
    <S extends WorkHistory, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    // Custom query methods
    List<WorkHistory> findByContactInfoId(Long contactInfoId);
    List<WorkHistory> findByCompanyContaining(String company);
}