package br.com.tecsinapse.controller;

import br.com.tecsinapse.cdi.annotation.UsuarioLogado;
import br.com.tecsinapse.model.Post;
import br.com.tecsinapse.model.Tag;
import br.com.tecsinapse.model.Usuario;
import br.com.tecsinapse.service.PostService;
import br.com.tecsinapse.service.TagService;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Named
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "posts", pattern = "/posts/", viewId = "/jsf/pages/protegido/geral/post/posts.xhtml"),
        @URLMapping(id = "post", pattern = "/post/id/#{postController.postId}/", viewId = "/jsf/pages/protegido/geral/post/post.xhtml"),
        @URLMapping(id = "post-novo", pattern = "/post/novo", viewId = "/jsf/pages/protegido/geral/post/post.xhtml")
})
public class PostController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private PostService postService;

    @Inject
    private TagService tagService;


    @Inject
    @UsuarioLogado
    private Usuario usuarioLogado;

    private List<Post> posts;
    private Long postId;
    private Post post;

    @URLAction(mappingId = "posts", onPostback = false)
    public void idPosts() {
        posts = postService.findAll();
    }

    @URLAction(mappingId = "post", onPostback = false)
    public void idPost() {
        post = postService.findById(postId);
    }

    @URLAction(mappingId = "post-novo", onPostback = false)
    public void idUsuarioNovo() {
        post = new Post();
    }

    public void salvar() {
        if (this.post.getUsuario() == null){
            this.post.setUsuario(this.usuarioLogado);
        }
        post = postService.save(post);
        Messages.addGlobalInfo("Post salvo com sucesso!");
    }
    public void setPublicado(Post post){
        post.setPublicado(true);
        this.post = this.postService.save(post);
    }

    public void setNaoPublicado(Post post) {
        post.setPublicado(false);
        this.post = this.postService.save(post);
    }

    @Transactional public void adicionarTag(String tag){
        for (String tagText : tag.split(" ")) {
            if (tagText.length() == 0)
                continue;
            Tag t = tagService.findPorTitulo(tagText);
            if (t == null) {
                t = new Tag();
                t.setTitulo(tagText);
                tagService.save(t);
            }
            Set<Tag> tags = this.post.getTags();
                if(this.post.addTag(t)) {
                    this.post = postService.save(this.post);
                }
        }
    }

    @Transactional
    public void removeTag(){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String,String> params = context.getExternalContext().getRequestParameterMap();
        String titulo = params.get("titulo");
        Tag t = tagService.findPorTitulo(titulo);
        this.post.removeTag(t);
        this.postService.save(this.post);
        this.tagService.save(t);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#"+titulo+"').remove()");

        return;
    }


    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
