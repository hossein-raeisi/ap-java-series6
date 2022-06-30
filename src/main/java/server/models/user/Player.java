package server.models.user;

import java.util.Scanner;

public class Player extends User{
    @Override
    public int play(int lastNumber) {
        Scanner sc = new Scanner(System.in);
        int number;
        while (true){
            String input = sc.next();
            try{
                number = Integer.parseInt(input);
            }catch (NumberFormatException nfe){
                continue;
            }
            if(this.numbers.contains(number)||number == -1) break;
        }
        sc.close();
        return number;
    }
}
