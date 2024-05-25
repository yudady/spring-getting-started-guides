package tw.yudady;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

@Slf4j
public class Test01 {

    @Test
    public void test01() {
        BigDecimal b1 = BigDecimal.ONE;
        BigDecimal b2 = new BigDecimal("1.00");
        int i = b1.compareTo(b2);
        log.info("i = " + i);
    }
}
