package hellojpa.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Album extends Item {

    private String author;
    private String etc;
}
