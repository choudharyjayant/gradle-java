package com.javabatch2.Training.Services;

import com.javabatch2.Training.Models.Element;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class Pagination<T> {
    private Integer pageNumber;
    private Integer resultsPerPage=20;
    private Integer totalResults;
    private List<T> items;


    public Pagination(Integer pageNumber, Integer totalResults, List<T> items) {
      this.pageNumber = pageNumber;
      this.resultsPerPage = 20;
      this.totalResults = totalResults;
      this.items = items;
    }
}
