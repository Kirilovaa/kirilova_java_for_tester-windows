package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void init(String admin, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_password_page.php");
        type(By.name("username"), admin);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void resetPass(Integer user) {
        wd.get(app.getProperty("web.baseUrl") + "manage_user_edit_page.php?user_id="+ user);
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void changePass(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }

}
