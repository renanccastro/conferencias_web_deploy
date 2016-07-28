package br.com.tecsinapse.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import br.com.tecsinapse.util.Constantes;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.apache.commons.io.FileUtils;
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
        @URLMapping(id = "perfil", pattern = "/perfil/", viewId = "/jsf/pages/protegido/geral/perfil/perfil.xhtml")
})
public class PerfilController implements Serializable {
    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    private String image;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    @UsuarioLogado
    private Usuario usuarioLogado;

    @URLAction(mappingId = "perfil", onPostback = false)
    public void idPerfil() {
    }

    public void salvarImagem() {
        String image = Base64.encodeToString(file.getContents());
        usuarioLogado.setProfileImage(image);
        usuarioService.salvar(usuarioLogado);
        this.file = null;
        Messages.addGlobalInfo("Imagem Atualizada com sucesso");
    }

    public void salvarNovaSenha() {
        if (!usuarioLogado.getSenha().equals(usuarioLogado.getSenhaConfirmacao())) {
            Messages.addGlobalWarn("As senhas precisam ser iguais!");
            return;
        }
        usuarioLogado.setSenha(Token.sha256(usuarioLogado.getSenha()));
        usuarioService.salvar(usuarioLogado);
//        usuarioLogado.setSenha(null);
//        usuarioLogado.setSenhaConfirmacao(null);
        Messages.addGlobalInfo("Senha atualizada!");
    }
    public String getProfilePicture(){
        String image = getUsuarioLogado().getProfileImage();
        if (image == null)
            return "data:image/png;base64," + Constantes.DEFAULT_PROFILE_IMAGE;
        return "data:image/png;base64," + image;
    }

    public void upload() {
        System.out.println("sssss");
        if (file != null) {
            try {
                System.out.println(file.getFileName());
                InputStream fin2 = file.getInputstream();
                FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        } else {
            FacesMessage msg = new FacesMessage("Please select image!!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
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
}
