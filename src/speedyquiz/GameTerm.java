package speedyquiz;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matrikel-Nr. 3354235
 */
public class GameTerm extends JFrame implements GameClient {
	private int points = 0;

	private String playerName;
	private Game game;
	private JPanel panelNorth;
	private List<QuestionNumberLabel> questionLabels = new ArrayList<>();
	private JPanel panelCenter;
	private JLabel labelQuestion = new JLabel("", JLabel.CENTER);
	private JPanel panelSouth;
	private JLabel time = new JLabel("10", JLabel.CENTER);
	private JButton[] answerButtons = new JButton[4];

	public GameTerm(String playerName, Game game) {
		this.playerName = playerName;
		this.game = game;

		this.initUI();
	}

	private void initUI() {
		this.setTitle(playerName);

		this.panelNorth = new JPanel(new GridLayout(1, this.game.getNumQuestionsAmount(), 5, 5));
		for (int i = 0; i < this.game.getQuestionPool().size(); i++) {
			QuestionNumberLabel label = new QuestionNumberLabel(i + 1);
			label.setBackground(label.getStatus().getColor());
			questionLabels.add(label);
			this.panelNorth.add(label);
		}
		this.add(panelNorth, BorderLayout.NORTH);

		this.panelCenter = new JPanel(new GridLayout(2, 1));
		panelCenter.add(labelQuestion);
		panelCenter.add(time);
		this.add(panelCenter);


		this.panelSouth = new JPanel(new GridLayout(2, 2, 5, 5));
		for (int i = 0; i < 4; i++) {
			JButton b = new JButton(String.valueOf(i));
			int finalI = i;
			b.addActionListener(e -> {
				this.game.answerSelected(this, finalI);
			});
			answerButtons[i] = b;
			panelSouth.add(b);
		}
		this.add(panelSouth, BorderLayout.SOUTH);


		this.setSize(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		if (this.points > 0) {
			return points;
		}
		return 0;
	}

	@Override
	public void setQuestion(int questionIndex, Question question) {
		this.questionLabels.get(questionIndex).setStatus(Status.ACTIVE);
		this.questionLabels.get(questionIndex).setBackground(this.questionLabels.get(questionIndex).getStatus().getColor());
		labelQuestion.setText(question.getQuestionText());
		for (int i = 0; i < 4; i++) {
			answerButtons[i].setText(question.getAnswers()[i]);
		}
	}

	@Override
	public void setRemainingSeconds(int seconds) {
		this.time.setText(String.valueOf(seconds));
	}

	@Override
	public void gameIsOver() {
		for (JButton b :
				this.answerButtons) {
			b.setEnabled(false);
		}
	}

	@Override
	public void setAnswerState(int questionIndex, Status status) {
		this.questionLabels.get(questionIndex).setBackground(status.getColor());
		points += status.getPoints();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
