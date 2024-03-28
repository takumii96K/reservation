package org.example.reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name ="image_name")
    private String name; // ファイル名

    @Column(name = "image_url")
    private String path; // 画像のURL（クラウドストレージ内のファイルへのリンク）

    @Column(name ="image_type")
    private String type;

    @OneToOne(mappedBy = "image")
    @JsonBackReference
    private Product product;
}
