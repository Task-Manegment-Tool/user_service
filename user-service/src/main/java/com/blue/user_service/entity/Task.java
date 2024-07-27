package com.blue.user_service.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TASK")
@ToString
public class Task {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")

    private LocalDateTime expiryTime;
    private Long userId;

    private TaskStatus status;


//    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "project_id")
//    @JsonBackReference
//    private Project project;
}

