import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by liutri on 2017-03-30.
 */
public class UserData {

    private Map<Integer, User> users = new HashMap();

    UserData () {
        List<User> usersArray = Arrays.asList(
                new User(1, "Testas Testauskas", "testas@testis.com"),
                new User(2, "Testutis Testukas", "testutis@test.com"),
                new User(3, "Testis Testimas", "testis@test.com"),
                new User(4, "Testas Tesutinskas", "testas@test.com"),
                new User(5, "Testelis Testimas", "testelis@test.com")
        );

        usersArray.forEach(
                (user) -> {this.users.put(user.getId(), user);
                });
    }

    public void create(User user) {
        user.setId(users.size()+1);
        users.put(user.getId(), user);
    }

    public void delete(int id) {
        users.remove(id);
    }

    public User get(int id) {
        return users.get(id);
    }

    public void update(int id, User user) {
        user.setId(id);
        users.put(id, user);
    }

    public List<User> getAll() {
        return users.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public List<User> findByName(String name) {
        return users.entrySet().stream().filter(
                (entry) -> entry.getValue().getName().contains(name)
        ).map( Map.Entry::getValue ).collect(Collectors.toList());
    }
}
