package backend.academy;

import backend.academy.app.App;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
