package server.helpers;

import commons.dataClasses.UserInfo;
import server.models.user.Player;
import server.models.user.User;

public class UserHelper {
    public static UserInfo getUserInfo(User user){
        UserInfo.Type type = user instanceof Player? UserInfo.Type.user: UserInfo.Type.bot;
        int id = Integer.parseInt(user.id);
        Integer[] cards = user.numbers.toArray(new Integer[0]);
        int cardsNumber = cards.length;
        return new UserInfo(id,type,cards,cardsNumber);
    }

}