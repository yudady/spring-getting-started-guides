package web06.producer.service;

import web06.producer.model.ProducerUser;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerUserService {

	public Map<Integer, ProducerUser> getUser() {

		Map<Integer, ProducerUser> list = ProducerUser.userFactory();
		log.info("getUser size : {}", list.size());
		return list;

	}

	public Map<Integer, ProducerUser> deleteuser(Integer UserId) {

		Map<Integer, ProducerUser> list = ProducerUser.userFactory();
		list.remove(UserId);
		log.info("deleteuser size : {}", list.size());
		return list;
	}

}
