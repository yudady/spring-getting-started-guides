package io09.service;

import io09.excel.PoiExcelWriter;
import io09.excel.RawPoiExcelWriter;
import io09.model.Employee;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmployeeService {


    public PoiExcelWriter get() throws Exception {
        //OdsCustomerLiabilityQueryService will be replaced by CustomerGroupService
        List<Employee> list = List.of();
        //liabilityService.getList(criteria);
        // add title
        String[] titles = List.of("CustomerName", "DepositGroupId").toArray(new String[8]);

        RawPoiExcelWriter writer = new RawPoiExcelWriter(titles, 20);

        // add list
        int count = 0;
        for (Employee data : list) {
            writer.goNextRow();
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("");
            writer.rowValues(
                    data.getId(),
                    data.getName()
            );
            // mark out for performance issue
            // sb.append(MONEY_FORMAT.format(data.getBetAmount())).append(",");
            // sb.append(MONEY_FORMAT.format(data.getDepositAmount())).append(",");
            count++;

            // break if row count exceeds maximum rows of excel
            if (count >= 65534) {
                break;
            }
        }

        return writer;
    }
}
