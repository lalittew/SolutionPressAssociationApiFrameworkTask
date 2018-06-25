package framework;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import models.SongRequest;

//This class helps to send the create the request and send
public class RequestHelper {

    public static CustomResponse getRequest(String path, Class<?> responseClass) throws IOException {
        return Request.Get(path)
                .execute()
                .handleResponse(httpResponse -> handleCustomResponse(httpResponse, responseClass));

    }

    public static CustomResponse postRequest(String path, SongRequest body, Class<?> responseClass) throws IOException {

        return  Request.Post(path)
                .connectTimeout(1000)
                .socketTimeout(1000)
                .bodyString(body.toJsonString(), ContentType.APPLICATION_JSON)
                .execute().handleResponse(httpResponse -> handleCustomResponse(httpResponse, responseClass));
    }


    private static CustomResponse handleCustomResponse(HttpResponse httpResponse, Class<?> responseClass) throws IOException {

        CustomResponse response = new CustomResponse();

        String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
        Object responseParsed = gson.fromJson(responseString, responseClass);

        response.setEntity(responseParsed);
        return response;
    }

	public static int patchRequest(String path) throws IOException {

    	HttpResponse response = Request.Patch("http://turing.niallbunting.com:3001/api/video/"+path+"")
    			.connectTimeout(1000)
                .socketTimeout(1000)
    		    .execute()
    		    .returnResponse();
		int status = response.getStatusLine().getStatusCode();
	//	System.out.println("Lalit:"+status);
		return status;
    
    }

	public static Response deleteRequest(String path) throws IOException {

        return  Request.Delete("http://turing.niallbunting.com:3001/api/video/"+path+"")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute();
        
    }
   
}

