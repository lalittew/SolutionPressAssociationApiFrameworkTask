package video;

import static junit.framework.TestCase.assertEquals;

import java.text.SimpleDateFormat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.CustomResponse;
import framework.RequestHelper;
import framework.RequestHolder;
import framework.ResponseHolder;
import models.SongRequest;
import models.VideoRequest;
import models.VideoResponse;


public class VideoStepDefinition {

    private ResponseHolder responseHolder = new ResponseHolder();
    private RequestHolder requestHolder = new RequestHolder();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static int statusLine;

   
    @Given("^the user request the api to get the list of video$")
    public void the_user_request_the_api_to_get_the_list_of_video() throws Throwable {

        CustomResponse response = RequestHelper.getRequest("http://turing.niallbunting.com:3001/api/video", VideoResponse[].class);

        responseHolder.setResponse(response);
    }
    
    
    @Then("^user should get \"([^\"]*)\" as status code$")
    public void user_should_get_as_status_code(String arg1) throws Throwable {
    	CustomResponse response = responseHolder.getResponse();
    //	VideoResponse responses = (VideoResponse) response.getEntity();
    	assertEquals("Unexpected status code", Integer.parseInt(arg1), response.getStatusCode());
    
    }
    
    @Then("^The lists should returns \"([^\"]*)\" videos$")
    public void the_lists_should_returns_videos(String arg1) throws Throwable {
    	
    	CustomResponse response = responseHolder.getResponse();

        VideoResponse[] responses = (VideoResponse[]) response.getEntity();

        assertEquals("Unexpected size", Integer.parseInt(arg1), responses.length);
    }
    
    @Then("^I update video with \"([^\"]*)\"from list$")
    public void i_update_video_with_from_list(String arg1) throws Throwable {
   
    	statusLine=	RequestHelper.patchRequest(arg1); 	//To fire the patch request
    	
    }
    
    @Then("^user should get \"([^\"]*)\" as status line$")
    public void user_should_get_as_status_line(String arg1) throws Throwable {
    	
    	assertEquals("Response code retrived is different from expected",Integer.parseInt(arg1),statusLine); 
    	System.out.println("Lalit: "+statusLine); //To get the status line coming in response
    }
    
    
    @Given("^User want to add \"([^\"]*)\" of \"([^\"]*)\" which is published on \"([^\"]*)\"$")
    		public void user_want_to_add_of_which_is_published_on(String artitsName, String songName, String date) throws Throwable {
    	 VideoRequest postVideo = new VideoRequest(artitsName, songName, dateFormat.parse(date));
         requestHolder.setRequest(postVideo);
    		   
    		}


    @When("^I add the video to the system$")
    public void when_I_add_the_video_to_the_system() throws Throwable {

        SongRequest requestBody = requestHolder.getRequest();

        CustomResponse response = RequestHelper.postRequest("http://turing.niallbunting.com:3001/api/video", requestBody, VideoResponse.class);
        responseHolder.setResponse(response);
        
  //      CustomResponse responses = responseHolder.getResponse();

    }

    @Then("^The video is successfully added$")
    public void the_video_is_successfully_added() throws Throwable {

        CustomResponse response = responseHolder.getResponse();
        VideoRequest bodySent = (VideoRequest) requestHolder.getRequest();
        VideoResponse responses = (VideoResponse) response.getEntity();
       
   //     assertEquals("Unexpected status code", 201, response.getStatusCode());
        assertEquals(bodySent.getArtist(), responses.getArtist());
        assertEquals(bodySent.getSong(), responses.getSong());
        assertEquals(bodySent.getPublishDate(), responses.getPublishDate());

        //    requestHolder.setRequest(null); //To flush the request
        //    responseHolder.setResponse(null); //To flush the response
    }
    
    @Then("^The lists should returns \"([^\"]*)\" videoss$")
    public void the_lists_should_returns_videoss(String arg1) throws Throwable {
        
    }

    @Given("^I delete video at \"([^\"]*)\" from list$")
    public void i_delete_video_with_from_list(String position) throws Throwable {
    	CustomResponse response = responseHolder.getResponse();
	    VideoResponse[] responses = (VideoResponse[]) response.getEntity();
	
	    int sizeOfList= responses.length; //Get the length of response body
       
	    if((Integer.parseInt(position))<sizeOfList) {
        String ID= responses[(Integer.parseInt(position))].get_id();
        RequestHelper.deleteRequest(ID); 	
          }
	    else {
	    	 assertEquals("Unexpected size", Integer.parseInt(position), responses.length);
	    }
    }
    @Then("^The list of videos contains \"([^\"]*)\" videos$")
    public void the_list_of_videos_contains(String arg1) throws Throwable {
    	
    	CustomResponse response = RequestHelper.getRequest("http://turing.niallbunting.com:3001/api/video", VideoResponse[].class);

        responseHolder.setResponse(response);
        
        CustomResponse responsed = responseHolder.getResponse();

        VideoResponse[] responses = (VideoResponse[]) responsed.getEntity();
        assertEquals("Unexpected size", Integer.parseInt(arg1), responses.length);
     
    }


}
