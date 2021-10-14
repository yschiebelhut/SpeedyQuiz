package speedyquiz;

import java.awt.*;

/**
 * @author Matrikel-Nr. 3354235
 */
public enum Status {
	ACTIVE(Color.ORANGE, 0, "aktuell gestellte Frage"),
	CORRECT(Color.GREEN, 1, "richtig beantwortete Frage"),
	WRONG(Color.RED, -1, "falsch beantwortete Frage"),
	PENDING(Color.WHITE, 0, "noch nicht gespielte/ausstehende Frage"),
	NO_ANSWER(Color.GRAY, 0, "Frage auf die ein anderer Spieler geantwortet hat");

	private Color color;
	private int points;
	private String meaning;


	Status(Color color, int points, String meaning) {
		this.color = color;
		this.points = points;
		this.meaning = meaning;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
}
