package br.com.tecsinapse.controller;

import br.com.tecsinapse.cdi.annotation.UsuarioLogado;
import br.com.tecsinapse.model.Igreja;
import br.com.tecsinapse.model.Usuario;
import br.com.tecsinapse.service.IgrejaService;
import br.com.tecsinapse.service.UsuarioService;
import br.com.tecsinapse.util.Constantes;
import br.com.tecsinapse.util.Token;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.apache.shiro.codec.Base64;
import org.omnifaces.util.Messages;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.InputStream;
import java.io.Serializable;

@Named
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "igreja", pattern = "/igreja/", viewId = "/jsf/pages/protegido/geral/igreja/igreja.xhtml")
})
public class IgrejaController implements Serializable {
    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    private String image;

    @Inject
    private IgrejaService igrejaService;

    @Inject
    @UsuarioLogado
    private Usuario usuarioLogado;

    @URLAction(mappingId = "igreja", onPostback = false)
    public void idIgreja() {
    }


    public void salvarImagem() {
        String image = Base64.encodeToString(file.getContents());
        usuarioLogado.getIgreja().setProfileImage(image);
        igrejaService.save(usuarioLogado.getIgreja());
        this.file = null;
        Messages.addGlobalInfo("Imagem Atualizada com sucesso");
    }

    public void salvar() {
        igrejaService.save(usuarioLogado.getIgreja());
        Messages.addGlobalInfo("Igreja salva com sucesso!");
    }


    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }


    public Igreja getIgrejaObj() {
        return this.getUsuarioLogado().getIgreja();
    }

    public void setIgrejaObj(Igreja igrejaObj) {
        this.getUsuarioLogado().setIgreja(igrejaObj);
    }
}
