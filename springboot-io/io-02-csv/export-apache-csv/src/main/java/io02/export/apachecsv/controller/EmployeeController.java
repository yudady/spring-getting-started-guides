package io02.export.apachecsv.controller;

import io02.export.apachecsv.service.EmployeeService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping({"/", "/export-employees"})
    public ResponseEntity<InputStreamResource> exportCSV(HttpServletResponse response) throws Exception {

        String filename = "employees.csv";
        InputStreamResource file = new InputStreamResource(employeeService.load());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/csv")).body(file);

    }
}