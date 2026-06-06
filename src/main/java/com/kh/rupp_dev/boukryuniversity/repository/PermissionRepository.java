package com.kh.rupp_dev.boukryuniversity.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.rupp_dev.boukryuniversity.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>{

    Optional<Permission> findByName(String name);

    boolean existsByNameAndModule(String name , String module);

    List<Permission> findByModule(String module);

    Set<Permission> findByIdIn(List<Integer> id);

}
