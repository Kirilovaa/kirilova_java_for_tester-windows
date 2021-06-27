package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletion() {
        app.getContactHelper().findElement();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlertDelete();
        app.getContactHelper().returnToHome();
    }
}
