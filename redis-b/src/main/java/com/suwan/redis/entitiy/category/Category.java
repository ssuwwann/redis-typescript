package com.suwan.redis.entitiy.category;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENT_ID")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private List<Category> children = new ArrayList<>();

  private Integer level;
}
