package io03.export.pdf.controller;

import io03.export.pdf.model.Employee;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io03.export.pdf.repository.EmployeeRepository;
import io03.export.pdf.util.PDFGenerator;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = {"/", "/export"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeeReport() throws IOException {

        List<Employee> employees = (List<Employee>) employeeRepository.findAll();

        // !important use ByteArrayInputStream
        ByteArrayInputStream bis = PDFGenerator.employeePDFReport(employees);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employees.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
    }
}