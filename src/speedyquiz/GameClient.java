package speedyquiz;

/**
 * @author Matrikel-Nr. 3354235
 */
public interface GameClient {
	public String getPlayerName();

	public int getPoints();

	public void setQuestion(int questionIndex, Question question);

	public void setRemainingSeconds(int seconds);

	public void gameIsOver();

	public void setAnswerState(int questionIndex, Status status);
}
