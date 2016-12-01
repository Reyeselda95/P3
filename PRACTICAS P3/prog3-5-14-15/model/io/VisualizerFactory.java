package model.io;

import java.util.Objects;

import model.exceptions.io.TetrisIOException;

/**
 * Clase VisualizerFactory: Representa la fabrica que nos devuelve un visualizador del juego actual.
 *
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
public class VisualizerFactory {
	/**
	 * Constructor por defecto de la clase VisualizerFactory
	 */
	public VisualizerFactory() {
	}

	/**
	 * createVisualizer(String s): Dado un string concreto crea un tipo de visualizador u otro
	 * 
	 * @param s
	 *            es un String que se utiliza para crear el
	 * @return deveuelve un visualizador según la opción escogida
	 * @throws TetrisIOException
	 *             lanza TetrisIOException
	 */
	public static IVisualizer createVisualizer(String s) throws TetrisIOException {
		s = Objects.requireNonNull(s, "El parametro s no puede ser null.");
		switch (s) {
			case "console":
				return new VisualizerConsole();
				// Para cuando se quiera visualizar con la interfaz grafica
				/*
				 * case "window":
				 * return new VisualizerWindow();
				 */
			default:
				throw new TetrisIOException("No se ha podido crear un nuevo visualizador para el juego.");
		}
	}
}
