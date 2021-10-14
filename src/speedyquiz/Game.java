package speedyquiz;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Matrikel-Nr. 3354235
 */
public class Game {

	private List<Question> questionPool;
	private int numQuestionsAmount;
	private int curQuestion = 0;
	private boolean isRunning = false;
	private List<GameClient> clients = new ArrayList<>();

	private int totalTime = 0;
	private Thread totalTimeThread = new Thread(() -> {
		while (isRunning) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.totalTime++;
		}
	});

//	private int questionTime;
	private class questionTimeThread implements Runnable {

		private int questionTime;
		private int curcurQuestion;

		@Override
		public void run() {
			this.questionTime = 10;
			curcurQuestion = curQuestion;
			for (GameClient c :
					clients) {
				c.setRemainingSeconds(questionTime);
			}
			while (questionTime != 0 && curcurQuestion == curQuestion) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.questionTime--;
				for (GameClient c :
						clients) {
					c.setRemainingSeconds(questionTime);
				}
			}
			if (curcurQuestion == curQuestion) {

				answerSelected(null, curQuestion);
			}
		}
	}

	Thread t = new Thread(new questionTimeThread());
//	private Thread questionTimeThread = new Thread(() -> {
//		this.questionTime = 10;
//		for (GameClient c :
//				clients) {
//			c.setRemainingSeconds(questionTime);
//		}
//		while (questionTime != 0) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			this.questionTime--;
//			for (GameClient c :
//					clients) {
//				c.setRemainingSeconds(questionTime);
//			}
//		}
//		answerSelected(null, curQuestion);
//	});

	public Game(List<Question> questionPool, int numQuestionsAmount) throws GameException {
		if (questionPool.size() < numQuestionsAmount) {
			throw new GameException("Too few questions available");
		}
		Collections.shuffle(questionPool);
		this.questionPool = questionPool.subList(0, numQuestionsAmount);
		this.numQuestionsAmount = numQuestionsAmount;
	}

	public void registerClient(GameClient client) {
		if (!isRunning) {
			clients.add(client);
		}
	}

	public int getQuestionsCount() {
		return numQuestionsAmount;
	}

	public void startGame() {
		isRunning = true;
		for (GameClient client :
				clients) {
			client.setQuestion(curQuestion, questionPool.get(curQuestion));
		}
		this.totalTimeThread.start();
//		this.questionTimeThread.start();
		t = new Thread(new questionTimeThread());;
		t.run();
		// thread here, curquestion++, answer selected
	}

	public void answerSelected(GameClient client, int index) {
		Question q = questionPool.get(curQuestion);
		for (GameClient c :
				clients) {
			if (c.equals(client)) {
				if (q.getCorrectIndex() == index) {
					c.setAnswerState(curQuestion, Status.CORRECT);
				} else {
					c.setAnswerState(curQuestion, Status.WRONG);
				}
			} else {
				c.setAnswerState(curQuestion, Status.NO_ANSWER);
			}
		}
		curQuestion++;
		if (curQuestion != numQuestionsAmount) {
			for (GameClient c :
					clients) {
				c.setQuestion(curQuestion, questionPool.get(curQuestion));
			}
			t.stop();

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t = new Thread(new questionTimeThread());
			t.start();
		} else {
			this.gameFinished();
		}
	}

	private void gameFinished() {
		t.stop();
		isRunning = false;
		String message = "Game finished after " + this.totalTime + " seconds: ";
		for (GameClient c :
				clients) {
			c.gameIsOver();
			message += c.getPlayerName() + " (";
			message += c.getPoints();
			message += "), ";
		}
		message = message.substring(0, message.length() - 2);

		try (PrintWriter p = new PrintWriter(new FileWriter("highscore.txt", true))) {
			p.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, message);
	}

	public List<Question> getQuestionPool() {
		return questionPool;
	}

	public void setQuestionPool(List<Question> questionPool) {
		this.questionPool = questionPool;
	}

	public int getNumQuestionsAmount() {
		return numQuestionsAmount;
	}

	public void setNumQuestionsAmount(int numQuestionsAmount) {
		this.numQuestionsAmount = numQuestionsAmount;
	}
}
