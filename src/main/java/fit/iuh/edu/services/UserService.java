package  fit.iuh.edu.services;
import fit.iuh.edu.entities.Product;
import fit.iuh.edu.entities.User;
import fit.iuh.edu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    public static final String HASH_KEY = "User";
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RedisTemplate template;


    RestTemplate restTemplate = new RestTemplate();


    public User save(User user){
        template.opsForHash().put(HASH_KEY,user.getId(),user);
        userRepository.save(user);
        return user;
    }

    public List<User> findAll() {
        return  template.opsForHash().values(HASH_KEY);



    }

    public User findUserById(String id){
        String apiUrl = "http://localhost:8081/api/v1/product?userid="+id;

        // Gọi API GET và chuyển đổi kết quả thành đối tượng ResponseEntity
        ResponseEntity<List<Product>> products=  restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {});

        System.out.println(products.getBody());
        User user =userRepository.findById(id).get();
        user.setProducts(products.getBody());
        return user;
//       User user= (User) template.opsForHash().get(HASH_KEY,id);
//        System.out.println(user.toString());
//       user.setProducts(products.getBody());
//       update(user);
//
//       return (User) template.opsForHash().get(HASH_KEY,id);

    }

    public String deleteUser(String id){
        template.opsForHash().delete(HASH_KEY,id);
        userRepository.deleteById(id);
        return "User removed !!";
    }

    public User update(User user) {
        template.opsForHash().put(HASH_KEY, user.getId(), user);
        return userRepository.save(user);
    }
}