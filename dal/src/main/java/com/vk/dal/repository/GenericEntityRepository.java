package com.vk.dal.repository;

import com.vk.dal.domain.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface GenericEntityRepository <T extends GenericEntity> extends JpaRepository<T, Long> {

}
