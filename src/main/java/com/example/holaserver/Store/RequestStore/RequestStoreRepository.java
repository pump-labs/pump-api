package com.example.holaserver.Store.RequestStore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestStoreRepository extends JpaRepository<RequestStore, Long> {
    Boolean existsByStoreIdAndUserId(Long storeId, Long userId);
    Long countByStoreIdAndRemovedAtIsNull(Long storeId);
    RequestStore findByStoreIdAndUserId(Long storeId, Long userId);

}
