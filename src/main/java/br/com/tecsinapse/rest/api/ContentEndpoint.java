package br.com.tecsinapse.rest.api;

import br.com.tecsinapse.model.Post;
import br.com.tecsinapse.model.Video;
import br.com.tecsinapse.service.PostService;
import br.com.tecsinapse.service.VideoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by renancastro on 26/05/16.
 */
@Path("/content")
public class ContentEndpoint {

    @Inject
    private VideoService videoService;

    @Inject
    private PostService postService;


    @Path("/videos")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @GET
    public Response getVideos() {
        List<Video> allVideos = videoService.findAll();
        return Response.ok().entity(allVideos).build();
    }

    @Path("/posts")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @GET
    public Response getPosts() {
        List<Post> publishedPosts = postService.findPublished();
        return Response.ok().entity(publishedPosts).build();
    }

}
