package k8s05.backend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import k8s05.backend.model.UserServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    Map<Integer, UserServer> list = new HashMap<>();

    public Map<Integer, UserServer> init() {
        list.put(9898, new UserServer("kevin", "kevin@gmail.com"));
        list.put(8456, new UserServer("ginger", "ginger@gmail.com"));
        list.put(1234, new UserServer("pro", "pro@gmail.com"));
        list.put(5678, new UserServer("kent", "kent@gmail.com"));

        IntStream.range(1, 100).forEach(num -> {
            list.put(num, new UserServer("tommy" + num, num + "-tommy@gmail.com"));
        });
        return list;

    }

    public Map<Integer, UserServer> getUser() {
        log.info("getUser size : {}", list.size());
        return list;
    }

    public Map<Integer, UserServer> deleteuser(Integer UserId) {
        list.remove(UserId);
        log.info("deleteuser size : {}", list.size());
        return list;
    }

}
