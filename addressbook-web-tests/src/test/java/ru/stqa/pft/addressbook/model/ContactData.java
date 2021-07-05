package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String userNameParametr;
  private final String userMiddleName;
  private final String userLastName;
  private final String userNickname;
  private final String group;
  private final String title1;
  private final String companyName;
  private final String companyAddress;
  private final String homeTel;
  private final String mobelTel;
  private final String workTel;
  private final String fax1;
  private final String email1;
  private final String email2;
  private final String email3;
  private final String homePage;
  private final String bday;
  private final String bounth;
  private final String byear;
  private final String aday;
  private final String amonth;
  private final String ayear;

  public ContactData(String userNameParametr, String userMiddleName, String userLastName, String userNickname, String group, String title1, String companyName, String companyAddress, String homeTel, String mobelTel, String workTel, String fax1, String email1, String email2, String email3, String homePage, String Bday, String bounth, String byear, String aday, String amonth, String ayear) {
    this.userNameParametr = userNameParametr;
    this.userMiddleName = userMiddleName;
    this.userLastName = userLastName;
    this.userNickname = userNickname;
    this.group = group;
    this.title1 = title1;
    this.companyName = companyName;
    this.companyAddress = companyAddress;
    this.homeTel = homeTel;
    this.mobelTel = mobelTel;
    this.workTel = workTel;
    this.fax1 = fax1;
    this.email1 = email1;
    this.email2 = email2;
    this.email3 = email3;
    this.homePage = homePage;
    this.bday = Bday;
    this.bounth = bounth;
    this.byear = byear;
    this.aday = aday;
    this.amonth = amonth;
    this.ayear = ayear;
  }

  public String getUserNameParametr() {
    return userNameParametr;
  }

  public String getUserMiddleName() {
    return userMiddleName;
  }

  public String getUserLastName() {
    return userLastName;
  }

  public String getUserNickname() {
    return userNickname;
  }

  public String getTitle1() {
    return title1;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getCompanyAddress() {
    return companyAddress;
  }

  public String getHomeTel() {
    return homeTel;
  }

  public String getMobelTel() {
    return mobelTel;
  }

  public String getWorkTel() {
    return workTel;
  }

  public String getFax1() {
    return fax1;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getHomePage() {
    return homePage;
  }

  public String getBday() {
    return bday;
  }

  public String getBounth() {
    return bounth;
  }

  public String getByear() {
    return byear;
  }

  public String getAday() {
    return aday;
  }

  public String getAmonth() {
    return amonth;
  }

  public String getAyear() {
    return ayear;
  }

  public String getGroup() {
    return group;
  }
}
