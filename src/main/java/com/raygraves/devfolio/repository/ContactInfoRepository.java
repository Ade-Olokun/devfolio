package com.raygraves.devfolio.repository;

import com.raygraves.devfolio.model.ContactInfo;
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
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {

    // Standard CRUD operations
    <S extends ContactInfo> S save(S entity);
    <S extends ContactInfo> List<S> saveAll(Iterable<S> entities);
    Optional<ContactInfo> findById(Long id);
    boolean existsById(Long id);
    List<ContactInfo> findAll();
    List<ContactInfo> findAllById(Iterable<Long> ids);
    long count();
    void deleteById(Long id);
    void delete(ContactInfo entity);
    void deleteAllById(Iterable<? extends Long> ids);
    void deleteAll(Iterable<? extends ContactInfo> entities);
    void deleteAll();

    // JPA specific methods
    void flush();
    <S extends ContactInfo> S saveAndFlush(S entity);
    <S extends ContactInfo> List<S> saveAllAndFlush(Iterable<S> entities);
    void deleteAllInBatch(Iterable<ContactInfo> entities);
    void deleteAllByIdInBatch(Iterable<Long> ids);
    void deleteAllInBatch();

    // Querying methods
    List<ContactInfo> findAll(Sort sort);
    Page<ContactInfo> findAll(Pageable pageable);
    <S extends ContactInfo> Optional<S> findOne(Example<S> example);
    <S extends ContactInfo> List<S> findAll(Example<S> example);
    <S extends ContactInfo> List<S> findAll(Example<S> example, Sort sort);
    <S extends ContactInfo> Page<S> findAll(Example<S> example, Pageable pageable);
    <S extends ContactInfo> long count(Example<S> example);
    <S extends ContactInfo> boolean exists(Example<S> example);
    <S extends ContactInfo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    // Custom query methods
    Optional<ContactInfo> findByEmail(String email);
    List<ContactInfo> findByFullNameContaining(String name);
}