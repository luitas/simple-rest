/**
 * Created by liutri on 3/23/2017.
 */
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static spark.Spark.*;

public class Comments {

    public static void main(String[] args) {
        UserData userData = new UserData();

        port(1234);

        path("/users", () -> {
            get("", (req, res) -> {
                    return UserController.getAllUsers(req, res, userData);
                } , new JsonTransformer());

            get("/:id", (req, res) -> {
                return UserController.getUser(req, res, userData);
            } , new JsonTransformer());

            get("/name/:name", (req, res) -> {
                return UserController.findUserByName(req, res, userData);
            } , new JsonTransformer());

            post("", (req, res) -> {
                return UserController.createUser(req, res, userData);
            } , new JsonTransformer());

            put("/:id", (req, res) -> {
                return UserController.updateUser(req, res, userData);
            } , new JsonTransformer());

            delete("/:id", (req, res) -> {
                return UserController.deleteUser(req, res, userData);
            } , new JsonTransformer());

        });

        exception(Exception.class, (e, req, res) -> {
            res.status(HTTP_BAD_REQUEST);
            JsonTransformer jsonTransformer = new JsonTransformer();
            res.body(jsonTransformer.render( new ErrorMessage(e) ));
        });

        after((req, rep) -> rep.type("application/json"));

    }
}
