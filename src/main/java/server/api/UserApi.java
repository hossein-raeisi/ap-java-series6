package server.api;

import server.helpers.GameHelper;
import server.helpers.UserHelper;
import server.models.user.Player;
import server.security.Control;
import spark.Request;
import spark.Response;

public class UserApi {

    public static String getUserInfo(Request request, Response response) {
        Player player = Control.getPlayerFromAuthToken(request.headers("Auth-Token"));
        response.body(GameHelper.toString(UserHelper.getUserInfo(player)));
        return response.body();
    }
}
