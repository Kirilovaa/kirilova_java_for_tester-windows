package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
//      если в БД нет ни одной группы, то создаем с названием test1
        app.contact().HomePage();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
//      если у нас нет ни одного контакта, то создаем его
        if (!app.contact().isThereAContact()) {
            app.contact().create(new ContactData().withUserName("userName")
                    .withUserLastName("userLastName")
                    .withHomeTel("home").withMobelTel("mobil").withWorkTel("work")
                    .withEmail1("email1").withEmail2("email2").withEmail3("email3"), true);
        app.contact().HomePage();
        }
    }

    @Test
    public void testContactAddToGroup() {
        Groups allGroups = app.db().groups();
        Contacts allContacts = app.db().contacts();
        Contacts contactsForAdding = new Contacts();
        GroupData selectedGroup;
        for (ContactData contactData : allContacts) {
            Groups groups = contactData.getGroups();
            if (groups.size() < allGroups.size()) {
                contactsForAdding.add(contactData);
            }
        }
        if (contactsForAdding.size() == 0) {
            GroupData newGroup = new GroupData().withName("test 1");
            app.goTo().groupPage();
            app.group().create(newGroup);
            allGroups = app.db().groups();
            contactsForAdding = allContacts;
            app.goTo().groupPage();
        }
        ContactData addedContactToGroup = contactsForAdding.iterator().next();
        selectedGroup = app.contact().findGroupForAdding(addedContactToGroup, allGroups).iterator().next();
        app.contact().addInGroup(addedContactToGroup, selectedGroup);
        app.contact().HomePage();
        app.contact().selectGroupList(selectedGroup);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), allContacts.size());
        for (ContactData contact : after) {
            if (contact.getId() == addedContactToGroup.getId()) {
                assertThat(addedContactToGroup.getGroups().withAdded(selectedGroup), equalTo(contact.getGroups()));
            }
        }
        verifyContactListUI();
    }



//        Contacts before = app.db().contactsInGroup();
//        app.contact().addToGroup();
//        ContactData deletedContact = before.iterator().next();
//        app.contact().HomePage();
//        assertEquals(app.contact().count(), before.size());
//        Contacts after = app.db().contactsInGroup();
//        assertEquals(after, equalTo(before.size() + 1));
//    }
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
//1) В тесте добавления контакта в группу надо найти такой контакт, который можно добавить в группу. А если таких контактов нет (в случае, если все контакты добавлены во все группы), то надо создать новую группу или контакт.
//2) В тесте удаления контакта из группы, надо найти контакт, состоящий в группе. Если таких контактов нет, то предварительно добавляйте любой контакт в любую группу.
//3) В качестве проверок реализуйте сравнение по содержимому списков групп того контакта, который был добавлен в группу или удалён из группы.
//проверить содержимое удаленного\добавленного в группу контакта

//список с группами
//список с контактами
//цикл добавления контактов группу
//если все контакты добавлены во все группы, то создать контакт или группу