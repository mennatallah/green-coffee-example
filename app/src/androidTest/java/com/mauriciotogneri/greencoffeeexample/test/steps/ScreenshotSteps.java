package com.mauriciotogneri.greencoffeeexample.test.steps;

import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.Then;

import java.io.File;

import androidx.test.InstrumentationRegistry;

public class ScreenshotSteps extends GreenCoffeeSteps
{
    @Then("^I take a screenshot named '(\\w+)'$")
    public void iSeeAnEmptyLoginForm(String name)
    {
        String fileName = String.format("%s/%s.jpg", locale(), name);
        File file = new File(InstrumentationRegistry.getTargetContext().getExternalFilesDir("screenshots"), fileName);
        takeScreenshot(file);
    }
}