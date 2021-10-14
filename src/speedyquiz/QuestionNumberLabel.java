package speedyquiz;

import javax.swing.*;

/**
 * @author Matrikel-Nr. 3354235
 */
public class QuestionNumberLabel extends JLabel {
	private Status status = Status.PENDING;

	public QuestionNumberLabel(int number) {
		super(String.valueOf(number));
		this.setOpaque(true);
		this.setHorizontalAlignment(JLabel.CENTER);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
