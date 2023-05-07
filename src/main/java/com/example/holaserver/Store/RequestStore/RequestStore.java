package com.example.holaserver.Store.RequestStore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Table(name="store_request_log")
@Where(clause = "removed_at Is NULL")
@NoArgsConstructor
public class RequestStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long storeId;
    private Long userId;
    private Timestamp removedAt;
}
