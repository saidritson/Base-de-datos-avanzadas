package com.mycompany.hiChatJpa.service;

import com.mycompany.hiChatJpa.entitys.Chat;
import com.mycompany.hiChatJpa.entitys.Usuario;
import java.util.List;

/**
 * interfaz que define las reglas de negocio antes de llamar alos dao
 * @author gatog
 */
public interface IChatService {

    void registrarChat(Chat chat) throws Exception;

    void actualizarChat(Chat chat) throws Exception;

    void eliminarChat(Long id) throws Exception;

    Chat buscarPorId(Long id);

    List<Chat> listarChats();

    List<Chat> listarPorNombre(String nombre);

    List<Chat> listarPorParticipante(Usuario usuario);
   
        /**
     * Cambia el alias (nombre) de un chat.
     * @param chat chat al que se desea cambiar el alias
     * @param nuevoNombre nuevo alias del chat
     * @return true si el alias se cambió correctamente, false en caso contrario
     * @throws Exception si ocurre un error en la operación
     */
    Boolean cambiarAliasDelChat(Chat chat, String nuevoNombre) throws Exception;

    /**
     * Carga todos los chats en los que participa un usuario.
     * Similar a listarPorParticipante, pero puede incluir datos adicionales
     * (último mensaje, estado, usuarios, etc.)
     * @param usuario el usuario del cual se desean cargar los chats
     * @return lista de chats con información relevante
     * @throws Exception si ocurre un error en la operación
     */
    List<Chat> cargarChatsDelUsuario(Usuario usuario) throws Exception;

    /**
     * Envía un mensaje dentro de un chat, validando que no exista un bloqueo
     * entre los participantes.
     * @param chat chat donde se enviará el mensaje
     * @param mensaje contenido del mensaje
     * @return true si el mensaje fue enviado correctamente, false si existe un bloqueo
     * @throws Exception si ocurre un error en la operación
     */
    Boolean mandarMensaje(Chat chat, String mensaje) throws Exception;
}
