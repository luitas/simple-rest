import spark.Request;
import spark.Response;

/**
 * Created by liutri on 2017-03-30.
 */
public class UserController {

    private static final int HTTP_BAD_REQUEST = 400;
    private static final int HTTP_NOT_FOUND = 404;

    public static Object getAllUsers(Request request, Response response, UserData userData) {
        return userData.getAll();
    }

    public static Object getUser(Request request, Response response, UserData userData) {
        try {
            int id = Integer.valueOf(request.params("id"));
            User user = userData.get(id);
            if (user == null) {
                throw new Exception("Nepavyko rasti");
            }
            return user;
        } catch (Exception e) {
            response.status(HTTP_NOT_FOUND);
            return new ErrorMessage("Nepavyko rasti vartotojo su id: " + request.params("id"));
        }
    }

    public static Object createUser(Request request, Response response, UserData userData) {
        User user = JsonTransformer.fromJson(request.body(), User.class);
        userData.create(user);
        return "OK";
    }

    public static Object updateUser(Request request, Response response, UserData userData) {

        try {
            User user = JsonTransformer.fromJson(request.body(), User.class);
            int id = Integer.valueOf(request.params("id"));
            userData.update(id, user);
            return "OK";
        } catch (Exception e) {
            response.status(HTTP_NOT_FOUND);
            return new ErrorMessage("Nepavyko rasti vartotojo su id: " + request.params("id"));
        }
    }

    public static Object deleteUser(Request request, Response response, UserData userData) {
        try {
            int id = Integer.valueOf(request.params("id"));
            userData.delete(id);
            return "OK";
        } catch (Exception e) {
            response.status(HTTP_NOT_FOUND);
            return new ErrorMessage("Nepavyko rasti vartotojo su id: " + request.params("id"));
        }
    }

    public static Object findUserByName(Request request, Response response, UserData userData) {
        return userData.findByName(request.params("name"));
    }
}
