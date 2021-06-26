package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {

  public  NavigationHelper navigationHelper;
  protected ChromeDriver wd;

  public ContactHelper(ChromeDriver wd) {
    this.wd = wd;
  }

  public void submitContactCreation() {
      wd.findElement(By.name("submit")).click();
    }

    public void fillContactForm(ContactData contactData) {
      unit(By.name("firstname"), contactData.getUserNameParametr());
      wd.findElement(By.name("middlename")).click();
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(contactData.getUserMiddleName());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(contactData.getUserLastName());
      wd.findElement(By.name("nickname")).click();
      wd.findElement(By.name("nickname")).clear();
      wd.findElement(By.name("nickname")).sendKeys(contactData.getUserNickname());
      wd.findElement(By.name("title")).click();
      wd.findElement(By.name("title")).clear();
      wd.findElement(By.name("title")).sendKeys(contactData.getTitle1());
      wd.findElement(By.name("company")).click();
      wd.findElement(By.name("company")).clear();
      wd.findElement(By.name("company")).sendKeys(contactData.getCompanyName());
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(contactData.getCompanyAddress());
      wd.findElement(By.name("theform")).click();
      wd.findElement(By.name("home")).click();
      wd.findElement(By.name("home")).clear();
      wd.findElement(By.name("home")).sendKeys(contactData.getHomeTel());
      wd.findElement(By.name("mobile")).click();
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys(contactData.getMobelTel());
      wd.findElement(By.name("work")).click();
      wd.findElement(By.name("work")).clear();
      wd.findElement(By.name("work")).sendKeys(contactData.getWorkTel());
      wd.findElement(By.name("fax")).click();
      wd.findElement(By.name("fax")).clear();
      wd.findElement(By.name("fax")).sendKeys(contactData.getFax1());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(contactData.getEmail1());
      wd.findElement(By.name("email2")).click();
      wd.findElement(By.name("email2")).clear();
      wd.findElement(By.name("email2")).sendKeys(contactData.getEmail2());
      wd.findElement(By.name("email3")).click();
      wd.findElement(By.name("email3")).clear();
      wd.findElement(By.name("email3")).sendKeys(contactData.getEmail3());
      wd.findElement(By.name("homepage")).click();
      wd.findElement(By.name("homepage")).clear();
      wd.findElement(By.name("homepage")).sendKeys(contactData.getHomePage());
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
      wd.findElement(By.name("bday")).click();
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBounth());
      wd.findElement(By.name("bmonth")).click();
      wd.findElement(By.name("byear")).click();
      wd.findElement(By.name("byear")).clear();
      wd.findElement(By.name("byear")).sendKeys(contactData.getByear());
      new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getAday());
      wd.findElement(By.name("aday")).click();
      new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getAmonth());
      wd.findElement(By.name("amonth")).click();
      wd.findElement(By.name("ayear")).click();
      wd.findElement(By.name("ayear")).clear();
      wd.findElement(By.name("ayear")).sendKeys(contactData.getAyear());
    }

  private void unit(By locat, String txt) {
    wd.findElement(locat).click();
    wd.findElement(locat).clear();
    wd.findElement(locat).sendKeys(txt);
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
