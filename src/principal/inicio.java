package principal;

import java.text.ParseException;

import interfaceDeUsuario.InterfaceDeUsuario;
import persistencia.PersistenciaComSerializacao;

public class inicio {

	public static void main(String[] args) throws ParseException {
		(new InterfaceDeUsuario(new PersistenciaComSerializacao())).mostrarMenuPrincipal();

	}

}
