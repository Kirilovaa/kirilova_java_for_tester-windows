package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().HomePage();
        if (!app.contact().isThereAContactWithoutGroup()) {
            app.contact().create(new ContactData().withUserName("userName")
                    .withUserLastName("userLastName")
                    .withHomeTel("home").withMobelTel("mobil").withWorkTel("work")
                    .withEmail1("email1").withEmail2("email2").withEmail3("email3"), true);
        app.contact().HomePage();
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testContactAddToGroup() {
        Contacts before = app.db().contactsInGroup();
        app.contact().addToGroup();
        app.contact().HomePage();
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contactsInGroup();
        assertEquals(after, equalTo(before.size() + 1));
    }
}


//
// Тест на добавление в группу
// Проверить есть ли контакт без группы (нон) если нет, то создать
// Проверить есть ли группа, если нет, то создать группу 1
// добавляем какой-то контакт в группу 1
//
//Тест на удаление из группы
//Проверить есть ли контакт, если нет, то создать
//Проверить есть ли какая-то группа, если нет, то создать группу 1
//Проверить есть ли контакты в группе 1, если если такие есть, то удаляем какую-то из группы 1
//