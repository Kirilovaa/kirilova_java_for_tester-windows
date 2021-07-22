package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletion() {
        app.contact().HomePage();
        if (! app.contact().isThereAContact()){
            app.contact().create(new ContactData().withUserName("userName").withUserMiddleName("userMiddleName").withUserLastName("userLastName"), true);
        }
        List<ContactData> before = app.contact().getContactList();
        app.contact().selectContact(before.size()-1);
        app.contact().deleteContact();
        app.contact().acceptAlertDelete();
        app.contact().HomePage();
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
