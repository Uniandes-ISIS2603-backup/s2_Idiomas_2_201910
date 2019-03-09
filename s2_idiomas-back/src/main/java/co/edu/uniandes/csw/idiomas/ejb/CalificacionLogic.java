package co.edu.uniandes.csw.idiomas.ejb;


import co.edu.uniandes.csw.idiomas.persistence.CalificacionPersistence;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para las calificaciones.
 *
 * @author jdruedaa
 */
@Stateless
public class CalificacionLogic {

    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());

    @Inject
    private CalificacionPersistence calificacionPersistence;

}