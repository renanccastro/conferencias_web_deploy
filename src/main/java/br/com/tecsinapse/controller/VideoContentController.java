package br.com.tecsinapse.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import br.com.tecsinapse.model.Video;
import br.com.tecsinapse.service.VideoService;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.apache.shiro.codec.Base64;
import org.omnifaces.util.Messages;

import br.com.tecsinapse.cdi.annotation.UsuarioLogado;
import br.com.tecsinapse.model.Usuario;
import br.com.tecsinapse.service.UsuarioService;
import br.com.tecsinapse.util.Token;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "videos", pattern = "/videos/", viewId = "/jsf/pages/protegido/geral/video/videos.xhtml"),
        @URLMapping(id = "video", pattern = "/video/id/#{videoContentController.videoId}/", viewId = "/jsf/pages/protegido/geral/video/video.xhtml"),
        @URLMapping(id = "video-novo", pattern = "/video/novo", viewId = "/jsf/pages/protegido/geral/video/video.xhtml")


})
public class VideoContentController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private VideoService videoService;

    @Inject
    @UsuarioLogado
    private Usuario usuarioLogado;
    private UploadedFile file;

    private List<Video> videos;
    private Long videoId;
    private Video video;

    @Inject
    private UsuarioService usuarioService;

    @URLAction(mappingId = "videos", onPostback = false)
    public void idVideos() {
        videos = videoService.findAll();
    }

    @URLAction(mappingId = "video", onPostback = false)
    public void idUsuario() {
        video = videoService.findById(videoId);
    }

    @URLAction(mappingId = "video-novo", onPostback = false)
    public void idUsuarioNovo() {
        video = new Video();
    }

    public void salvar() {
        if (this.video.getUsuario() == null){
            this.video.setUsuario(this.usuarioLogado);
            this.usuarioLogado.getVideos().add(this.video);
        }
        if(file.getFileName().length() != 0){
//            TODO: Upload para o S3
            video.setThumbnail(Base64.encodeToString(file.getContents()));
        }
        videoService.save(video);
        this.usuarioService.save(this.usuarioLogado);
        Messages.addGlobalInfo("Vídeo salvo com sucesso!");
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    public UploadedFile getFile(){
        return this.file;
    }
}
