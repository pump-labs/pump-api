package com.example.holaserver.Review;

import com.example.holaserver.Common.BaseTimeEntity;
import com.example.holaserver.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long storeId;

    private Long userId;

    private String reviewText;

    private Timestamp removedAt;
}