package data05.mode;

import org.springframework.data.annotation.Id;

public record User(@Id Long id, String name) {
}

