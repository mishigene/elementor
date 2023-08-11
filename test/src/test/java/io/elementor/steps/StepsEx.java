package io.elementor.steps;

import io.elementor.TestContext;

import static org.hamcrest.MatcherAssert.assertThat;

public class StepsEx extends StepsBase{

    private final String ADDRESS = "http://www.youtube.com";

    public StepsEx(TestContext context) {
        super(context);
    }


//    @Given("I have navigated to youtube")
//    public void navigateToYT(){
//        Browser browser = context.getResource(Browser.class);
//        browser.navigate(ADDRESS, YouTubePage.class);
//    }

//    @Given("Gmail test")
//    public void gmailTest(){
//        GmailService gmailService = new GmailService();
//        List<Email> messages = gmailService.getMessages("welcome@crowdvocate.com");
//        int x=1;
//    }
//
//    @Given("On the main YouTube page - search for \"{}\"")
//    public void searchYT(String term){
//        Browser browser = context.getResource(Browser.class);
//        YouTubePage ytPage = browser.getCurrentPage();
//        ytPage.search(term);
//        browser.createPage(YTSearchResults.class);
//    }
//
//    @When("On YT search results page - middle click on video with title \"{}\"")
//    public void midClickOnResult(String title){
//        Browser browser = context.getResource(Browser.class);
//        YTSearchResults results = browser.getCurrentPage();
//        browser.waitForPage(YTChannelPage.class, () -> results.midClickOnResult(title));
//    }
//
//    @Then("On the channel page - there is {} subscribers")
//    public void verifySubscriberCount(int subsCount){
//        Browser browser = context.getResource(Browser.class);
//        YTChannelPage page = browser.getCurrentPage();
//        Assertions.softAssert(()->assertThat(page.getSubsCount(),equalTo(subsCount)));
//    }


}
