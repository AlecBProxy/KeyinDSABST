package com.example.bst;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



@Controller
public class TreeController {

    @Autowired
    private TreeRecordRepository treeRecordRepository;
    private final Gson gson = new Gson();


    public TreeController(TreeRecordRepository treeRecordRepository) {
        this.treeRecordRepository = treeRecordRepository;
    }

    @GetMapping({"/", "/enter-numbers"})
    public String enterNumbersForm() {
        return "enter-numbers";
    }

    // Accept numbers, build BST, return JSON
    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String numbers, Model model) {
        String[] parts = numbers.split(",");
        BST bst = new BST();
        for (String p : parts) {
            bst.insert(Integer.parseInt(p.trim()));
        }

        String jsonTree = gson.toJson(bst.root);

        TreeRecord record = new TreeRecord();
        record.setInputNumbers(numbers);
        record.setTreeJson(jsonTree);
        treeRecordRepository.save(record);

        model.addAttribute("treeJson", bst.root);
        return "tree-visual";
    }

    @GetMapping("/previous-trees")
    public String viewPreviousTrees(Model model) {
        model.addAttribute("trees", treeRecordRepository.findAll());
        return "previous-trees";
    }

    private List<Integer> parseNumbers(String numbers) {
        if (numbers == null || numbers.trim().isEmpty()) return new ArrayList<>();
        return Arrays.stream(numbers.split("[,\\s]+"))
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
