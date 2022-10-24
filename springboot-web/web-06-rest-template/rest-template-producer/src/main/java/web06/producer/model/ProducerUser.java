package web06.producer.model;

import java.util.HashMap;
import java.util.Map;

public class ProducerUser {

	private String name;
	private String mail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public ProducerUser(String name, String mail) {
		super();
		this.name = name;
		this.mail = mail;
	}

	public static Map<Integer, ProducerUser> userFactory() {
		ProducerUser user1 = new ProducerUser("sibin", "knowledgeFactory4upeoples@gmail.com");
		ProducerUser user2 = new ProducerUser("knf", "knowledgeFactory4upeoples@gmail.com");
		ProducerUser user3 = new ProducerUser("sibin2", "knowledgeFactory4upeoples@gmail.com");
		ProducerUser user4 = new ProducerUser("knf2", "knowledgeFactory4upeoples@gmail.com");

		Map<Integer, ProducerUser> list = new HashMap<>();
		list.put(9898, user1);
		list.put(8456, user2);
		list.put(1234, user3);
		list.put(5678, user4);
		return list;

	}

}
