package com.fraga.APIRest.mocks;

import com.fraga.APIRest.data.model.Permission;
import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.dto.UsuarioAtualizarDTO;
import com.fraga.APIRest.dto.UsuarioRequestDTO;
import com.fraga.APIRest.dto.UsuarioResponseDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UsuarioMock {

    public UsuarioResponseDTO getUsuarioResponseDTO(){
        UsuarioResponseDTO usuario = new UsuarioResponseDTO();
        usuario.setNomeCompleto("Nome Completo 1");
        usuario.setNomeUsuario("Nome Usuario 1");
        return usuario;
    }
    public Usuario getUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto("Nome Completo 1");
        usuario.setNomeUsuario("Nome Usuario 1");
        usuario.setActive(true);
        usuario.setSenha("senha 1");
        usuario.setPermissions(new ArrayList<>(){{
            add(getAdminPermission());
        }});
        usuario.setEnabled(true);
        usuario.setId(1L);
        return usuario;
    }

    public Usuario getUsuario2(){
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto("Nome Completo 2");
        usuario.setNomeUsuario("Nome Usuario 2");
        usuario.setActive(true);
        usuario.setSenha("senha 2");
        usuario.setPermissions(new ArrayList<>(){{
            add(getCommonUserPermission());
        }});
        usuario.setEnabled(true);
        usuario.setId(2L);
        return usuario;
    }

    public UsuarioRequestDTO getUsuarioRequestDTO(){
        UsuarioRequestDTO usuario = new UsuarioRequestDTO();
        usuario.setNomeCompleto("Nome Completo 1");
        usuario.setNomeUsuario("Nome Usuario 1");
        usuario.setSenha("Senha 1");
        return usuario;
    }

    public UsuarioAtualizarDTO getUsuarioAtualizarDTO(){
        UsuarioAtualizarDTO usuario = new UsuarioAtualizarDTO();
        usuario.setNomeCompleto("Nome Completo Atualizado");
        return usuario;
    }

    public List<Usuario> getUsuarios(){
        return Arrays.asList(getUsuario(), getUsuario2());
    }

    public Permission getAdminPermission(){
        Permission permission = new Permission();
        permission.setId(1L);
        permission.setDescription("ADMIN");
        return permission;
    }

    public Permission getCommonUserPermission(){
        Permission permission = new Permission();
        permission.setId(2L);
        permission.setDescription("COMMON_USER");
        return permission;
    }
}
