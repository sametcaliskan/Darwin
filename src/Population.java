import java.util.ArrayList;
import java.util.List;

public class Population {
	private List<Individual> individualList;

	public Population() {
		individualList = new ArrayList<>();
	}

	public List<Individual> getIndividualList() {
		return individualList;
	}

	public void setIndividualList(List<Individual> individualList) {
		this.individualList = individualList;
	}

	public void addIndividual(Individual individual) {
		individualList.add(individual);
	}
}
