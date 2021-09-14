package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class RestTests extends TestBase{

    @Test
    public void testCreateIssueRest() {
        try {
            skipIfNotFixedRest(1288);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
