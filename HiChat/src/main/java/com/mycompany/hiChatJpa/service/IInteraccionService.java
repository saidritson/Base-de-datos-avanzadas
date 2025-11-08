package com.mycompany.hiChatJpa.service;

import com.mycompany.hiChatJpa.entitys.Interaccion;
import com.mycompany.hiChatJpa.entitys.TipoInteraccion;
import com.mycompany.hiChatJpa.entitys.Usuario;
import java.util.List;

/**
 * interfaz que define los metodos crud de una interaccion
 * @author gatog
 */
public interface IInteraccionService {

    void registrarInteraccion(Interaccion interaccion) throws Exception;



    /**
     * Permite que un usuario dé "like" a otro usuario.
     * Si ambos usuarios se han dado like, se genera un "match".
     * @param idUsuario ID del usuario receptor del like
     * @return true si se generó un match, false si solo se registró el like
     * @throws Exception si ocurre un error en la operación
     */
    Boolean darLike(Long idUsuario) throws Exception;

    /**
     * Permite que un usuario dé "dislike" a otro usuario.
     * @param idUsuario ID del usuario receptor del dislike
     * @return true si se registró correctamente, false en caso contrario
     * @throws Exception si ocurre un error en la operación
     */
    Boolean darDislike(Long idUsuario) throws Exception;

    /**
     * Permite que un usuario dé un "super like" a otro usuario.
     * @param idUsuario ID del usuario receptor del super like
     * @return true si se generó un match, false si solo se registró el super like
     * @throws Exception si ocurre un error en la operación
     */
    Boolean darSuperLike(Long idUsuario) throws Exception;

    /**
     * Bloquea a un usuario, lo que deshabilita el chat entre ambos.
     * @param idUsuario ID del usuario a bloquear
     * @return true si el usuario fue bloqueado correctamente, false si ya estaba bloqueado
     * @throws Exception si ocurre un error en la operación
     */
    Boolean bloquearUsuario(Long idUsuario) throws Exception;

    /**
     * Desbloquea a un usuario previamente bloqueado.
     * @param idUsuario ID del usuario a desbloquear
     * @return true si se realizó el desbloqueo, false si no existía bloqueo previo
     * @throws Exception si ocurre un error en la operación
     */
    Boolean desbloquearUsuario(Long idUsuario) throws Exception;
    void actualizarInteraccion(Interaccion interaccion) throws Exception;

    void eliminarInteraccion(Long id) throws Exception;

    Interaccion buscarPorId(Long id);

    List<Interaccion> listarInteracciones();

    List<Interaccion> listarPorEmisor(Usuario usuario);

    List<Interaccion> listarPorReceptor(Usuario usuario);

    List<Interaccion> listarPorTipo(TipoInteraccion tipo);
}
