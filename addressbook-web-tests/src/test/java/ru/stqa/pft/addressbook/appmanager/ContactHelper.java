package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoNewContact() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getUserName());
    type(By.name("middlename"), contactData.getUserMiddleName());
    type(By.name("lastname"), contactData.getUserLastName());
    type(By.name("nickname"), contactData.getUserNickname());
    type(By.name("title"), contactData.getTitle1());
    type(By.name("company"), contactData.getCompanyName());
    type(By.name("address"), contactData.getCompanyAddress());
    type(By.name("home"), contactData.getHomeTel());
    type(By.name("mobile"), contactData.getMobelTel());
    type(By.name("work"), contactData.getWorkTel());
    type(By.name("fax"), contactData.getFax1());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomePage());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
    click(By.name("bday"));
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBounth());
    click(By.name("bmonth"));
    type(By.name("byear"), contactData.getByear());
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getAday());
    click(By.name("aday"));
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getAmonth());
    click(By.name("amonth"));
    type(By.name("ayear"), contactData.getAyear());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void initContactModification() { click(By.xpath("//img[@alt='Edit']")); }

  public void submitContactModification() { click(By.name("update")); }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();}

  public void deleteContact() { click(By.xpath("//input[@value='Delete']")); }

  public void acceptAlertDelete() { wd.switchTo().alert().accept(); }

  public void returnToHomePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

  public boolean isThereAContact(){
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contact, boolean b) {
   gotoNewContact();
   fillContactForm(contact,b);
   submitContactCreation();
   returnToHomePage();
  }

  public int getContactCount() {
      return wd.findElements(By.name("selected[]")).size();
    }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> rows = wd.findElements(By.tagName("tr"));
//    for (WebElement element : cells ) {
//     String userName = element.findElement(By.xpath(".//td[3]")).getText();
//      String userLastName = element.findElement(By.xpath(".//td[2]")).getText();
//      cells.get(el)
    for(int a= 2;a<rows.size();a++){
      //WebElement baseTable = wd.findElement(By.tagName("table"));
      WebElement tableRow = wd.findElement(By.xpath("//tr["+a+"]"));
      String userLastName = tableRow.findElement(By.xpath(".//td[2]")).getText();
      String userName = tableRow.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(tableRow.findElement(By.xpath(".//td[1]//input")).getAttribute("value"));
      ContactData contact = new ContactData(id, userName, null, userLastName, null, null, null, null, null, null, null, null, null,null,null, null, null, null,null,null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}

