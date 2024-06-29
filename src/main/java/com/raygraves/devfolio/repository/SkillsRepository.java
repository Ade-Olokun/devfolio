package com.raygraves.devfolio.repository;

import com.raygraves.devfolio.model.Skills;
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
public interface SkillsRepository extends JpaRepository<Skills, Long> {

    // Standard CRUD operations
    <S extends Skills> S save(S entity);
    <S extends Skills> List<S> saveAll(Iterable<S> entities);
    Optional<Skills> findById(Long id);
    boolean existsById(Long id);
    List<Skills> findAll();
    List<Skills> findAllById(Iterable<Long> ids);
    long count();
    void deleteById(Long id);
    void delete(Skills entity);
    void deleteAllById(Iterable<? extends Long> ids);
    void deleteAll(Iterable<? extends Skills> entities);
    void deleteAll();

    // JPA specific methods
    void flush();
    <S extends Skills> S saveAndFlush(S entity);
    <S extends Skills> List<S> saveAllAndFlush(Iterable<S> entities);
    void deleteAllInBatch(Iterable<Skills> entities);
    void deleteAllByIdInBatch(Iterable<Long> ids);
    void deleteAllInBatch();

    // Querying methods
    List<Skills> findAll(Sort sort);
    Page<Skills> findAll(Pageable pageable);
    <S extends Skills> Optional<S> findOne(Example<S> example);
    <S extends Skills> List<S> findAll(Example<S> example);
    <S extends Skills> List<S> findAll(Example<S> example, Sort sort);
    <S extends Skills> Page<S> findAll(Example<S> example, Pageable pageable);
    <S extends Skills> long count(Example<S> example);
    <S extends Skills> boolean exists(Example<S> example);
    <S extends Skills, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    // Custom query methods
    List<Skills> findByContactInfoId(Long contactInfoId);
    List<Skills> findBySkillNameContaining(String skillName);
}