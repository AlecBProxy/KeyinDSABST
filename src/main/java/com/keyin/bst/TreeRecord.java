package com.example.bst;

import jakarta.persistence.*;

@Entity
public class TreeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputNumbers;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String treeJson;

    private boolean balanced;

    public Long getId() { return id; }
    public String getInputNumbers() { return inputNumbers; }
    public String getTreeJson() { return treeJson; }
    public boolean isBalanced() { return balanced; }

    public void setId(Long id) { this.id = id; }
    public void setInputNumbers(String inputNumbers) { this.inputNumbers = inputNumbers; }
    public void setTreeJson(String treeJson) { this.treeJson = treeJson; }
    public void setBalanced(boolean balanced) { this.balanced = balanced; }
}
