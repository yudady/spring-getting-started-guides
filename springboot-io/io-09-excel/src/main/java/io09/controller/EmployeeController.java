package io09.controller;

import io09.excel.PoiExcelWriter;
import io09.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;


@RestController
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    EmployeeService service;


    @GetMapping({"/"})
    public ResponseEntity<InputStreamResource> exportCSV(HttpServletResponse response) throws Exception {

        PoiExcelWriter writer = service.get();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            writer.write(baos);
            byte[] b = baos.toByteArray();
//            String fileName = "member_group_liability_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + ".xls";
//            Response.ResponseBuilder response = Response.ok(b);
//            response.type("application/vnd.ms-excel;");
//            response.header("Content-Disposition", "attachment; filename=" + fileName);
//            return response.build();
            return null;
        } finally {
            writer.close();
        }

    }
}