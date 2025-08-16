package com.keyin.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

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

    @GetMapping("/previous-trees/latest")
    @ResponseBody
    public String getLatestTree() {
        return treeRecordRepository.findAll()
                .stream()
                .reduce((first, second) -> second) // get latest
                .map(TreeRecord::getTreeJson)
                .orElse("{}");
    }


    // Accept numbers, build BST, return JSON
    @PostMapping("/process-numbers")
    @ResponseBody
    public String processNumbers(@RequestParam String numbers) {
        // Parse and insert numbers
        String[] parts = numbers.split(",");
        BST bst = new BST();
        for (String p : parts) {
            bst.insert(Integer.parseInt(p.trim()));
        }

        // Convert tree root to JSON
        String jsonTree = gson.toJson(bst.root);

        // Save to DB
        TreeRecord record = new TreeRecord();
        record.setInputNumbers(numbers);
        record.setTreeJson(jsonTree);
        treeRecordRepository.save(record);

        // Return raw JSON string
        return jsonTree;
    }

    // Serve visualization page (loads tree-visual.html)
    @GetMapping("/visualize")
    public String visualizeTree(Model model) {
        // You could fetch the most recent tree from DB if you want
        // For now, just return the template
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
