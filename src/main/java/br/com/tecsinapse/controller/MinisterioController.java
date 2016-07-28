package br.com.tecsinapse.controller;

import br.com.tecsinapse.cdi.annotation.UsuarioLogado;
import br.com.tecsinapse.model.Ministerio;
import br.com.tecsinapse.model.Usuario;
import br.com.tecsinapse.model.Video;
import br.com.tecsinapse.service.MinisterioService;
import br.com.tecsinapse.service.UsuarioService;
import br.com.tecsinapse.service.VideoService;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.apache.shiro.codec.Base64;
import org.omnifaces.util.Messages;
import org.primefaces.model.UploadedFile;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "ministerios", pattern = "/ministerios/", viewId = "/jsf/pages/protegido/geral/igreja/ministerios/ministerios.xhtml"),
        @URLMapping(id = "ministerio", pattern = "/ministerio/id/#{ministerioController.ministerioId}/", viewId = "/jsf/pages/protegido/geral/igreja/ministerios/ministerio.xhtml"),
        @URLMapping(id = "ministerio-novo", pattern = "/ministerio/novo", viewId = "/jsf/pages/protegido/geral/igreja/ministerios/ministerio.xhtml")


})
public class MinisterioController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private MinisterioService ministerioService;

    @Inject
    private UsuarioService usuarioService;


    @Inject
    @UsuarioLogado
    private Usuario usuarioLogado;



    private List<Usuario> usuarios;

    private UploadedFile file;

    private List<Ministerio> ministerios;
    private Long ministerioId;
    private Ministerio ministerio;

    @URLAction(mappingId = "ministerios", onPostback = false)
    public void idMinisterios() {
        ministerios = ministerioService.findAll();
    }

    @URLAction(mappingId = "ministerio", onPostback = false)
    public void idUsuario() {
        ministerio = ministerioService.findById(ministerioId);
        usuarios = usuarioService.findAllByChurch(this.usuarioLogado.getIgreja());
    }

    @URLAction(mappingId = "ministerio-novo", onPostback = false)
    public void idUsuarioNovo() {
        ministerio = new Ministerio();
        usuarios = usuarioService.findAllByChurch(this.usuarioLogado.getIgreja());
    }

    public void salvar() {
        if (ministerio.getIgreja() == null)
            ministerio.setIgreja(usuarioLogado.getIgreja());

        ministerioService.save(ministerio);
        Messages.addGlobalInfo("Ministério salvo com sucesso!");
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    public UploadedFile getFile(){
        return this.file;
    }

    public List<Ministerio> getMinisterios() {
        return ministerios;
    }

    public void setMinisterios(List<Ministerio> ministerios) {
        this.ministerios = ministerios;
    }

    public void setMinisterioId(Long ministerioId) {
        this.ministerioId = ministerioId;
    }

    public Long getMinisterioId() {
        return ministerioId;
    }

    public Ministerio getMinisterio() {
        return ministerio;
    }

    public void setMinisterio(Ministerio ministerio) {
        this.ministerio = ministerio;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
