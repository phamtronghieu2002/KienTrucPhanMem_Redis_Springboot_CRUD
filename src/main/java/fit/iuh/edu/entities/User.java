package fit.iuh.edu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Entity
@Table(name="USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User implements Serializable {

    @Id
    private String id;


    private String user_name;


    private String address;
}
