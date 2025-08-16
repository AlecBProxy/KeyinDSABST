package com.example.bst;

import jakarta.persistence.*;

@Entity
@Table(name = "tree_records")
public class TreeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String inputNumbers;

    @Lob
    @Column(name = "tree_json", columnDefinition = "TEXT")
    private String treeJson;

    private boolean balanced;

    public TreeRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getInputNumbers() { return inputNumbers; }
    public void setInputNumbers(String inputNumbers) { this.inputNumbers = inputNumbers; }

    public String getTreeJson() { return treeJson; }
    public void setTreeJson(String treeJson) { this.treeJson = treeJson; }

    public boolean isBalanced() { return balanced; }
    public void setBalanced(boolean balanced) { this.balanced = balanced; }
}
