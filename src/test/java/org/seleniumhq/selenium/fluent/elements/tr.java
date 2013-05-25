package org.seleniumhq.selenium.fluent.elements;

import org.junit.Test;
import org.openqa.selenium.By;
import org.seleniumhq.selenium.fluent.BaseTest2;
import org.seleniumhq.selenium.fluent.FluentExecutionStopped;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class tr extends BaseTest2 {

    @Test
    public void tr_functionality() {

        setupExpecations("tr");

        FluentWebElements fe = fwd.tr()
                .tr(By.xpath("@foo = 'bar'"))
                .tr(By.cssSelector("baz"))
                .trs();

        assertThat(fe, notNullValue());
        verifications("tr");
    }

    @Test
    public void trs_functionality() {

        setupExpecations2("tr");

        FluentWebElements fe = fwd.tr()
                .trs(By.name("qux"));

        assertThat(fe, notNullValue());

        verifications2("tr");

    }

    @Test
    public void tr_mismatched() {

        when(wd.findElement(By.linkText("mismatching_tag_name"))).thenReturn(we);
        when(we.getTagName()).thenReturn("boo");

        try {
            fwd.tr(By.linkText("mismatching_tag_name"))
                    .clearField();
            fail("should have barfed");
        } catch (FluentExecutionStopped e) {
            assertThat(e.getMessage(), equalTo("AssertionError during invocation of: ?.tr(By.linkText: mismatching_tag_name)"));
            assertThat(e.getCause().getMessage(), equalTo("tag was incorrect, should have been tr but was boo"));
        }

    }


}