package com.onion;

import com.onion.entity.Solution;
import org.springframework.stereotype.Repository;

public interface Solver {
    void setSolution(Solution solution);

    Solution getSolution();

    void run();

}
